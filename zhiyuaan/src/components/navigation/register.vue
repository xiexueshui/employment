<template>

<el-form ref="form" :model="form" :rules="rules" label-width="80px">
  <el-form-item label="身份" prop="idtentity"  style="width:65%">
    <el-select v-model="form.idtentity" placeholder="请选择你的身份">
                <el-option

                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
    </el-select>

  </el-form-item>
  <el-form-item label="手机号" prop="phone">
    <el-input v-model="form.phone" style="width:60%" placeholder="请输入手机号"
></el-input>

  </el-form-item>
  <el-form-item label="获取验证码">
    <el-input style="width:40%" v-model="form.verification " placeholder="输入验证码"></el-input>
    <el-button @click="getverigyclick">{{getverigy}}</el-button>
  </el-form-item>
  <el-form-item label="密码" prop="password">
     <el-input v-model="form.password" style="width:60%" placeholder="请填写6-16数字加字母" ></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="repassword">
     <el-input v-model="form.repassword" style="width:60%"></el-input>
  </el-form-item>
  <el-form-item>
     <el-button type="primary" @click="submitForm('form')">注册</el-button>
    <el-button @click="resetForm('form')">重置</el-button>
  </el-form-item>
</el-form>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    let validphone = (rule, value, callback) => {
      let reg = /^1[34578]\d{9}$/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    }
    let validpassword = (rule, value, callback) => {
      let reg = /^.*(?=.{6,16})(?=.*\d)(?=.*[a-zA-Z]).*$/
      if (!reg.test(value)) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    }
    let validrepassword = (rule, value, callback) => {
      if (this.form.password !== this.form.repassword) {
        callback(new Error('账号必须是由4-9位数字和字母组合'))
      } else {
        callback()
      }
    }
    return {
      options: [{
        value: '0',
        label: '学生'
      }, {
        value: '1',
        label: '老师'
      }],
      form: {
        phone: '',
        verification: '',
        password: '',
        repassword: '',
        idtentity: '0'
      },
      rules: {

        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
          { validator: validphone, message: '请输入合法的手机号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { validator: validpassword, message: '请填写6-16数字加字母', trigger: 'blur' }
        ],
        repassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' },
          { validator: validrepassword, message: '二次密码不一样', trigger: 'blur' }

        ]

      },
      getverigy: '获取验证码'
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.get('/user/register.do', {params: {'condition': JSON.stringify(this.form)}})
            .then(function (response) {
              console.log(response)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },

    getverigyclick () {
      let count = 60
      var that = this
      axios.get('/user/sendverification.do', {params: {'condition': JSON.stringify(this.form)}})
        .then(function (response) {
          if (response.data.code === 0) {
            var timer = setInterval(() => {
              if (count > 0 && count <= 60) {
                that.getverigy = '还剩' + count-- + '秒'
              } else {
                clearInterval(timer)
              }
            }, 1000)
          }
        })
    }

  }
}
</script>
