import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import pxtorem from 'postcss-pxtorem';
// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  css: {
    postcss: {
      plugins: [
        pxtorem({
          rootValue: 37.5, // 这里写设计稿的宽度/10即可，例如设计稿宽度是750px就写75

          propList: ["*"], // 需要转换的属性，默认转换所有属性
          selectorBlackList: [], // CSS选择器黑名单，防止部分选择器被转换
          exclude: /\/node_modules\//i, // 忽略包文件转换rem
        }),
      ],
    },
  },
  server: {
    host:'0.0.0.0',
    port: 3001, // 设置前端开发服务器端口为 3001
    proxy: {
      // 本地代理配置
      "/api/": {
        target: "http://192.168.1.15:8081/", // 指向后端服务的地址
        changeOrigin: true, // 跨域时需要设置
        rewrite: (path) => path.replace(/^\/api/, ""), // 将请求路径中的 /api 去掉
      },
    },
    watch: {
      usePolling: true, // 开启文件轮询，解决某些环境下无法监听文件变化的问题
    },
    hmr: true, // 确保 HMR (Hot Module Replacement) 开启
  },
});
