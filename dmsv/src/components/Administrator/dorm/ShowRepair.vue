<template>
    <div class="showrepair">
        <el-card>
            <el-row :gutter="20" style="height: 60px;">
                        <el-col :span="4" class="text">
                            <span>bid:</span>
                        </el-col>
                        <el-col :span="6" class="text">
                            <el-input v-model="inputBid" placeholder="请输入bid" />
                        </el-col>
                        <el-col :span="4" class="text">
                            <span>did:</span>
                        </el-col>
                        <el-col :span="6" class="text">
                            <el-input v-model="inputDid" placeholder="请输入did" />
                        </el-col>
                        <el-col :span="4" class="text">
                            <el-button @click="show(1)" round :disabled="!open">查找</el-button>
                        </el-col>
            </el-row>
            <el-row :gutter="20" style="height: 60px;">
                <el-col :span="4">
                    <el-text>是否开启条件查询</el-text>
                </el-col>
                <el-col :span="2">
                    <el-switch v-model="open" class="ml-2"
                        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949" />
                    </el-col>
                </el-row>
            <el-row justify="center">
                <el-col :span="9">
                    <el-row style="height: 370px;">
                        <el-col>
                            <el-table :data="tableData" style="width: 100%" max-height="360">
                                <el-table-column prop="rid" label="rid" width="120" />
                                <el-table-column prop="wid" label="wid" width="120" />
                                <el-table-column prop="name" label="姓名" width="120" />
                                <el-table-column prop="question" label="问题" width="120" />
                                
                            </el-table>
                        </el-col>
                    </el-row>
                    <el-row>
                        <div class="demo-pagination-block">
                            <el-pagination :current-page="pageInfo.pageNum" :page-size="pageInfo.pageSize"
                                layout="total, prev, pager, next" :total="pageInfo.total"
                                @current-change="handleCurrentChange" />
                        </div>
                    </el-row>
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
            pageInfo: {},
            open:false,
            inputBid:'',
            inputDid:'',
        }
    },
     methods: {
        handleCurrentChange(val) {
            this.tableData = [];
            if(this.open){
                this.show(val);
                return;
            }
            this.axios.get("/api/admin/dorm/repair/" + val + "/" + this.pageInfo.pageSize)
                .then(
                    response => {
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
                    }
                )
        },
        show(val) {
            this.axios.get("/api/admin/dorm/repairBid/" +this.inputBid+"/"+this.inputDid+"/"+val+"/"+this.pageInfo.pageSize )
                .then(res => {
                    let info = res.data;
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
                })
                .catch(error => {
                    console.log(error);
                })
        }
    },
    created() {
        this.axios.get("/api/admin/dorm/repair/1/8")
            .then(response => {
                console.log(response);
                let info = response.data;
                this.tableData = info.data;
                console.log(info);
                console.log(info["code"]);
                if (info["code"]=== 200) {
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
                this.pageInfo = {
                    total: info.pageInfo["total"],
                    pageSize: info.pageInfo["pageSize"],
                    pageNum: info.pageInfo["pageNum"]
                };
            }).catch(error => {
                console.log(error);
            })
    },
}
</script>
<style scoped>
.showrepair{
    height: 90%;
}
.el-card{
    height: 100%;
}
</style>