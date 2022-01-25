package com.itheima.pinda.authority.dto.auth;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserExportDTO", description = "用户导出DTO")
public class UserExportDTO {

    @ApiModelProperty(value = "orgId")
    private Long orgId;

}
