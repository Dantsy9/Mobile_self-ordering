package com.example.service;

import com.example.dto.AmountDTO;
import com.example.entity.Business;
import com.example.entity.Cart;
import com.example.entity.Goods;
import com.example.mapper.CartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车业务处理
 **/
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private BusinessService businessService;

    /**
     * 新增
     */
    public void add(Cart cart) {
        cartMapper.insert(cart);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            cartMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * 根据ID查询
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Cart> selectAll(Cart cart) {
        List<Cart> cartList = cartMapper.selectAll(cart);
        for (Cart c : cartList) {
            Goods goods = goodsService.selectById(c.getGoodsId());
            c.setGoods(goods);
            Business business = businessService.selectById(c.getBusinessId());
            c.setBusiness(business);
        }
        return cartList;
    }

    /**
     * 查询用户在某商家的购物车信息
     */
    public List<Cart> selectUserCart(Integer userId, Integer businessId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBusinessId(businessId);
        return this.selectAll(cart);
    }

    /**
     * 分页查询
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = selectAll(cart);
        return PageInfo.of(list);
    }

    /**
     * 计算购物车总价
     */
    public AmountDTO calc(Integer userId, Integer businessId) {
        Cart cart =new Cart();
        cart.setUserId(userId);
        cart.setBusinessId(businessId);
//        查询当前用户在某一商家的购物车信息
        List<Cart> cartList = this.selectAll(cart);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal actual = BigDecimal.ZERO;
        for (Cart c : cartList) {
            Goods goods = c.getGoods();
            if (goods != null) {
                BigDecimal price = goods.getPrice();
                BigDecimal actualPrice = goods.getActualPrice();
//                原价
                amount = amount.add(price.multiply(BigDecimal.valueOf(c.getNum())));
//                优惠价
                actual = actual.add(actualPrice.multiply(BigDecimal.valueOf(c.getNum())));
            }
        }
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);
        amountDTO.setActual(actual);
//        优惠金额
        amountDTO.setDiscount(amount.subtract(actual));
        return amountDTO;
    }

    public void deleteCart(Integer userId, Integer businessId) {
        cartMapper.deleteCart(userId,businessId);
    }
}