package cn.ldr.data.serviceimpl;

import cn.ldr.data.dao.mapper.FileMapper;
import cn.ldr.data.entity.File;
import cn.ldr.data.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class IFileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

}
