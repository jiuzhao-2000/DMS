<template>
    <div class="addmaintain">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="12">
                    <el-form ref="form" :model="form" size="large" label-width="80px">
                        <el-form-item label="账号id:">
                            <el-input v-model="form.mid"></el-input>
                        </el-form-item>
                        <el-form-item label="物品id:">
                            <el-input v-model="form.wid"></el-input>
                        </el-form-item>
                        <el-form-item label="物品名:">
                            <el-input v-model="form.wealthName"></el-input>
                        </el-form-item>
                        <el-form-item label="维修时间:">
                            <el-date-picker type="date" placeholder="选择日期" v-model="form.maintainTime"
                                style="width: 100%;"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit" style="width: 30%;"
                                auto-insert-space="true">添加维修信息</el-button>
                            <el-button style="width: 30%;" auto-insert-space="true">取消</el-button>
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
            this.axios.post("/api/maintain/maintained/add", this.form)
                .then(response => {
                    let info = response.data;
                    if (info.code === 200) {
                        ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'success',
                        });
                    }
                    else {
                        ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'error',
                        });
                    }
                })
        }
    }
}
</script>
<style scoped>
.addmaintain {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>