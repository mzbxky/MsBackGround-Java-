package com.ruoyi.util;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class GetTokenUtil {
    public static String getToken(){
        try {
            String mediaId = "800050613231";
            String secret = "xLmbRdBWmJRMVA46=CJC{y8>m>Di&{CQ";
//            String appid = "1200423177";
            String time = ""+System.currentTimeMillis();
            String sign = sha1(mediaId+secret+time);
            String token = base64(mediaId+","+time+","+sign).replaceAll("\\r|\\n","");
            System.out.println(token);
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String sha1(String data){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] b = data.getBytes();
            md.update(b);
            byte[] b2 = md.digest();
            int len = b2.length;
            String str = "0123456789abcdef";
            char[] ch = str.toCharArray();
            char[] chs = new char[len*2];
            for (int i = 0,k=0;i<len;i++){
                byte b3=b2[i];
                chs[k++] =ch[b3 >>> 4 & 0xf];
                chs[k++] =ch[b3 & 0xf];
            }
            return new String(chs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public static String base64(String src){
        byte[] b = null;
        String s = "";
        try {
            b = src.getBytes(StandardCharsets.UTF_8);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (b!=null){
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }
}

