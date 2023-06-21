<template>
    <div class="main">
        <el-container>
            <MainHeader />

            <el-container class="mainBody">
                <el-aside width="200px">
                    <AdministratorAside />
                </el-aside>
                <el-container>
                    <el-main>
                        <Breadcrumb />
                        <router-view></router-view>
                    </el-main>
                    <el-footer>
                        <MainFooter />
                    </el-footer>
                </el-container>
            </el-container>
        </el-container>

    </div>
</template>
<script>
import AdministratorAside from '@/components/Administrator/AdministratorAside.vue'
import MainHeader from '@/components/commons/MainHeader.vue'
import MainFooter from '@/components/commons/MainFooter.vue'
import Breadcrumb from '@/components/commons/Breadcrumb.vue'
export default {
    name: 'AdminiMain',
    components: { AdministratorAside, MainHeader, MainFooter, Breadcrumb },
}
</script>
<style >
* {
    margin: 0;
    padding: 0;
}

.main {
    height: 100%;
    width: 100%;
}

.mainBody {
    position: absolute;
    width: 100%;
    top: 60px;
    height: 100%;
}

.el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    height: 100%;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
}

body>.el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}</style>