<template>
    <div class="showself">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="20">
                    <el-descriptions title="用户信息">
                        <el-descriptions-item label="aid:">{{ tableDate.aid }}</el-descriptions-item>
                        <el-descriptions-item label="姓名:">{{ tableDate.name }}</el-descriptions-item>
                        <el-descriptions-item label="手机号:">{{ tableDate.phone }}</el-descriptions-item>
                        <el-descriptions-item label="管理楼:">
                            <template v-for="(item, index) in menus">
                                {{ item }}&nbsp;
                            </template>
                        </el-descriptions-item>
                    </el-descriptions>
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
            tableDate: {},
            menus: []
        }
    },
    created() {
        this.axios.get("/api/admin/self/show")
            .then(response => {
                let info = response.data;
                if (info.code === 200) {
                    this.tableDate = info.data;
                    this.menus = info.data.adminBuildingName;
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
            }).catch(
                error => {
                    console.log(error);
                }
            )
    }
}
</script>
<style scoped>
.showself {
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>