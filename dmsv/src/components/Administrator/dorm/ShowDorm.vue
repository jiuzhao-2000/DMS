<template>
    <div class="showdorm">
        <el-card>
            <el-row class="cardheard">
                <el-col :span="24">
                    <el-row :gutter="20" style="height: 60px;">
                        <el-col :span="4" class="text">
                            <el-text>宿舍楼名:</el-text>
                        </el-col>
                        <el-col :span="16" class="text">
                            <el-input v-model="inputName" placeholder="请输入楼名" />
                        </el-col>
                        <el-col :span="4" class="text">
                            <el-button @click="query(this.pageInfo.pageNum)" round :disabled="!open">查找</el-button>
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
                    <el-row justify="center" style="height: 310px;">
                        <el-col :span="22">
                            <el-table :data="tableData" border style="width: 100%;" max-height="300">
                                <el-table-column fixed prop="bid" label="楼号" width="120">
                                </el-table-column>
                                <el-table-column prop="did" label="宿舍号" width="120">
                                </el-table-column>
                                <el-table-column prop="buildingName" label="宿舍楼名" width="120">
                                </el-table-column>
                                <el-table-column prop="occupancy" label="状态" width="120">
                                </el-table-column>
                                <el-table-column prop="bedNum" label="几人寝室" width="120">
                                </el-table-column>
                                <el-table-column prop="waterAndElectricity" label="水电费" width="200">
                                </el-table-column>
                                <el-table-column prop="pay" label="是否付款" width="120">
                                </el-table-column>
                                <el-table-column prop="health" label="卫生评分" width="120">
                                </el-table-column>
                                <el-table-column fixed="right" label="操作" width="100">
                                    <template slot-scope="scope">
                                        <el-button @click="handleClick(scope.row)" size="small">查看</el-button>
                                        <el-button size="small">编辑</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </el-col>
                    </el-row>

                </el-col>
            </el-row>
            <el-row justify="center">
                <el-col :span="9">
                    <div class="demo-pagination-block">
                        <el-pagination :current-page="pageInfo.pageNum" :page-size="pageInfo.pageSize"
                            layout="total, prev, pager, next" :total="pageInfo.total"
                            @current-change="handleCurrentChange" />
                    </div>
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
            inputName: "",
            open: false,
        }
    },
    methods: {
        handleCurrentChange(val) {
            if (this.open) {
                this.axios.get("/api/admin/dorm/show/" + this.inputName + "/" + val + "/" + this.pageInfo.pageSize)
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
            } else {
                this.axios.get("/api/admin/dorm/show/" + val + "/" + this.pageInfo.pageSize)
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
        handleClick(row) {
            console.log(row);
        },
        query(val) {
            this.axios.get("/api/admin/dorm/show/" + this.inputName + "/" + val + "/" + this.pageInfo.pageSize)
                .then(response => {
                    let info = response.data;
                    this.tableData = info.data;
                    if (info.code === 200) {
                        this.pageInfo = {
                        total: info.pageInfo["total"],
                        pageSize: info.pageInfo["pageSize"],
                        pageNum: info.pageInfo["pageNum"]
                    };
                    }
                    else{
                        this.pageInfo = {
                        total: 0,
                        pageSize: this.pageInfo.pageSize,
                        pageNum: 1
                        };
                        ElMessage({
                            showClose: true,
                            message: info["message"],
                            type: 'error',
                        });
                    }
                    
                    
                }).catch(error => {
                    console.log(error);
                })
        },
    },
    created() {
        this.axios.get("/api/admin/dorm/show/1/6")
            .then(response => {
                let info = response.data;
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
}
</script>
<style scoped>
.showdorm {
    height: 90%;
}

.el-card {
    height: 100%;
}

.cardheard {
    height: 450px;
}
</style>