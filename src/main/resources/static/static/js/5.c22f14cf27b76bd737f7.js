webpackJsonp([5],{EFxY:function(e,t){},hnEz:function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=l("mvHQ"),a=l.n(o),r=l("woOf"),i=l.n(r),n=l("ZIen"),s={name:"employManager",data:function(){return{listLoading:!0,list:null,total:0,listQuery:{current:1,size:10},dialogFormVisible:!1,dialogStatus:"create",textMap:{update:"修改职业",create:"添加职业"},ruleForm:{id:"",employName:"",professional:"",educational:"",moneyType:""},rules:{employName:[{required:!0,message:"请输入职业名称",trigger:"blur"}],professional:[{required:!0,message:"请输入专业",trigger:"blur"}],educational:[{required:!0,message:"请输入学历",trigger:"blur"}],moneyType:[{required:!0,message:"请输入薪资",trigger:"blur"}]}}},created:function(){this.axiosdata()},methods:{handleSizeChange:function(e){this.listQuery.size=e,this.axiosdata()},handleCurrentChange:function(e){this.listQuery.current=e,this.axiosdata()},onSubmitSelect:function(){this.axiosdata()},axiosdata:function(){var e=this;this.listLoading=!0,Object(n.a)({method:"post",url:"/employ/list",data:this.listQuery}).then(function(t){console.log(t),e.listLoading=!1,e.list=t.data.records,e.total=t.data.total})},TJCollectionForm:function(){i()(this.$data.ruleForm,this.$options.data().ruleForm),this.dialogStatus="create",this.dialogFormVisible=!0},BJCollectionForm:function(e){this.ruleForm=JSON.parse(a()(this.list[e])),this.dialogStatus="update",this.dialogFormVisible=!0},submitCollectionForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;Object(n.a)({method:"post",url:"/employ/add",data:t.ruleForm}).then(function(e){console.log(e),0===e.code?(t.axiosdata(),t.dialogFormVisible=!1,t.$message.success("添加数据成功！")):t.$message.error("添加数据失败！")})})},updateCollectionForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;Object(n.a)({method:"post",url:"/employ/update",data:t.ruleForm}).then(function(e){0===e.code?(t.axiosdata(),t.dialogFormVisible=!1,t.$message.success("修改数据成功！")):t.$message.error("修改数据失败！")})})},deleteVisible:function(e){var t=this;this.$confirm("确定要删除【"+e.row.employName+"】吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(n.a)({method:"post",url:"/employ/del/"+e.row.id,data:e.row}).then(function(e){0===e.code?(t.axiosdata(),t.dialogFormVisible=!1,t.$message.error("删除完毕")):t.$message.error("删除数据失败！")})})}}},c={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticStyle:{padding:"5px 20px"}},[l("br"),e._v(" "),l("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.listQuery}},[l("el-form-item",{attrs:{label:"职业名称"}},[l("el-input",{attrs:{placeholder:"职业名称"},model:{value:e.listQuery.employName,callback:function(t){e.$set(e.listQuery,"employName",t)},expression:"listQuery.employName"}})],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.onSubmitSelect}},[e._v("查询")])],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"success",icon:"el-icon-edit"},on:{click:e.TJCollectionForm}},[e._v("添加")])],1)],1),e._v(" "),l("el-table",{attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[l("el-table-column",{attrs:{align:"center",label:"ID",width:"50",type:"index"}}),e._v(" "),l("el-table-column",{attrs:{label:"职业名称",prop:"employName"}}),e._v(" "),l("el-table-column",{attrs:{label:"专业",prop:"professional"}}),e._v(" "),l("el-table-column",{attrs:{label:"学历",prop:"educational"}}),e._v(" "),l("el-table-column",{attrs:{label:"薪资",prop:"moneyType"}}),e._v(" "),l("el-table-column",{attrs:{label:"创建时间",prop:"createTime"}}),e._v(" "),l("el-table-column",{attrs:{fixed:"right",label:"操作",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"修改密码",placement:"top"}},[l("el-button",{attrs:{type:"primary",icon:"el-icon-edit",circle:""},on:{click:function(l){return e.BJCollectionForm(t.$index)}}})],1),e._v(" "),l("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"删除",placement:"top"}},[l("el-button",{attrs:{type:"danger",icon:"el-icon-delete",circle:""},on:{click:function(l){return e.deleteVisible(t)}}})],1)]}}])})],1),e._v(" "),l("el-pagination",{attrs:{"current-page":e.listQuery.page,"page-sizes":[10],"page-size":e.listQuery.limit,layout:"total, sizes, prev, pager, next, jumper",total:e.total,background:!0},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),e._v(" "),l("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[l("el-form",{ref:"collectionform",attrs:{rules:e.rules,model:e.ruleForm,"label-width":"120px","label-position":"left"}},[l("el-form-item",{attrs:{label:"职业名称",prop:"employName"}},[l("el-input",{attrs:{placeholder:"职业名称"},model:{value:e.ruleForm.employName,callback:function(t){e.$set(e.ruleForm,"employName",t)},expression:"ruleForm.employName"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"专业",prop:"professional"}},[l("el-input",{attrs:{placeholder:"专业"},model:{value:e.ruleForm.professional,callback:function(t){e.$set(e.ruleForm,"professional",t)},expression:"ruleForm.professional"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"学历",prop:"educational"}},[l("el-radio-group",{model:{value:e.ruleForm.educational,callback:function(t){e.$set(e.ruleForm,"educational",t)},expression:"ruleForm.educational"}},[l("el-radio",{attrs:{label:"专科"}},[e._v("专科")]),e._v(" "),l("el-radio",{attrs:{label:"本科"}},[e._v("本科")]),e._v(" "),l("el-radio",{attrs:{label:"研究生"}},[e._v("研究生")]),e._v(" "),l("el-radio",{attrs:{label:"博士"}},[e._v("博士")])],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"薪资",prop:"educational"}},[l("el-radio-group",{model:{value:e.ruleForm.moneyType,callback:function(t){e.$set(e.ruleForm,"moneyType",t)},expression:"ruleForm.moneyType"}},[l("el-radio",{attrs:{label:"5K以下"}},[e._v("5K以下")]),e._v(" "),l("el-radio",{attrs:{label:"5k-10k"}},[e._v("5k-10k")]),e._v(" "),l("el-radio",{attrs:{label:"10K-20K"}},[e._v("10K-20K")]),e._v(" "),l("el-radio",{attrs:{label:"20K-30K"}},[e._v("20K-30K")]),e._v(" "),l("el-radio",{attrs:{label:"30k以上"}},[e._v("30k以上")])],1)],1)],1),e._v(" "),l("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),e._v(" "),"create"==e.dialogStatus?l("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitCollectionForm("collectionform")}}},[e._v("添 加")]):l("el-button",{attrs:{type:"warning"},on:{click:function(t){return e.updateCollectionForm("collectionform")}}},[e._v("修 改")])],1)],1)],1)},staticRenderFns:[]};var u=l("VU/8")(s,c,!1,function(e){l("EFxY")},null,null);t.default=u.exports}});
//# sourceMappingURL=5.c22f14cf27b76bd737f7.js.map