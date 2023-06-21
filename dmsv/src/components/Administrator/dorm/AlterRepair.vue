<template>
    <div class="alterrepair">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="16">
                    <el-form ref="form" size="large" :model="form" label-width="80px">
                <el-form-item label="bid">
                    <el-input v-model="form.bid"></el-input>
                </el-form-item>
                <el-form-item label="did">
                    <el-input v-model="form.did"></el-input>
                </el-form-item>
                <el-form-item label="物品名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="问题">
                    <el-input v-model="form.question"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit" style="width: 40%;">立即创建</el-button>
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
    name:"AlterRespair",
    data() {
      return {
        form: {},
      }
    },
    methods: {
      onSubmit() {
        this.axios.post("/api/admin/dorm/repair/alter",this.form)
        .then(response=>{
            console.log(response);
            let info=response.data;
            if(info["code"]==200){
                ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'success',
                        });
            }
            else{
                ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'error',
                        });
            }
        }).catch(error=>{
            console.log(error);
        })
      }
    }
  }
</script>
<style scoped>
.alterrepair{
    height: 90%;
}
.el-card{
    height: 100%;
}
</style>