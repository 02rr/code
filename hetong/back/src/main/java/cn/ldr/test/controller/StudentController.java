package cn.ldr.test.controller;

import cn.ldr.basics.log.LogType;
import cn.ldr.basics.log.SystemLog;
import cn.ldr.basics.utils.PageUtil;
import cn.ldr.basics.utils.ResultUtil;
import cn.ldr.basics.baseVo.PageVo;
import cn.ldr.basics.baseVo.Result;
import cn.ldr.data.utils.ldrNullUtils;
import cn.ldr.test.entity.Student;
import cn.ldr.test.service.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@RestController
@Api(tags = "demo例子管理接口")
@RequestMapping("/ldr/student")
@Transactional
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @SystemLog(about = "查询单条demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-01")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条demo例子")
    public Result<Student> get(@RequestParam String id){
        return new ResultUtil<Student>().setData(iStudentService.getById(id));
    }

    @SystemLog(about = "查询全部demo例子个数", type = LogType.MORE_MOUDLE,doType = "STUDENT-02")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部demo例子个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iStudentService.count());
    }

    @SystemLog(about = "查询全部demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-03")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部demo例子")
    public Result<List<Student>> getAll(){
        return new ResultUtil<List<Student>>().setData(iStudentService.list());
    }

    @SystemLog(about = "查询demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-04")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询demo例子")
    public Result<IPage<Student>> getByPage(@ModelAttribute Student student ,@ModelAttribute PageVo page){
        QueryWrapper<Student> qw = new QueryWrapper<>();
        if(!ldrNullUtils.isNull(student.getName())) {
            qw.like("name",student.getName());
        }
        if(!ldrNullUtils.isNull(student.getNumber())) {
            qw.like("number",student.getNumber());
        }
        if(!ldrNullUtils.isNull(student.getSex())) {
            qw.like("sex",student.getSex());
        }
        if(!ldrNullUtils.isNull(student.getSchool())) {
            qw.like("school",student.getSchool());
        }
        IPage<Student> data = iStudentService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<Student>>().setData(data);
    }

    @SystemLog(about = "增改demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-05")
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改demo例子")
    public Result<Student> saveOrUpdate(Student student){
        if(iStudentService.saveOrUpdate(student)){
            return new ResultUtil<Student>().setData(student);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "新增demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-06")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增demo例子")
    public Result<Student> insert(Student student){
        iStudentService.saveOrUpdate(student);
        return new ResultUtil<Student>().setData(student);
    }

    @SystemLog(about = "编辑demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-07")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑demo例子")
    public Result<Student> update(Student student){
        iStudentService.saveOrUpdate(student);
        return new ResultUtil<Student>().setData(student);
    }

    @SystemLog(about = "删除demo例子", type = LogType.MORE_MOUDLE,doType = "STUDENT-08")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除demo例子")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iStudentService.removeById(id);
        }
        return ResultUtil.success();
    }
}
