package com.itheima.pinda.goods.dto;


import com.itheima.pinda.goods.entity.BookInfo;
import com.itheima.pinda.goods.entity.GoodsInfo;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BookInfoPageDTO extends BookInfo {

    private LocalDateTime startCreateTime;
    private LocalDateTime endCreateTime;

}
