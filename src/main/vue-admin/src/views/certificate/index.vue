<template>
  <div>
    <h2>证书列表</h2>
    <div class="tip custom-block">
      <p class="custom-block-title">TIP</p>
      <p>域名要在阿里云平台，因为后面的验证需要添加一条txt类型的解析记录。创建证书后就可以配置部署信息，开启到期自动更新开关，证书申请成功后会自动部署。</p>
      <p>初次创建证书自动处理过程。1.请求阿里云申请证书。2.域名添加一条txt类型的记录。3.申请证书成功。4.根据部署配置上传证书文件。</p>
    </div>
    <div style="display: flex; height: 50px">
      <el-button type="primary" @click="handleSync" :loading="syncing">同步阿里云</el-button>
      <el-button type="primary" @click="certificateAddDialog.visible=true">创建证书</el-button>
    </div>
    <el-table :data="dataList">
      <el-table-column prop="instanceId" label="资源ID" />
      <el-table-column prop="certType" label="品牌">
        <template #default="scope">
          <certificate-type-select :model-value="scope.row.certType" show-text></certificate-type-select>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <certificate-status-select :model-value="scope.row.status" show-text></certificate-status-select>
        </template>
      </el-table-column>
      <el-table-column prop="sans" label="绑定域名">
        <template #default="scope">
          <template v-if="scope.row.sans">
            <p v-for="item in scope.row.sans.split(',')" :key="item">{{ item }}</p>
          </template>
          <template v-else>{{scope.row.domain}}</template>
        </template>
      </el-table-column>
      <el-table-column prop="deployStatus" label="部署结果">
        <template #default="scope">
          <template v-if="scope.row.deployStatus==='SUCCESS'">
            成功
          </template>
          <template v-else-if="scope.row.deployStatus==='FAIL'">
            <el-popover
              placement="top-start"
              title="失败原因"
              :width="200"
              trigger="hover"
              :content="scope.row.deployError"
            >
              <template #reference>
                失败
              </template>
            </el-popover>
          </template>
        </template>
      </el-table-column>
      <el-table-column prop="deployDate" label="部署时间" />
      <el-table-column prop="nextDeployDate" label="下次部署" />
      <el-table-column prop="endDate" label="有效期限" />
      <el-table-column prop="cronStatus" label="到期更新" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" link @click="handleShowDeploy(scope.row)">配置</el-button>
          <template v-if="scope.row.status==='PAYED'">
            <el-button type="primary" link @click="handleCancel(scope.row)">取消</el-button>
          </template>
          <template v-if="scope.row.status==='ISSUED' || scope.row.status==='WILLEXPIRED'">
            <el-button type="primary" link @click="handleDeploy(scope.row)">部署</el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>

    <certificate-deploy-dialog v-model="deployDialog.visible" :domain="curData.domain"
                               @success="handleQuery"></certificate-deploy-dialog>
    <certificate-add-dialog v-model="certificateAddDialog.visible" @success="handleAddSuccess"></certificate-add-dialog>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, toRefs } from 'vue'
// 组件
import { ElMessage, ElMessageBox } from 'element-plus'
import CertificateAddDialog from '@/components/dialog/CertificateAddDialog.vue'
import CertificateTypeSelect from '@/components/select/CertificateTypeSelect.vue'
import CertificateStatusSelect from '@/components/select/CertificateStatusSelect.vue'
import CertificateDeployDialog from '@/components/dialog/CertificateDeployDialog.vue'
// api
import { deployCertificateDeploy } from '@/api/certificate/deploy'
import { cancelCertificate, getCertificateList, syncCertificate } from '@/api/certificate/certificate'
// type
import type { Dialog } from '@/type/common'
import type { Certificate } from '@/api/certificate/type/certificate'

const state = reactive({
  syncing: false,
  dataList: [] as Certificate[],
  curData: {} as Certificate,
  deployDialog: {} as Dialog,
  certificateAddDialog: {} as Dialog
})
const { syncing, dataList, curData, deployDialog, certificateAddDialog } = toRefs(state)

const handleSync = async () => {
  state.syncing = true
  try {
    await syncCertificate()
    await handleQuery()
  } finally {
    state.syncing = false
  }
}

const handleAddSuccess = () => {
  handleSync()
  setTimeout(() => {
    handleSync()
  }, 5000)
}

const handleQuery = async () => {
  const { data } = await getCertificateList()
  state.dataList = data
}

const handleShowDeploy = (certificate: Certificate) => {
  state.curData = certificate
  state.deployDialog.visible = true
}

const handleDeploy = async (certificate: Certificate) => {
  ElMessageBox.confirm(
    '确认部署吗？',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  )
    .then(async () => {
      await deployCertificateDeploy({ domain: certificate.domain })
      await handleQuery()
      ElMessage({
        type: 'success',
        message: '部署成功'
      })
    })
    .catch(() => {
    })
}

const handleCancel = (certificate: Certificate) => {
  ElMessageBox.confirm(
    '确认取消吗?待申请状态可去阿里云后台继续申请证书，或者取消后在本系统重新申请',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  )
    .then(async () => {
      try {
        await cancelCertificate({ instanceId: certificate.instanceId })
        ElMessage({
          type: 'success',
          message: '取消成功'
        })
      } finally {
        await handleSync()
      }
    })
    .catch(() => {
    })
}

onMounted(() => {
  handleQuery()
})
</script>

<style scoped>
.custom-block.tip {
  padding: 8px 16px;
  background-color: rgba(64, 158, 255, .1);
  border-radius: 4px;
  border-left: 5px solid #409eff;
  margin: 20px 0;
}

.custom-block .custom-block-title {
  font-weight: 700;
}
</style>