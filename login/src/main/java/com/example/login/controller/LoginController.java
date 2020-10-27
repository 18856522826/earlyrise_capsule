package com.example.login.controller;

import com.example.login.service.base.MainAccountService;
import com.example.login.utils.JwtUtils;
import model.Dto.InAccountDto;
import model.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/12
 * @since 1.0
 */
@RestController
@RequestMapping("LOGIN")
public class LoginController {
    @Resource
    private MainAccountService mainAccountService;
    @PostMapping("in")
    public ResponseEntity in(@RequestBody InAccountDto inAccountDto){
        String a="xx";
        String pass="123";
        if (inAccountDto.getPassword().equals(pass)){
          return  ResponseEntity.ok("success",JwtUtils.generate(inAccountDto.getAccount()));
        }
        return ResponseEntity.fail();
    }
    @PostMapping("test")
    public ResponseEntity test(@RequestBody InAccountDto inAccountDto){
      return  ResponseEntity.ok(mainAccountService.getAcc(inAccountDto.getAccount()));
    }
}
