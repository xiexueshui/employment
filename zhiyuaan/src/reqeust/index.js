import axios from 'axios'

import { Message } from 'element-ui'
import router from '@/router'

// 导出request方法，供其它地方使用
export function request (config) {
  const instance = axios.create()

  instance.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

  // ------------------请求拦截-------------------//
  // ------------在发送请求之前做些什么------------//
  instance.interceptors.request.use(config => {
    // 若存在token则带token
    let token = null
    const user = window.localStorage.getItem('user')
    if (user) {
      token = JSON.parse(user).token
    }

    if (token) {
      config.headers.token = token
    }
    console.log(config)
    if (!token && config.url !== '/user/login' && config.url !== '/user/register' && config.url !== '/user/logout') {
      Message({
        message: '用户未登录请登录',
        type: 'warning'
      })
      router.push('/login')
    }
    // 放行
    return config
  }, err => {
    console.log('请求拦截=>', err)
    return err
  })

  // ------------------响应拦截-------------------//
  // -------------对响应数据做点什么-------------//
  instance.interceptors.response.use(res => {
    // 例：后端数据处理错误，并返回错误原因；前端获取错误原因并展示
    console.log('响应拦截=>', res.data)
    if (res.data.code !== 0) {
      Message({
        message: res.data.message + '，请重试！',
        type: 'warning'
      })
    }
    return res ? res.data : res
  }, err => {
    if (err.response.status === 401) {
      router.push('/login')
    }
    console.log(err.response.status)
    console.log('响应拦截错误完整信息=>', err.response)
    // 也可以在这里对不同的错误码做不同的展示处理
    throw err
  })

  return instance(config)
}
