package com.wuyiz.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统消息内容
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_msg_content")
@ApiModel(value = "SysMsgContent对象", description = "系统消息内容")
public class SysMsgContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String message;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;


}
