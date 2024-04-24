import axios from 'axios';

// 创建 Axios 实例
const service = axios.create({
    baseURL: '/api', // 设置基础 URL
    timeout: 5000 // 设置请求超时时间
});

// 请求拦截器
service.interceptors.request.use(
    config => {
        // 在发送请求之前做些什么，比如添加请求头
        return config;
    },
    error => {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    response => {
        // 对响应数据做点什么，比如统一处理错误码或数据格式化
        if (response.status === 200) { // 假设服务器返回的数据包含 code 字段用于表示状态
            return response.data; // 返回实际数据
        } else {
            // 处理错误状态码
            return Promise.reject(new Error(response.status || 'Error'));
        }
    },
    error => {
        // 对响应错误做点什么，比如统一处理网络错误或服务器错误
        if (error.response) {
            // 请求已发出，服务器也响应了状态码，但状态码不在 2xx 范围内
            return Promise.reject(error.response.data);
        } else if (error.request) {
            // 请求已发出，但没有收到任何响应
            return Promise.reject(error.request);
        } else {
            // 发送请求时发生了某些事情，导致请求没有能够成功发送
            return Promise.reject(error.message);
        }
    }
);

export default service;