package com.xmc.controller;

import com.xmc.dao.GoodsRepository;
import com.xmc.domain.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.UUID;

@RestController("/goods")
public class GoodsController {

    @Autowired
    GoodsRepository goodsRepository;

    @PostMapping(value = "/addGoods" )
    public Long add(@RequestBody Goods goods){
        Goods goods1 = goodsRepository.save(goods);
        return goods1.getId();
    }

}
