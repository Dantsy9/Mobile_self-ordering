package com.example.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.example.common.enums.OrderStatusEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.dto.res.AmountDTO;
import com.example.dto.res.CountByDayResponseDto;
import com.example.dto.res.CountByMonthResponseDto;
import com.example.dto.res.OrdersDTO;
import com.example.entity.Account;
import com.example.entity.Cart;
import com.example.entity.Orders;
import com.example.entity.OrdersItem;
import com.example.exception.CustomException;
import com.example.mapper.OrdersMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private CartService cartService;

    @Resource
    private OrdersItemService ordersItemService;

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
        ordersItemService.deleteByOrderId(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        if (orders.getStatus().equals(OrderStatusEnum.SERVE_MEALS.getValue())){
            orders.setPayTime(DateUtil.now());
        }
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

    /**
     * 用户下单
     */
    @Transactional
    public void addOrder(OrdersDTO ordersDTO) {
        Integer businessId = ordersDTO.getBusinessId();
        Account currentUser = TokenUtils.getCurrentUser();
        Integer userId = currentUser.getId();
        String phone = currentUser.getPhone();
        if (userId == null) {
            throw new CustomException(ResultCodeEnum.NO_LOGIN);
        }
        List<Cart> cartList = cartService.selectUserCart(currentUser.getId(), businessId);
        if (CollUtil.isEmpty(cartList)) {
            throw new CustomException(ResultCodeEnum.NO_GOODS);

        }
        Orders orders = new Orders();
        orders.setBusinessId(businessId);
        orders.setUserId(userId);
        orders.setPhone(phone);
        String now = DateUtil.now();
        orders.setPayTime(now);
        orders.setTableNum(ordersDTO.getTableNum());
        orders.setComment(ordersDTO.getRemark());

//        设置商品信息
        AmountDTO amountDTO = cartService.calc(userId, businessId);
        orders.setAmount(amountDTO.getAmount());
        orders.setDiscount(amountDTO.getDiscount());
        orders.setActual(amountDTO.getActual());

//        获取购物车商品总数
        Integer nums =  cartList.stream().map(Cart::getNum).reduce(Integer::sum).orElse(0);
        orders.setName(cartList.get(0).getGoods().getName() + "等" +nums + "件商品");
        orders.setCover(cartList.get(0).getGoods().getImg());

//        订单编号
        orders.setOrderNo(IdUtil.getSnowflakeNextIdStr()); //雪花算法生成
//        设置订单状态
        orders.setStatus(OrderStatusEnum.SERVE_MEALS.getValue());
        this.add(orders);

//        设置订单详细信息
        for (Cart cart : cartList) {
            OrdersItem ordersItem = new OrdersItem();
            ordersItem.setOrderId(orders.getId());
            ordersItem.setGoodsName(cart.getGoods().getName());
            ordersItem.setGoodsImg(cart.getGoods().getImg());
            ordersItem.setPrice(cart.getGoods().getActualPrice());
            ordersItem.setNum(BigDecimal.valueOf(cart.getNum()));
            ordersItemService.add(ordersItem);
        }

//         清空购物车
        cartService.deleteCart(userId, businessId);
    }

    /**统计商家近七天的销售金额*/
    public List<CountByDayResponseDto> countByDay(String businessId) {
        return ordersMapper.countByDay(businessId);
    }

    public List<CountByMonthResponseDto> countByMonth() {
        //获取当前日期
        LocalDate nowTime = LocalDate.now();
        //获取上个月的today
        LocalDate lastMonthToday = nowTime.minusMonths(1);
        return ordersMapper.countByMonth(nowTime, lastMonthToday);
    }

}