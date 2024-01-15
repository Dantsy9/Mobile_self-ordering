package com.example.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.example.common.Result;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Orders;
import com.example.mapper.OrdersMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Resource
    private OrdersMapper ordersMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {
        ordersMapper.insert(orders);
    }

    /**
     * 删除
     */
    @Transactional
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        Account currentUser = TokenUtils.getCurrentUser();
        String role =currentUser.getRole();
        if (RoleEnum.BUSINESS.name().equals(role)){//若为商家，只可查询自己的信息
            orders.setBusinessId(currentUser.getId());//设置自己的id作为查询条件
        }
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        String role =currentUser.getRole();
        if (RoleEnum.BUSINESS.name().equals(role)){//若为商家，只可查询自己的信息
            orders.setBusinessId(currentUser.getId());//设置自己的id作为查询条件
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }

}