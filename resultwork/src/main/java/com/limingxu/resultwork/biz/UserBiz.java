package com.limingxu.resultwork.biz;

import com.limingxu.resultwork.entity.User;
import com.limingxu.resultwork.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBiz {
    @Autowired
    private UserMapper mapper;

    public boolean login(String username, String password) {
        User user = mapper.findByUsername(username);
        return user != null && user.getLoginPwd().equals(password);
    }

    public boolean register(User user) {
        int result = mapper.insert(user);
        return result > 0;
    }
    public User getUserByUsername(String username) {
        return mapper.findByUsername(username);
    }
    public boolean isUsernameAvailable(String username) {
        return mapper.findByUsername(username) == null;
    }
    public void setUserMapper(UserMapper userMapper) {
        this.mapper = userMapper;
    }
}