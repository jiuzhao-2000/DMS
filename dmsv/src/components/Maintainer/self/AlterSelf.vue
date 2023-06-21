<template>
    <div class="alterself">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="16">
                    <h1 style="height: 50px;">
                        注意您正在改个人信息！
                    </h1>
                    <el-form ref="form" size="large" :model="form" label-width="80px">
                        <el-form-item label="姓名:">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="电话号码:">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit" style="width: 100%;">修改</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </el-card>
    </div>
</template>
<script>
import { ElMessage, ElMessageBox } from 'element-plus'
export default {
    data() {
        return {
            form: {
                name: '',
                phone: '',
            },

        }
    },
    methods: {
        onSubmit() {
            //输入密码确认
            ElMessageBox.prompt('请输入密码', 'Tip', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
            })
                .then(({ value }) => {
                    console.log(value);
                    //验证密码
                    this.axios.post("/api/maintain/self/isTrue", { mid: localStorage.getItem("mid"), pwd: value })
                        .then(response => {
                            let info = response.data;
                            //密码校验正确
                            if (info["code"] == 200) {
                                //修改信息
                                this.axios.post("/api/maintain/self/alter", {
                                    mid: localStorage.getItem("mid"),
                                    name: this.form.name,
                                    phone: this.form.phone
                                })
                                    .then(res => {
                                        console.log(res);
                                        let data = res.data;
                                        if (data["code"] == 200) {
                                            ElMessage({
                                                showClose: true,
                                                message: data["message"],
                                                type: 'success',
                                            });
                                        } else {
                                            ElMessage({
                                                showClose: true,
                                                message: data["message"],
                                                type: 'error',
                                            });
                                        }
                                    })
                            } else {
                                ElMessage({
                                    showClose: true,
                                    message: '密码错误',
                                    type: 'error',
                                })
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        })
                })
                .catch(() => {
                    ElMessage({
                        type: 'info',
                        message: 'Input canceled',
                    })
                })
            //修改
            console.log('submit!');
        }
    }
}
</script>
<style scoped>
.alterself {
    position: relative;
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>