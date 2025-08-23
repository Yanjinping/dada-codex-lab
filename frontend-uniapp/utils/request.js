export default function request(options) {
  const app = getApp();
  const baseUrl = app?.globalData?.BASE_URL || '';
  return new Promise((resolve, reject) => {
    uni.request({
      url: baseUrl + options.url,
      method: options.method || 'GET',
      data: options.data || {},
      success: (res) => resolve(res.data),
      fail: reject
    });
  });
}
