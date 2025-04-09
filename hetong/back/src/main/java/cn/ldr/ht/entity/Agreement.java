package cn.ldr.ht.entity;

import cn.ldr.basics.baseClass.ldrBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * @author 郑为中
 * CSDN: Designer 小郑
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_agreement")
@TableName("a_agreement")
@ApiModel(value = "合同")
public class Agreement extends ldrBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "合同标题")
    private String title;

    @ApiModelProperty(value = "甲方")
    private String firstParty;

    @ApiModelProperty(value = "乙方")
    private String secondParty;

    @ApiModelProperty(value = "到期日期")
    private String date;

    @ApiModelProperty(value = "合同原文件")
    private String file1;

    @ApiModelProperty(value = "审批状态")
    private String auditStatus;

    @ApiModelProperty(value = "审批人")
    private String auditUser;

    @ApiModelProperty(value = "审批时间")
    private String auditTime;

    @ApiModelProperty(value = "签订状态")
    private String signStatus;

    @ApiModelProperty(value = "合同文件")
    private String file2;

    @ApiModelProperty(value = "签订时间")
    private String signTime;

    @ApiModelProperty(value = "发起人ID")
    private String signId;

    @ApiModelProperty(value = "发起人")
    private String signUser;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "到期日期")
    private Long dateTemp;
}