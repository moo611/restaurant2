import { createApp } from 'vue'
//import './style.css'
import App from './App.vue'
import 'amfe-flexible'
import 'vant/lib/index.css'; // 引入 Vant 样式
import router from "./router"; // 引入 router 配置
import { Toast, Button, Form, Field,Radio,RadioGroup,Dialog,List,Cell,Image } from "vant";

const app = createApp(App);

app.use(router);
app.use(Toast);
app.use(Button);
app.use(Form);
app.use(Field);
app.use(Radio);
app.use(RadioGroup);
app.use(Dialog);
app.use(List);
app.use(Cell)
app.use(Image)
app.mount("#app");
