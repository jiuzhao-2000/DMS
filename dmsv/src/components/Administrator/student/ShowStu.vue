<template>
    <div class="showstu">
        <el-card>
            <el-row :gutter="10">
                <el-col :span="24">
                    <el-tabs tab-position="top" v-model="tabselect" style="height: 100px" class="demo-tabs">
                        <el-tab-pane label="按姓名查找" name="first">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>姓名:</span>
                                </el-col>
                                <el-col :span="16">
                                    <el-input v-model="inputName" placeholder="请输入学生姓名" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="queryName(this.pageInfo.pageNum)" round
                                        :disabled="!open">查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="按楼名查找" name="second">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>楼名:</span>
                                </el-col>
                                <el-col :span="16">
                                    <el-input v-model="inputBName" placeholder="请输入宿舍楼名" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="queryBName(this.pageInfo.pageNum)" round
                                        :disabled="!open">查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="按学号查找" name="third">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>学号:</span>
                                </el-col>
                                <el-col :span="16">
                                    <el-input v-model="inputSid" placeholder="请输入学生学号" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="querySid" round :disabled="!open">查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                        <el-tab-pane label="按学号姓名查找" name="fourth">
                            <el-row :gutter="10">
                                <el-col :span="4">
                                    <span>学号:</span>
                                </el-col>
                                <el-col :span="6">
                                    <el-input v-model="inputSid" placeholder="请输入学生学号" />
                                </el-col>
                                <el-col :span="4">
                                    <span>姓名:</span>
                                </el-col>
                                <el-col :span="6">
                                    <el-input v-model="inputName" placeholder="请输入学生姓名" />
                                </el-col>
                                <el-col :span="4">
                                    <el-button @click="querySidName(this.pageInfo.pageNum)" round
                                        :disabled="!open">查找</el-button>
                                </el-col>
                            </el-row>
                        </el-tab-pane>
                    </el-tabs>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :span="4">
                    <span>是否开启条件查询</span>
                </el-col>
                <el-col :span="6">
                    <el-switch v-model="open" class="ml-2"
                        style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949" />
                </el-col>
            </el-row>
            <el-row justify="center" style="height: 350px;">
                <el-col :span="22">
                    <el-table :data="tableData" max-height="340">
                        <el-table-column fixed prop="sid" label="学号" width="150" />
                        <el-table-column prop="name" label="姓名" width="120" />
                        <el-table-column prop="sex" label="性别" width="120" />
                        <el-table-column prop="buildingName" label="宿舍楼" width="120" />
                        <el-table-column prop="did" label="宿舍号" width="120" />
                        <el-table-column prop="bedId" label="床号" width="120" />
                        <el-table-column prop="state" label="状态" width="120" />
                        <el-table-column prop="phone" label="手机号" width="120" />
                        <el-table-column fixed="right" label="Operations" width="120">
                            <template #default="scope">
                                <el-button link type="primary" size="small"
                                    @click.prevent="handleClick(scope.$index)">修改</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>
            <el-row :gutter="10">
                <el-col :offset="8">
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
            inputSid: "",
            inputName: "",
            inputBName: "",
            tabselect: "first",
            open: false,
        }
    },
    methods: {
        handleClick(val) {
            console.log(val);
            let s = this.tableData[val];
            this.$router.push({
                name: "administratorstualter",
                query: {
                    sid: s.sid,
                    name: s.name,
                    sex: s.sex,
                    buildingName: s.buildingName,
                    did: s.did,
                    bedId: s.bedId,
                    state: s.state,
                    phone: s.phone,
                }
            });
        },
        handleCurrentChange(val) {
            if (!this.open) {
                this.axios.get("/api/admin/stu/show/" + val + "/" + this.pageInfo.pageSize)
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
                    ).catch(error => {
                        console.log(error);
                    })
                return;
            }
            switch (this.tabselect) {
                case "first":
                    this.queryName(val);
                    break;
                case "second":
                    this.queryBName(val);
                    break;
                case "third":
                    this.querySid();
                    break;
                case "fourth":
                    this.querySidName(val);
                    break;
                default:
                    console.log("没有条件的查询");
                    break;
            }
        },
        queryName(val) {
            this.axios.get("/api/admin/stu/show/sName/" + this.inputName + "/" + val + "/" + this.pageInfo.pageSize)
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
                ).catch(error => {
                    console.log(error);
                })
        },
        queryBName(val) {
            this.axios.get("/api/admin/stu/show/bName/" + this.inputBName + "/" + val + "/" + this.pageInfo.pageSize)
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
                ).catch(error => {
                    console.log(error);
                })
        },
        querySid() {
            this.axios.get("/api/admin/stu/show/sid/" + this.inputSid)
                .then(response => {
                    console.log(response);
                    let info = response.data;
                    this.tableData = [info.data];
                    if (info.code == 200) {
                        this.pageInfo = {
                            total: 1,
                            pageSize: this.pageInfo.pageSize,
                            pageNum: info.pageInfo["pageNum"]
                        };

                    } else {
                        this.pageInfo = {
                            total: 1,
                            pageSize: this.pageInfo.pageSize,
                            pageNum: info.pageInfo["pageNum"]
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
        querySidName(val) {
            console.log(this.inputSid);
            this.axios.get("/api/admin/stu/show/sidName/" + this.inputSid + "/" + this.inputName + "/" + val + "/" + this.pageInfo.pageSize)
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
    },
    created() {
        this.axios.get("/api/admin/stu/show/1/7")
            .then(response => {
                console.log(response);
                let info = response.data;
                this.tableData = info.data;
                if (info.code === 200) {
                    ElMessage({
                        showClose: true,
                        message: info["message"],
                        type: 'success',
                    });
                    this.pageInfo = {
                        total: info.pageInfo["total"],
                        pageSize: info.pageInfo["pageSize"],
                        pageNum: info.pageInfo["pageNum"]
                    };
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
    }
}
</script>
<style scoped>
.showstu {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>