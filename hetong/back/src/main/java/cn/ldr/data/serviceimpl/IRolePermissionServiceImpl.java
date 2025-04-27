package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.RolePermissionMapper;
import cn.ldr.data.entity.RolePermission;
import cn.ldr.data.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IRolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
