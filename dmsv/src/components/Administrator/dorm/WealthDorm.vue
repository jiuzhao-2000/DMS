<template>
    <div class="wealthdorm">
        <el-card>
            <el-row :gutter="10" style="height: 60px;margin-top: 30px;">
                <el-col :span="4" >
                    <span>楼号:</span>
                </el-col>
                <el-col :span="7" >
                    <el-input v-model="form.bid" placeholder="请输入楼号" />
                </el-col>
                <el-col :span="4" >
                    <span>宿舍号:</span>
                </el-col>
                <el-col :span="7" >
                    <el-input v-model="form.did" placeholder="请输入宿舍号" />
                </el-col>
                <el-col :span="2" >
                    <el-button @click="query(1, this.pageInfo.pageSize)" round>查找</el-button>
                </el-col>
            </el-row>
            <el-row justify="center" style="height: 370px;">
                <el-col :span="18">
                    <el-table :data="tableData" style="width: 100%" max-height="360">
                        <el-table-column fixed type="selection" width="55" />
                        <el-table-column prop="wid" label="标识" width="180" />
                        <el-table-column prop="buildingName" label="宿舍楼" width="180" />
                        <el-table-column prop="did" label="宿舍号" width="180" />
                        <el-table-column prop="name" label="名称" width="180" />
                        <el-table-column prop="value" label="价值" width="180" />
                    </el-table>
                </el-col>
            </el-row>
            <el-row justify="center">
                <el-col :span="4">
                    <div class="demo-pagination-block">
                        <el-pagination :current-page="pageInfo.pageNum" :page-size="pageInfo.pageSize"
                            layout="total, prev, pager, next" :total="pageInfo.total"
                            @current-change="handleCurrentChange" />
                    </div>
                </el-col>
                <el-col :span="2">
                    <el-button type="primary" size="small" @click="submit">
                        报修
                    </el-button>
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
            tableData: [],
            form: {
                bid: 0,
                did: 0,
            },
            select: [],
            pageInfo: {
                pageNum: 1,
                pageSize: 8,
            },
            multipleSelection: [],
        }
    },
    methods: {
        clicka(row) {
            console.log(row)
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        submit() {
            let infos = [];
            this.multipleSelection.forEach((item) => {
                infos.push({ rid: 0, wid: item.wid, name: item.name, question: item.question });
            });
            this.axios.post("/api/admin/dorm/repair/add", infos)
                .then(response => {
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
                }).catch(function (error) {
                    console.log(error);
                })
        },
        handleCurrentChange(val) {
            console.log(this.tableData);
            this.query(val, this.pageInfo.pageSize);
        },
        query(pageNum, pageSize) {
            this.axios.get("/api/admin/dorm/wealth/" + this.form.bid + "/" + this.form.did + "/" + pageNum + "/" + pageSize)
                .then(response => {
                    console.log(response);
                    let info = response.data;
                    if (info.code !== 200) {
                        ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'error',
                        });
                    }
                    this.tableData = info.data;
                    this.pageInfo = {
                        total: info.pageInfo["total"],
                        pageSize: info.pageInfo["pageSize"],
                        pageNum: info.pageInfo["pageNum"]
                    };
                }).catch(error => {
                    console.log(error);
                })
        }
    },
}
</script>
<style scoped>
.wealthdorm {
    height: 90%;
}

.el-card {
    height: 100%;
}


</style>