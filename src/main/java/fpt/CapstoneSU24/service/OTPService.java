package fpt.CapstoneSU24.service;


import fpt.CapstoneSU24.model.OTP;
import fpt.CapstoneSU24.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class OTPService {
    private final OTPRepository otpRepository;

    @Autowired
    public  OTPService(OTPRepository otpRepository){
        this.otpRepository = otpRepository;
    }

    @Transactional
    public void updateOTPCode(String email, String codeOTP,Date expiryTime) {
        //Date expiryTime = calculateExpiryTime(2); // OTP sẽ hết hạn sau 2 phút
//        OTP otp = otpRepository.findOTPByEmail(email);
//        if (otp == null) {
//            otp = new OTP();
//            otp.setEmail(email);
//            otp.setCodeOTP(codeOTP);
//            otp.setExpiryTime(expiryTime);
            //otpRepository.save(otp);
//        } else {
           otpRepository.updateOtpByEmail(email, codeOTP, expiryTime);
//        }
    }

    public Date calculateExpiryTime(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return calendar.getTime();
    }
    public boolean verifyOTP(String email, String codeOTP) {
        OTP otp = otpRepository.findOTPByEmail(email);

        if (otp == null) {
            return false;
        }
        if (new Date().after(otp.getExpiryTime())) {
            // OTP đã hết hạn
            return false;
        }
        return (otp.getCodeOTP().trim()).equals(codeOTP);
    }

}