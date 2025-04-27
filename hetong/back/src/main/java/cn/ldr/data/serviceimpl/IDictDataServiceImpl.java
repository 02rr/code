package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.DictDataMapper;
import cn.ldr.data.entity.DictData;
import cn.ldr.data.service.IDictDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IDictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

}
