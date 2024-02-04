package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface AdminMapper {

    /**
      * 修改
    */
    int updateById(Admin admin);

    /**
      * 根据ID查询
    */
    Admin selectById(Integer id);

    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);
}