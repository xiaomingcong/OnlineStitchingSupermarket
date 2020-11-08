package com.xmc.dao.impl;

import com.xmc.dao.GoodsRepository;
import com.xmc.domain.Goods;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepositoryImpl extends GoodsRepository , CrudRepository<Goods,Long> {
}
