package com.limingxu.resultwork.controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;
import java.util.Map;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("User");
        session.removeAttribute("token");
        return ResponseEntity.ok(Collections.singletonMap("code", "0")); // 返回成功状态
    }
}