package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.common.Result;
import com.example.common.enums.RoleEnum;
import com.example.dto.CountByDayResponseDto;
import com.example.entity.Account;
import com.example.entity.Orders;
import com.example.mapper.OrdersMapper;
import com.example.mapper.repository.OrdersRepository;
import com.example.utils.TokenUtils;
import com.example.vo.CountByDayResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersService.class);

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrdersRepository ordersRepository;

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

    /**
     * 营业额统计，按一周的区分
     * @return
     */
    public Result countByDay(Long businessId) {
        List<CountByDayResponseDto> resultList = new ArrayList<>();

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 将时间部分设置为"23:59:59"
        LocalDateTime endOfDay = currentDate.atTime(LocalTime.MIN);
        // 格式化日期时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String todayTime = endOfDay.format(formatter);

        // 获取上周的今天日期
        LocalDate lastWeekToday = currentDate.minusWeeks(1);
        // 将时间部分设置为"00:00:00"
        LocalDateTime startOfDay = lastWeekToday.atTime(LocalTime.MIN);
        // 格式化日期时间
        String lastDayTime = startOfDay.format(formatter);

        //条件构造器，构造查询条件并返回列表
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        //where语句：eq→等于
        wrapper.eq("business_id" ,businessId);
        //范围查询
        wrapper.between("pay_time", lastDayTime, todayTime);
        //order语句
        wrapper.orderByAsc("pay_time");
        //分组语句
        wrapper.groupBy("pay_time");
        //ordersRepository中的方法
        List<CountByDayResponseVo> vos = ordersRepository.countByDay(wrapper);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<String> dateList = new ArrayList<>();

        //如果不存在记录
        if (CollectionUtils.isEmpty(vos)) {
            //遍历给定的日期期间的每一天,如果日期间存在间隔就补全
            for (int i = 0; !Duration.between(startOfDay.plusDays(i), endOfDay).isNegative(); i++) {
                //添加日期
                dateList.add(startOfDay.plusDays(i).format(formatter2));
            }
            List<CountByDayResponseDto> result = new ArrayList<>();
            dateList.forEach(s -> {
                CountByDayResponseDto dto = new CountByDayResponseDto();
                dto.setDayTime(s);
                dto.setActual(0L);
                result.add(dto);
            });
            return Result.success(result);
        }

        //遍历vos列表，吧"yyyy-MM-dd HH:mm:ss"格式的时间改成"yyyy-MM-dd"
        vos.forEach(vo -> {
            LocalDateTime dateTime = LocalDateTime.parse(vo.getDayTime(), formatter);
            vo.setDayTime(dateTime.format(formatter2));
        });

        //进行日期的补全
        //遍历给定的日期期间的每一天,如果日期间存在间隔就补全
        for (int i = 0; !Duration.between(startOfDay.plusDays(i), endOfDay).isNegative(); i++) {
            //添加日期
            dateList.add(startOfDay.plusDays(i).format(formatter2));
        }

        //整理结果
        List<CountByDayResponseDto> result = new ArrayList<>();
        for (int j = 0; j < dateList.size(); j++) {
            CountByDayResponseDto resDto = new CountByDayResponseDto();
            resDto.setDayTime(dateList.get(j));
            if (vos.get(j).getDayTime().equals(dateList.get(j))) {
                resDto.setActual(vos.get(j).getActual());
            } else {
                resDto.setActual(0L);
            }
            result.add(resDto);
        }

        return Result.success(result);
    }

}