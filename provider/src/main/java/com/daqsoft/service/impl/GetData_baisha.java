package com.daqsoft.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daqsoft.config.ConstantWords;
import com.daqsoft.pojo.DataData;
import com.daqsoft.pojo.DataPassage;
import com.daqsoft.pojo.Info;
import com.daqsoft.repository.DataDataRepository;
import com.daqsoft.repository.DataPassageRepository;
import com.daqsoft.service.GetData;
import com.daqsoft.service.UrlSession;
import com.daqsoft.util.DateUtil;
import com.daqsoft.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("GetData_baisha")
public class GetData_baisha implements GetData {
    @Autowired
    private UrlSession urlSession;
    @Autowired
    private DataDataRepository dataDataRepository;

    @Autowired
    private DataPassageRepository   dataPassageRepository;

    @Override
    public void getData(DataPassage dataPassage,String url) {

            Info info =  HttpUtil.captureHtmlSSLGet(url);
            if(info.getCode() == 0){
                JSONObject JSON = JSONObject.parseObject(info.getConnet());
                JSONArray jsonArray =  JSON.getJSONArray(dataPassage.getResolveReturnData());
                int l =  jsonArray.size();
                List<DataData> li  =  new ArrayList<DataData>();
                if(l !=0){
                    DataData data = null;
                    for(int i = 0;i<l;i++){
                        data = new DataData(jsonArray.getJSONObject(i).toJSONString(),dataPassage.getId(),url);
                        li.add(data);
                    }
                    //DateUtil.getNextm();
                    dataDataRepository.save(li);
                    //设置下一个查询时间 并保存。
                    dataPassage.setParam( DateUtil.getNextm(dataPassage.getParam(),1,dataPassage.getType(),dataPassage.getDateType()));
                    dataPassageRepository.save(dataPassage);
                }
            }else{
                System.out.println(info.getError());
            }
        }





}
