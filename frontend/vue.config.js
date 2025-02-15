const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
     port: 8081,
     proxy: {
       '/api': {
         target: 'http://localhost:8080',
         ws: true,
         changeOrigin: true
       }
     }
   },
   // Change build paths to make them Maven compatible
   // see https://cli.vuejs.org/config/
   outputDir: 'target/dist',
   assetsDir: 'static'
})
