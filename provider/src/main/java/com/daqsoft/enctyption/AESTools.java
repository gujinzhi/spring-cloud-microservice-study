package com.daqsoft.enctyption;





import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class AESTools {

    static String BoncKey="three.*?";



    private static String ZteThirdKey = "cdUIBNmsTLTJuwy";

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    /**
     * 解密
     */
    public static String jiemi(String s,String key)
    {
        //byte[] decryptFrom = AESTool.parseHexStr2Byte(s);
        //byte[] decryptResult = AESTool.decrypt(decryptFrom,key);



        try{
            byte[] decryptFrom = parseHexStr2Byte(s);
            String decryptResult = aesDecryptByBytes(decryptFrom,key);
            //下面关键 转码
            //s = new String(decryptResult,"utf-8");
            return decryptResult;

            //return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
    /**
     * 加密
     * String s  需要加密的字符串
     * String password   用于加密的密钥
     */
    public static String jiami(String s,String password)
    {
        try{
            byte[] encryptResult = aesEncryptToBytes(s, password);
            String  encryptResultStr = parseByte2HexStr(encryptResult);

            return encryptResultStr;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "";
    }
    public static String aesDecryptByBytes(byte[] encryptBytes,
                                           String decryptKey) throws Exception {
        byte[] decryptBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKey(decryptKey).getEncoded(), "AES"));
            decryptBytes = cipher.doFinal(encryptBytes);
        } catch (Exception e) {

            return null;
        }
        //utf 自己添加
        return new String(decryptBytes,"utf-8");
    }
    /**
     * 如果服务器系统为linux，则需要用此方法获取key
     * @param strKey
     * @return
     */
    public static SecretKey getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128, secureRandom);
            return _generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }
    }

    /**
     * AES加密(适用于linux,windows...)
     * @param content
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getKey(encryptKey).getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static void main(String[] str){
        JSONArray datas = new JSONArray();
        JSONObject map = new JSONObject();
        map.put("cname", "201903");
        map.put("dateNum", "20");
        map.put("provincecode","018");

        map.put("hourtime", "16");

        map.put("citycode","V0130100");
       // map.put("sceniccode","0183009");
        //map.put("sceniccode", "0183009");

//		ma.put("cname", "201810");
//		ma.put("dateNum", "31");
//		ma.put("minuteTime", "1400");
//		ma.put("provincecode", "018");
//		ma.put("citycode", "V0130100");
//		ma.put("sceniccode", "0183091");
        datas.add(map);


        System.out.println(datas.toJSONString());
        String jiamiData = jiami(datas.toJSONString(), "cdUIBNmsTLTJuwy");
        String jiami ="{\"inf\": 105,\"apikey\": \"cdzkdqTour109\",\"data\": \""+AESTools.jiami(jiamiData,"cdUIBNmsTLTJuwy")+"\"}";
        System.out.println(jiamiData);

    }
}

