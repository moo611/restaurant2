import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000, // 设置前端开发服务器端口为 3000
    proxy: {
      // 本地代理配置
      '/api/': {
        target: 'http://localhost:8081/',  // 指向后端服务的地址
        changeOrigin: true,               // 跨域时需要设置
        rewrite: (path) => path.replace(/^\/api/, ''), // 将请求路径中的 /api 去掉
      },
    },
    watch: {
      usePolling: true, // 开启文件轮询，解决某些环境下无法监听文件变化的问题
    },
    hmr: true,  // 确保 HMR (Hot Module Replacement) 开启
  },
})
