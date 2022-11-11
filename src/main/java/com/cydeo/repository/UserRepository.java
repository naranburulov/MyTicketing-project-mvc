package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Long> {



    //get user based on username (derived query):
    User findByUserName(String userName);

    //we use this annotation for custom save/update/delete queries
    // to either finish the function,
    // or in case of unsuccessful processing - roll back
    @Transactional
    User deleteByUserName(String userName);

}
