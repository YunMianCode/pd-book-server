package com.itheima.pinda.goods.dto;

import com.itheima.pinda.goods.entity.PoliticalInfo;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PoliticalInfoPageDTO extends PoliticalInfo {

    private LocalDateTime startCreateTime;
    private LocalDateTime endCreateTime;

}
