package cn.ldr.test.entity;

import cn.ldr.basics.baseClass.ldrBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_teacher")
@TableName("a_teacher")
@ApiModel(value = "教师")
public class Teacher extends ldrBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "年龄")
    private BigDecimal age;

    @ApiModelProperty(value = "毕业院校")
    private String graduated;

    @ApiModelProperty(value = "工资")
    private BigDecimal wages;

    @ApiModelProperty(value = "在职状态")
    private String status;

    @ApiModelProperty(value = "签名")
    private String autograph;

    @ApiModelProperty(value = "个人简历")
    private String resume;

    @ApiModelProperty(value = "备注")
    private String remark;
}