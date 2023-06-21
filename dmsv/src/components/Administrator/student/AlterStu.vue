<template>
    <div class="alterstu">
        <el-card>
            <el-row justify="center">
                <el-col :span="12">
                    <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="学号:">
                    <el-text v-model="form.sid">{{ form.sid }}</el-text>
                </el-form-item>
                <el-form-item label="姓名:">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别:">
                    <el-input v-model="form.sex"></el-input>
                </el-form-item>
                <el-form-item label="宿舍楼:">
                    <el-input v-model="form.buildingName"></el-input>
                </el-form-item>
                <el-form-item label="楼号:">
                    <el-input v-model="form.did"></el-input>
                </el-form-item>
                <el-form-item label="床号:">
                    <el-input v-model="form.bedId"></el-input>
                </el-form-item>
                <el-form-item label="状态:">
                    <el-input v-model="form.state"></el-input>
                </el-form-item>
                <el-form-item label="手机号:">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button :auto-insert-space=true type="primary" @click="onSubmit" style="width: 40%;">修改</el-button>
                    <el-button :auto-insert-space=true style="width: 40%;">取消</el-button>
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
                sid: '',
                name: '',
                sex: '',
                buildingName: '',
                did: 0,
                bedId: 0,
                state: '',
                phone: '',
            }
        }
    },
    methods: {
        onSubmit() {
            console.log('submit!');
            ElMessageBox.prompt('请输入密码', 'Tip', {
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
            })
                .then(({ value }) => {
                    this.axios.post("/api/admin/stu/alter/" + localStorage.getItem("aid") + "/" + value, this.form)
                        .then(response => {
                            console.log(response);
                            let info = response.data;
                            if (info.code == 200) {
                                ElMessage({
                                    showClose: true,
                                    message: info["message"],
                                    type: 'success',
                                });
                                this.$router.push({name:"administratorstushow"});
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
                })
        }
    },
    created() {
        let sid = this.$route.query.sid;
        if (sid == null || sid === "") {
            this.$router.push({ name: "administratorstushow" })
            return;
        }
        this.form = this.$route.query;
    }
}
</script>
<style scoped>
.alterstu{
    height: 90%;
}
.el-card{
    height: 100%;
}
</style>