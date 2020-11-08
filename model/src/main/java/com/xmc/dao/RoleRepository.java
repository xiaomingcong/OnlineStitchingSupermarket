package com.xmc.dao;

import com.xmc.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RoleRepository extends CrudRepository<Role,Long> {

    @Override
    <S extends Role> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    Iterable<Role> findAllById(Iterable<Long> longs);

//    List<Role> findByIds(Long[] ids);
}
