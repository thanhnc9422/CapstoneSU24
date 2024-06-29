package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.dto.DataMailDTO;
import fpt.CapstoneSU24.dto.sdi.ClientSdi;
import fpt.CapstoneSU24.model.OTP;
import fpt.CapstoneSU24.repository.ClientRepository;
import fpt.CapstoneSU24.repository.OTPRepository;
import fpt.CapstoneSU24.repository.PartyRepository;
import fpt.CapstoneSU24.util.Const;
import fpt.CapstoneSU24.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService implements ClientRepository {
    private final EmailService mailService;
    private final RedisSortedSetService redisSortedSetService;
    private final OTPRepository otpResponsitory;
    private final PartyRepository partyRepository;
    private final OTPService otpService;

    @Autowired
    public ClientService(EmailService mailService, RedisSortedSetService redisSortedSetService,
                         OTPRepository otpResponsitory, PartyRepository partyRepository, OTPService otpService) {
        this.mailService = mailService;
        this.redisSortedSetService = redisSortedSetService;
        this.otpResponsitory = otpResponsitory;
        this.partyRepository = partyRepository;
        this.otpService = otpService;

    }

    @Override
    public Boolean create(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            //đoc ở file const
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            String codeOTP = DataUtils.generateTempPwd(6);
            props.put("codeOTP", codeOTP);

            dataMail.setProps(props);
            Date dateOTP = new Date();
            // Lưu redis
            redisSortedSetService.saveHash("OTPCODE",sdi.getEmail(), codeOTP);
        // tam cmt de test cho do bi spam
            //mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean createMailAndSaveSQL(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            //đoc ở file const
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_SENDOTP);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            String codeOTP = DataUtils.generateTempPwd(6);
            props.put("codeOTP", codeOTP);

            dataMail.setProps(props);
            Date expiryTime = otpService.calculateExpiryTime(2); // OTP sẽ hết hạn sau 2 phút

            OTP otpCheck = otpResponsitory.findOTPByEmail(sdi.getEmail());
           if(otpCheck == null) { // neu chua tung xac thuc bao h thi tao moi khong thi update
               otpCheck = new OTP(sdi.getEmail(), codeOTP, expiryTime);
               otpResponsitory.save(otpCheck);
           }else{
               otpService.updateOTPCode(sdi.getEmail(), codeOTP,expiryTime);
           }
            // tam cmt de test cho do bi spam
         // mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_SENDOTP);
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean notification(ClientSdi sdi) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            //đoc ở file const
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_NOTIFICATION);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("username", sdi.getUsername());
            dataMail.setProps(props);
            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_NOTIFICATION);
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean checkOTP(String email, String otp) {
        try {
           String otpRar = (redisSortedSetService.getHash("OTPCODE",email)).toString();
           if(otpRar.equals(otp)) {
               System.out.println("yuuuus");
               return true;
           }
           else{
               return false;
           }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean checkOTPinSQL(String email, String otp) {
        try {
            return otpService.verifyOTP(email, otp);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }
}
