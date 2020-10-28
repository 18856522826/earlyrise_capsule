package com.example.capsule.controller;

import com.example.capsule.service.BaseSysService;
import com.example.capsule.service.base.CapsuleService;
import model.Po.Capsule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Notice: 基础数据模块
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
    private BaseSysService baseSysService;

    /**
     * 添加胶囊信息
     * @param capsule 胶囊信息
     */
    @PostMapping("addCapsule")
    public void addCapsule(@RequestBody Capsule capsule){
        baseSysService.limitAdd(capsule);
    }

    /**
     * 随机查询胶囊信息
     * @param mianId 主用户id
     */
    @GetMapping("randSelect")
    public Capsule randSelect(@RequestParam("id") String mianId){
       return baseSysService.randSelect(mianId);
    }
}
