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
    label: 'DV 类型的证书',
    value: 'DV'
  },
  {
    label: 'EV 类型的证书',
    value: 'EV'
  },
  {
    label: 'OV 类型的证书',
    value: 'OV'
  },
  {
    label: '免费证书',
    value: 'FREE'
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
