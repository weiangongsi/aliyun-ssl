<template>
  <div class="login-container">
    <div class="login">
      <h3 style="text-align: center">登录</h3>
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form" label-position="top">
        <el-form-item prop="username" label="用户名">
          <el-input v-model="loginForm.username" placeholder="请输入用户名称" type="text" tabindex="1"></el-input>
        </el-form-item>

        <el-form-item prop="password" label="密码">
          <el-input v-model="loginForm.password" type="password" placeholder="请输入用户密码" tabindex="2"
                    @keyup.enter="handleLogin"></el-input>
        </el-form-item>

        <el-form-item style="margin-top: 30px">
          <el-button :loading="loading" type="primary" @click.prevent="handleLogin" style="width: 100%;"> 登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, toRefs ,watch} from 'vue'
// 组件依赖
import { ElForm, ElInput } from 'element-plus'
/// type
import type { LoginReq } from '@/api/login/type/login'

import useStore from '@/stores'
import router from '@/router'

import { useRoute } from 'vue-router'

const route = useRoute()
const { user } = useStore()

const loginFormRef = ref(ElForm)
const state = reactive({
  loginForm: {} as LoginReq,
  loginRules: {
    username: [{ required: true, trigger: 'blur', message: '账号必填' }],
    password: [{ required: true, trigger: 'blur', message: '密码必填' }]
  },
  loading: false,
  redirect: '',
  otherQuery: {}
})

const { loginForm, loginRules, loading } = toRefs(state)

function handleLogin() {
  loginFormRef.value.validate((valid: boolean) => {
    if (valid) {
      state.loading = true
      user.login(state.loginForm).then(() => {
        router.push({ path: state.redirect || '/', query: state.otherQuery })
      }).finally(() => {
        state.loading = false
      })
    } else {
      return false
    }
  })
}

watch(
  route,
  () => {
    const query = route.query;
    if (query) {
      state.redirect = query.redirect as string;
      state.otherQuery = getOtherQuery(query);
    }
  },
  {
    immediate: true
  }
);

function getOtherQuery(query: any) {
  return Object.keys(query).reduce((acc: any, cur: any) => {
    if (cur !== 'redirect') {
      acc[cur] = query[cur];
    }
    return acc;
  }, {});
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
}

.login {
  width: 400px;
  height: 300px;
  border: 1px solid #f0f0f0;
  padding: 20px;
}

.el-form-item {
  margin-top: 20px;
}
</style>
