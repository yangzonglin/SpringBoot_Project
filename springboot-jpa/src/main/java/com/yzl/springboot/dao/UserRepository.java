package com.yzl.springboot.dao;

import com.yzl.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author yzl
 * @Create 2019/12/30
 */
public interface UserRepository extends JpaRepository<User,Integer>{
}
