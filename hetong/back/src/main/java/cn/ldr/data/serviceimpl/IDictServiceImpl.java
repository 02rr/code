package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.DictMapper;
import cn.ldr.data.entity.Dict;
import cn.ldr.data.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IDictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

}
