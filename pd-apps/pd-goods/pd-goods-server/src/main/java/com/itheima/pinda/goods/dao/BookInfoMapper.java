package com.itheima.pinda.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pinda.goods.VO.BookInfoTopFiveVo;
import com.itheima.pinda.goods.entity.BookInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInfoMapper extends BaseMapper<BookInfo> {
    List<BookInfoTopFiveVo> getTopFiveBooks();
}
