package com.example.capsule.controller;

import com.example.capsule.service.base.CapsuleService;
import model.Po.Capsule;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2020/10/28
 * @since 1.0
 */
@RestController
@RequestMapping("BaseSys")
public class BaseSysController {
    @Resource
    private CapsuleService capsuleService;

    @PostMapping("addCapsule")
    public void addCapsule(@RequestBody Capsule capsule){
        capsuleService.insertCapsule(capsule);
    }
}
