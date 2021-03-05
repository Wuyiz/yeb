package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.OplogMapper;
import com.wuyiz.server.pojo.Oplog;
import com.wuyiz.server.service.OplogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements OplogService {

}
