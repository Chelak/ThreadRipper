package com.lessons.celac.demo.controller;

import com.lessons.celac.demo.components.LocalExecutorService;

import com.lessons.celac.demo.service.ExternalServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Controller
public class HomeController {


    private final LocalExecutorService localExecutorService;

    public HomeController( LocalExecutorService localExecutorService) {
        this.localExecutorService = localExecutorService;
    }

    @ResponseBody
    @GetMapping(value = {"/test"})
    public ResponseEntity<String> getTest() throws InterruptedException {
        ExternalServiceImpl externalService = new ExternalServiceImpl();
        ExecutorService executorService = localExecutorService.getLocalExecutionService();
        executorService.execute(externalService);
        Thread.sleep(10000);
        externalService.shutdown();
        executorService.shutdownNow();
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
