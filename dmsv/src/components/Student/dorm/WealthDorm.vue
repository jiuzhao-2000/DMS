<template>
    <div class="wealthdorm">
        <el-card>
            <el-row justify="center" style="height: 450px;margin-top: 40px;">
                <el-col :span="15">
                    <el-table :data="tableData" style="width: 100%" max-height="430"
                        @selection-change="handleSelectionChange">
                        <el-table-column fixed type="selection" width="55" />
                        <el-table-column prop="wid" label="标识" width="120" />
                        <el-table-column prop="name" label="名称" width="120" />
                        <el-table-column prop="value" label="价值" width="120" />
                        <el-table-column prop="buildingName" label="宿舍楼" width="120" />
                        <el-table-column prop="did" label="宿舍号" width="120" />
                        <el-table-column fixed="right" label="问题" width="120">
                            <template #default="scope">
                                <el-input placeholder="请输入问题" v-model="scope.row.question" clearable></el-input>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>
            <el-button type="primary" size="large" style="width: 600px;" @click="submit">
                报修
            </el-button>
        </el-card>

    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            tableData: [{
                wid: '0',
                name: '0',
                value: 0.0,
                buildingName: '',
                did: 0,
                question: ''
            }],
            multipleSelection: [],
        }
    },
    methods: {
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        submit() {
            let infos = [];
            this.multipleSelection.forEach((item) => {
                infos.push({ rid: 0, wid: item.wid, name: item.name, question: item.question });
            });
            this.axios.post("/api/student/dorm/repair/add", infos)
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
        }
    },
    mounted() {
        this.axios.get("/api/student/dorm/wealth")
            .then(response => {
                this.tableData = response.data;
            }).catch(function (error) {
                console.log(error);
            })
    }
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