<template>
    <div style="padding: 5px 20px;">
      <el-form :inline="true" :model="listQuery" class="demo-form-inline">
        <el-form-item label="年份">
          <el-date-picker
          v-model="listQuery.year"
          value-format="yyyy"
              type="year"
              default-value="2024"
              placeholder="选择年">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="学校">
          <el-input v-model="listQuery.collegeName" placeholder="学校"></el-input>
        </el-form-item>
        <el-form-item>

          <el-button type="primary" @click="onSubmitSelect" icon="el-icon-search">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="TJCollectionForm" icon="el-icon-edit">添加</el-button>
        </el-form-item>
      </el-form>
      <!-- 表格-->
      <el-table :data="list" element-loading-text="Loading" border fit highlight-current-row>
        <el-table-column label="年份" prop="year">
        </el-table-column>
        <el-table-column label="院校编码" prop="collegeCode">
        </el-table-column>
        <el-table-column label="院校名称" prop="collegeName">
        </el-table-column>
        <el-table-column label="专业编码" prop="specialtyCode">
        </el-table-column>
        <el-table-column label="专业名称" prop="specialtyName">
        </el-table-column>
        <el-table-column label="投档最低分" prop="lowestScore">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-tooltip class="item" effect="dark" content="修改" placement="top">
              <el-button type="primary" icon="el-icon-edit" circle @click="BJCollectionForm(scope.$index)"></el-button>
            </el-tooltip>
            <el-tooltip class="item" effect="dark" content="删除" placement="top">
              <el-button type="danger" icon="el-icon-delete" circle @click="deleteVisible(scope)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.page"
        :page-sizes="[10]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"
        :background="true">
      </el-pagination>

      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
        <el-form ref="collectionform" :rules="rules" :model="ruleForm" label-width="120px" label-position="left">
          <el-form-item label="年份" prop="year" >
            <el-date-picker
          v-model="ruleForm.year"
          value-format="yyyy"
              type="year"
              default-value="2024"
              placeholder="选择年">
          </el-date-picker>
          </el-form-item>
          <el-form-item label="院校编码" prop="collegeCode" >
            <el-input placeholder="请输入" v-model="ruleForm.collegeCode"></el-input>
          </el-form-item>
          <el-form-item label="院校名称" prop="collegeName" >
            <el-input placeholder="请输入" v-model="ruleForm.collegeName"></el-input>
          </el-form-item>
          <el-form-item label="专业编码" prop="specialtyCode" >
            <el-input placeholder="请输入" v-model="ruleForm.specialtyCode"></el-input>
          </el-form-item>
          <el-form-item label="专业名称" prop="specialtyName" >
            <el-input placeholder="请输入" v-model="ruleForm.specialtyName"></el-input>
          </el-form-item>
          <el-form-item label="投档最低分" prop="lowestScore" >
            <el-input placeholder="请输入" v-model="ruleForm.lowestScore"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" v-if="dialogStatus=='create'" @click="submitCollectionForm('collectionform')">添 加</el-button>
          <el-button type="warning" v-else @click="updateCollectionForm('collectionform')">修 改</el-button>
        </div>
      </el-dialog>

    </div>
  </template>

<script>
import { request } from '../../reqeust/index'
export default {
  name: 'employManager',
  data () {
    return {
      /* typecode:this.$route.query.typecode,
           typetitle:this.$route.query.typetitle */
      listLoading: true,
      list: null,
      total: 0,
      listQuery: {
        current: 1,
        size: 10
      },
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        update: '修改职业',
        create: '添加职业'
      },

      ruleForm: {
        id: '',
        employName: '',
        professional: '',
        educational: '',
        moneyType: '',
        employDes: ''
      },

      rules: {
        year: [{
          required: true,
          message: '请输入',
          trigger: 'blur'
        }],
        collegeCode: [{
          required: true,
          message: '请输入专业',
          trigger: 'blur'
        }],
        collegeName: [{
          required: true,
          message: '请输入',
          trigger: 'blur'
        }],
        specialtyName: [{
          required: true,
          message: '请输入',
          trigger: 'blur'
        }],
        lowestScore: [{
          required: true,
          message: '请输入',
          trigger: 'blur'
        }]
      }
    }
  },
  created () {
    this.axiosdata()
  },
  methods: {
    handleSizeChange (val) {
      this.listQuery.size = val
      this.axiosdata()
    },
    handleCurrentChange (val) {
      this.listQuery.current = val
      this.axiosdata()
    },
    onSubmitSelect () {
      this.axiosdata()
    },
    axiosdata () {
      this.listLoading = true
      request({
        method: 'post',
        url: '/volunteer/page',
        data: this.listQuery
      }).then((response) => {
        console.log(response)
        this.listLoading = false
        this.list = response.data.records
        this.total = response.data.total
      })
    },

    /* 添加表单 */
    TJCollectionForm () {
      /* 表单重置 */
      Object.assign(this.$data.ruleForm, this.$options.data().ruleForm)

      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    /* 编辑表单 */
    BJCollectionForm ($index) {
      // 显示修改对话框
      this.ruleForm = JSON.parse(JSON.stringify(this.list[$index]))
      // 以下效果和上面的不一样
      // this.ruleForm=this.list[$index]

      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },

    /* 添加按钮 */
    submitCollectionForm (formname) {
      this.$refs[formname].validate((valid) => {
        if (valid) {
          request({
            method: 'post',
            url: '/volunteer/add',
            data: this.ruleForm
          }).then((response) => {
            console.log(response)
            if (response.code === 0) {
              this.axiosdata()
              this.dialogFormVisible = false
              this.$message.success('添加数据成功！')
            } else {
              this.$message.error('添加数据失败！')
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    /* 修改按钮 */
    updateCollectionForm (formname) {
      this.$refs[formname].validate((valid) => {
        if (valid) {
          request({
            method: 'post',
            url: '/volunteer/update',
            data: this.ruleForm
          }).then((response) => {
            if (response.code === 0) {
              this.axiosdata()
              this.dialogFormVisible = false
              this.$message.success('修改数据成功！')
            } else {
              this.$message.error('修改数据失败！')
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    /* 删除按钮 */
    deleteVisible (scope) {
      this.$confirm('确定要删除吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request({
          method: 'post',
          url: '/volunteer/del/' + scope.row.id,
          data: scope.row
        }).then((response) => {
          if (response.code === 0) {
            this.axiosdata()
            this.dialogFormVisible = false
            this.$message.error('删除完毕')
          } else {
            this.$message.error('删除数据失败！')
          }
        })
      })
    }
  }
}
</script>

  <style>
  </style>
