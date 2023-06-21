<template>
    <div class="addvisitor">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="20">
                    <el-form ref="form" :model="form" label-width="100px">
                        <el-form-item label="拜访者身份证">
                            <el-input v-model="form.vid"></el-input>
                        </el-form-item>
                        <el-form-item label="拜访者姓名">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="拜访学生名">
                            <el-input v-model="form.stuName"></el-input>
                        </el-form-item>
                        <el-form-item label="拜访时间">
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择日期" v-model="form.visitTime"
                                    style="width: 100%;"></el-date-picker>
                            </el-col>
                            <el-col class="line" :span="2">-</el-col>
                            <el-col :span="11">
                                <el-date-picker type="date" placeholder="选择时间" v-model="form.visitedTime"
                                    style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="关系">
                            <el-input v-model="form.relationship"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" size="large" style="width: 50%;" :auto-insert-space=true
                                @click="onSubmit">立即创建</el-button>
                            <el-button size="large" style="width: 40%;" :auto-insert-space=true>取消</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>

        </el-card>

    </div>
</template>
<script>
export default {
    data() {
        return {
            form: {}
        }
    },
    methods: {
        onSubmit() {
            console.log(this.form);
            this.axios.post("/api/student/add", this.form)
                .then(response => {
                    console.log(response);
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
                }).catch(error => {
                    console.log(error);
                })
        }
    }
}
</script>
<style scoped>
.addvisitor {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>