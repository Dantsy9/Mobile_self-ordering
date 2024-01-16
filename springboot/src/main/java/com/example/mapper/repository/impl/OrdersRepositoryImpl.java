package com.example.mapper.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Orders;
import com.example.mapper.OrdersMapper;
import com.example.mapper.repository.OrdersRepository;
import com.example.vo.CountByDayResponseVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class OrdersRepositoryImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersRepository {

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public List<CountByDayResponseVo> countByDay(QueryWrapper wrapper) {
        return this.ordersMapper.countByDay(wrapper);
    }
}
