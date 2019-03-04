package com.didispace.dao;

import com.didispace.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    public List<User> getAllUser() {
        List<User> list = new ArrayList<User>();
        for (int i=0;i<20;i++){
            list.add(new User(i,"name"+i));
        }
        return list;
    }
}
