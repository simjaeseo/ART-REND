import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App)

app.use(store)
app.use(router)
app.mount('#app')

window.Kakao.init('4c906933ce965e211fe9908cafa8951b')
