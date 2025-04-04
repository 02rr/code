<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">添加企业合同</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="合同标题" prop="title">
                <Input v-model="form.title" clearable maxlength="240" show-word-limit placeholder="请输入合同标题..." style="width:570px" />
            </FormItem>
            <FormItem label="甲方" prop="firstParty">
                <Input v-model="form.firstParty" clearable maxlength="240" show-word-limit placeholder="请输入合同甲方..." style="width:570px" />
            </FormItem>
            <FormItem label="乙方" prop="secondParty">
                <Input v-model="form.secondParty" clearable maxlength="240" show-word-limit placeholder="请输入合同乙方..." style="width:570px" />
            </FormItem>
            <FormItem label="到期日期" prop="date">
                <DatePicker type="date" placeholder="请选择合同到期日期..." format="yyyy-MM-dd" @on-change="changeDate" style="width: 570px"></DatePicker>
            </FormItem>
            <FormItem label="合同原文件" prop="file1">
                <upload-file-input v-model="form.file1" placeholder="请上传合同原文件..." accept=".pdf" style="width:570px"></upload-file-input>
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    addAgreement
} from "./api.js";
import uploadFileInput from "@/views/template/upload-file-input";
export default {
    name: "add",
    components: {
        uploadFileInput
    },
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                title: "",
                firstParty: "",
                secondParty: "",
                file1: "",
                auditStatus: "",
                auditUser: "",
                auditTime: "",
                signStatus: "",
                file2: "",
                signTime: "",
                date: "",
                signId: "",
                signUser: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {},
        changeDate(e) {
            this.form.date = e;
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    addAgreement(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
