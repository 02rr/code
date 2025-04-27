package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.RoleMapper;
import cn.ldr.data.entity.Role;
import cn.ldr.data.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IRoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
