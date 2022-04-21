package com.itheima.pinda.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.goods.dao.PoliticalInfoMapper;
import com.itheima.pinda.goods.entity.PoliticalInfo;
import com.itheima.pinda.goods.service.PoliticalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PoliticalInfoServiceImpl extends ServiceImpl<PoliticalInfoMapper, PoliticalInfo> implements PoliticalInfoService {

    @Autowired
    private PoliticalInfoMapper politicalInfoMapper;

    @Override
    public List<PoliticalInfo> exportAll(PoliticalInfo politicalInfo) {
        String name = politicalInfo.getName();
        String theme = politicalInfo.getTheme();
        String cat = politicalInfo.getCat();
        if (name != null && theme != null && cat != null) {
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery().like(PoliticalInfo::getName, name)
                    .like(PoliticalInfo::getTheme, theme)
                    .like(PoliticalInfo::getCat, cat));
            return politicalInfos;
        }else if(name != null && theme != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery().like(PoliticalInfo::getName, name)
                    .like(PoliticalInfo::getTheme, theme));
            return politicalInfos;
        }
        else if(name != null && cat != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery().like(PoliticalInfo::getName, name)
                    .like(PoliticalInfo::getCat, cat));
            return politicalInfos;
        }
        else if(theme != null && cat != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery().like(PoliticalInfo::getName, name)
                    .like(PoliticalInfo::getTheme, theme));
            return politicalInfos;
        }
        else if(name != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery()
                    .like(PoliticalInfo::getName, name));
            return politicalInfos;
        }
        else if(theme != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery()
                    .like(PoliticalInfo::getTheme, theme));
            return politicalInfos;
        }
        else if(cat != null){
            List<PoliticalInfo> politicalInfos = baseMapper.selectList(Wrappers.<PoliticalInfo>lambdaQuery()
                    .like(PoliticalInfo::getCat, cat));
            return politicalInfos;
        }
        List<PoliticalInfo> users = baseMapper.selectList(null);
        return users;
    }

    @Override
    public void viewPolitical(Integer politicalId) {
        PoliticalInfo politicalInfo = baseMapper.selectOne(new LambdaQueryWrapper<PoliticalInfo>().eq(SuperEntity::getId, politicalId));
        int count = politicalInfo.getCount();
        politicalInfo.setCount(++count);
        baseMapper.updateById(politicalInfo);
    }

    @Override
    public int getPoliticalCount() {
        List<PoliticalInfo> politicalInfos = baseMapper.selectList(null);
        return politicalInfos.size();
    }

    @Override
    public List<PoliticalInfo> getViewTopFive() {
        List<PoliticalInfo> topFive = politicalInfoMapper.getViewTopFive();
        return topFive;
    }
}
