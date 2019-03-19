package com.daqsoft.service;

public interface UrlSession {

    public void setSession(String session,String sessionKey);
    public void setSession(String session,String sessionKey,Long TimeUnit);
    public String getSession(String sessionKey) throws InterruptedException;
}
