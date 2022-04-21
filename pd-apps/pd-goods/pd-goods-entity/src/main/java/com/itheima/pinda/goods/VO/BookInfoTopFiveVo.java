package com.itheima.pinda.goods.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BookInfoTopFiveVo {

    private String cat;

    private int count;

}
