import { createWebHistory, createRouter } from 'vue-router';

//로그인 페이지
import LoginView from '@/views/accounts/LoginView.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginView,
  }  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;