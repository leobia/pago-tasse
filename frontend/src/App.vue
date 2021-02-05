<template>
  <div id="app">

    <vs-navbar text-white square center-collapsed color="dark" v-model="active">
      <template #left>
        <img src="/logo2.png" alt="">
      </template>
      <vs-navbar-item :active="active === '/home'" id="home" to="/" @click="active = '/home'">
        Home
      </vs-navbar-item>
      <vs-navbar-item :active="active === '/history'" id="history"
                      @click="active = '/history'" to="/history">
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
      active: '/home',
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
