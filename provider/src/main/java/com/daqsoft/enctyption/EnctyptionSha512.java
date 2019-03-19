package com.daqsoft.enctyption;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Sha512 加解密的实现
 */
//@Service("EnctyptionSha512")
public class EnctyptionSha512 implements Enctyption {
    @Override
    public String paramEnctyption(String param) {
        return SHA(param, "SHA-512");
    }

    @Override
    public String paramDecrypt() {
        return null;
    }


    /**
     * 字符串 SHA 加密
     *
     * @param
     * @return
     */
    private String SHA(final String strText, final String strType)
    {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0)
        {
            try
            {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }

        return strResult;
    }


    public static void main(String[] s){
        EnctyptionSha512 ssd = new EnctyptionSha512();
        //sha512(sha512(pwd) + baisha01)
        //
        String u = "baisha01";
        String p = "Hn@bais!01";
        System.out.println(ssd.paramEnctyption(ssd.paramEnctyption(p)+u));

    }



}
