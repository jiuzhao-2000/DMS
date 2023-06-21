<template>
    <div class="breadcrumb">
        <el-card>
            <el-breadcrumb separator=">">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-for="(item , index) in $route.matched" :key="index">
                {{ item.meta.name }}
            </el-breadcrumb-item>
        </el-breadcrumb>
        </el-card>
    </div>
</template>
<style scoped>
.breadcrumb{
    height:10%;
}
</style>