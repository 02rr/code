<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">企业合同详情</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="合同标题" prop="title">
                <Input v-model="form.title" readonly style="width:570px" />
            </FormItem>
            <FormItem label="甲方" prop="firstParty">
                <Input v-model="form.firstParty" readonly style="width:570px" />
            </FormItem>
            <FormItem label="乙方" prop="secondParty">
                <Input v-model="form.secondParty" readonly style="width:570px" />
            </FormItem>
            <FormItem label="到期日期" prop="date">
                <Input v-model="form.date" readonly style="width:570px" />
            </FormItem>
            <FormItem label="合同原文件" prop="file1" v-show="form.file1!=''">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary" @Click="downFile(form.file1)">下载</Button>
            </FormItem>
            <FormItem label="审批状态" prop="auditStatus">
                <Input v-model="form.auditStatus" readonly style="width:570px" />
            </FormItem>
            <FormItem label="审批人" prop="auditUser">
                <Input v-model="form.auditUser" readonly style="width:570px" />
            </FormItem>
            <FormItem label="审批时间" prop="auditTime">
                <Input v-model="form.auditTime" readonly style="width:570px" />
            </FormItem>
            <FormItem label="签订状态" prop="signStatus">
                <Input v-model="form.signStatus" readonly style="width:570px" />
            </FormItem>
            <FormItem label="合同文件" prop="file2" v-show="form.file2!=''">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary" @Click="downFile(form.file2)">下载</Button>
            </FormItem>
            <FormItem label="签订时间" prop="signTime">
                <Input v-model="form.signTime" readonly style="width:570px" />
            </FormItem>
            <FormItem label="发起人" prop="signUser">
                <Input v-model="form.signUser" readonly style="width:570px" />
            </FormItem>
            <Form-item class="br">
                <!-- <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button> -->
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    editAgreement
} from "./api.js";
export default {
    name: "edit",
    components: {},
    props: {
        data: Object
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
                signId: "",
                signUser: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {
            this.handleReset();
            this.form = this.data;
        },
        downFile(e) {
            window.open(e + "?preview=true");
        },
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    editAgreement(this.form).then(res => {
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
