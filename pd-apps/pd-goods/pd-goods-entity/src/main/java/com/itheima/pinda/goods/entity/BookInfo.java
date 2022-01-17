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
@TableName("book")
public class BookInfo extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    private String name;

    private String price;

    private String brand;

    private String description;

    private String cat;

    private String detailCat;

    private String image;

    private String url;

    private int status;

    private String lastCrawlTime;

}
