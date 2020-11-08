package com.xmc.dao;

import com.xmc.domain.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface PermissionRepository extends CrudRepository<Permission,Long> {

    @Override
    <S extends Permission> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
