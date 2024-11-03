import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import router from './router'; // 引入 router 配置
import ElementPlus from 'element-plus'; // 引入 Element Plus
import 'element-plus/dist/index.css'; // 引入 Element Plus 的 CSS
// createApp(App).use(router).mount('#app')


const app = createApp(App);

app.use(router);
app.use(ElementPlus); // 使用 Element Plus
app.mount('#app');
