import axios from "./axios";
//登录
export const getLogin = (param) => {
	return axios.request({
		url: 'http://localhost:8081/user/login',
		method: 'post',
		data: param
	})
}
// 查询个人信息
export const selectUserInfo = (userId) => {
	return axios.request({
		url: 'http://localhost:8081/user/selectUserInfo',
		method: 'get',
		params: { userId: userId }
	})
}
// 修改个人信息
export const updUserInfo = (param) => {
	return axios.request({
		url: 'http://localhost:8081/user/updUserInfo',
		method: 'post',
		data: param
	})
}
//修改密码
export const updUserPwd = (userId, userPwd) => {
	return axios.request({
		url: 'http://localhost:8081/user/updUserPwd',
		method: 'get',
		params: { userId: userId, userPwd: userPwd }
	})
}
//条件查询并获取班级通知
export const getAllInformByUserId = (userId, pageNum, pageSize, informTitle) => {
	return axios.request({
		url: 'http://localhost:8081/inform/getAllInformByUserId',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, informTitle: informTitle }
	})
}
// 学生提交每日健康信息
export const insertHealthInfo = (param) => {
	return axios.request({
		url: 'http://localhost:8081/health/insertHealthInfo',
		method: 'post',
		data: param
	})
}
//学生查询每日健康记录
export const getDayHealthInfo = (userId, pageNum, pageSize, inputStr, datetime1, datetime2) => {
	return axios.request({
		url: 'http://localhost:8081/health/getDayHealthInfo',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, location: inputStr, datetime1: datetime1, datetime2: datetime2 }
	})
}
// 图表1
export const members1 = () => {
	return axios.request({
		url: 'http://localhost:8081/health/members1',
		method: 'get',
	})
}
// 图表2
export const members2 = () => {
	return axios.request({
		url: 'http://localhost:8081/health/members2',
		method: 'get',
	})
}
// 查询面向全体学生的通知
export const getStudentInfo = () => {
	return axios.request({
		url: 'http://localhost:8081/inform/getStudentInfo',
		method: 'get',
	})
}
//根据健康信息的id删除
export const deleteInfoByHealthId = (param) => {
	return axios.request({
		url: 'http://localhost:8081/health/deleteInfoByHealthId',
		method: 'post',
		data: param
	})
}
//学生提交请假申请
export const insertLeaveInfo = (param) => {
	return axios.request({
		url: 'http://localhost:8081/leave/insertLeaveInfo',
		method: 'post',
		data: param
	})
}
//获取全部班级
export const selectAllClasses = () => {
	return axios.request({
		url: 'http://localhost:8081/classes/selectAllClasses',
		method: 'get',
	})
}
//获取自己的请假记录
export const getLeaveMeInfo = (userId, pageNum, pageSize, inputStr, datetime1, datetime2) => {
	return axios.request({
		url: 'http://localhost:8081/leave/getLeaveMeInfo',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, leaveType: inputStr, datetime1: datetime1, datetime2: datetime2 }
	})
}
//根据请假id删除记录
export const deleteInfoByLeaveId = (param) => {
	return axios.request({
		url: 'http://localhost:8081/leave/deleteInfoByLeaveId',
		method: 'post',
		data: param
	})
}
//老师发布通知
export const insertInformInfoS = (param) => {
	return axios.request({
		url: 'http://localhost:8081/inform/insertInformInfoS',
		method: 'post',
		data: param
	})
}
// 老师获取本班学生信息
export const getStudentInfoS = (userId, pageNum, pageSize, inputClass, inputName) => {
	return axios.request({
		url: 'http://localhost:8081/user/getStudentInfoS',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, inputClass: inputClass, inputName: inputName }
	})
}
//老师根据学生id删除
export const deleteUserByIdS = (param) => {
	return axios.request({
		url: 'http://localhost:8081/user/deleteUserByIdS',
		method: 'post',
		data: param
	})
}
//根据老师id查负责的班级
export const getClassesByUserId = (userId) => {
	return axios.request({
		url: 'http://localhost:8081/classes/getClassesByUserId',
		method: 'get',
		params: { userId: userId }
	})
}
//老师查看学生健康信息
export const getStuHealthInfoS = (userId, pageNum, pageSize, inputClass, inputName, datetime1, datetime2) => {
	return axios.request({
		url: 'http://localhost:8081/health/getStuHealthInfoS',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, inputClass: inputClass, inputName: inputName, datetime1: datetime1, datetime2: datetime2 }
	})
}
//老师审核假条记录
export const getAllLeaveInfoByS = (userId, pageNum, pageSize, inputClass, inputName) => {
	return axios.request({
		url: 'http://localhost:8081/leave/getAllLeaveInfoByS',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, inputClass: inputClass, inputName: inputName }
	})
}
//老师查看每日提交进度members3
export const members3 = (userId) => {
	return axios.request({
		url: 'http://localhost:8081/health/members3',
		method: 'get',
		params: { userId: userId }
	})
}
//老师审核学生假条
export const UpdStuLeaveInfo = (leaveId, leaveState, leaveNo) => {
	return axios.request({
		url: 'http://localhost:8081/leave/UpdStuLeaveInfo',
		method: 'get',
		params: { leaveId: leaveId, leaveState:leaveState, leaveNo: leaveNo }
	})
}
// 老师查询已审批过的假条
export const getStateLeaveInfo = (userId, pageNum, pageSize, inputClass, inputName) => {
	return axios.request({
		url: 'http://localhost:8081/leave/getStateLeaveInfo',
		method: 'get',
		params: { userId: userId, pageNum: pageNum, pageSize: pageSize, inputClass: inputClass, inputName: inputName }
	})
}