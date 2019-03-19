package com.daqsoft.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daqsoft.config.ConstantWords;
import com.daqsoft.pojo.DataData;
import com.daqsoft.pojo.DataPassage;
import com.daqsoft.pojo.Info;
import com.daqsoft.repository.DataDataRepository;
import com.daqsoft.repository.DataPassageRepository;
import com.daqsoft.repository.impl.DataLogRepositoryImpl;
import com.daqsoft.service.GetData;
import com.daqsoft.service.UrlSession;
import com.daqsoft.util.DateUtil;
import com.daqsoft.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;


@Service("GetData_xiecheng_thread")
public class GetData_xiecheng_thread implements GetData  {
    private DataDataRepository dataDataRepository;
    private DataLogRepositoryImpl dataLogRepositoryImpl;
    private DataPassageRepository   dataPassageRepository;
    private DataPassage dataPassage;
    private String url;
    public  void  setdata(DataPassage dataPassage,String url,DataDataRepository dataDataRepository,DataLogRepositoryImpl dataLogRepositoryImpl,DataPassageRepository   dataPassageRepository){
        this.dataPassage = dataPassage;
        this.url = url;
        this.dataDataRepository = dataDataRepository;
        this.dataLogRepositoryImpl = dataLogRepositoryImpl;
        this.dataPassageRepository = dataPassageRepository;
    }
    public void getData() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                //变更行级别锁定
                dataPassage.setUpdateBoo(ConstantWords.TABLE_UPDAYE_ROW_STATUS_FALSE);
                dataPassageRepository.save(dataPassage);
                Info info = HttpUtil.captureHtmlGet(url);
                dataLogRepositoryImpl.saveDataPassageLog(dataPassage,3);
                if (info.getCode() == 0) {
                    JSONObject JSON = JSONObject.parseObject(info.getConnet());
                    JSONArray jsonArray = JSON.getJSONArray(dataPassage.getResolveReturnData());
                    int l = jsonArray.size();
                    if (l != 0) {
                        DataData data = null;
                        for (int i = 0; i < l; i++) {
                            data = new DataData(jsonArray.getJSONObject(i).toJSONString(), dataPassage.getId(), url, dataPassage.getProject());
                            dataDataRepository.save(data);
                        }
                        //   DateUtil.getNextm();

                        //设置下一个查询时间 并保存。
                        dataPassage.setParam(DateUtil.getNextm(dataPassage.getParam(), 1, dataPassage.getType(), dataPassage.getDateType()));
                        dataPassageRepository.save(dataPassage);
                    }
                } else {
                    System.out.println(info.getError());
                }
            } catch (Exception e) {
                e.printStackTrace();
                dataLogRepositoryImpl.saveDataPassageLog_error(dataPassage, e.getMessage());
            } finally {
                dataPassage.setUpdateBoo(ConstantWords.TABLE_UPDAYE_ROW_STATUS_TRUE);
                dataPassageRepository.save(dataPassage);
            }
        }
    }

    @Override
    public void run() {
        getData();
    }


}
