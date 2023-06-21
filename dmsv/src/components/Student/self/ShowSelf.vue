<template>
    <div class="showself">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="18">
                    <el-descriptions title="用户信息">
                        <el-descriptions-item label="学号:">{{ sid }}</el-descriptions-item>
                        <el-descriptions-item label="姓名:">{{ name }}</el-descriptions-item>
                        <el-descriptions-item label="手机号:">{{ phone }}</el-descriptions-item>
                        <el-descriptions-item label="性别:">{{ sex }}</el-descriptions-item>
                        <el-descriptions-item label="宿舍楼:">{{ buildingName }}</el-descriptions-item>
                        <el-descriptions-item label="宿舍号:">{{ did }}</el-descriptions-item>
                        <el-descriptions-item label="床号:">{{ bedId }}</el-descriptions-item>
                        <el-descriptions-item label="状态:">{{ state }}</el-descriptions-item>
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
            sid: "",
            name: "",
            phone: "",
            sex: "",
            buildingName: "",
            did: 0,
            bedId: 0,
            state: "",
        }
    },
    created() {
        this.axios.get("/api/student/self/query")
            .then(response => {
                let info = response.data;
                this.sid = info["sid"];
                this.name = info["name"];
                this.phone = info["phone"];
                this.sex = info["sex"];
                this.buildingName = info["buildingName"];
                this.did = info["did"];
                this.bedId = info["bedId"];
                this.state = info["state"];
            }
            ).catch(function (error) {
                console.log(error);
            })

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