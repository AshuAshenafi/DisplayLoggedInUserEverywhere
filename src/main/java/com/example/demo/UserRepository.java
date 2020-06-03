package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);               // this like: SELECT * FROM user WHERE username=?
                                                        // ... so question mark is replaced by the parameter username

}
