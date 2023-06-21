<template>
  <div class="alterself">
    <el-card>
      <el-row justify="center" style="margin-top: 50px;">
        <el-col :span="18">
          <el-form ref="form" :model="form" size="large" label-width="80px">
            <el-form-item label="姓名:">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="电话:">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit" auto-insert-space="true" style="width: 40%;">立即创建</el-button>
              <el-button auto-insert-space="true" style="width: 40%;">取消</el-button>
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
      this.axios.post("/api/admin/self/alter", {
        aid: localStorage.getItem("aid"),
        name: this.form.name,
        phone: this.form.phone
      })
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
        }).catch(error => {
          console.log(error);
        })
    }
  }
}
</script>
<style scoped>
.alterself {
  height: 90%;
}

.el-card {
  height: 100%;
}
</style>