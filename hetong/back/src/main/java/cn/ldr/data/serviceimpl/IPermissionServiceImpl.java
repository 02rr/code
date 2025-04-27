package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.PermissionMapper;
import cn.ldr.data.entity.Permission;
import cn.ldr.data.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
