package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.example.common.Result;
import com.example.dto.res.CountByDayResponseDto;
import com.example.entity.Orders;
import com.example.dto.res.OrdersDTO;
import com.example.entity.OrdersItem;
import com.example.service.OrdersItemService;
import com.example.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @Resource
    private OrdersItemService ordersItemService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return Result.success();
    }

    /**
     * 新增
     */
    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody OrdersDTO ordersDTO) {
        ordersService.addOrder(ordersDTO);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        ordersService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Orders orders) {
        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Orders orders = ordersService.selectById(id);
        return Result.success(orders);
    }

    /**
     * 根据商家Id统计近七天数据
     */
    @GetMapping("/countByDay/{businessId}")
    public Result countByDay(@PathVariable String businessId) {
        List<CountByDayResponseDto> list = ordersService.countByDay(businessId);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Orders orders ) {
        List<Orders> list = ordersService.selectAll(orders);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Orders orders,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Orders> page = ordersService.selectPage(orders, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/chart")
    //折线图
    public Result charts(Orders orderDate, OrdersItem ordersItemDate) {
        orderDate.setStatus("已完成");
        List<Orders> list = ordersService.selectAll(orderDate);
        Set<String> dates = list.stream().map(Orders:: interceptPayTime).collect(Collectors.toSet());
        List<String> dateList = CollUtil.newArrayList(dates);
        dateList.sort(Comparator.naturalOrder());
        List<Dict> lineList = new ArrayList<>();
        for (String date : dateList) {
            BigDecimal sum = list.stream().filter(orders ->  orders.interceptPayTime().equals(date)).map(Orders::getActual).
                    reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            Dict dict = Dict.create();
            Dict line = dict.set("date", date).set("value", sum);
            lineList.add(line);
        }

        //柱状图
        List<OrdersItem> list1 = ordersItemService.selectAll(ordersItemDate);
        Set<String> categories = list1.stream().map(OrdersItem:: getGoodsName).collect(Collectors.toSet());
        List<Dict> barList = new ArrayList<>();
        for (String cate : categories) {
            BigDecimal sum = list1.stream().filter(ordersItem ->  ordersItem.getGoodsName().equals(cate)).map(OrdersItem::getNum).
                    reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            Dict dict = Dict.create();
            Dict bar = dict.set("name", cate).set("value", sum);
            barList.add(bar);
        }

        //饼图


        Dict res = Dict.create().set("line", lineList).set("bar", barList);
        return Result.success(res);
    }
}