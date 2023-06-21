<template>
    <div class="showdorm">
        <el-card>
            <el-row justify="center" style="margin-top: 50px;">
                <el-col :span="20">
                    <el-descriptions title="宿舍信息">
                        <el-descriptions-item label="楼号:">{{ dorm.bid }}</el-descriptions-item>
                        <el-descriptions-item label="宿舍号:">{{ dorm.did }}</el-descriptions-item>
                        <el-descriptions-item label="宿舍楼名:">{{ dorm.buildingName }}</el-descriptions-item>
                        <el-descriptions-item label="床数:">{{ dorm.bedNum }}</el-descriptions-item>
                        <el-descriptions-item label="水电费:">{{ dorm.waterAndElectricity }}</el-descriptions-item>
                        <el-descriptions-item label="是否缴费:">{{ dorm.pay }}</el-descriptions-item>
                        <el-descriptions-item label="卫生评分:">{{ dorm.health }}</el-descriptions-item>
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
            dorm: {
                bid: 0,
                did: 0,
                buildingName: '',
                bedNum: 0,
                waterAndElectricity: 0.0,
                pay: false,
                health: 0,
            }
        }
    },
    created() {
        this.axios.get("/api/student/dorm/show").then(response => {
            this.dorm = response.data;
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
</style>