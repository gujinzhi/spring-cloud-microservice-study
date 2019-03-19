package com.daqsoft.repository.impl;

import com.daqsoft.pojo.DataLog;
import com.daqsoft.pojo.DataPassage;
import com.daqsoft.repository.DataLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataLogRepositoryImpl  {
    @Autowired
    DataLogRepository dataLogRepository;


    public void saveLog(DataLog log){
        dataLogRepository.save(log);
    }

    public void saveLog(Long dataPassageId,String mess){
        dataLogRepository.save(new DataLog(dataPassageId,mess));
    }
    public void saveDataPassageLog(DataPassage dataPassage,int i){
        String str = "projectID:"+ dataPassage.getProject()+",apiName：" + dataPassage.getName();
        switch (i){
            case 1 :
                str += ",UpdateBoo: "+dataPassage.getUpdateBoo();
            case 2 :
                str += ",start,param: "+dataPassage.getParam();
        }
        dataLogRepository.save(new DataLog(dataPassage.getId(),str));
    }

    public void saveDataPassageLog_error(DataPassage dataPassage, String message) {
        String str = "projectID:"+ dataPassage.getProject()+",apiName：" + dataPassage.getName();
                str += ",error,errorMessage: "+message;
        dataLogRepository.save(new DataLog(dataPassage.getId(),str));
    }
}
