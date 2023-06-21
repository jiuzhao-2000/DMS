<template>
  <div class="mainLogin">
    <el-row type="flex" justify="center" style="padding: 150px 0 0 0;height: 260px;">
      <el-col :span="8">
        <el-row :gutter="12" justify="center" class="card" style="height: 260px;">
          <el-tabs v-model="activeName" type="card" class="demo-tabs" @tab-click="handleClick">
            <el-tab-pane label="学生" name="first">
              <StudentLogin />
            </el-tab-pane>
            <el-tab-pane label="舍管" name="second">
              <AdministratorLogin />
            </el-tab-pane>
            <el-tab-pane label="维修人员" name="third">
              <MaintainerLogin />
            </el-tab-pane>
          </el-tabs>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import StudentLogin from './StudentLogin.vue'
import AdministratorLogin from './AdministratorLogin.vue'
import MaintainerLogin from './MaintainerLogin.vue'
export default {
  name: "MainLogin",
  data() {
    return {
      activeName: 'first'
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  },
  components: { StudentLogin, AdministratorLogin, MaintainerLogin },
}
</script>
<style scoped>
.demo-tabs>.el-tabs__content {
  padding: 32px;
  color: #000000;
  font-size: 32px;
  font-weight: 600;
}
.card{
  background-color: blanchedalmond;
  box-shadow:10px 10px grey;
  border-radius: 20px;
}
.mainLogin {
  height: 100vh;
  background: radial-gradient(
      circle at 60% 90%,
      rgb(32, 243, 9),
      transparent 60%
    ),
    radial-gradient(
      circle at 20px 20px,
      rgba(7, 251, 169, 0.8),
      transparent 25%
    ),
    #06d1ec;
}
</style>