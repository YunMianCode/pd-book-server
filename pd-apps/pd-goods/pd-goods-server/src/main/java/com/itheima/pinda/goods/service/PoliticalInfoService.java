package com.itheima.pinda.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.entity.PoliticalInfo;

import java.util.List;

public interface PoliticalInfoService extends IService<PoliticalInfo> {

    List<PoliticalInfo> exportAll(PoliticalInfo politicalInfo);

}
