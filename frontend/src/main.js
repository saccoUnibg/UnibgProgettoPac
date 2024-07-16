import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const app = createApp(App)
    .use(router);

axios.defaults.withCredentials = true;
axios.defaults.baseURL = 'http://localhost:8080';
app.config.globalProperties.axios = axios;

app.mount('#app');