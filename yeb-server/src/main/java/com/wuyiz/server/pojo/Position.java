package com.wuyiz.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_position")
@ApiModel(value = "Position对象", description = "职位")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职位")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "YYYY-MM-dd", timezone = "UTC")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;


}
