/* empty css                  */import"./el-form-item-l0sNRNKZ.js";import{d as w,u as v,s as V,t as F,v as I,x as R,o as k,y as q,g as _,f as a,w as n,i as e,b as B,z as c,A as E,B as C,h as Q,C as f,q as S,r as N,D as j,j as K,F as O}from"./index-skPlrUAX.js";import{_ as U}from"./_plugin-vue_export-helper-DlAUqK2U.js";const z=i=>(S("data-v-84bef8ee"),i=i(),N(),i),A={class:"login-container"},D={class:"login"},L=z(()=>_("h3",{style:{"text-align":"center"}},"登录",-1)),M=w({__name:"index",setup(i){const u=B(),{user:g}=v(),p=V(f),o=F({loginForm:{},loginRules:{username:[{required:!0,trigger:"blur",message:"账号必填"}],password:[{required:!0,trigger:"blur",message:"密码必填"}]},loading:!1,redirect:"",otherQuery:{}}),{loginForm:l,loginRules:h,loading:y}=I(o);function m(){p.value.validate(t=>{if(t)o.loading=!0,g.login(o.loginForm).then(()=>{O.push({path:o.redirect||"/",query:o.otherQuery})}).finally(()=>{o.loading=!1});else return!1})}R(u,()=>{const t=u.query;t&&(o.redirect=t.redirect,o.otherQuery=x(t))},{immediate:!0});function x(t){return Object.keys(t).reduce((r,s)=>(s!=="redirect"&&(r[s]=t[s]),r),{})}return(t,r)=>{const s=j,b=K;return k(),q("div",A,[_("div",D,[L,a(e(f),{ref_key:"loginFormRef",ref:p,model:e(l),rules:e(h),class:"login-form","label-position":"top"},{default:n(()=>[a(s,{prop:"username",label:"用户名"},{default:n(()=>[a(e(c),{modelValue:e(l).username,"onUpdate:modelValue":r[0]||(r[0]=d=>e(l).username=d),placeholder:"请输入用户名称",type:"text",tabindex:"1"},null,8,["modelValue"])]),_:1}),a(s,{prop:"password",label:"密码"},{default:n(()=>[a(e(c),{modelValue:e(l).password,"onUpdate:modelValue":r[1]||(r[1]=d=>e(l).password=d),type:"password",placeholder:"请输入用户密码",tabindex:"2",onKeyup:E(m,["enter"])},null,8,["modelValue"])]),_:1}),a(s,{style:{"margin-top":"30px"}},{default:n(()=>[a(b,{loading:e(y),type:"primary",onClick:C(m,["prevent"]),style:{width:"100%"}},{default:n(()=>[Q(" 登录 ")]),_:1},8,["loading"])]),_:1})]),_:1},8,["model","rules"])])])}}}),P=U(M,[["__scopeId","data-v-84bef8ee"]]);export{P as default};
