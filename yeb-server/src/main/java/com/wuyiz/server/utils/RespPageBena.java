package com.wuyiz.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-07 19:48
 * @description: 分页公共返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBena {
    // 总数
    private Long total;
    // 数据
    private List<?> data;
}
