package com.itheima.pinda.goods.entity;


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

    private String name;

    private String theme;

    private String description;

    private String cat;

    private String detailCat;

    private String type;

    private String imageUrl;

    private String articleUrl;

    private String videoUrl;

    private int status;

    private String lastCrawlTime;

}
