<template>
    <div class="showmaintain">
        <el-card>
            <el-row justify="center">
                <el-col :span="10">
                    <el-row style="height: 460px;">
                        <el-table :data="tableData" style="width: 100%" max-height="450">
                            <el-table-column prop="mid" label="维修人员id" width="150" />
                            <el-table-column prop="wid" label="财产id" width="120" />
                            <el-table-column prop="wealthName" label="物品名" width="120" />
                            <el-table-column prop="maintainTime" label="维修时间" width="120" />
                        </el-table>
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
            pageInfo: {},
            tableData: [],
        }
    }, methods: {
        deleteRow(index, rows) {
            rows.splice(index, 1);
        },
        handleCurrentChange(val) {
            this.tableData = [];
            this.axios.get("/api/maintain/maintained/show/" + val + "/" + this.pageInfo.pageSize)
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
    },
    created() {
        this.axios.get("/api/maintain/maintained/show/1/10")
            .then(response => {
                console.log(response);
                let info = response.data;
                if (info["code"] === 200) {
                    this.tableData = info.data;
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
    },
}
</script>
<style scoped>
.showmaintain {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>