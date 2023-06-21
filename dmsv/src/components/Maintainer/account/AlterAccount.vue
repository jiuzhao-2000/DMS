<template>
    <div class="alteraccount">
        <el-card>
            <el-row justify="center">
                <el-col :span="12" style="margin-top: 50px;">
                    <el-row justify="center" style="height: 60px;">
                            <h1>您正在修改账号信息！</h1>
                    </el-row>
                    <el-form ref="form" size="large" :model="form" label-width="100px" hide-required-asterisk="true">
                        <el-form-item label="账号:" :rules="[
                            { required: true, message: '请输入账号', trigger: 'blur' },
                        ]">
                            <el-input v-model="form.mid" @change="checkmid"></el-input>
                        </el-form-item>
                        <el-form-item label="原密码:" :rules="[
                            { required: true, message: '请输入原密码', trigger: 'blur' },
                            { min: 6, max: 10, message: '长度在6-10位', trigger: 'blur' },
                        ]">
                            <el-input type="password" @change="checkpwd" v-model="form.oldpwd"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码:" :rules="[
                            { required: true, message: '请输入密码', trigger: 'blur' },
                            { min: 6, max: 10, message: '长度在6-10位', trigger: 'blur' },
                        ]">
                            <el-input type="password" v-model="form.newpwd"></el-input>
                        </el-form-item>
                        <el-form-item label="在输入一次:" :rules="[
                            { required: true, message: '请输入密码', trigger: 'blur' },
                            { min: 6, max: 10, message: '长度在6-10位', trigger: 'blur' },
                        ]">
                            <el-input type="password" @change="checkagainpwd" v-model="form.againpwd"></el-input>
                        </el-form-item>
                        <el-form-item label="验证:">
                            <el-input v-model="form.vaildpwd"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" style="width: 100%;" @click="onSubmit">修改</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>

        </el-card>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            form: {}
        }
    },
    methods: {
        onSubmit() {
            [
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        //修改密码
                        this.axios.post("/api/maintain/account/update", { mid: this.form.mid, pwd: this.form.newpwd })
                            .then(response => {
                                console.log(response);
                                let info = response.data;
                                //修改成功，退出登入
                                if (info["code"] == 200) {
                                    this.axios.get("/api/logout/maintain").then(
                                        response => {
                                            //后端登出成功
                                            if (response.data["code"] == 200) {
                                                ElMessage({
                                                    showClose: true,
                                                    message: '修改成功请重新登入',
                                                    type: 'success',
                                                });
                                                localStorage.clear();
                                                window.location.href = "/";
                                            }
                                            //后端登出失败
                                            else {
                                                ElMessage({
                                                    showClose: true,
                                                    message: '未知错误',
                                                    type: 'error',
                                                });
                                                window.location.href = "/";
                                            }
                                        }
                                    ).catch(error => {
                                        console.log(error);
                                    })

                                } else {
                                    ElMessage({
                                        showClose: true,
                                        message: info["message"],
                                        type: 'error',
                                    });
                                }
                            }).catch(error => {
                                console.log(error);
                            })
                    }
                })
            ]
        },
        checkpwd() {
            let mid = localStorage.getItem("mid");
            this.axios.post("/api/maintain/self/isTrue", { pwd: this.form.oldpwd, mid: mid })
                .then(response => {
                    let info = response.data;
                    if (info["mid"] == "") {
                        ElMessage({
                            showClose: true,
                            message: '密码错误',
                            type: 'error',
                        });
                    }
                }).catch(error => {
                    console.log(error);
                })
        },
        checkmid() {
            let mid = localStorage.getItem("mid");
            if (this.form.mid !== mid) {
                ElMessage({
                    showClose: true,
                    message: '账号不对',
                    type: 'error',
                });
            }
        },
        checkagainpwd() {
            if (this.form.newpwd !== this.form.againpwd) {
                ElMessage({
                    showClose: true,
                    message: '两次输入的新密码不一致',
                    type: 'error',
                });
            }
        }
    }
}
</script>
<style scoped>
.alteraccount {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>