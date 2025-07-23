import './assets/main.css'
import './assets/style.css'
import ja from './lang/ja.json';

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createVuetify } from 'vuetify';
import { createI18n } from 'vue-i18n';

import App from './App.vue'
import router from './router'
import 'vuetify/styles';
import '@mdi/font/css/materialdesignicons.css';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App)
const vuetify = createVuetify({
  components,
  directives,
})

const i18n = createI18n({
  legacy: false,
  locale: 'ja',
  messages: {
    ja
  }
});

app.use(createPinia())
app.use(router)
app.use(vuetify);
app.use(i18n);
app.mount('#app')
app.use(ElementPlus)


