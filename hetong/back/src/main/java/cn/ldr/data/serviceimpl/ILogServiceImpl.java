package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.LogMapper;
import cn.ldr.data.entity.Log;
import cn.ldr.data.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Service
public class ILogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
