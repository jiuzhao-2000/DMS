<template>
    <div class="showfloor">
        <el-card>
            <el-row :gutter="10">
                <el-col :span="24">
                    <el-tabs tab-position="top" v-model="tabselect" style="height: 100px" class="demo-tabs">
                        <el-tab-pane label="按楼名查找" name="first">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>楼名:</span>
                                </el-col>
                                <el-col :span="16">
                                    <el-input v-model="inputName" placeholder="请输入宿舍楼全名" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="queryName" round >查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="按bid查找" name="second">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>bid:</span>
                                </el-col>
                                <el-col :span="16">
                                    <el-input v-model="inputBid" placeholder="请输入bid" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="queryBid" round >查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="按楼名bid查找" name="third">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>Bid:</span>
                                </el-col>
                                <el-col :span="6">
                                    <el-input v-model="inputBid" placeholder="请输入bid" />
                                </el-col>
                                <el-col :span="4">
                                    <span>楼名:</span>
                                </el-col>
                                <el-col :span="6">
                                    <el-input v-model="inputName" placeholder="请输入宿舍楼全名" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="queryBidName" round >查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                    </el-tabs>
                </el-col>
            </el-row>
            <el-row justify="center">
                <el-col :span="9">
                    <el-table :data="tableData" border style="width: 100%">
                        <el-table-column fixed prop="bid" label="bid" width="120">
                        </el-table-column>
                        <el-table-column prop="name" label="楼名" width="120">
                        </el-table-column>
                        <el-table-column prop="phone" label="手机号" width="120">
                        </el-table-column>
                        <el-table-column prop="location" label="地址" width="120">
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>

        </el-card>
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    methods: {
        queryName() {
            this.axios.get("/api/admin/building/showByName/" + this.inputName)
                .then(response => {
                    console.log(response);
                    let info = response.data;
                    this.tableData = [info.data];
                    if (info["code"] == 200) {
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
        queryBid() {
            this.axios.get("/api/admin/building/showByBid/" + this.inputBid)
                .then(response => {
                    console.log(response);
                    let info = response.data;
                    this.tableData = [info.data];
                    if (info["code"] == 200) {
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
        queryBidName() {
            this.axios.get("/api/admin/building/showByBidName/" + this.inputName + "/" + this.inputBid)
                .then(response => {
                    console.log(response);
                    let info = response.data;
                    this.tableData = [info.data];
                    if (info["code"] == 200) {
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
    },

    data() {
        return {
            tableData: [],
            inputBid: "",
            inputName: "",
            tabselect: "first",
        }
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