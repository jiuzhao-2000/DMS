<template>
    <div class="showfloor">
        <el-card>
            <el-row :gutter="10">
                <el-col :span="4">
                    <span>楼名:</span>
                </el-col>
                <el-col :span="16">
                    <el-input v-model="input" placeholder="Please input" />
                </el-col>
                <el-col :span="4">
                    <el-button @click="query" round>查找</el-button>
                </el-col>
            </el-row>
            <el-row justify="center" style="height: 440px; margin-top: 20px;">
                <el-col :span="14">
                    <el-table :data="tableData" style="width: 100%" max-height="400">
                        <el-table-column fixed prop="bid" label="楼号" width="180" />
                        <el-table-column prop="name" label="姓名" width="180" />
                        <el-table-column prop="phone" label="手机" width="180" />
                        <el-table-column prop="location" label="地址" width="180" />
                    </el-table>
                </el-col>
            </el-row>
            <el-row justify="center">
                <el-col :span="5">
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
            input: '',
            tableData: [],
            pageInfo: {},
        }
    },
    methods: {
        query() {
            if (this.input === null || this.input === '') {
                this.axios.get("/api/student/building/show/" + this.pageInfo.pageNum + "/" + this.pageInfo.pageSize)
                    .then(response => {
                        console.log(response);
                        let info = response.data;
                        if (info["code"] == 200) {
                            this.tableData = info["data"];
                            this.pageInfo = {
                                total: info.pageInfo["total"],
                                pageSize: info.pageInfo["pageSize"],
                                pageNum: info.pageInfo["pageNum"]
                            };
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
                    })
                return;
            }
            this.axios.get("/api/student/building/show/" + this.input + "/" + 1 + "/" + this.pageInfo.pageSize)
                .then(
                    response => {
                        console.log(response);
                        let info = response.data;
                        if (info["code"] == 200) {
                            this.tableData = info["data"];
                            this.pageInfo = {
                                total: info.pageInfo["total"],
                                pageSize: info.pageInfo["pageSize"],
                                pageNum: info.pageInfo["pageNum"]
                            };
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
                    }
                )
                .catch(error => {
                    console.log(error);
                })
        },
        handleCurrentChange(val) {
            this.axios.get("/api/student/building/show/" + this.input + "/" + val + "/" + this.pageInfo.pageSize)
                .then(
                    response => {
                        let info = response.data;
                        if (info["code"] == 200) {
                            this.tableData = info["data"];
                            this.pageInfo = {
                                total: info.pageInfo["total"],
                                pageSize: info.pageInfo["pageSize"],
                                pageNum: info.pageInfo["pageNum"]
                            };
                        } else {
                            ElMessage({
                                showClose: true,
                                message: info["message"],
                                type: 'error',
                            });
                        }

                    }
                )
        },
    },
    created() {
        this.axios.get("/api/student/building/show/" + 1 + "/" + 9)
            .then(response => {
                let info = response.data;
                if (info["code"] == 200) {
                    this.tableData = info["data"];
                    this.pageInfo = {
                        total: info.pageInfo["total"],
                        pageSize: info.pageInfo["pageSize"],
                        pageNum: info.pageInfo["pageNum"]
                    };
                } else {
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
</script>
<style scoped>
.showfloor {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>