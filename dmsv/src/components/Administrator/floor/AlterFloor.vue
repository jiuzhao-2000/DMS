<template>
    <div class="alterfloor">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="18">
                    <el-form ref="form" size="large" :model="form" label-width="120px">
                        <el-form-item label="要修改的楼号:">
                            <el-input v-model="form.bid"></el-input>
                        </el-form-item>
                        <el-form-item label="修改后的楼名:">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="修改后的电话:">
                            <el-input v-model="form.phone"></el-input>
                        </el-form-item>
                        <el-form-item label="修改后的地址:">
                            <el-input v-model="form.location"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button style="width: 40%;" type="primary" @click="onSubmit">立即创建</el-button>
                            <el-button style="width: 40%;">取消</el-button>
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
            console.log('submit!');
            this.axios.post("/api/admin/building/alter", this.form)
                .then(response => {
                    let info = response.data;
                    if (info["code"] == 200) {
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
                }).catch(error => {
                    console.log(error);
                })
        }
    }
}
</script>
<style scoped>
.alterfloor {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>