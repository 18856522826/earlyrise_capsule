package com.example.capsule.mapper;

import model.Po.Capsule;
import org.apache.ibatis.annotations.Mapper;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/10/28
 * @since 1.0
 */
@Mapper
public interface CapsuleMapper {

    /**
     * 插入capsule
     * @param capsule 参数
     */
    void insert(Capsule capsule);

}
