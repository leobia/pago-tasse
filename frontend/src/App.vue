<template>
  <div id="app">

    <vs-navbar text-white square center-collapsed color="dark">
      <template #left>
        <img src="@/assets/logo.png" alt="" sizes="32" >
      </template>
      <vs-navbar-item :active="activeTab === '/'" id="home" to="/" @click="activeTab = '/'">
        Home
      </vs-navbar-item>
      <vs-navbar-item :active="activeTab === '/history'" id="history"
                      @click="activeTab = '/history'" to="/history">
        History
      </vs-navbar-item>
    </vs-navbar>

    <router-view class="page-content"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: '/',
    };
  },

  mounted() {
    this.active = this.$router.currentRoute.path;
  },
};
</script>

<style>

#app {
  font-family: Poppins,-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,
  Fira Sans,Droid Sans,Helvetica Neue,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

</style>
