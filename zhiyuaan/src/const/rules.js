
export default {
  username: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { min: 1, max: 16, message: '长度在 1 到 16 个字符', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '邮箱不能为空', trigger: 'blur' },
    { validator: (rule, value, callback) => {
      let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    },
    message: '请输入合法的email',
    trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '手机号不能为空', trigger: 'blur' },
    { validator: (rule, value, callback) => {
      let reg = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    },
    message: '请输入合法的手机号',
    trigger: 'blur' }
  ],
  realname: [
    { required: true, message: '真实姓名不能为空', trigger: 'blur' },
    { validator: (rule, value, callback) => {
      let reg = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    },
    message: '请输入真实姓名',
    trigger: 'blur' }
  ],
  idnum: [
    { required: true, message: '请选择活动区域', trigger: 'change' },
    { validator: (rule, value, callback) => {
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    },
    message: '请输入正确的身份证号',
    trigger: 'blur' }
  ],
  school: [
    { required: true, message: '请输入学校名', trigger: 'blur' }

  ]
}
