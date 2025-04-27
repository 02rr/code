package cn.ldr.ht.controller;

import cn.hutool.core.date.DateUtil;
import cn.ldr.basics.log.LogType;
import cn.ldr.basics.log.SystemLog;
import cn.ldr.basics.utils.PageUtil;
import cn.ldr.basics.utils.ResultUtil;
import cn.ldr.basics.baseVo.PageVo;
import cn.ldr.basics.baseVo.Result;
import cn.ldr.basics.utils.SecurityUtil;
import cn.ldr.data.entity.User;
import cn.ldr.data.service.IUserService;
import cn.ldr.data.utils.ldrNullUtils;
import cn.ldr.data.vo.AntvVo;
import cn.ldr.ht.entity.Agreement;
import cn.ldr.ht.service.IAgreementService;
import cn.hutool.core.util.StrUtil;
import cn.ldr.test.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@Api(tags = "合同管理接口")
@RequestMapping("/ldr/agreement")
@Transactional
public class AgreementController {

    @Autowired
    private IAgreementService iAgreementService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SecurityUtil securityUtil;

    private final Long DAY_CUO = 86400L;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条合同")
    public Result<Agreement> get(@RequestParam String id){
        return new ResultUtil<Agreement>().setData(iAgreementService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部合同个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iAgreementService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部合同")
    public Result<List<Agreement>> getAll(){
        return new ResultUtil<List<Agreement>>().setData(iAgreementService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询合同")
    public Result<IPage<Agreement>> getByPage(@ModelAttribute Agreement agreement ,@ModelAttribute PageVo page){
        QueryWrapper<Agreement> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("sign_id",currUser.getId());
        }
        if(!ldrNullUtils.isNull(agreement.getTitle())) {
            qw.like("title",agreement.getTitle());
        }
        if(!ldrNullUtils.isNull(agreement.getFirstParty())) {
            qw.like("first_party",agreement.getFirstParty());
        }
        if(!ldrNullUtils.isNull(agreement.getSecondParty())) {
            qw.like("second_party",agreement.getSecondParty());
        }
        if(!ldrNullUtils.isNull(agreement.getAuditStatus())) {
            qw.eq("audit_status",agreement.getAuditStatus());
        }
        if(!ldrNullUtils.isNull(agreement.getSignStatus())) {
            qw.eq("sign_status",agreement.getSignStatus());
        }
        long ldrNowCuo = System.currentTimeMillis() / 1000;
        IPage<Agreement> data = iAgreementService.page(PageUtil.initMpPage(page),qw);
        for (Agreement vo : data.getRecords()) {
            long cuo = ldrDateToCuo(vo.getDate());
            vo.setDateTemp((cuo - ldrNowCuo) / DAY_CUO);
        }
        return new ResultUtil<IPage<Agreement>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改合同")
    public Result<Agreement> saveOrUpdate(Agreement agreement){
        if(iAgreementService.saveOrUpdate(agreement)){
            return new ResultUtil<Agreement>().setData(agreement);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增合同")
    public Result<Agreement> insert(Agreement agreement){
        User currUser = securityUtil.getCurrUser();
        agreement.setAuditStatus("未审批");
        agreement.setAuditUser("");
        agreement.setAuditTime("");
        agreement.setSignStatus("未签订");
        agreement.setFile2("");
        agreement.setSignTime("");
        agreement.setSignId(currUser.getId());
        agreement.setSignUser(currUser.getNickname());
        iAgreementService.saveOrUpdate(agreement);
        return new ResultUtil<Agreement>().setData(agreement);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑合同")
    public Result<Agreement> update(Agreement agreement){
        iAgreementService.saveOrUpdate(agreement);
        return new ResultUtil<Agreement>().setData(agreement);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除合同")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iAgreementService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ApiOperation(value = "审批合同")
    public Result<Object> audit(@RequestParam String id){
        Agreement as = iAgreementService.getById(id);
        if(as == null) {
            return ResultUtil.error("合同不存在");
        }
        User currUser = securityUtil.getCurrUser();
        as.setAuditStatus("已审批");
        as.setAuditTime(DateUtil.now());
        as.setAuditUser(currUser.getNickname());
        iAgreementService.saveOrUpdate(as);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/notAudit", method = RequestMethod.POST)
    @ApiOperation(value = "驳回合同")
    public Result<Object> notAudit(@RequestParam String id){
        Agreement as = iAgreementService.getById(id);
        if(as == null) {
            return ResultUtil.error("合同不存在");
        }
        User currUser = securityUtil.getCurrUser();
        as.setAuditStatus("已驳回");
        as.setAuditTime(DateUtil.now());
        as.setAuditUser(currUser.getNickname());
        iAgreementService.saveOrUpdate(as);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    @ApiOperation(value = "签订合同")
    public Result<Object> sign(@RequestParam String id,@RequestParam String fileUrl){
        Agreement as = iAgreementService.getById(id);
        if(as == null) {
            return ResultUtil.error("合同不存在");
        }
        as.setSignStatus("已签订");
        as.setFile2(fileUrl);
        as.setSignTime(DateUtil.now());
        iAgreementService.saveOrUpdate(as);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        QueryWrapper<Agreement> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<User> userQw = new QueryWrapper<>();
        userQw.eq("id",currUser.getId());
        userQw.inSql("id","SELECT user_id FROM a_user_role WHERE del_flag = 0 AND role_id = '1536606659751841799'");
        if(iUserService.count(userQw) < 1L) {
            qw.eq("sign_id",currUser.getId());
        }
        List<Agreement> teacherList = iAgreementService.list(qw);
        for (Agreement o : teacherList) {
            boolean flag = false;
            String str = getAgreementStatus(o);
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),str)) {
                    flag = true;
                    vo.setValue(vo.getValue().add(BigDecimal.ONE));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(str);
                vo.setType("合同数量");
                vo.setValue(BigDecimal.ONE);
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }

    private String getAgreementStatus(Agreement a) {
        if(Objects.equals("已签订",a.getSignStatus())) {
            return "已签订";
        }
        return a.getAuditStatus();
    }

    private static long ldrDateToCuo(String s){
        s += " 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(s).getTime()/1000;
        } catch (Exception e) {
            return System.currentTimeMillis()/1000;
        }
    }
}
