<template>
  <view class="title-bar">
    <text class="title">{{ title }}</text>
  </view>
</template>

<script setup>
const props = defineProps({
  title: {
    type: String,
    default: ''
  }
});
</script>

<style>
.title-bar {
  padding: 16rpx;
  background-color: #f8f8f8;
}
.title {
  font-size: 32rpx;
  font-weight: bold;
}
</style>
