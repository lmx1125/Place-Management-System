package com.limingxu.resultwork.controller;

import com.limingxu.resultwork.biz.UserBiz;
import com.limingxu.resultwork.entity.User;
import com.limingxu.resultwork.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isValidUser = userBiz.login(user.getUserName(), user.getLoginPwd());
            if (isValidUser) {
                String token = UUID.randomUUID().toString(); // 生成唯一的令牌
                HttpSession session = request.getSession();
                session.setAttribute("User", user);
                session.setAttribute("token", token); // 将令牌存储在会话中
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("code", "0");
                responseBody.put("token", token); // 将令牌返回给客户端
                return ResponseEntity.ok(responseBody);
            } else {
                return ResponseEntity.ok(ResponseUtil.generateErrorResponse("1", "用户名或密码错误"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtil.generateErrorResponse("2", "登录失败：" + e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> userRegister(@RequestBody User user) {
        try {
            boolean isUsernameAvailable = userBiz.isUsernameAvailable(user.getUserName());
            if (!isUsernameAvailable) {
                return ResponseEntity.ok(ResponseUtil.generateErrorResponse("1", "用户名已存在"));
            }
            boolean isSuccess = userBiz.register(user);
            if (isSuccess) {
                return ResponseEntity.ok(Collections.singletonMap("code", "0"));
            } else {
                return ResponseEntity.ok(ResponseUtil.generateErrorResponse("2", "注册失败"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseUtil.generateErrorResponse("3", "注册失败：" + e.getMessage()));
        }
    }
    @GetMapping("/getUserInfo/{userName}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String userName) {
        User user = userBiz.getUserByUsername(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
