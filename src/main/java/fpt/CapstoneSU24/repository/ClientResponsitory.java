package fpt.CapstoneSU24.repository;

import fpt.CapstoneSU24.dto.sdi.ClientSdi;

public interface ClientResponsitory {
    Boolean create(ClientSdi sdi);


    Boolean checkOTP(String email, String otp);
}
