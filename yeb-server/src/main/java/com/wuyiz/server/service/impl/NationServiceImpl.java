package com.wuyiz.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyiz.server.mapper.NationMapper;
import com.wuyiz.server.pojo.Nation;
import com.wuyiz.server.service.NationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 民族表 服务实现类
 * </p>
 *
 * @author suhai
 * @since 2021-03-06
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}
