<template>
  <div>
    <a v-show="imgshow" @click="dialogTableVisible = true">登录|注册</a>
    <el-submenu index="2-4" v-show="!imgshow">
      <template slot="title">
        <img
          v-bind:src="user.imgurl"
          style="background-color: white;border-radius:50%"
          height="30em"
          width="30em"
        >
      </template>
      <el-menu-item index="/myaccount">账号修改</el-menu-item>

      <el-menu-item @click.native="layout">退出</el-menu-item>
    </el-submenu>
    <el-dialog :visible.sync="dialogTableVisible">
      <el-tabs style="margin-top: -30px">
        <el-tab-pane label="登录">

          <el-row>
            <el-col :offset="6" :span="5">
              <el-input v-model="user.phone" style="width:250%" placeholder="请输入手机号"></el-input>
            </el-col>
          </el-row>
          <el-row>
            <el-col :offset="6" :span="5">
              <el-input
                v-model="user.password"
                type="password"
                style="width:250%"
                placeholder="请输入密码"
              ></el-input>
            </el-col>
          </el-row>

          <el-row>
            <el-col :offset="7" :span="4">
              <el-button type="success" style="width:250%" @click="login">登录</el-button>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="注册">
          <register></register>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import Vue from 'vue'
import SlideVerify from 'vue-monoplasty-slide-verify'
import register from './register'
import axios from 'axios'
import md5 from 'js-md5'
import { delCookie, getCookie, setCookie } from '@/utils/util.js'

Vue.use(SlideVerify)

export default {
  components: { register },
  data () {
    return {
      imgshow: true,

      dialogTableVisible: false,
      user: {
        phone: '',
        password: '',
        idtentity: ''
      },
      view: true
    }
  },
  mounted () {
    this.islogin()
  },
  methods: {
    onSuccess () {
      this.view = false
    },
    onFail () {
      this.msg = ''
    },
    onRefresh () {
      this.msg = ''
    },
    Select () {
      this.$router.push('/myaccount')
    },
    layout () {
      this.imgshow = true
      delCookie('token')
      this.$router.push('/')
    },
    login () {
      var that = this
      this.user.password = md5(this.user.password)
      axios
        .get('/user/login.do', { params: { user: JSON.stringify(this.user) } })
        .then(function (response) {
          if (response.data.code === 0) {
            that.user = response.data.data
            that.imgshow = false
            that.dialogTableVisible = false
            setCookie('token', response.headers.token)
            localStorage.setItem('user', JSON.stringify(that.user))

            if (response.data.data.idtentity === '1') {
              debugger
              that.$router.push('/course_publish')
            } else if (response.data.data.idtentity === '2') {
              that.$router.push('/manager_index')
            }
            // that.$store.dispatch('commitUserList', that.user)
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    /** 判断是否登录 */
    islogin () {
      var that = this
      if (getCookie('token') === null || getCookie('token') === undefined) {
      } else {
        axios
          .get('/user/getuser.do', {
            headers: { token: getCookie('token') },
            params: { token: getCookie('token') }
          })
          .then(function (response) {
            that.imgshow = false
            that.user = response.data
            localStorage.setItem('user', JSON.stringify(that.user))
          })
          .catch(function (error) {
            console.log(error)
          })
      }
    }
  }
}
</script>

<style>
.dialog {
  margin-top: -20px;
}
</style>
