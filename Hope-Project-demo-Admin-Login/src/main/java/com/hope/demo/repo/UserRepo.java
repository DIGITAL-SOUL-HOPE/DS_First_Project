package com.hope.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hope.demo.model.Users;

@Repository
public interface UserRepo extends MongoRepository<Users, String> {
   Users findByUsername(String username);
    
    boolean existsByUsername(String username);
    
    boolean existsByContactNo(String contactNo);
    
    Optional<Users> findByContactNo(String contactNo);
}
