/* empty css             *//* empty css                  */import{s as S}from"./request-Dk3dc0Nj.js";import{d as U,r as K,q as L,t as q,c as x,C as M,o as V,a as E,w as a,D as G,m as b,b as e,e as _,f as o,z as F,F as $,v as H,A as z,B as N,s as k,G as A,H as I,I as O,J as Q,K as X,L as Y,M as W,N as j,O as Z,P as ee,Q as B,p as le,l as te,S as P,T as ae,U as oe}from"./index-0Htkk6bB.js";import{_ as ne}from"./_plugin-vue_export-helper-DlAUqK2U.js";function se(){return S({url:"/certificate/list",method:"post"})}function ue(u){return S({url:"/certificate/add",method:"post",data:u})}function de(u){return S({url:"/certificate/cancel",method:"post",data:u})}function re(){return S({url:"/certificate/sync",method:"post"})}const ie=b("div",{class:"dialog-header"},[b("div",{class:"dialog-header-title"},"创建证书")],-1),ce={class:"dialog-footer"},pe=U({__name:"CertificateAddDialog",props:{modelValue:{type:Boolean,default:!1},domain:{}},emits:["update:modelValue","success"],setup(u,{emit:g}){const y=K(F),m=u,r=L({formData:{},rules:{domain:[{required:!0,message:"请输入域名",trigger:["blur","change"]}]}}),{formData:n,rules:h}=q(r),i=g,s=x({get:()=>m.modelValue,set:d=>{i("update:modelValue",d)}});M(()=>m.modelValue,async d=>{d?setTimeout(()=>{y.value.clearValidate()},200):r.formData={}});const c=()=>{i("update:modelValue",!1)},D=()=>{y.value.validate(async d=>{d&&(await ue(r.formData),$.success("成功"),i("update:modelValue",!1),i("success"))})};return(d,l)=>{const p=H,f=z,w=N,v=G;return V(),E(v,{modelValue:s.value,"onUpdate:modelValue":l[1]||(l[1]=C=>s.value=C),width:"500px","close-icon":"CircleClose"},{header:a(()=>[ie]),footer:a(()=>[b("span",ce,[e(w,{onClick:c},{default:a(()=>[_("取消")]),_:1}),e(w,{type:"primary",onClick:D},{default:a(()=>[_("确认")]),_:1})])]),default:a(()=>[e(o(F),{ref_key:"dataFormRef",ref:y,model:o(n),rules:o(h),"label-width":"80px"},{default:a(()=>[e(f,{prop:"domain",label:"域名"},{default:a(()=>[e(p,{modelValue:o(n).domain,"onUpdate:modelValue":l[0]||(l[0]=C=>o(n).domain=C)},null,8,["modelValue"])]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["modelValue"])}}}),me=U({__name:"CertificateTypeSelect",props:{modelValue:{},showText:{type:Boolean,default:!1},width:{default:"100%"}},emits:["update:modelValue"],setup(u,{emit:g}){const y=[{label:"DV 类型的证书",value:"DV"},{label:"EV 类型的证书",value:"EV"},{label:"OV 类型的证书",value:"OV"},{label:"免费证书",value:"FREE"}],m=u,r=g,n=x({get:()=>m.modelValue,set:s=>{r("update:modelValue",s)}}),h=x(()=>{const s=y.find(c=>c.value===m.modelValue);return s?s.label:""}),i=()=>{r("update:modelValue",void 0)};return(s,c)=>{const D=Y,d=X;return s.showText?(V(),k(I,{key:0},[_(A(h.value),1)],64)):(V(),E(d,{key:1,modelValue:n.value,"onUpdate:modelValue":c[0]||(c[0]=l=>n.value=l),style:Q({width:s.width}),onClear:i,clearable:""},{default:a(()=>[(V(),k(I,null,O(y,l=>e(D,{key:l.value,label:l.label,value:l.value},null,8,["label","value"])),64))]),_:1},8,["modelValue","style"]))}}}),fe=U({__name:"CertificateStatusSelect",props:{modelValue:{},showText:{type:Boolean,default:!1},width:{default:"100%"}},emits:["update:modelValue"],setup(u,{emit:g}){const y=[{label:"待申请",value:"PAYED"},{label:"审核中",value:"CHECKING"},{label:"审核失败",value:"CHECKED_FAIL"},{label:"已签发",value:"ISSUED"},{label:"即将过期",value:"WILLEXPIRED"},{label:"已过期",value:"EXPIRED"},{label:"未激活",value:"NOTACTIVATED"},{label:"吊销完成",value:"REVOKED"}],m=u,r=g,n=x({get:()=>m.modelValue,set:s=>{r("update:modelValue",s)}}),h=x(()=>{const s=y.find(c=>c.value===m.modelValue);return s?s.label:""}),i=()=>{r("update:modelValue",void 0)};return(s,c)=>{const D=Y,d=X;return s.showText?(V(),k(I,{key:0},[_(A(h.value),1)],64)):(V(),E(d,{key:1,modelValue:n.value,"onUpdate:modelValue":c[0]||(c[0]=l=>n.value=l),style:Q({width:s.width}),onClear:i,clearable:""},{default:a(()=>[(V(),k(I,null,O(y,l=>e(D,{key:l.value,label:l.label,value:l.value},null,8,["label","value"])),64))]),_:1},8,["modelValue","style"]))}}});function _e(u){return S({url:"certificate/deploy/detail",method:"post",data:u})}function ye(u){return S({url:"certificate/deploy/add",method:"post",data:u})}function Ve(u){return S({url:"certificate/deploy/deploy",method:"post",data:u})}const be=b("div",{class:"dialog-header"},[b("div",{class:"dialog-header-title"},"配置")],-1),ge=b("h4",null,"域名",-1),ve=b("h4",null,"服务器连接配置",-1),he=b("h4",null,"到期自动更新开关",-1),we={class:"dialog-footer"},De=U({__name:"CertificateDeployDialog",props:{modelValue:{type:Boolean,default:!1},domain:{default:""}},emits:["update:modelValue","success"],setup(u,{emit:g}){const y=K(F),m=u,r=L({formData:{},rules:{host:[{required:!0,message:"请输入主机ip",trigger:["blur","change"]}],port:[{required:!0,message:"请输入端口号",trigger:["blur","change"]}],username:[{required:!0,message:"请输入用户名",trigger:["blur","change"]}],password:[{required:!0,message:"请输入密码",trigger:["blur","change"]}],path:[{required:!0,message:"请输入证书路径",trigger:["blur","change"]}],cronStatus:[{required:!0,message:"请输入选择开关状态",trigger:["blur","change"]}]}}),{formData:n,rules:h}=q(r),i=g,s=x({get:()=>m.modelValue,set:d=>{i("update:modelValue",d)}});M(()=>m.modelValue,async d=>{if(d){const{data:l}=await _e({domain:m.domain});l?r.formData=l:r.formData={},y.value.clearValidate()}else r.formData={}});const c=()=>{i("update:modelValue",!1)},D=()=>{y.value.validate(async d=>{d&&(r.formData.domain=m.domain,await ye(r.formData),$.success("成功"),i("update:modelValue",!1),i("success"))})};return(d,l)=>{const p=H,f=z,w=W,v=j,C=N,R=G;return V(),E(R,{modelValue:s.value,"onUpdate:modelValue":l[7]||(l[7]=t=>s.value=t),width:"500px","close-icon":"CircleClose"},{header:a(()=>[be]),footer:a(()=>[b("span",we,[e(C,{onClick:c},{default:a(()=>[_("取消")]),_:1}),e(C,{type:"primary",onClick:D},{default:a(()=>[_("确认")]),_:1})])]),default:a(()=>[e(o(F),{ref_key:"dataFormRef",ref:y,model:o(n),rules:o(h),"label-width":"80px"},{default:a(()=>[ge,_(" "+A(m.domain)+" ",1),ve,e(f,{prop:"host",label:"host"},{default:a(()=>[e(p,{modelValue:o(n).host,"onUpdate:modelValue":l[0]||(l[0]=t=>o(n).host=t)},null,8,["modelValue"])]),_:1}),e(f,{prop:"port",label:"port"},{default:a(()=>[e(p,{modelValue:o(n).port,"onUpdate:modelValue":l[1]||(l[1]=t=>o(n).port=t)},null,8,["modelValue"])]),_:1}),e(f,{prop:"username",label:"用户名"},{default:a(()=>[e(p,{modelValue:o(n).username,"onUpdate:modelValue":l[2]||(l[2]=t=>o(n).username=t)},null,8,["modelValue"])]),_:1}),e(f,{prop:"password",label:"密码"},{default:a(()=>[e(p,{modelValue:o(n).password,"onUpdate:modelValue":l[3]||(l[3]=t=>o(n).password=t),type:"password","show-password":""},null,8,["modelValue"])]),_:1}),e(f,{prop:"path",label:"证书路径"},{default:a(()=>[e(p,{modelValue:o(n).path,"onUpdate:modelValue":l[4]||(l[4]=t=>o(n).path=t)},null,8,["modelValue"])]),_:1}),e(f,{prop:"shell",label:"执行脚本"},{default:a(()=>[e(p,{modelValue:o(n).shell,"onUpdate:modelValue":l[5]||(l[5]=t=>o(n).shell=t),placeholder:"例如：docker restart nginx"},null,8,["modelValue"])]),_:1}),he,e(f,{prop:"cronStatus"},{default:a(()=>[e(v,{modelValue:o(n).cronStatus,"onUpdate:modelValue":l[6]||(l[6]=t=>o(n).cronStatus=t)},{default:a(()=>[e(w,{label:"ON"},{default:a(()=>[_("开启")]),_:1}),e(w,{label:"OFF"},{default:a(()=>[_("关闭")]),_:1})]),_:1},8,["modelValue"])]),_:1})]),_:1},8,["model","rules"])]),_:1},8,["modelValue"])}}}),J=u=>(le("data-v-09bb98f8"),u=u(),te(),u),Ce=J(()=>b("h2",null,"证书列表",-1)),ke=J(()=>b("div",{class:"tip custom-block"},[b("p",{class:"custom-block-title"},"TIP"),b("p",null,"域名要在阿里云平台，因为后面的验证需要添加一条txt类型的解析记录。创建证书后就可以配置部署信息，开启到期自动更新开关，证书申请成功后会自动部署。"),b("p",null,"初次创建证书自动处理过程。1.请求阿里云申请证书。2.域名添加一条txt类型的记录。3.申请证书成功。4.根据部署配置上传证书文件。")],-1)),Ee={style:{display:"flex",height:"50px"}},Se=U({__name:"index",setup(u){const g=L({syncing:!1,dataList:[],curData:{},deployDialog:{},certificateAddDialog:{}}),{syncing:y,dataList:m,curData:r,deployDialog:n,certificateAddDialog:h}=q(g),i=async()=>{g.syncing=!0;try{await re(),await c()}finally{g.syncing=!1}},s=()=>{i(),setTimeout(()=>{i()},5e3)},c=async()=>{const{data:p}=await se();g.dataList=p},D=p=>{g.curData=p,g.deployDialog.visible=!0},d=async p=>{P.confirm("确认部署吗？","提示",{confirmButtonText:"确认",cancelButtonText:"取消",type:"warning"}).then(async()=>{await Ve({domain:p.domain}),await c(),$({type:"success",message:"部署成功"})}).catch(()=>{})},l=p=>{P.confirm("确认取消吗?待申请状态可去阿里云后台继续申请证书，或者取消后在本系统重新申请","提示",{confirmButtonText:"确认",cancelButtonText:"取消",type:"warning"}).then(async()=>{try{await de({instanceId:p.instanceId}),$({type:"success",message:"取消成功"})}finally{await i()}}).catch(()=>{})};return Z(()=>{c()}),(p,f)=>{const w=N,v=ae,C=oe,R=ee;return V(),k("div",null,[Ce,ke,b("div",Ee,[e(w,{type:"primary",onClick:i,loading:o(y)},{default:a(()=>[_("同步阿里云")]),_:1},8,["loading"]),e(w,{type:"primary",onClick:f[0]||(f[0]=t=>o(h).visible=!0)},{default:a(()=>[_("创建证书")]),_:1})]),e(R,{data:o(m)},{default:a(()=>[e(v,{prop:"instanceId",label:"资源ID"}),e(v,{prop:"certType",label:"品牌"},{default:a(t=>[e(me,{"model-value":t.row.certType,"show-text":""},null,8,["model-value"])]),_:1}),e(v,{prop:"status",label:"状态"},{default:a(t=>[e(fe,{"model-value":t.row.status,"show-text":""},null,8,["model-value"])]),_:1}),e(v,{prop:"sans",label:"绑定域名"},{default:a(t=>[t.row.sans?(V(!0),k(I,{key:0},O(t.row.sans.split(","),T=>(V(),k("p",{key:T},A(T),1))),128)):B("",!0)]),_:1}),e(v,{prop:"deployStatus",label:"部署结果"},{default:a(t=>[t.row.deployStatus==="SUCCESS"?(V(),k(I,{key:0},[_(" 成功 ")],64)):t.row.deployStatus==="FAIL"?(V(),E(C,{key:1,placement:"top-start",title:"失败原因",width:200,trigger:"hover",content:t.row.deployError},{reference:a(()=>[_(" 失败 ")]),_:2},1032,["content"])):B("",!0)]),_:1}),e(v,{prop:"deployDate",label:"部署时间"}),e(v,{prop:"nextDeployDate",label:"下次部署"}),e(v,{prop:"endDate",label:"有效期限"}),e(v,{prop:"cronStatus",label:"到期更新"}),e(v,{label:"操作"},{default:a(t=>[e(w,{type:"primary",link:"",onClick:T=>D(t.row)},{default:a(()=>[_("配置")]),_:2},1032,["onClick"]),t.row.status==="PAYED"?(V(),E(w,{key:0,type:"primary",link:"",onClick:T=>l(t.row)},{default:a(()=>[_("取消")]),_:2},1032,["onClick"])):B("",!0),t.row.status==="ISSUED"?(V(),E(w,{key:1,type:"primary",link:"",onClick:T=>d(t.row)},{default:a(()=>[_("部署")]),_:2},1032,["onClick"])):B("",!0)]),_:1})]),_:1},8,["data"]),e(De,{modelValue:o(n).visible,"onUpdate:modelValue":f[1]||(f[1]=t=>o(n).visible=t),domain:o(r).domain,onSuccess:c},null,8,["modelValue","domain"]),e(pe,{modelValue:o(h).visible,"onUpdate:modelValue":f[2]||(f[2]=t=>o(h).visible=t),onSuccess:s},null,8,["modelValue"])])}}}),Fe=ne(Se,[["__scopeId","data-v-09bb98f8"]]);export{Fe as default};
