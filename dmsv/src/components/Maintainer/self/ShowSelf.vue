<template>
    <div class="showself">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="20">
                    <el-descriptions title="用户信息">
                        <el-descriptions-item label="id:">{{ tableDate.mid }}</el-descriptions-item>
                        <el-descriptions-item label="姓名:">{{ tableDate.name }}</el-descriptions-item>
                        <el-descriptions-item label="手机号:">{{ tableDate.phone }}</el-descriptions-item>
                        <el-descriptions-item label="维修次数:">{{ tableDate.workNum }}</el-descriptions-item>
                    </el-descriptions>
                </el-col>
            </el-row>

        </el-card>
    </div>
</template>
<script>
export default {
    data() {
        return {
            tableDate: {}
        }
    },
    created() {
        this.axios.get("/api/maintain/self/show")
            .then(response => {
                console.log(response);
                this.tableDate = response.data;
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
    position: relative;
    height: 90%;
}

.el-card {
    height: 100%;
}
</style>