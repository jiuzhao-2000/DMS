<template>
    <div class="showrepairlist">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="7">
                    <el-row style="height: 420px;">
                        <el-col>
                            <el-table :data="tableData" style="width: 100%" max-height="360">
                                <el-table-column prop="rid" label="rid" width="120" />
                                <el-table-column prop="repairTime" label="日期" width="120" />
                                <el-table-column fixed="right" label="Operations" width="120">
                                    <template #default="scope">
                                        <el-button link type="primary" size="small"
                                            @click.native.prevent="show(scope.$index, tableData)">
                                            详情
                                        </el-button>
                                    </template>
                                </el-table-column>
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

            <el-dialog v-model="dialogTableVisible" title="订单详细信息">
                <el-row justify="center">
                    <el-col :span="12">
                        <el-table :data="gridData">
                            <el-table-column property="wid" label="wid" width="100" />
                            <el-table-column property="name" label="name" width="120" />
                            <el-table-column property="question" label="question" width="200" />
                        </el-table>
                    </el-col>
                </el-row>
            </el-dialog>
        </el-card>

    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            tableData: [],
            gridData: [],
            dialogTableVisible: false,
            pageInfo: {},
        }
    }, methods: {
        handleCurrentChange(val) {
            this.tableData = [];
            this.axios.get("/api/maintain/repair/show/list/" + val + "/" + this.pageInfo.pageSize)
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
        show(index, rows) {
            this.axios.get("/api/maintain/repair/show/" + rows[index].rid)
                .then(res => {
                    this.gridData = res.data;
                    this.dialogTableVisible = true;
                })
                .catch(error => {
                    console.log(error);
                })
        }
    },
    created() {
        this.axios.get("/api/maintain/repair/show/list/1/8")
            .then(response => {
                let info = response.data;
                this.tableData = info.data;
                if (info.code == 200) {
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
.showrepairlist {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>