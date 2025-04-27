package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.UserRoleMapper;
import cn.ldr.data.entity.UserRole;
import cn.ldr.data.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
