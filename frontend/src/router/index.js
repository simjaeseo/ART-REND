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
import ArtistDetailPageView from '@/views/artwork/ArtistDetailPageView.vue'
import GenrePageView from '@/views/artwork/GenrePageView.vue'
import GenreDetailPageView from '@/views/artwork/GenreDetailPageView.vue'
import MovementPageView from '@/views/artwork/MovementPageView.vue'
import MovementDetailPageView from '@/views/artwork/MovementDetailPageView.vue'
import UserInfoView from '@/views/accounts/UserInfoView.vue'
import UnregisterView from '@/views/accounts/UnregisterView.vue'
import NotFoundView from '@/views/accounts/NotFoundView.vue'
// import { mapGetters } from 'vuex'

const requireAuth = () => (from, to, next) => {
	const token = localStorage.getItem('token')
	if (token) {
		return next()
	}
	alert('로그인이 필요한 서비스입니다.')
	next('/')
}
// const isExisted = () => (from, to, next) => {
// 	const isExisted = mapGetters.isExisted
// 	if (isExisted.isExisted) {
// 		alert('접근할수없는페이지입니다.')
// 		return from()
// 	}
// 	next()
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
		// beforeEnter: isExisted(),
	},
	{
		path: '/select',
		name: 'SelectImage',
		component: SelectImageView,
		// beforeEnter: isExisted(),
	},
	{
		path: '/mypage/:memberId',
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
		path: '/detail/:artworkId',
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
		// beforeEnter: isExisted(),
	},
	{
		path: '/artist',
		name: 'ArtistPage',
		component: ArtistPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/artist/:name',
		name: 'ArtistDetailPage',
		component: ArtistDetailPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/genre',
		name: 'GenrePage',
		component: GenrePageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/genre/:name',
		name: 'GenreDetailPage',
		component: GenreDetailPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/movement',
		name: 'MovementPage',
		component: MovementPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/movement/:name',
		name: 'MovementDetailPage',
		component: MovementDetailPageView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/user/form',
		name: 'UserInfo',
		component: UserInfoView,
		// beforeEnter: isExisted(),
	},
	{
		path: '/unregister',
		name: 'Unregister',
		component: UnregisterView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/404',
		name: 'NotFound',
		component: NotFoundView,
	},
	{
		path: '/:pathMatch(.*)*',
		redirect: '/404',
	},
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
})

export default router
