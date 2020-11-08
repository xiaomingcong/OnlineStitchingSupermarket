package com.xmc.rest;

import com.xmc.dao.GoodsRepository;
import com.xmc.domain.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(value = "helloController")
@RestController
public class HelloController {

    @ApiOperation(value = "hello")
    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @ApiOperation(value = "postHello")
    @PostMapping("/postHello")
    public String PostHello(){
        return "post Hello!";
    }


    @Autowired
    GoodsRepository goodsRepository;


}
