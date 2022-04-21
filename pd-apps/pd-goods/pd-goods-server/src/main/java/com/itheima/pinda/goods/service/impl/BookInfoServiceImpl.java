package com.itheima.pinda.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.goods.VO.BookInfoTopFiveVo;
import com.itheima.pinda.goods.dao.BookInfoMapper;
import com.itheima.pinda.goods.dao.GoodsInfoMapper;
import com.itheima.pinda.goods.dao.PoliticalInfoMapper;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.entity.PoliticalInfo;
import com.itheima.pinda.goods.service.BookInfoService;
import com.itheima.pinda.goods.service.PoliticalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public List<BookInfoTopFiveVo> getTopFiveBooks() {
        List<BookInfoTopFiveVo> topFiveBooks = bookInfoMapper.getTopFiveBooks();
        return topFiveBooks;
    }

    @Override
    public Integer getBookCount() {
        List<BookInfo> bookInfoList = baseMapper.selectList(null);
       return bookInfoList.size();
    }
}

