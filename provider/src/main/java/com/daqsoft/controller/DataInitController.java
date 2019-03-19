package com.daqsoft.controller;

import com.daqsoft.config.ConstantWords;
import com.daqsoft.pojo.DataPassage;
import com.daqsoft.repository.DataPassageRepository;

import com.daqsoft.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataInitController {

    @Autowired
   private DataImportService dataImport;

    @GetMapping("/test")
    public Map getTask_h() {
        Map map  = new HashMap();
        map.put("s","200");
        try {
            for(;;){
                dataImport.getData_h();
            }


        }catch (Exception e){
            e.printStackTrace();
            map.put("s",e.getMessage());
            return map;
        }

    }


}
