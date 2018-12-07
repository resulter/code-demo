package com.example.codedemo.controller;

import com.example.codedemo.service.SerialNumberService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    @Autowired
    SerialNumberService serialNumberService;

    @RequestMapping(value = "/getTodayCodeWithBizCode",method = RequestMethod.GET)
    public  String getTodayCodeWithBizCode(String bizCode){
        String result = "";
        try {
            Preconditions.checkNotNull(bizCode,"请输入业务码");
            if(!serialNumberService.isLegal(bizCode)){
              return "请输入正确业务码（01-99）";
            }
            result = serialNumberService.generate(bizCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getTodayCode",method = RequestMethod.GET)
    public  String getTodayCode(){
        String result = "";
        try {
            result = serialNumberService.generate("01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
