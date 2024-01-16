package com.example.mapper.repository;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Orders;
import com.example.vo.CountByDayResponseVo;

import java.util.List;

/**
 * @author V
 */
public interface OrdersRepository extends IService<Orders> {
    List<CountByDayResponseVo> countByDay(QueryWrapper wrapper);
}
