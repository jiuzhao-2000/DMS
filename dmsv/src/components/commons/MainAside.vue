<template>
    <div>
        <el-aside width="200px">
            <el-menu router active-text-color="#ffd04b" background-color="#545c64"
                class="el-menu-vertical-demo" default-active="2" text-color="#fff">
                <template v-for="(item, index) in menus">
                    <el-sub-menu :index="index + ''" :key="index" v-if="!item.hidden">
                        <template #title>
                            <span>{{ item.name }}</span>
                        </template>
                        <el-menu-item-group v-for="(child, index) in item.children" :key="index">
                            <el-menu-item :index="child.path">{{ child.name }}</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>

                </template>
            </el-menu>
        </el-aside>
    </div>
</template>
<script>
export default {
    name: 'MainAside',
    data() {
        return {
            menus: []
        };
    },
    created() {
        this.menus = [...this.$router.options.routes[1].children];
    }
}
</script>
<style scoped></style>