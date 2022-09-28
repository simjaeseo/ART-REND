package com.artrend.authservice.global.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {


    public String getDI(String info) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(info.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
