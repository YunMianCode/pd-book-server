package com.itheima.pinda.goods.dao;

import ch.qos.logback.classic.spi.PlatformInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.entity.PoliticalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticalInfoMapper extends BaseMapper<PoliticalInfo> {

    List<PoliticalInfo> exportByKey(PoliticalInfo politicalInfo);

}
