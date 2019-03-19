package com.daqsoft.repository.impl;


import com.daqsoft.config.ConstantWords;
import com.daqsoft.pojo.DataLogin;
import com.daqsoft.repository.DataLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoginRepositoryImpl<T>  {

        @Autowired
        private DataLoginRepository dataLoginRepository;

        public List<DataLogin> findDataLoginList(DataLogin data){

            Example<DataLogin> example = Example.of(data);
            List<DataLogin> li = dataLoginRepository.findAll(example);
            return li;
        }


         public DataLogin findDataLogin(DataLogin data){
             List<DataLogin> list = findDataLoginList(data);
             if(list.size()>0){
                    return list.get(0);
             }
             return null;

         }

}
