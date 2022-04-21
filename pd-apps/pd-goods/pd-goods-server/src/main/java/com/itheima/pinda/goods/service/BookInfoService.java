package com.itheima.pinda.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pinda.goods.VO.BookInfoTopFiveVo;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.entity.GoodsInfo;

import java.util.List;

public interface BookInfoService extends IService<BookInfo> {
    List<BookInfoTopFiveVo> getTopFiveBooks();

    Integer getBookCount();
}
