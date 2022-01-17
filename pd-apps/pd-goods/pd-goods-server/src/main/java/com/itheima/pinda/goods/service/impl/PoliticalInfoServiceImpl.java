package com.itheima.pinda.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.goods.dao.BookInfoMapper;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.service.BookInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PoliticalInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {
}
