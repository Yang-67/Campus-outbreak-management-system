import axios from "./axios";
//登录
export const getLogin =(param)=>{
	return axios.request({
		url:'http://localhost:8081/user/login',
		method:'post',
		data:param
	})
}
// 查询个人信息
export const selectUserInfo =(userId)=>{
	return axios.request({
		url:'http://localhost:8081/user/selectUserInfo',
		method:'get',
		params:{userId:userId}
	})
}
// 修改个人信息
export const updUserInfo =(param)=>{
	return axios.request({
		url:'http://localhost:8081/user/updUserInfo',
		method:'post',
		data:param
	})
}
//修改密码
export const updUserPwd =(userId,userPwd)=>{
	return axios.request({
		url:'http://localhost:8081/user/updUserPwd',
		method:'get',
		params:{userId:userId,userPwd:userPwd}
	})
}