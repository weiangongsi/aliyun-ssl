<template>
  <el-dialog v-model="dialogVisible" width="500px" close-icon="CircleClose">
    <template #header>
      <div class="dialog-header">
        <div class="dialog-header-title">创建证书</div>
      </div>
    </template>
    <el-form ref="dataFormRef" :model="formData" :rules="rules" label-width="80px">
      <el-form-item prop="domain" label="域名">
        <el-input v-model="formData.domain" />
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
import type { CertificateAddReq } from '@/api/certificate/type/certificate'
// api
import { addCertificate } from '@/api/certificate/certificate'
//ref
const dataFormRef = ref(ElForm)

interface Props {
  modelValue: boolean;
  domain?: string;
}

const props = withDefaults(defineProps<Props>(), {
  modelValue: false
})

const state = reactive({
  formData: {} as CertificateAddReq,
  rules: {
    domain: [
      { required: true, message: '请输入域名', trigger: ['blur', 'change'] }
    ]
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
    setTimeout(()=>{
      dataFormRef.value.clearValidate()
    },200)
  } else {
    state.formData = {} as CertificateAddReq
  }
})
const handleCancel = () => {
  emit('update:modelValue', false)
}

const handleSubmit = () => {
  dataFormRef.value.validate(async (valid: any) => {
    if (valid) {
      await addCertificate(state.formData)
      ElMessage.success('成功')
      emit('update:modelValue', false)
      emit('success')
    }
  })
}
</script>

<style lang="scss" scoped>
</style>
