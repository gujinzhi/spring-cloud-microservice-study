package com.daqsoft.service.impl;

import com.daqsoft.config.ConstantWords;
import com.daqsoft.service.DataImportService;
import com.daqsoft.service.RedisService;
import com.daqsoft.service.UrlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlSessionImpl implements UrlSession {
    @Autowired
    private RedisService template;

    @Autowired
    private DataImportService dataImportService;

    @Override
    public void setSession(String session ,String sessionKey) {
        this.setSession(session,sessionKey, ConstantWords.REDIS_TIMEUNIT);

    }

    @Override
    public void setSession(String session, String sessionKey, Long TimeUnit) {
        template.set(sessionKey,session,TimeUnit);
    }

    @Override
    public String getSession(String sessionKey)  {
        String session = template.get(sessionKey)+"";
       // System.out.println(session.trim().length());

        if( session ==null  || "null".equals(session.trim()) || session.trim().length() ==0  ){
            dataImportService.setSession(sessionKey);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getSession(sessionKey);
        }
        return  session;
    }
}
