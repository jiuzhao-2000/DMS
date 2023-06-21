<template>
    <div class="altervisitor">
        <el-card>
            <el-text class="mx-1"></el-text>
            <el-form ref="form" :model="form" label-width="100px">
                <el-form-item label="拜访者身份证">
                    <el-text v-model="form.vid">{{ form.vid }}</el-text>
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
                    <el-button type="primary" @click="onSubmit">修改</el-button>
                    <el-button>取消</el-button>
                </el-form-item>
            </el-form>
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
            console.log(this.form);
            this.axios.post("/api/student/update", this.form)
                .then(response => {
                    console.log(response.data);
                    let info = response.data;
                    if (info["code"] == 200) {
                        ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'success',
                        });
                        this.$router.push({name:"studentvisitshow"})
                    }else{
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
    },
    mounted() {
        let vid = this.$route.query.vid;
        if (vid == null || vid === "") {
            this.$router.push({ name: "studentvisitshow" })
            return;
        } else {
            this.form = this.$route.query;
        }
    },
}
</script>
<style scoped>
.altervisitor {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>