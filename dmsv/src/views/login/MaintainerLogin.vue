<template>
  <div>
    <el-row type="flex" justify="center">
      <h3>Welcome Back!</h3>
    </el-row>
    <el-row type="flex" justify="center">
      <el-form :model="form" status-icon ref="form" label-width="100px" class="demo-ruleForm" :hide-required-asterisk=true>
        <el-form-item label="mid" prop="mid" :rules="[
          { required: true, message: '请输入账号', trigger: 'blur' },
          { max: 10, message: '长度最大10位', trigger: 'blur' },
        ]">
          <el-input type="text" v-model="form.mid" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="pwd" prop="pwd" :rules="[
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 10, message: '长度在6-10位', trigger: 'blur' },
        ]">
          <el-input type="password" v-model="form.pwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login(form)">登入</el-button>
          <el-button @click="reset(form)">重置</el-button>
        </el-form-item>
      </el-form>
    </el-row>

  </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
  name: 'MaintainerLogin',
  data() {
    return {
      form: {
        mid: '',
        pwd: '',
      },
    }
  },
  methods: {
    login(form) {
      [
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.axios.post("/api/login/maintain", { mid: this.form.mid, pwd: this.form.pwd })
              .then(response=> {
                let info = response.data;
                console.log(info);
                if (info["code"] ==200) {
                  localStorage.setItem("mid", info["data"]["mid"]);
                  localStorage.setItem("name", info["data"]["name"]);
                  window.location.href = "/maintainer";
                } else {
                  ElMessage({
                    showClose: true,
                    message: '账号或密码错误',
                    type: 'error',
                  })
                }
              })
              .catch(function (error) {
                console.log(error);
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      ]
    },
    reset(form) {
      [
        form.mid = "",
        form.pwd = ""
      ]
    }
  }
}
</script>
<style scoped></style>