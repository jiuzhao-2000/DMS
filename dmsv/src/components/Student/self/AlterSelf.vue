<template>
    <div class="alterself">
        <el-card>
            <el-row justify="start" style="margin-top: 50px;">
                <el-col :span="12" :offset="1">
                    <el-row style="height: 80px;">
                        <span>
                            您正在修改电话号码！
                        </span>
                    </el-row>
                    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="电话号码:" prop="phone">
                            <el-input type="text" v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" style="width: 40%;" @click="onSubmit()"
                                :auto-insert-space=true>修改电话</el-button>
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
            form: {
                phone: '',
            },
            rules: {
                phone: [{
                    pattern: /^(13[0-9]|14[0-9]|15[0-9]|16[6]|18[0-9]|19[6,9]|17[0-9])\d{8}$/i,
                    message: "请输入正确的手机号码",
                    trigger: "blur"
                }],
            }
        }
    },
    methods: {
        onSubmit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    this.axios.post("/api/student/self/update/" + this.form.phone)
                        .then(response => {
                            let info = response.data;
                            if (info["code"] == 200) {
                                ElMessage({
                                    showClose: true,
                                    message: info["message"],
                                    type: 'success',
                                });
                            } else {
                                ElMessage({
                                    showClose: true,
                                    message: info["message"],
                                    type: 'error',
                                });
                            }
                        }).catch(function (error) {
                            console.log(error);
                        })
                }
            })

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