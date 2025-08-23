<template>
  <slot />
</template>

<script>
export default {
  onLaunch() {
    this.globalData = this.globalData || {};
    this.globalData.BASE_URL = 'http://localhost:8080';
  }
};
</script>

<style>
/* global styles */
</style>
