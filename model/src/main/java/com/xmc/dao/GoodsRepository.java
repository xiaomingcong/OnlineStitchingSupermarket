package com.xmc.dao;

import com.xmc.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface GoodsRepository extends CrudRepository<Goods,Long> {

    @Override
    <S extends Goods> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
