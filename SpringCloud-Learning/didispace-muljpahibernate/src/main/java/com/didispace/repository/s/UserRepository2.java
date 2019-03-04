package com.didispace.repository.s;

import com.didispace.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository2 extends JpaRepository<Message, Long> {

//    User findByName(String name);

//    @Query("from User u where u.user_name=:name")
//    User findUser(@Param("name") String name);
}
