import { createApp } from 'vue';
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import locale from 'element-plus/lib/locale/lang/zh-cn';
import 'font-awesome/css/font-awesome.min.css'
import router from './router'
import axios from 'axios';
import VueAxios from 'vue-axios';

router.beforeEach((to, from, next) => {
    console.log(to.path);
    if (to.path!='/'&&(localStorage.getItem("name") === null || localStorage.getItem("name") === '')) {
        window.location.href='/';
    }
    else {
        next();
    }
})
const app = createApp(App)
.use(ElementPlus, { locale })
.use(VueAxios, axios)
.use(router)
.mount('#app');
