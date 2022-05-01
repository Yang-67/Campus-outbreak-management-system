import axios from "axios"
import config from '../config/index.js'

/*判断环境*/
const baseUrl = process.env.N0DE_ENV === 'development' ? config.baseUrl.dev : config.baseUrl.pro

class HttpRequest {
	constructor(baseUrl) {
		this.baseUrl = baseUrl
	}

	getInsideCongig() {
		const config = {
			baseUrl: this.baseUrl,
			header: {

			}
		}
		return config
	}
	/*拦截器*/
	interceptors(instance) {
		// 添加请求拦截器
		instance.interceptors.request.use(function(config) {
			config.headers['Content-Type'] = 'application/json;charset=utf-8';

			//config.headers['token'] = user.token;  // 设置请求头
			let data = localStorage.getItem("data") ? JSON.parse(localStorage.getItem("data")) : null
			if (data) {
				config.headers['token'] = data.token; // 设置请求头
			}
            
			// 在发送请求之前做些什么
			return config;
		}, function(error) {
			// 对请求错误做些什么
			return Promise.reject(error);
		});

		// 添加响应拦截器
		instance.interceptors.response.use(function(response) {
			// 2xx 范围内的状态码都会触发该函数。
			// 对响应数据做点什么
			return response;
		}, function(error) {
			// 超出 2xx 范围的状态码都会触发该函数。
			// 对响应错误做点什么
			return Promise.reject(error);
		});
	}

	request(options) {
		const instance = axios.create()
		options = {
			...this.getInsideCongig(),
			...options
		}
		this.interceptors(instance)
		return instance(options)
	}
}

export default new HttpRequest(baseUrl)