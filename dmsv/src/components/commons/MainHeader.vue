<template>
    <div class="header">
        <el-header>
            <div class="left">宿舍管理系统</div>
            <div class="right">
                <el-tag>{{ name }}</el-tag>
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
            </div>
        </el-header>
    </div>
</template>
<script>
export default {
    name: 'MainHeader',
    data(){
        return {
            name:""
        }
    },
    created(){
        this.name=localStorage.getItem("name");
    }
}
</script>
<style scoped>
.header{
    width: 100%;
    height: 100%;
}
.el-header{
    display: flex;
    /* height: 100%; */
    justify-content: space-between;
    align-items: center;
    background-color: aquamarine;

}

.left{
    display: flex;
    align-items: center;
    font-weight: bold;
    font-size: large;
}

.right{
    display: flex;
    align-items: center;
}
.el-tag{
    margin-right: 5px;
}


</style>