package com.daqsoft.service;

import com.daqsoft.pojo.DataPassage;
import com.daqsoft.repository.DataDataRepository;
import com.daqsoft.repository.DataPassageRepository;
import com.daqsoft.repository.impl.DataLogRepositoryImpl;

public interface GetData extends Runnable {

//    public void getData();
public  void  setdata(DataPassage dataPassage, String url, DataDataRepository dataDataRepository, DataLogRepositoryImpl dataLogRepositoryImpl, DataPassageRepository dataPassageRepository);



}
