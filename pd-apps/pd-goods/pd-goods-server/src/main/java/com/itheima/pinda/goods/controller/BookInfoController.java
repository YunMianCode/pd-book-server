package com.itheima.pinda.goods.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.pinda.base.BaseController;
import com.itheima.pinda.base.R;
import com.itheima.pinda.base.entity.SuperEntity;
import com.itheima.pinda.database.mybatis.conditions.Wraps;
import com.itheima.pinda.database.mybatis.conditions.query.LbqWrapper;
import com.itheima.pinda.dozer.DozerUtils;
import com.itheima.pinda.goods.VO.BookInfoTopFiveVo;
import com.itheima.pinda.goods.dto.BookInfoPageDTO;
import com.itheima.pinda.goods.dto.BookInfoUpdateDTO;
import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.service.BookInfoService;
import com.itheima.pinda.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/bookInfo")
@Api(value = "bookInfo", tags = "图书信息")
public class BookInfoController extends BaseController {
    @Autowired
    private DozerUtils dozer;
    @Autowired
    private BookInfoService bookInfoService;

    @ApiOperation(value = "查询图书信息", notes = "查询图书信息")
    @GetMapping("/page")
    @SysLog("查询图书信息")
    public R<IPage<BookInfo>> list(BookInfoPageDTO data) {
        Page<BookInfo> page = getPage();
        LbqWrapper<BookInfo> wrapper = Wraps.lbQ();
        wrapper.like(BookInfo::getName, data.getName())
                .geHeader(BookInfo::getCreateTime, data.getStartCreateTime())
                .leFooter(BookInfo::getCreateTime, data.getEndCreateTime())
                .like(BookInfo::getCat,data.getCat())
                .orderByDesc(BookInfo::getLastCrawlTime);
        bookInfoService.page(page,wrapper);
        return success(page);
    }

    /**
     * 修改商品信息
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改图书信息", notes = "修改图书信息不为空的字段")
    @PutMapping
    @SysLog("修改图书信息")
    public R<BookInfo> update(@RequestBody @Validated(SuperEntity.Update.class) BookInfoUpdateDTO data) {
        BookInfo bookInfo = dozer.map(data, BookInfo.class);
        bookInfoService.updateById(bookInfo);
        return success(bookInfo);
    }

    /**
     * 删除商品信息
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除图书信息", notes = "根据id物理图书信息")
    @SysLog("删除图书信息")
    @DeleteMapping
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        log.info("正在删除id为{}的图书",ids);
        bookInfoService.removeByIds(ids);
        return success();
    }

    @ApiOperation(value = "获取图书分类前五名", notes = "获取图书分类前五名")
    @SysLog("获取图书分类排名")
    @GetMapping("getTopFiveBooks")
    public R<List<BookInfoTopFiveVo>> getTopFiveBooks(){
        log.info("正在获取图书前五名");
        List<BookInfoTopFiveVo> topFiveBooks = bookInfoService.getTopFiveBooks();
        return success(topFiveBooks);
    }

    @ApiOperation(value = "获取图书总量",notes = "获取图书总量")
    @SysLog("获取图书总量")
    @GetMapping("getBookCount")
    public R<Integer> getUserCount(){
        Integer bookCount = bookInfoService.getBookCount();
        return success(bookCount);
    }
}
