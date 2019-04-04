package org.jellyfin.apiclient.interaction.cryptography;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static String getHash(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String digest = null;

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(text.getBytes("UTF-8"));

        //converting byte array to Hexadecimal
        StringBuilder sb = new StringBuilder(2*hash.length);
        for(byte b : hash){
            sb.append(String.format("%02x", b&0xff));
        }

        digest = sb.toString();

        return digest;

    }
}