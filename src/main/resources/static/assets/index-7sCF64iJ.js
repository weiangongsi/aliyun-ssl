/* empty css             */import{s as x}from"./request-Dk3dc0Nj.js";import{n as y,d as S,r as v,q as E,t as k,o as b,s as I,m as h,b as s,w as l,f as o,v as f,x as V,y as F,e as R,z as _,p as T,l as B,A as q,B as A}from"./index-0Htkk6bB.js";import{_ as C}from"./_plugin-vue_export-helper-DlAUqK2U.js";function N(e){return x({url:"login",method:"post",data:e})}const U=y({id:"user",state:()=>({token:localStorage.getItem("token")||""}),actions:{async RESET_STATE(){this.$reset()},login(e){const{username:i,password:u}=e;return new Promise((a,t)=>{N({username:i.trim(),password:u}).then(n=>{const{token:r}=n.data;localStorage.setItem("token",r),this.token=r,a(r)}).catch(n=>{t(n)})})},logout(){return new Promise(e=>{localStorage.removeItem("token"),this.RESET_STATE(),e(void 0)})}}}),K=()=>({user:U()}),P=e=>(T("data-v-a641021d"),e=e(),B(),e),z={class:"login-container"},L={class:"login"},M=P(()=>h("h3",{style:{"text-align":"center"}},"登录",-1)),$=S({__name:"index",setup(e){const i=v(_),{user:u}=K(),a=E({loginForm:{},loginRules:{username:[{required:!0,trigger:"blur",message:"账号必填"}],password:[{required:!0,trigger:"blur",message:"密码必填"}]},loading:!1}),{loginForm:t,loginRules:n,loading:r}=k(a);function c(){i.value.validate(g=>{if(g)a.loading=!0,u.login(a.loginForm).then(()=>{window.location.href="/"}).finally(()=>{a.loading=!1});else return!1})}return(g,d)=>{const m=q,w=A;return b(),I("div",z,[h("div",L,[M,s(o(_),{ref_key:"loginFormRef",ref:i,model:o(t),rules:o(n),class:"login-form","label-position":"top"},{default:l(()=>[s(m,{prop:"username",label:"用户名"},{default:l(()=>[s(o(f),{modelValue:o(t).username,"onUpdate:modelValue":d[0]||(d[0]=p=>o(t).username=p),placeholder:"请输入用户名称",type:"text",tabindex:"1"},null,8,["modelValue"])]),_:1}),s(m,{prop:"password",label:"密码"},{default:l(()=>[s(o(f),{modelValue:o(t).password,"onUpdate:modelValue":d[1]||(d[1]=p=>o(t).password=p),type:"password",placeholder:"请输入用户密码",tabindex:"2",onKeyup:V(c,["enter"])},null,8,["modelValue"])]),_:1}),s(m,{style:{"margin-top":"30px"}},{default:l(()=>[s(w,{loading:o(r),type:"primary",onClick:F(c,["prevent"]),style:{width:"100%"}},{default:l(()=>[R(" 登录 ")]),_:1},8,["loading"])]),_:1})]),_:1},8,["model","rules"])])])}}}),J=C($,[["__scopeId","data-v-a641021d"]]);export{J as default};
