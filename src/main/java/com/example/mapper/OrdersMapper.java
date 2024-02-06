package com.example.mapper;

import com.example.dto.res.CountByDayResponseDto;
import com.example.dto.res.CountByMonthResponseDto;
import com.example.entity.Orders;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

public interface OrdersMapper {

    /**
     * 新增
     */
    int insert(Orders orders);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Orders orders);

    /**
     * 根据ID查询
     */
    Orders selectById(Integer id);

    /**
     * 查询所有
     */
    List<Orders> selectAll(Orders orders);

    List<CountByDayResponseDto> countByDay(String businessId);

    @Select("SELECT IFNULL(SUM(b.actual), 0) as asctual , a.name as businessName" +
            " FROM business a" +
            " left JOIN orders b on b.business_id = a.id" +
            " where b.status  = '已完成' and b.pay_time BETWEEN #{startTime} and #{endTime}" +
            " group by a.name")
    List<CountByMonthResponseDto> countByMonth(@Param("startTime")LocalDate startTime,@Param("endTime")LocalDate endTime);
}