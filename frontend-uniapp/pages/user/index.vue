<template>
  <view class="page">
    <TitleBar title="Users" />
    <view class="search-bar">
      <input v-model="kw" placeholder="Search users" @confirm="onSearch" />
      <button @click="onSearch">Search</button>
    </view>
    <scroll-view
      class="list"
      scroll-y
      :refresher-enabled="true"
      :refresher-triggered="refreshing"
      @refresherrefresh="onRefresh"
      @scrolltolower="loadMore"
    >
      <view v-for="user in users" :key="user.id" class="item">{{ user.name }}</view>
      <view v-if="loading" class="loading">Loading...</view>
      <view v-if="finished" class="finished">No more</view>
    </scroll-view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import TitleBar from '@/components/header/TitleBar.vue';
import { fetchUserPage } from '@/api/user.js';

const kw = ref('');
const page = ref(1);
const size = 20;
const users = ref([]);
const loading = ref(false);
const finished = ref(false);
const refreshing = ref(false);

const load = async (reset = false) => {
  if (loading.value || (finished.value && !reset)) {
    return;
  }
  loading.value = true;
  const data = await fetchUserPage({ page: page.value, size, kw: kw.value });
  const list = data?.data || data?.list || data || [];
  if (reset) {
    users.value = list;
  } else {
    users.value = users.value.concat(list);
  }
  if (!list || list.length < size) {
    finished.value = true;
  } else {
    finished.value = false;
  }
  loading.value = false;
};

const onSearch = () => {
  page.value = 1;
  finished.value = false;
  load(true);
};

const loadMore = () => {
  if (!finished.value) {
    page.value += 1;
    load();
  }
};

const onRefresh = async () => {
  refreshing.value = true;
  page.value = 1;
  finished.value = false;
  await load(true);
  refreshing.value = false;
};

onMounted(() => {
  load(true);
});
</script>

<style>
.page {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.search-bar {
  display: flex;
  padding: 10rpx;
}
.search-bar input {
  flex: 1;
  border: 1px solid #ccc;
  padding: 8rpx;
}
.search-bar button {
  margin-left: 8rpx;
}
.list {
  flex: 1;
}
.item {
  padding: 16rpx;
  border-bottom: 1px solid #eee;
}
.loading,
.finished {
  text-align: center;
  padding: 16rpx;
  color: #999;
}
</style>
