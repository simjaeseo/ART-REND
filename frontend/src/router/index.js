import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/accounts/LoginView.vue'
import SignUpView from '@/views/accounts/SignUpView.vue'
import SelectImageView from '@/views/accounts/SelectImageView.vue'
import MyPageView from '@/views/accounts/MyPageView.vue'
import MainPageView from '@/views/artwork/MainPageView.vue'
import DetailPageView from '@/views/artwork/DetailPageView.vue'
import ArtWorksArrayView from '@/views/artwork/ArtWorksArrayView.vue'
import KakaoAuthView from '@/views/accounts/KakaoAuthView.vue'

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
	{
		path: '/mypage',
		name: 'MyPage',
		component: MyPageView,
	},
	{
		path: '/main',
		name: 'Main',
		component: MainPageView,
	},
	{
		path: '/detail',
		name: 'Detail',
		component: DetailPageView,
	},
	{
		path: '/artworks',
		name: 'Artworks',
		component: ArtWorksArrayView,
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
