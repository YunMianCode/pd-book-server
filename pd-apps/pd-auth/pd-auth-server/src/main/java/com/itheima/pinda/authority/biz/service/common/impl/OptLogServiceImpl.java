package com.itheima.pinda.authority.biz.service.common.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.authority.biz.dao.common.OptLogMapper;
import com.itheima.pinda.authority.entity.common.OptLog;
import com.itheima.pinda.authority.util.DateUtil;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.log.entity.OptLogDTO;
import com.itheima.pinda.authority.biz.service.common.OptLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * 业务实现类
 * 操作日志
 */
@Slf4j
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    @Autowired
    DozerUtils dozer;

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(dozer.map(entity, OptLog.class));
    }

    @Autowired
    private OptLogMapper optLogMapper;

    @Override
    public HashMap<String, Integer> getVisitOfWeek() {
        String nowDate = DateUtil.OperDate.getToday();
        HashMap<String, Integer> map = new HashMap<>();
        LocalDate dateTime = DateUtil.OperDate.parse(nowDate);
        int i = 7;
        int num =  0;
        while (i-- > 0){
            String yesterday = DateUtil.OperDate.adjustDay(dateTime, num--, DateUtil.OperDate.DATE_PATTERN);
            List<String> visitOfWeek = optLogMapper.getVisitOfWeek(yesterday);
            map.put(yesterday,visitOfWeek.size());
        }
        return map;
    }
}