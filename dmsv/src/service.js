import axios from "axios";
import { getToken } from "./utils/Token";
import { ElMessage  } from "element-plus";
const service=axios.create({
    baseURL:'http://localhost:8080',//baseURL自动加在请求地址上
    timeout:5000
})
//请求拦截器
service.interceptors.request.use((config)=>{
    //请求之前做什么
    config.headers.token=getToken('token');
    return config;
},(error)=>{
    return Promise.reject(error)
})
//响应拦截器
service.interceptors.response.use((response)=>{
    //对响应数据做什么
    let { status,message }=response.data;
    if(status!=200){
        ElMessage({
            showClose: true,
            message: message||'Warning, this is a warning message.',
            type: 'warning',
          })
    }
    return response;
},(error)=>{
    return Promise.reject(error)
})
export default axios