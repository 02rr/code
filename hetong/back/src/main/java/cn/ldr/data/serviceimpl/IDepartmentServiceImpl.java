package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.DepartmentMapper;
import cn.ldr.data.entity.Department;
import cn.ldr.data.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IDepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
