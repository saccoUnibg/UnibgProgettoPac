package com.unibg.UnibgProject.utils;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.stereotype.Component;

@Component
public class EncodingUtils {

    public static String encode(String psw){
        return Base64.toBase64String(Base64.encode(psw.getBytes()));
    }


    public static String decode(String pswEncoded){
        return Base64.toBase64String(Base64.decode(pswEncoded.getBytes()));
    }

}
