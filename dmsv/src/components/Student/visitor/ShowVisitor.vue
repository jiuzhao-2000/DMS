<template>
  <div class="showVisitor">
    <el-card>
      <el-row justify="center" style="height:480px;">
        <el-col :span="20">
          <el-table :data="tableData" style="width: 100%" max-height="400">
            <el-table-column fixed prop="vid" label="访客号" width="200" />
            <el-table-column prop="name" label="姓名" width="120" />
            <el-table-column prop="stuName" label="拜访学生" width="120" />
            <el-table-column prop="visitTime" label="拜访时间" width="120" />
            <el-table-column prop="visitedTime" label="结束时间" width="120" />
            <el-table-column prop="relationship" label="关系" width="120" />
            <el-table-column prop="phone" label="手机" width="150" />
            <el-table-column fixed="right" label="Operations" width="120">
              <template #default="scope">
                <el-button type="primary" :auto-insert-space=true size="small" @click.prevent="handleClick(scope.$index)">修改</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-row justify="center">
        <el-col :span="5">
          <div class="demo-pagination-block">
            <el-pagination :current-page="pageInfo.pageNum" :page-size="pageInfo.pageSize"
              layout="total, prev, pager, next" :total="pageInfo.total" @current-change="handleCurrentChange" />
          </div>
        </el-col>
      </el-row>
    </el-card>

  </div>
</template>
<script>
export default {
  data() {
    return {
      tableData: [],
      pageInfo: {},
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.tableData = [];
      this.axios.get("/api/student/query/" + val + "/" + this.pageInfo.pageSize)
        .then(
          response => {
            let info = response.data;
            info.data.forEach(element => {
              this.tableData.push({
                vid: element.vid,
                name: element.name,
                stuName: element.stuName,
                visitTime: element.visitTime,
                visitedTime: element.visitedTime,
                relationship: element.relationship,
                phone: element.phone
              })
            });
            this.pageInfo = {
              total: info.pageInfo["total"],
              pageSize: info.pageInfo["pageSize"],
              pageNum: info.pageInfo["pageNum"]
            };
          }
        )
    },
    handleClick(val) {
      let v = this.tableData[val];
      this.$router.push({
        name: "studentvisitalter",
        query: {
          vid: v.vid,
          name: v.name,
          stuName: v.stuName,
          visitTime: v.visitTime,
          visitedTime: v.visitedTime,
          relationship: v.relationship,
          phone: v.phone
        }
      });
    }
  },
  created() {
    this.axios.get("/api/student/query/1/10")
      .then(
        response => {
          let info = response.data;
          info.data.forEach(element => {
            this.tableData.push({
              vid: element.vid,
              name: element.name,
              stuName: element.stuName,
              visitTime: element.visitTime,
              visitedTime: element.visitedTime,
              relationship: element.relationship,
              phone: element.phone
            })
          });
          this.pageInfo = {
            total: info.pageInfo["total"],
            pageSize: info.pageInfo["pageSize"],
            pageNum: info.pageInfo["pageNum"]
          };
        }
      )
      .catch(function (error) {
        console.log(error);
      })
  }
}
</script>
<style scoped>
.showVisitor {
  height: 90%;
}

.el-card {
  height: 100%;
}
</style>