package com.example.mapper;

import com.example.dto.res.countByBusinessIdResponseDto;
import com.example.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 操作goods相关数据接口
 */
public interface GoodsMapper {

    /**
     * 新增
     */
    int insert(Goods goods);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Goods goods);

    /**
     * 根据ID查询
     */
    Goods selectById(Integer id);

    /**
     * 查询所有
     */
    List<Goods> selectAll(Goods goods);

    @Select("SELECT a.name AS goodName, IFNULL(SUM(b.num),0) AS goodsCount " +
            " FROM goods a" +
            " LEFT JOIN orders_item b on b.goods_name = a.name" +
            " LEFT JOIN orders c on c.business_id = a.business_id AND c.pay_time BETWEEN #{startTime} and #{endTime} " +
            " WHERE a.business_id = #{businessId}" +
            " GROUP BY a.name")
    List<countByBusinessIdResponseDto> goodsCount(@Param("businessId")String businessId, @Param("startTime")LocalDate startTime, @Param("endTime") LocalDate endTime);

}