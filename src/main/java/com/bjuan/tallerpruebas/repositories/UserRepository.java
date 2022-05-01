package com.bjuan.tallerpruebas.repositories;

import com.bjuan.tallerpruebas.model.user.MyUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
