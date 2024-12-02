
<template>
  <div>
    <el-menu

class="el-menu-demo"
mode="horizontal"

background-color="#409EFF"
text-color="#fff"
active-text-color="#ffd04b"
>
<el-menu-item  index="3" >
  <img style="height: 60px;"
src="@/assets/jy.png"
/>

</el-menu-item>
<el-menu-item  >
  <span class="span">志愿通</span>
</el-menu-item>
<el-menu-item style="float: right;" @click="logout">
  <img style="height: 40px;"
src="../../assets/loyout.png"
/>
</el-menu-item>
</el-menu>
    <el-container>
      <el-aside width="200px">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
           @select = "jump"
          >
            <el-menu-item index="employManager" >
              <i class="el-icon-document"></i>
              <span slot="title">志愿信息管理</span>
            </el-menu-item>
            <el-menu-item index="userManager">
              <i class="el-icon-setting"></i>
              <span slot="title">人员管理</span>
            </el-menu-item>

          </el-menu>

      </el-aside>
      <el-container>
        <el-main>
          <router-view></router-view>
          </el-main>

      </el-container>
    </el-container>
  </div>

</template>

<script>
import { request } from '../../reqeust/index'
export default {
  methods: {
    jump (index) {
      this.$router.push({path: index})
    },
    logout () {
      var _this = this
      request({
        url: '/user/logout',
        method: 'post',
        data: {}
      }).then(res => {
        if (res.code === 0) {
          localStorage.removeItem('user')
          _this.$router.push('/login')
        }
      })
    }
  }
}
</script>
