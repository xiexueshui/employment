<template>
  <div>
    <el-menu class="el-menu-demo" mode="horizontal" background-color="#409EFF" text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="3">
        <img style="height: 60px;" src="../assets/jy.png" />
      </el-menu-item>

      <el-menu-item>
        <span class="span">志愿通</span>
      </el-menu-item>
      <el-menu-item style="float: right;" @click="changeUserInfo">
        <el-avatar size="medium" :src="user.avatar"></el-avatar>
      </el-menu-item>
      <el-menu-item style="float: right;" @click="logout">
        <img style="height: 40px;" src="../assets/loyout.png" />
      </el-menu-item>
      <el-menu-item>
        <el-button type="primary" @click="dialogVisible = true">专业推荐</el-button>
      </el-menu-item>
    </el-menu>

    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="年份">
        <el-date-picker v-model="formInline.year" value-format="yyyy" type="year" default-value="2024"
          placeholder="选择年">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="学校">
        <el-input v-model="formInline.collegeName" placeholder="学校"></el-input>
      </el-form-item>
      <!-- <el-form-item label="学历">
    <el-select v-model="formInline.educational" placeholder="学历" clearable>
      <el-option label="专科" value="专科"></el-option>
      <el-option label="本科" value="本科"></el-option>
      <el-option label="研究生" value="研究生"></el-option>
      <el-option label="博士" value="博士"></el-option>
    </el-select>
  </el-form-item> -->
      <el-form-item label="专业">
        <el-input v-model="formInline.specialtyName" placeholder="专业"></el-input>
      </el-form-item>
      <!-- <el-form-item label="薪资">
    <el-select v-model="formInline.moneyType" placeholder="薪资" clearable="true">
      <el-option label="5K以下" value="5K以下"></el-option>
      <el-option label="5k-10k" value="5k-10k"></el-option>
      <el-option label="10K-20K" value="10K-20K"></el-option>
      <el-option label="20k-30k" value="20k-30k"></el-option>
      <el-option label="30k以上" value="30k以上"></el-option>
    </el-select>
  </el-form-item> -->

    </el-form>

    <el-row>
      <el-col :span="6" v-for="item in list" :key="item.id" style="padding-right: 10px;padding-top: 10px; ">
        <el-card style="height: 200px;">
          <div slot="header">
            <span>{{ item.collegeName }}</span>
            <!-- <div v-if = "item.isShowTo" style="float: right; padding: 3px 0">已投递</div>
          <el-button @click="showToEmply(item.id)" v-if = "!item.isShowTo" style="float: right; padding: 3px 0" type="text">投递</el-button> -->
          </div>
          <el-tag size="mini">{{ item.specialtyName }}</el-tag>
          <!-- <div> 薪资待遇 : {{ item.moneyType }}</div> -->
          <div>投档最低分 : {{ item.lowestScore }}</div>
          <div>院校代号 : {{ item.collegeCode }}</div>
          <div>专业代号 : {{ item.specialtyCode }}</div>

          <!-- <div> 投档最低分 : <div style="font-size: 15px;
    color: #3031338c;">
          {{ item.employDes }}
        </div></div> -->
        </el-card>
      </el-col>
    </el-row>

    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page="formInline.current" style="position: fixed;bottom: 0;" :page-sizes="[10, 20, 50]"
      :page-size="formInline.size" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible">
      <USER />
    </el-dialog>
    <el-dialog title="专业推荐" :visible.sync="dialogVisible">
      <div>
        <el-form :inline="true" :model="listQuery">
          <el-form-item label="年份" :required="true">
            <el-select v-model="listQuery.year" placeholder="年份" clearable>
              <el-option label="2022" value="2022"></el-option>
              <el-option label="2023" value="2023"></el-option>
              <el-option label="2024" value="2024"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="分数" :required="true">
            <el-input v-model="listQuery.score" placeholder="最低分数线"></el-input>
          </el-form-item>
          <el-form-item>
            <el-form-item label="高考分类" prop="category" :required="true">
              <el-radio-group v-model="listQuery.category">
                <el-radio label="物理">物理</el-radio>
                <el-radio label="历史">历史</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-button type="primary" @click="onSubmitSelect" icon="el-icon-search">查询</el-button>
          </el-form-item>


          <br />
          <el-form-item label="排名">
            {{ score }}名
          </el-form-item>
        </el-form>
        <!-- 表格-->
        <el-table :data="dialogList" element-loading-text="Loading" border fit highlight-current-row>

          <el-table-column prop="collegeName" label="学校" width="180">
          </el-table-column>
          <el-table-column prop="specialtyName" label="专业">
          </el-table-column>
          <el-table-column prop="lowestScore" label="最低分数线">
          </el-table-column>

        </el-table>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { request } from '../reqeust'
import USER from './user.vue'
export default {
  components: { USER },
  data() {
    return {
      dialogFormVisible: false,
      dialogVisible: false,
      score: 0,
      listQuery: {
        category: "物理",
        score: 600,
        year: 2024
      },
      total: 0,
      dialogList: [],
      data: {
        current: 1,
        size: 100
      },
      formInline: {
        current: 1,
        size: 10,
        year: '2024'
      },
      list: [],
      user: JSON.parse(localStorage.getItem('user'))
    }
  },
  created() {
    // console.log(this.user)
    // request({
    //   url: '/employ/list',
    //   method: 'post',
    //   data: this.formInline
    // }).then(res => {
    //   if (res.code === 0) {
    //     this.list = res.data.records
    //   }
    // })
    this.getList()
  },
  methods: {
    onSubmitSelect() {
      var _this = this
      request({
        url: '/volunteer/recommend/' + _this.listQuery.year + '/' + _this.listQuery.category + '/' + _this.listQuery.score,
        method: 'get'
      }).then(res => {
        if (res.code === 0) {
          this.score = res.data.num
          this.dialogList = res.data.list
        }
      })
    },
    handleSizeChange(val) {
      this.formInline.size = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.formInline.current = val
      this.getList()
    },
    showToEmply(val) {
      console.log(val)
      var _this = this
      request({
        url: '/userEmploy/add',
        method: 'post',
        data: {
          employId: val
        }
      }).then(res => {
        if (res.code === 0) {
          _this.$message({
            message: '投递成功',
            type: 'success'
          })
          _this.getList()
        }
      })
    },
    getList() {
      request({
        url: '/volunteer/page',
        method: 'post',
        data: this.formInline
      }).then(res => {
        if (res.code === 0) {
          this.list = res.data.records
          this.total = res.data.total
        }
      })
    },
    changeUserInfo() {
      this.dialogFormVisible = true
      // this.$router.push('/user')
    },
    logout() {
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

<style>
.span {
  margin: 0 auto;
  margin-left: 30px;
  font-family: "Arial Negreta", "Arial Normal", Arial;
  font-weight: 700;
  font-style: normal;
  font-size: 20px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
