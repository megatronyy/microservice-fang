package com.fang.cloud.controller;

import com.fang.cloud.entity.LogInfo;
import com.fang.cloud.repository.LogInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quwb on 2017/11/28.
 */
@RestController
public class LogInfoController {
    @Autowired
    private LogInfoRepository logInfoRepository;

    @GetMapping("/log/{id}")
    public LogInfo findById(@PathVariable int id){
        LogInfo logInfo = this.logInfoRepository.findOne(id);
        return logInfo;
    }
}
