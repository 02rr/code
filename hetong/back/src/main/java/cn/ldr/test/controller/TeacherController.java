package cn.ldr.test.controller;

import cn.ldr.basics.log.LogType;
import cn.ldr.basics.log.SystemLog;
import cn.ldr.basics.utils.PageUtil;
import cn.ldr.basics.utils.ResultUtil;
import cn.ldr.basics.baseVo.PageVo;
import cn.ldr.basics.baseVo.Result;
import cn.ldr.data.utils.ldrNullUtils;
import cn.ldr.data.vo.AntvVo;
import cn.ldr.test.entity.Teacher;
import cn.ldr.test.service.ITeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@RestController
@Api(tags = "demotrue管理接口")
@RequestMapping("/ldr/teacher")
@Transactional
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    @SystemLog(about = "查询单条demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-01")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条demotrue")
    public Result<Teacher> get(@RequestParam String id){
        return new ResultUtil<Teacher>().setData(iTeacherService.getById(id));
    }

    @SystemLog(about = "查询全部demotrue个数", type = LogType.MORE_MOUDLE,doType = "TEACHER-02")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部demotrue个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iTeacherService.count());
    }

    @SystemLog(about = "查询全部demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-03")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部demotrue")
    public Result<List<Teacher>> getAll(){
        return new ResultUtil<List<Teacher>>().setData(iTeacherService.list());
    }

    @SystemLog(about = "查询demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-04")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询demotrue")
    public Result<IPage<Teacher>> getByPage(@ModelAttribute Teacher teacher ,@ModelAttribute PageVo page){
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        if(!ldrNullUtils.isNull(teacher.getName())) {
            qw.like("name",teacher.getName());
        }
        if(!ldrNullUtils.isNull(teacher.getEducation())) {
            qw.eq("education",teacher.getEducation());
        }
        if(!ldrNullUtils.isNull(teacher.getGraduated())) {
            qw.like("graduated",teacher.getGraduated());
        }
        IPage<Teacher> data = iTeacherService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Teacher>>().setData(data);
    }

    @SystemLog(about = "增改demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-05")
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改demotrue")
    public Result<Teacher> saveOrUpdate(Teacher teacher){
        if(iTeacherService.saveOrUpdate(teacher)){
            return new ResultUtil<Teacher>().setData(teacher);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "新增demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-06")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增demotrue")
    public Result<Teacher> insert(Teacher teacher){
        iTeacherService.saveOrUpdate(teacher);
        return new ResultUtil<Teacher>().setData(teacher);
    }

    @SystemLog(about = "编辑demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-07")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑demotrue")
    public Result<Teacher> update(Teacher teacher){
        iTeacherService.saveOrUpdate(teacher);
        return new ResultUtil<Teacher>().setData(teacher);
    }

    @SystemLog(about = "删除demotrue", type = LogType.MORE_MOUDLE,doType = "TEACHER-08")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除demotrue")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iTeacherService.removeById(id);
        }
        return ResultUtil.success();
    }

    @SystemLog(about = "查询图表数据", type = LogType.CHART,doType = "CHART-01")
    @RequestMapping(value = "/getAntvVoList", method = RequestMethod.GET)
    @ApiOperation(value = "查询图表数据")
    public Result<List<AntvVo>> getAntvVoList(){
        List<AntvVo> ansList = new ArrayList<>();
        List<Teacher> teacherList = iTeacherService.list();
        for (Teacher o : teacherList) {
            boolean flag = false;
            for (AntvVo vo : ansList) {
                if(Objects.equals(vo.getTitle(),o.getName())) {
                    flag = true;
                    vo.setValue(vo.getValue().add(o.getWages()));
                    break;
                }
            }
            if(!flag) {
                AntvVo vo = new AntvVo();
                vo.setTitle(o.getName());
                vo.setType("工资金额");
                vo.setValue(o.getWages());
                ansList.add(vo);
            }
        }
        return new ResultUtil<List<AntvVo>>().setData(ansList);
    }
}
