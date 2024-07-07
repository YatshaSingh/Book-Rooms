//package com.assesment.roombooking.route;
//
//import com.assesment.roombooking.model.AuthRequestDTO;
//import com.assesment.roombooking.util.JwtService;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth/api")
//public class UserAPI {
//
//   @Autowired
//    JwtService jwtService;
//
//    @PostMapping("/login")
//    public String login(@RequestParam String  UserName){
//        Map<String, Object> claims = new HashMap<>();
//
//        return jwtService.generateToken(claims,UserName);
//    }
//
//
//}
