webpackJsonp([8],{NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var r=t("7+uW"),a={render:function(){var e=this.$createElement,n=this._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var o=t("VU/8")({name:"App"},a,!1,function(e){t("bkud")},null,null).exports,u=t("YaEn"),i=t("zL8q"),l=t.n(i),s=(t("tvR6"),t("mtWM")),m=t.n(s),c=t("NYxO");r.default.use(c.a);var p=new c.a.Store({state:{user:localStorage.getItem("user")},actions:{commitUserList:function(e,n){(0,e.commit)("setUser",n),localStorage.setItem("user",n)},commitUserImgurl:function(e,n){(0,e.commit)("setUserImgurl",n),localStorage.setItem("user",p)}},mutations:{setUser:function(e,n){e.user=n},setUserImgurl:function(e,n){e.user.imgurl=n}},getters:{getUser:function(e){return e.user}}}),d=p;r.default.config.productionTip=!1,r.default.use(l.a),r.default.prototype.$ajax=m.a,window.axios=m.a,new r.default({el:"#app",store:d,router:u.a,components:{App:o},template:"<App/>"})},YaEn:function(e,n,t){"use strict";var r=t("7+uW"),a=t("/ocq");r.default.use(a.a),n.a=new a.a({mode:"hash",routes:[{path:"/",name:"index",component:function(){return Promise.all([t.e(0),t.e(1)]).then(t.bind(null,"Quw4"))}},{path:"/login",name:"login",component:function(){return Promise.all([t.e(0),t.e(1)]).then(t.bind(null,"Quw4"))}},{path:"/index",name:"index",component:function(){return Promise.all([t.e(3),t.e(0)]).then(t.bind(null,"8hXn"))}},{path:"/user",name:"user",component:function(){return Promise.all([t.e(2),t.e(0)]).then(t.bind(null,"nXOw"))}},{path:"/manager",name:"manager",component:function(){return Promise.all([t.e(4),t.e(0)]).then(t.bind(null,"/6cs"))},children:[{path:"/userManager",name:"userManager",component:function(){return Promise.all([t.e(0),t.e(6)]).then(t.bind(null,"mKLz"))}},{path:"/employManager",name:"employManager",component:function(){return Promise.all([t.e(0),t.e(5)]).then(t.bind(null,"hnEz"))}}]}]})},bkud:function(e,n){},tvR6:function(e,n){}},["NHnr"]);
//# sourceMappingURL=app.53aa490377f89323e683.js.map