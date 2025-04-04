package cn.zwz.ht.serviceimpl;

import cn.zwz.ht.mapper.AgreementMapper;
import cn.zwz.ht.entity.Agreement;
import cn.zwz.ht.service.IAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 合同 服务层接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAgreementServiceImpl extends ServiceImpl<AgreementMapper, Agreement> implements IAgreementService {

    @Autowired
    private AgreementMapper agreementMapper;
}