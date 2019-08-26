package com.kavito.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author kavito
 * @date 2019/7/13 19:29
 */
@Mapper
public interface MysqlLockMapper {
    //删除id释放锁
    int deleteByPrimarykey(int id);
    //新增数据加锁，id为同一个数据
    int insert(int id);
}
