import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/accounts/LoginView.vue'
import SignUpView from '@/views/accounts/SignUpView.vue'
import SelectImageView from '@/views/accounts/SelectImageView.vue'
import MyPageView from '@/views/accounts/MyPageView.vue'
import MainPageView from '@/views/artwork/MainPageView.vue'
import DetailPageView from '@/views/artwork/DetailPageView.vue'
import ArtWorksArrayView from '@/views/artwork/ArtWorksArrayView.vue'
import KakaoAuthView from '@/views/accounts/KakaoAuthView.vue'
import ArtistPageView from '@/views/artwork/ArtistPageView.vue'

// 라우터가드는 개발 끝나고 주석 풀면 됨
// const requireAuth = () => (from, to, next) => {
// 	const token = localStorage.getItem('token')
// 	if (token) {
// 		return next()
// 	}
// 	alert('로그인이 필요한 서비스입니다.')
// 	next('/')
// }

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
		// beforeEnter: requireAuth(),
	},
	{
		path: '/select',
		name: 'SelectImage',
		component: SelectImageView,
		// beforeEnter: requireAuth(),
	},
	{
		path: '/mypage',
		name: 'MyPage',
		component: MyPageView,
		// beforeEnter: requireAuth(),
	},
	{
		path: '/main',
		name: 'Main',
		component: MainPageView,
		// beforeEnter: requireAuth(),
	},
	{
		path: '/detail/:artworkId',
		name: 'Detail',
		component: DetailPageView,
		// beforeEnter: requireAuth(),
	},
	{
		path: '/artworks',
		name: 'Artworks',
		component: ArtWorksArrayView,
		// beforeEnter: requireAuth(),
	},
	{
		path: '/auth',
		name: 'KakaoAuth',
		component: KakaoAuthView,
	},
	{
		path: '/artist',
		name: 'ArtistPage',
		component: ArtistPageView,
	},
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
})

export default router
