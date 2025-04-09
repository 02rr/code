package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.UserMapper;
import cn.ldr.data.entity.User;
import cn.ldr.data.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
