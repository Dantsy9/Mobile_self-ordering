package com.example.controller;

import com.example.common.Result;
import com.example.entity.Orders;
import com.example.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
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
    public Result charts(Orders orderDate) {
//        orderDate.setStatus("已完成");
//        List<Orders> list = ordersService.selectAll(orderDate);
//        Set<String> dates = list.stream().map(Orders:: interceptPayTime).collect(Collectors.toSet());
//        List<String> dateList = CollUtil.newArrayList(dates);
//        dateList.sort(Comparator.naturalOrder());
//        List<Dict> lineList = new ArrayList<>();
//        for (String date : dateList) {
//            BigDecimal sum = list.stream().filter(orders ->  orders.interceptPayTime().equals(date)).map(Orders::getActual).
//                    reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
//            Dict dict = Dict.create();
//            Dict line = dict.set("date", date).set("value", sum);
//            lineList.add(line);
//        }
//        return Result.success(lineList);
         return Result.success();
    }

    @PostMapping("/countByDay")
    public Result countByDay(Long businessId) {
        return Result.success(ordersService.countByDay(businessId));
    }
}