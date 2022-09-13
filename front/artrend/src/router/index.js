import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/accounts/LoginView.vue';
import SignUpView from '@/views/accounts/SignUpView.vue';
import SelectImageView from '@/views/accounts/SelectImageView.vue';

const routes = [
  {
    path: '/',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUpView,
  },
  {
    path: '/select',
    name: 'SelectImage',
    component: SelectImageView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
