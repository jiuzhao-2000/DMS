const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({

  transpileDependencies: true,

  lintOnSave: false,

  devServer: {
    host: 'localhost',
    port: 8080,
    open: true,
    proxy: {
      '/api': {
        target: 'http://localhost:9901/api/',
        ws:true,//代理websocked
        changeOrigin:true,//虚拟的站点需要更管origin
        secure: true,                   //是否https接口
        pathRewrite: {
          '^/api': ''
        },
        headers: {
          referer: 'http://localhost:9901/api/', //这里后端做了拒绝策略限制，请求头必须携带referer，否则无法访问后台
        }
      }
    }
  }

})