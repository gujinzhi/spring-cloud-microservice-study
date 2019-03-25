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
import com.daqsoft.util.DateUtil;
import com.daqsoft.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("GetData_baisha_thread")
public class GetData_baisha_thread  implements GetData {
    @Autowired
    private DataDataRepository dataDataRepository;
    @Autowired
    private DataLogRepositoryImpl dataLogRepositoryImpl;
    @Autowired
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

    @Override
    public void run() {
        getData( );
    }

    public void getData() {

        if (!Thread.currentThread().isInterrupted()) {
            try {
                //变更行级别锁定
                dataPassage.setUpdateBoo(ConstantWords.TABLE_UPDAYE_ROW_STATUS_FALSE);
                dataPassageRepository.save(dataPassage);

                Info info = HttpUtil.captureHtmlSSLGet(url);
                 dataLogRepositoryImpl.saveDataPassageLog(dataPassage,2);
                if (info.getCode() == 0) {
                    JSONObject JSON = JSONObject.parseObject(info.getConnet());
                    JSONArray jsonArray = JSON.getJSONArray(dataPassage.getResolveReturnData());
                    int l = jsonArray.size();
                    List<DataData> li = new ArrayList<DataData>();
                    if (l != 0) {
                        DataData data = null;
                        for (int i = 0; i < l; i++) {
                            data = new DataData(jsonArray.getJSONObject(i).toJSONString(), dataPassage.getId(), url,dataPassage.getProject());
                            li.add(data);
                        }
                        //DateUtil.getNextm();
                        dataDataRepository.save(li);
                        //设置下一个查询时间 并保存。
                        dataPassage.setParam(DateUtil.getNextm(dataPassage.getParam(), 1, dataPassage.getType(), dataPassage.getDateType()));
                        dataPassageRepository.save(dataPassage);
                    }else {
                        //当前条件没有数据 查看设置跳过该条件
                        if(dataPassage.getVerificationBoolean() == 1){
                            dataPassage.setParam(DateUtil.getNextm(dataPassage.getParam(), 1, dataPassage.getType(), dataPassage.getDateType()));
                            dataPassageRepository.save(dataPassage);
                        }
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





}
