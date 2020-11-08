package com.xmc.dao;

import com.xmc.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository extends CrudRepository<User,Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
