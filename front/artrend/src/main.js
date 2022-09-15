import { createApp } from 'vue'
import gAuthPlugin from 'vue3-google-oauth2'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App)
const gauthClientId =
	'356377478767-1a4lmsmmoneh7t278t5t4jlpgjshn7fn.apps.googleusercontent.com'

app.use(store)
app.use(router)
app.mount('#app')

window.Kakao.init('4c906933ce965e211fe9908cafa8951b')

app.use(gAuthPlugin, {
	clientId: gauthClientId,
	scope: 'email',
	prompt: 'consent',
})
