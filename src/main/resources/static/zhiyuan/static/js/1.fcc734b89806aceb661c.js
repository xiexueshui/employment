webpackJsonp([1],{Quw4:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=s("mvHQ"),l=s.n(r),a=s("ZIen"),i={data:function(){return{overlaylong:"overlaylong",overlaytitle:"overlaytitle",disfiex:0,user:{username:"",password:""},rules:{username:[{required:!0,message:"请输入昵称",trigger:"blur"},{min:1,max:16,message:"长度在 1 到 16 个字符",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:1,max:16,message:"长度在 1 到 16 个字符",trigger:"blur"}]}}},methods:{Signin:function(){var e=this;this.overlaylong="overlaylongleft",this.overlaytitle="overlaytitleright",setTimeout(function(){e.disfiex=1},200)},Signup:function(){var e=this;this.overlaylong="overlaylongright",this.overlaytitle="overlaytitleleft",setTimeout(function(){e.disfiex=0},200)},login:function(){var e=this;Object(a.a)({url:"/user/login",method:"post",data:this.user}).then(function(t){console.log(t),0===t.code&&(window.localStorage.setItem("user",l()(t.data)),0===t.data.isAdmin?e.$router.push("/index"):e.$router.push("/employManager"))})},register:function(){var e=this;Object(a.a)({url:"/user/register",method:"post",data:this.user}).then(function(t){0===t.code&&(e.$message({message:"注册成功，请登录",type:"success"}),e.user={},e.overlaylong="overlaylongright",e.overlaytitle="overlaytitleleft",setTimeout(function(){e.disfiex=0},200))})}}},o={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("div",{staticClass:"center"},[s("h1",{staticStyle:{"font-size":"90px"}},[e._v("志愿通")]),e._v(" "),s("div",{staticClass:"logon"},[s("div",{class:e.overlaylong},[0==e.disfiex?s("div",{staticClass:"overlaylong-Signin"},[s("h2",{staticClass:"overlaylongH2"},[e._v("登录")]),e._v(" "),s("el-form",{attrs:{model:e.user,rules:e.rules}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{type:"text",placeholder:"用户"},model:{value:e.user.username,callback:function(t){e.$set(e.user,"username",t)},expression:"user.username"}})],1),e._v(" "),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:e.user.password,callback:function(t){e.$set(e.user,"password",t)},expression:"user.password"}})],1)],1),e._v(" "),s("el-button",{attrs:{type:"primary",round:""},on:{click:e.login}},[e._v("登录")])],1):e._e(),e._v(" "),1==e.disfiex?s("div",{staticClass:"overlaylong-Signup"},[s("h2",{staticClass:"overlaylongH2"},[e._v("注册账户")]),e._v(" "),s("el-form",{attrs:{model:e.user,rules:e.rules}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{type:"text",placeholder:"用户"},model:{value:e.user.username,callback:function(t){e.$set(e.user,"username",t)},expression:"user.username"}})],1),e._v(" "),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"密码"},model:{value:e.user.password,callback:function(t){e.$set(e.user,"password",t)},expression:"user.password"}})],1)],1),e._v(" "),s("el-button",{attrs:{type:"primary",round:""},on:{click:e.register}},[e._v("注册")])],1):e._e()]),e._v(" "),s("div",{class:e.overlaytitle},[0==e.disfiex?s("div",{staticClass:"overlaytitle-Signin"},[s("h2",{staticClass:"overlaytitleH2"},[e._v("Hello")]),e._v(" "),s("p",{staticClass:"overlaytitleP"},[e._v("\n          请注册\n        ")]),e._v(" "),s("div",{staticClass:"buttongohs",on:{click:e.Signin}},[e._v("去注册")])]):e._e(),e._v(" "),1==e.disfiex?s("div",{staticClass:"overlaytitle-Signup"},[s("h2",{staticClass:"overlaytitleH2"},[e._v("欢迎回来")]),e._v(" "),s("p",{staticClass:"overlaytitleP"},[e._v("请登录")]),e._v(" "),s("div",{staticClass:"buttongohs",on:{click:e.Signup}},[e._v("去登录")])]):e._e()])])])])},staticRenderFns:[]};var n=s("VU/8")(i,o,!1,function(e){s("qj8Y")},null,null);t.default=n.exports},qj8Y:function(e,t){}});
//# sourceMappingURL=1.fcc734b89806aceb661c.js.map