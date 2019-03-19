package com.daqsoft.enctyption;

/**
 * 加解密的接口
 */
public interface Enctyption {

    /**
     * 加密接口
     * @param param 加密前字符串
     * @return
     */
    public String paramEnctyption(String param);

    /**
     * 解密接口
     * @return
     */
    public String paramDecrypt();
}
