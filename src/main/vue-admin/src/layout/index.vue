<template>
  <el-container>
    <el-header>
      <div>证书自动更新</div>
      <div>
        <el-button type="primary" text @click="handleLogout">退出</el-button>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu
          :default-active="activeMenu"
          class="el-menu-vertical"
          :router="true"
        >
          <el-menu-item index="/">
            首页
          </el-menu-item>
          <el-menu-item index="/certificate">
            证书列表
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <RouterView />
      </el-main>
    </el-container>
  </el-container>

</template>

<script setup lang="ts">
import { RouterView, useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'
import useStore from '@/stores'

const { user } = useStore()
const route = useRoute()
const router = useRouter();

const activeMenu = computed(() => {
  const { meta, path } = route
  // if set path, the sidebar will highlight the path you set
  if (meta.activeMenu) {
    return meta.activeMenu as string
  }
  return path
})

const handleLogout=()=>{
  user.logout().then(()=>{
    router.push(`/login?redirect=${route.fullPath}`);
  })
}
</script>

<style scoped>
.el-header {
  border-bottom: 1px solid #ededed;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.el-menu-vertical {
  border-right: none;
}
</style>