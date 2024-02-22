<template>
  <el-dialog v-model="dialogVisible" width="500px" close-icon="CircleClose">
    <template #header>
      <div class="dialog-header">
        <div class="dialog-header-title">配置</div>
      </div>
    </template>
    <el-form ref="dataFormRef" :model="formData" :rules="rules" label-width="80px">
      <h4>域名</h4>
      {{ props.domain }}
      <h4>服务器连接配置</h4>
      <el-form-item prop="host" label="host">
        <el-input v-model="formData.host" />
      </el-form-item>
      <el-form-item prop="port" label="port">
        <el-input v-model="formData.port" />
      </el-form-item>
      <el-form-item prop="username" label="用户名">
        <el-input v-model="formData.username" />
      </el-form-item>
      <el-form-item prop="password" label="密码">
        <el-input v-model="formData.password" type="password" show-password />
      </el-form-item>
      <el-form-item prop="path" label="证书路径">
        <el-input v-model="formData.path" />
      </el-form-item>
      <el-form-item prop="shell" label="执行脚本">
        <el-input v-model="formData.shell" placeholder="例如：docker restart nginx" />
      </el-form-item>
      <h4>到期自动更新开关</h4>
      <el-form-item prop="cronStatus">
        <el-radio-group v-model="formData.cronStatus">
          <el-radio label="ON">开启</el-radio>
          <el-radio label="OFF">关闭</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { computed, reactive, ref, toRefs, watch } from 'vue'
import { ElForm, ElMessage } from 'element-plus'
// type
import type { CertificateDeployAddReq } from '@/api/certificate/type/deploy'
// api
import { addCertificateDeploy, getCertificateDeployDetail } from '@/api/certificate/deploy'
//ref
const dataFormRef = ref(ElForm)

interface Props {
  modelValue: boolean;
  domain: string;
}

const props = withDefaults(defineProps<Props>(), {
  modelValue: false,
  domain: ''
})

const state = reactive({
  formData: {} as CertificateDeployAddReq,
  rules: {
    host: [
      { required: true, message: '请输入主机ip', trigger: ['blur', 'change'] }
    ],
    port: [{ required: true, message: '请输入端口号', trigger: ['blur', 'change'] }],
    username: [
      { required: true, message: '请输入用户名', trigger: ['blur', 'change'] }
    ],
    password: [{ required: true, message: '请输入密码', trigger: ['blur', 'change'] }],
    path: [{ required: true, message: '请输入证书路径', trigger: ['blur', 'change'] }],
    cronStatus: [{ required: true, message: '请输入选择开关状态', trigger: ['blur', 'change'] }]
  }
})

const { formData, rules } = toRefs(state)

const emit = defineEmits(['update:modelValue', 'success'])

const dialogVisible = computed({
  get: () => {
    return props.modelValue
  },
  set: value => {
    emit('update:modelValue', value)
  }
})

watch(() => props.modelValue, async value => {
  if (value) {
    const { data } = await getCertificateDeployDetail({ domain: props.domain })
    if (data) {
      state.formData = data
    } else {
      state.formData = {} as CertificateDeployAddReq
    }
    dataFormRef.value.clearValidate()
  } else {
    state.formData = {} as CertificateDeployAddReq
  }
})
const handleCancel = () => {
  emit('update:modelValue', false)
}

const handleSubmit = () => {
  dataFormRef.value.validate(async (valid: any) => {
    if (valid) {
      state.formData.domain = props.domain
      await addCertificateDeploy(state.formData)
      ElMessage.success('成功')
      emit('update:modelValue', false)
      emit('success')
    }
  })
}
</script>

<style lang="scss" scoped>
</style>
