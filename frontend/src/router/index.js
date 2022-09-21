import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/accounts/LoginView.vue'
import SignUpView from '@/views/accounts/SignUpView.vue'
import SelectImageView from '@/views/accounts/SelectImageView.vue'
import MyPageView from '@/views/accounts/MyPageView.vue'
import MainPageView from '@/views/artwork/MainPageView.vue'
import DetailPageView from '@/views/artwork/DetailPageView.vue'
import ArtWorksArrayView from '@/views/artwork/ArtWorksArrayView.vue'
import KakaoAuthView from '@/views/accounts/KakaoAuthView.vue'

const requireAuth = () => (from, to, next) => {
	const token = localStorage.getItem('token')
	if (token) {
		return next()
	}
	next('/')
}

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
		beforeEnter: requireAuth(),
	},
	{
		path: '/select',
		name: 'SelectImage',
		component: SelectImageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/mypage',
		name: 'MyPage',
		component: MyPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/main',
		name: 'Main',
		component: MainPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/detail',
		name: 'Detail',
		component: DetailPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/artworks',
		name: 'Artworks',
		component: ArtWorksArrayView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/auth',
		name: 'KakaoAuth',
		component: KakaoAuthView,
	},
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
})

export default router
