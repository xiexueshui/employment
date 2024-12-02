<!-- eslint-disable no-tabs -->
<style>
.center {
  height: 1080px;
  background-image: url("../assets/bg.jpg");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

h1 {
  font-size: 30px;
  color: black;
}

.logon {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  /* position: relative;
		overflow: hidden; */
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  margin-top: 20px;
  display: flex;
  background: -webkit-linear-gradient(right, #4284db, #29eac4);

  /* margin-bottom: 200px;
  margin-right: 200px; */
}

.overlaylong {
  border-radius: 10px 0 0 10px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlaylongleft {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(100%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlaylongright {
  border-radius: 10px 0 0 10px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlaytitle {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlaytitleH2 {
  font-size: 30px;
  color: #fff;
  margin-top: 20px;
}

.overlaytitleP {
  font-size: 15px;
  color: #fff;
  margin-top: 20px;
}

.overlaytitleleft {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
}

.overlaytitleright {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(-100%);
  transition: transform 0.6s ease-in-out;
}

.overlaytitle-Signin {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.overlaytitle-Signup {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.buttongohs {
  width: 180px;
  height: 40px;
  border-radius: 50px;
  border: 1px solid #fff;
  color: #fff;
  font-size: 15px;
  text-align: center;
  line-height: 40px;
  margin-top: 40px;
}

.overlaylongH2 {
  font-size: 25px;
  color: black;
  /* width: 250px; */
}

.overlaylong-Signin {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.overlaylong-Signup {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 10px 0;
  width: 240px;
}
h3 {
  font-size: 10px;
  margin-top: 10px;
  cursor: pointer;
}
.inupbutton {
  background-color: #29eac4;
  border: none;
  width: 180px;
  height: 40px;
  border-radius: 50px;
  font-size: 15px;
  color: #fff;
  text-align: center;
  line-height: 40px;
  margin-top: 30px;
}
</style>

<template>
  <div>

    <div class="center">
      <h1 style="font-size: 90px;">志愿通</h1>

    <div class="logon">
      <div :class="overlaylong">
        <div class="overlaylong-Signin" v-if="disfiex == 0">
          <h2 class="overlaylongH2">登录</h2>
          <el-form :model="user" :rules="rules" >
            <el-form-item  prop="username" >
              <el-input
              type="text"
              placeholder="用户"
              v-model="user.username"
            />
            </el-form-item>

            <el-form-item  prop="password" >
              <el-input type="password" placeholder="密码" v-model="user.password" />
            </el-form-item>

          </el-form>
          <!-- <h3>忘记密码?</h3> -->
          <el-button type="primary" round @click="login">登录</el-button>
        </div>
        <div class="overlaylong-Signup" v-if="disfiex == 1">
          <h2 class="overlaylongH2">注册账户</h2>
          <el-form :model="user" :rules="rules" >
            <el-form-item  prop="username" >
              <el-input
              type="text"
              placeholder="用户"
              v-model="user.username"
            />
            </el-form-item>

            <el-form-item  prop="password" >
              <el-input type="password" placeholder="密码" v-model="user.password" />
            </el-form-item>

          </el-form>
          <el-button type="primary" round  @click="register">注册</el-button>
        </div>
      </div>
      <div :class="overlaytitle">
        <div class="overlaytitle-Signin" v-if="disfiex == 0">
          <h2 class="overlaytitleH2">Hello</h2>
          <p class="overlaytitleP">
            请注册
          </p>
          <div class="buttongohs" @click="Signin">去注册</div>
        </div>
        <div class="overlaytitle-Signup" v-if="disfiex == 1">
          <h2 class="overlaytitleH2">欢迎回来</h2>
          <p class="overlaytitleP">请登录</p>
          <div class="buttongohs" @click="Signup">去登录</div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { request } from '../reqeust'
export default {
  data () {
    return {
      overlaylong: 'overlaylong',
      overlaytitle: 'overlaytitle',
      disfiex: 0,
      user: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 1, max: 16, message: '长度在 1 到 16 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 16, message: '长度在 1 到 16 个字符', trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    Signin () {
      this.overlaylong = 'overlaylongleft'
      this.overlaytitle = 'overlaytitleright'
      setTimeout(() => {
        this.disfiex = 1
      }, 200)
    },
    Signup () {
      this.overlaylong = 'overlaylongright'
      this.overlaytitle = 'overlaytitleleft'

      setTimeout(() => {
        this.disfiex = 0
      }, 200)
    },
    login () {
      request({
        url: '/user/login',
        method: 'post',
        data: this.user
      }).then(res => {
        console.log(res)
        if (res.code === 0) {
          window.localStorage.setItem('user', JSON.stringify(res.data))
          if (res.data.isAdmin === 0) {
            this.$router.push('/index')
          } else {
            this.$router.push('/employManager')
          }
        }
      })
    },
    register () {
      request({
        url: '/user/register',
        method: 'post',
        data: this.user
      }).then(res => {
        if (res.code === 0) {
          this.$message({
            message: '注册成功，请登录',
            type: 'success'
          })
          this.user = {}
          this.overlaylong = 'overlaylongright'
          this.overlaytitle = 'overlaytitleleft'

          setTimeout(() => {
            this.disfiex = 0
          }, 200)
        }
      })
    }
  }
}
</script>
