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

    /**
     * 修改capsule信息
     * @param capsule 参数
     */
    void update(Capsule capsule);

    /**
     * 随机获得一个胶囊
     * @param mainId 主用户id
     * @return 胶囊信息
     */
    Capsule select(String mainId);

}
