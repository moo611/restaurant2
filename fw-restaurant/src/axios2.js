import axios from 'axios';
import { ElMessage } from 'element-plus'; // 确保你已经安装并引入了 Element Plus
import { clear } from './utils/auth';
import router from './router';
// 创建 axios 实例
const instance = axios.create({
  // baseURL: 'http://localhost:3000', // 基础URL
  baseURL:'/api/',
  timeout: 10000,
  headers: { 'X-Custom-Header': 'foobar' },
});


// 响应拦截器
instance.interceptors.response.use(
  (response) => {
    const res = response.data;

    if (res.code === 200) {
      // 如果 code 为 200，返回一个成功的 Promise，结果是 res.data
      return Promise.resolve(res.data);
    }else if(res.code === 401 || res.code === 403){
      router.replace('/login')
      clear()
      return Promise.reject("token 过期或失效");
    } else {
      // 其他情况，弹出错误消息
      ElMessage.error(res.msg || 'Error');
      // 返回一个拒绝的 Promise，结果是错误信息
      return Promise.reject(new Error(res.msg || 'Error'));
    }
  },
  (error) => {
    // 如果发生网络错误或其他异常，弹出错误消息
    ElMessage.error(error.message || '网络错误');
    return Promise.reject(error);
  }
);

export default instance;
