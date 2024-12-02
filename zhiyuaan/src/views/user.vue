<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm"
      style="margin-top:20px;margin-left:20px">
      <el-form-item label="头像" prop="avatar">
        <img id="headPic" style=" border-radius:50%;width:5em;height:5em" v-bind:src="ruleForm.avatar"
          :onerror="(this.src = '@/assets/img/error.png')" />
        <el-upload list-type="picture" action="" accept=".jpg, .png" :limit="1" :auto-upload="false"
          :file-list="fileList" :on-change="getFile" :on-preview="handlePictureCardPreview"
          :on-remove="handleUploadRemove">
          <el-button size="small" type="primary">选择图片上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传一张jpg/png文件</div>
        </el-upload>

        <!-- <el-upload
            class="upload-demo"
            action="http://localhost:2334/user/uploadImage"
            :headers="token"
            :data="fileList"
            :on-change = "fileupdate"
            :limit="1"
            :before-upload="beforeAvatarUpload"
          >
            <el-button size="small" type="primary"> 更改头像</el-button>

          </el-upload> -->
      </el-form-item>
      <el-form-item label="姓名" prop="username">
        <el-input style="width:40%" v-model="ruleForm.username" placeholder="输入账号"></el-input>
      </el-form-item>

      <el-form-item label="昵称" prop="username">
        <el-input style="width:40%" v-model="ruleForm.nickname" placeholder="输入昵称"></el-input>
      </el-form-item>

      <el-form-item label="手机" prop="mobile">
        <el-input style="width:40%" v-model="ruleForm.mobile" placeholder="输入手机帐号"></el-input>
      </el-form-item>

      <!-- <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item> -->
      <el-form-item label="高考分类" prop="category">
        <el-radio-group v-model="ruleForm.category">
          <el-radio label="物理">物理</el-radio>
          <el-radio label="历史">历史</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- <el-form-item label="求职简介" prop="userDes">
          <el-input style="width:40%" type="textarea" v-model="ruleForm.userDes"></el-input>
        </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { request } from "../reqeust";
export default {
  data() {
    return {
      fileList: { userId: JSON.parse(localStorage.getItem("user")).userId },
      token: {},
      dialogImageUrl: "",
      dialogVisible: false,
      ruleForm: {
        username: "",
        mobile: "",
        sex: "",
        educational: "",
        category: ""
      },
      // 将配置的信息提取出来放置

      rules: {
        username: [
          { required: true, message: "请输入昵称", trigger: "blur" },
          { min: 1, max: 16, message: "长度在 1 到 16 个字符", trigger: "blur" }
        ],
        mobile: [
          { required: true, message: "请选择活动区域", trigger: "change" },
          {
            validator: (rule, value, callback) => {
              let reg = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
              if (!reg.test(value)) {
                callback(new Error("账号必须是由4-9位数字和字母组合"));
              } else {
                callback();
              }
            },
            message: "请输入合法的手机号",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    this.init();
  },
  computed: {
    user() {
      // return this.$store.state.count
      return localStorage.getItem("user");
    }
  },
  methods: {
    beforeAvatarUpload(file) {
      let isJPG = true;
      if (
        file.type !== "image/jpeg" &&
        file.type !== "image/png" &&
        file.type !== "image/jpg"
      ) {
        this.$message.error("格式不支持");
        isJPG = false;
      }
      const isLt2M = file.size / 1024 / 1024 < 1;

      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 1MB!");
      }
      return isJPG && isLt2M;
    },
    getFile(file, fileList) {
      this.getBase64(file.raw).then(res => {
        const params = res.split(",");
        console.log(params, "params");
        if (params.length > 0) {
          this.ruleForm.avatar = params[0] + "," + params[1];
        }
      });
    },
    // 获取图片转base64
    getBase64(file) {
      return new Promise(function (resolve, reject) {
        const reader = new FileReader();
        let imgResult = "";
        reader.readAsDataURL(file);
        reader.onload = function () {
          imgResult = reader.result;
        };
        reader.onerror = function (error) {
          reject(error);
        };
        reader.onloadend = function () {
          resolve(imgResult);
        };
      });
    },
    handleUploadRemove(file, fileList) {
      this.proofImage = "";
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    init() {
      this.ruleForm = JSON.parse(localStorage.getItem("user"));
    },
    fileupdate(file, fileList) {
      console.log(fileList);
    },
    submitForm(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          request({
            url: "/user/update",
            method: "post",
            data: this.ruleForm
          }).then(res => {
            if (res.code === 0) {
              console.log(JSON.stringify(res.data));
              window.localStorage.setItem("user", JSON.stringify(res.data));

              _this.$message({
                message: "修改成功",
                type: "success"
              });
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style></style>
