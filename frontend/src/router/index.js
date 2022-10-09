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
import ExhibitionView from '@/views/artwork/ExhibitionView.vue'
import { computed } from 'vue'
import store from '@/store'

const requireAuth = () => (from, to, next) => {
	const token = localStorage.getItem('token')
	if (token) {
		return next()
	}
	alert('로그인이 필요한 서비스입니다.')
	next('/')
}
const preventInfoPage = () => (from, to, next) => {
	const InfoForm = computed(() => store.getters.preventInfoPage)
	const isExisted = computed(() => store.getters.isExisted)
	if (InfoForm.value == false || isExisted.value.isExisted == 'false') {
		return next()
	}
	next('/main')
}
const preventSelectPage = () => (from, to, next) => {
	const select = computed(() => store.getters.preventSelectPage)
	const isExisted = computed(() => store.getters.isExisted)
	if (select.value == false || isExisted.value.isSelected == 'false') {
		return next()
	}
	next('/main')
}
const preventSignupPage = () => (from, to, next) => {
	const signup = computed(() => store.getters.preventSignupPage)
	const isExisted = computed(() => store.getters.isExisted)
	if (signup.value == false || isExisted.value.isNickName == 'false') {
		return next()
	}
	next('/main')
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
		beforeEnter: preventSignupPage(),
	},
	{
		path: '/select',
		name: 'SelectImage',
		component: SelectImageView,
		beforeEnter: preventSelectPage(),
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
		path: '/exhibition',
		name: 'ExhibitionPage',
		component: ExhibitionView,
		beforeEnter: requireAuth(),
	},
	{
		path: '/user/form',
		name: 'UserInfo',
		component: UserInfoView,
		beforeEnter: preventInfoPage(),
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
