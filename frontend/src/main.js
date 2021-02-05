import Vue from 'vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Vuesax from 'vuesax';
import App from './App.vue';
import './registerServiceWorker';
import router from './router';
import 'vuesax/dist/vuesax.css';
import 'boxicons';
import 'boxicons/css/boxicons.min.css';

Vue.config.productionTip = false;

Vue.use(Vuesax, {
  colors: {
    primary: '#5b3cc4',
    success: 'rgb(23, 201, 100)',
    danger: 'rgb(242, 19, 93)',
    warning: 'rgb(255, 130, 0)',
    dark: 'rgb(36, 33, 69)',
  },

});

Vue.use(VueAxios, axios);

new Vue({
  router,
  mounted() {
    this.enableInterceptor();
  },
  data: {
    loading: null,
    axiosInterceptor: null,
    loadingCounter: 0,
  },
  methods: {
    enableInterceptor() {
      this.axiosInterceptor = this.axios.interceptors.request.use((config) => {
        this.loading = this.$vs.loading();
        this.loadingCounter += 1;
        if (config && config.message) {
          this.showSuccess(config.message);
        }

        return config;
      }, (error) => {
        this.loadingCounter -= 1;
        if (this.loadingCounter <= 0) {
          this.loading.close();
        }
        this.showError(error.message);
        return Promise.reject(error);
      });

      this.axios.interceptors.response.use((response) => {
        this.loadingCounter -= 1;
        if (this.loadingCounter <= 0) {
          this.loading.close();
        }
        return response;
      }, (error) => {
        this.loadingCounter -= 1;
        if (this.loadingCounter <= 0) {
          this.loading.close();
        }
        this.showError(error.message);
        return Promise.reject(error);
      });
    },

    showError(message) {
      this.$vs.notification({
        color: 'danger',
        position: 'top-center',
        icon: '<i class=\'bx bxs-bug\' ></i>',
        title: 'Error',
        text: message,
      });
    },

    showSuccess(message) {
      this.$vs.notification({
        color: 'success',
        position: 'top-center',
        icon: '<i class=\'bx bxs-check-circle\' ></i>',
        text: message,
      });
    },
  },
  render: (h) => h(App),
}).$mount('#app');
