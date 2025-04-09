package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.SettingMapper;
import cn.ldr.data.entity.Setting;
import cn.ldr.data.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Service
public class ISettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

}
