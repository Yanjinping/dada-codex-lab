<script>
export default {
  onLaunch() {
    this.globalData = this.globalData || {};
    if (process.env.NODE_ENV === 'development') {
      this.globalData.BASE_URL = 'http://localhost:5349';
    }
  }
};
</script>

<style>
/* global styles */
</style>
