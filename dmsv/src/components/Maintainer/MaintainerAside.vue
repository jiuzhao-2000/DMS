<template>
    <div class="menu">
        <el-aside width="200px">
            <el-menu :unique-opened=true router active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
                default-active="2" text-color="#fff">
                <template v-for="(item, index) in menus">
                    <el-sub-menu :index="index + ''" :key="index" v-if="!item.hidden">
                        <template #title>
                            <i :class="item.icon"></i>
                            <span>{{ item.meta.name }}</span>
                        </template>
                        <el-menu-item-group v-for="(child, index) in item.children" :key="index">
                            <el-menu-item :index="child.path">
                                <i :class="child.icon"></i>
                                {{ child.meta.name }}
                            </el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>

                </template>
            </el-menu>
        </el-aside>
    </div>
</template>
<script>
export default {
    name: 'MaintainerAside',
    data() {
        return {
            menus: []
        };
    },
    created() {
        this.menus = [...this.$router.options.routes[2].children];
    }
}
</script>
<style scoped>
.menu {
  height: 100%;
}

.el-aside {
  height: 100%;
}

.el-menu {
  height: 100%;
}

.el-sub-menu {
  min-width: 0;
}
i{
  margin-right: 5px;
}
.el-menu-item {
  min-width: 0;
}
</style>