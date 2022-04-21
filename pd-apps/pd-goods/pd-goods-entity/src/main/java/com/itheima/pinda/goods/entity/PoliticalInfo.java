package com.itheima.pinda.goods.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itheima.pinda.base.entity.Entity;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("political")
public class PoliticalInfo extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    @Excel(name = "素材名称")
    private String name;

    @Excel(name = "主题")
    private String theme;

    @Excel(name = "描述")
    private String description;

    @Excel(name = "分类")
    private String cat;

    @Excel(name = "查看数量")
    private int count;

    @Excel(name = "详细分类")
    private String detailCat;

    @Excel(name = "类型")
    private String type;

    private String imageUrl;

    @Excel(name = "素材链接")
    private String articleUrl;

    private String videoUrl;

    private int status;

    @Excel(name = "更新时间")
    private String lastCrawlTime;

}
