<template>
  <template v-if="showText">{{ text }}</template>
  <template v-else>
    <el-select v-model="value" :style="{ width }" @clear="handleClear" clearable>
      <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
    </el-select>
  </template>
</template>

<script setup lang="ts">
import { computed } from 'vue';

const options = [
  {
    label: '待申请',
    value: 'PAYED'
  },
  {
    label: '审核中',
    value: 'CHECKING'
  },
  {
    label: '审核失败',
    value: 'CHECKED_FAIL'
  },
  {
    label: '已签发',
    value: 'ISSUED'
  },
  {
    label: '即将过期',
    value: 'WILLEXPIRED'
  },
  {
    label: '已过期',
    value: 'EXPIRED'
  },
  {
    label: '未激活',
    value: 'NOTACTIVATED'
  },
  {
    label: '吊销完成',
    value: 'REVOKED'
  }
];

interface Props {
  modelValue?: string | undefined;
  showText?: boolean;
  width?: string;
}

const props = withDefaults(defineProps<Props>(), {
  showText: false,
  width: '100%'
});

const emit = defineEmits(['update:modelValue']);

const value = computed({
  get: () => props.modelValue,
  set: value => {
    emit('update:modelValue', value);
  }
});

const text = computed(() => {
  const option = options.find(x => x.value === props.modelValue);
  if (option) {
    return option.label;
  }
  return '';
});

const handleClear = () => {
  emit('update:modelValue', undefined);
};
</script>
