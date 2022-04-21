package com.itheima.pinda.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.common.utils.ExcelUtils;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.goods.dto.PoliticalInfoPageDTO;
import com.itheima.pinda.goods.dto.PoliticalInfoUpdateDTO;
import com.itheima.pinda.goods.entity.PoliticalInfo;
import com.itheima.pinda.goods.service.PoliticalInfoService;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/politicalInfo")
@Api(value = "politicalInfo", tags = "素材信息")
public class PoliticalInfoController extends BaseController {
    @Autowired
    private DozerUtils dozer;
    @Autowired
    private PoliticalInfoService politicalInfoService;

    @ApiOperation(value = "查询素材信息", notes = "查询素材信息")
    @GetMapping("/page")
    @SysLog("查询素材信息")
    public R<IPage<PoliticalInfo>> list(PoliticalInfoPageDTO data) {
        Page<PoliticalInfo> page = getPage();
        LbqWrapper<PoliticalInfo> wrapper = Wraps.lbQ();
        wrapper.like(PoliticalInfo::getName, data.getName())
                .geHeader(PoliticalInfo::getCreateTime, data.getStartCreateTime())
                .leFooter(PoliticalInfo::getLastCrawlTime, data.getEndCreateTime())
                .like(PoliticalInfo::getCat, data.getCat())
                .like(PoliticalInfo::getTheme, data.getTheme())
                .isNotNull(PoliticalInfo::getName)
                .orderByDesc(PoliticalInfo::getLastCrawlTime);
        politicalInfoService.page(page, wrapper);
        return success(page);
    }

    /**
     * 修改商品信息
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改素材信息", notes = "修改素材信息不为空的字段")
    @PutMapping
    @SysLog("修改素材信息")
    public R<PoliticalInfo> update(@RequestBody @Validated(SuperEntity.Update.class) PoliticalInfoUpdateDTO data) {
        PoliticalInfo politicalInfo = dozer.map(data, PoliticalInfo.class);
        politicalInfoService.updateById(politicalInfo);
        return success(politicalInfo);
    }

    /**
     * 删除商品信息
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除素材信息", notes = "根据id物理素材信息")
    @SysLog("删除素材信息")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("正在删除id为{}的素材", ids);
        politicalInfoService.removeByIds(ids);
        return success();
    }

    @PostMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream", response = PoliticalInfo.class)
    public void exports(HttpServletResponse response,
                        @RequestBody PoliticalInfo politicalInfo) {
        log.info("============="+politicalInfo.getName());
        log.info("============="+politicalInfo.getTheme());
        log.info("============="+politicalInfo.getCat());
        List<PoliticalInfo> data = politicalInfoService.exportAll(politicalInfo);
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/ms-excel");
        response.setHeader("content-type", "application/octet-stream");
        String fileName = "数据-" + "素材信息" + ".xls";
        try {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); // 这句很重要，不然文件名为乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ExcelUtils.exportExcel(data, "花名册", "素材信息", PoliticalInfo.class, fileName, response);
    }

    @GetMapping("viewPoliticalInfo")
    @ApiOperation("查看素材")
    public void viewPolitical(@RequestParam Integer politicalId){
        politicalInfoService.viewPolitical(politicalId);
    }

    @GetMapping("getViewTopFive")
    @ApiOperation("获取查看量前五")
    public R<List<PoliticalInfo>> getViewTopFive(){
        List<PoliticalInfo> viewTopFive = politicalInfoService.getViewTopFive();
        return success(viewTopFive);
    }

    @GetMapping("getPoliticalCount")
    @SysLog("获取素材总量")
    @ApiOperation("获取素材总量")
    public R<Integer> getPoliticalCount(){
        int politicalCount = politicalInfoService.getPoliticalCount();
        return success(politicalCount);
    }
}
