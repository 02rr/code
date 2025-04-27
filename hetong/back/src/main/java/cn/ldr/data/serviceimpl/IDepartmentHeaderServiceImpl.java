package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.DepartmentHeaderMapper;
import cn.ldr.data.entity.DepartmentHeader;
import cn.ldr.data.service.IDepartmentHeaderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IDepartmentHeaderServiceImpl extends ServiceImpl<DepartmentHeaderMapper, DepartmentHeader> implements IDepartmentHeaderService {

}
