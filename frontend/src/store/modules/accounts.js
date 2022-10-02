import axios from 'axios'
import drf from '@/api/api'
import router from '@/router/index'

export default {
	state: {
		token: localStorage.getItem('token') || '',
		userId: null,
		likeArtWorkList: [],
		allUsers: [],
		userNickName: '',
		provider: null,
		providerId: null,
		isExisted: [],
		mainImage: [],
		selectList: [],
	},
	getters: {
		authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
		isLoggedIn: state => !!state.token,
		userId: state => state.userId,
		likeArtWorkList: state => state.likeArtWorkList,
		allUsers: state => state.allUsers,
		userNickName: state => state.userNickName,
		provider: state => state.provider,
		providerId: state => state.providerId,
		mainImage: state => state.mainImage,
		selectList: state => state.selectList,
		isExisted: state => state.isExisted,
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token
		},
		SET_USER_ID(state, id) {
			state.userId = id
		},
		SET_LIKE_ART_WORK_LIST(state, data) {
			state.likeArtWorkList = data
		},
		REMOVE_TOKEN(state) {
			state.userId = null
		},
		SET_ALL_USERS(state, users) {
			state.allUsers = users
		},
		SET_USER_NICKNAME(state, nickname) {
			state.userNickName = nickname
		},
		SET_PROVIDER(state, provider) {
			state.provider = provider
		},
		SET_PROVIDER_ID(state, providerId) {
			state.providerId = providerId
		},
		SET_USER_IS_EXISTED(state, data) {
			state.isExisted = data
			console.log(state.isExisted)
		},
		SET_MAIN_IMAGE(state, data) {
			state.mainImage = data
			console.log(state.mainImage)
		},
		SET_SELECT_LIST(state, data) {
			state.selectList = data
		},
	},
	actions: {
		saveToken({ commit }, token) {
			commit('SET_TOKEN', token)
			localStorage.setItem('token', token)
		},

		nickNameForm({ getters }, userNickName) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.nickNameForm(getters.userId),
				method: 'put',
				data: {
					nickname: userNickName,
				},
			})
				.then(() => {
					router.push({ name: 'SelectImage' })
				})
				.catch(() => {
					alert('에러발생!!!!!!!!!!!')
				})
		},

		doubleCheck({ getters, dispatch }, userNickName) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.nickNameCheck(getters.userId),
				method: 'post',
				data: {
					nickname: userNickName,
				},
			})
				.then(() => {
					dispatch('nickNameForm', userNickName)
				})
				.catch(() => {
					alert('사용할 수 없는 닉네임입니다.')
				})
		},
		getSelectImg({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.getSelectImg(),
				method: 'get',
			})
				.then(res => {
					console.log(res)
					commit('SET_SELECT_LIST', res.data.data)
				})
				.catch(err => console.log(err))
		},
		getMainBasedOnSelected({ getters }) {
			console.log('셀렉트후에선택된그림기반으로한번만가져오자!')
			console.log(getters.userId)
			axios({
				headers: getters.authHeader,
				url: drf.business.getMainBasedOnSelected(getters.userId),
				method: 'get',
			})
				.then(res => {
					console.log(res)
				})
				.catch(err => console.log(err))
		},
		selectForm({ getters, dispatch }, img) {
			axios({
				headers: getters.authHeader,
				url: drf.business.select(),
				method: 'post',
				data: {
					memberId: getters.userId,
					paintings: img,
				},
			})
				.then(() => {
					router.push({ name: 'Main' })
					dispatch('getMainBasedOnSelected')
				})
				.catch(() => {
					alert('그림을 2개 선택해주세요.')
				})
		},

		likeArtWorkList({ getters, commit }, memberId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.like(),
				method: 'get',
				params: {
					memberId: memberId,
				},
			})
				.then(res => {
					commit('SET_LIKE_ART_WORK_LIST', res.data.data)
				})
				.catch(() => {
					alert('에러가발생했다')
				})
		},
		removeToken({ commit }) {
			commit('SET_TOKEN', '')
			localStorage.setItem('token', '')
		},
		logout({ dispatch, commit, getters }) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.logout(),
				method: 'get',
			})
				.then(() => {
					dispatch('removeToken')
					commit('REMOVE_TOKEN')
					localStorage.setItem('vuex', '')
					confirm('로그아웃하시겠습니까?')
					router.push({ name: 'Login' })
				})
				.catch(err => console.log(err))
		},

		getAllUsers({ commit, getters }) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.getAllUsers(),
				method: 'get',
			})
				.then(res => {
					commit('SET_ALL_USERS', res.data)
				})
				.catch(err => console.log(err))
		},

		getUserNickname({ commit, getters }, memberId) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.getUserNickname(memberId),
				method: 'get',
			})
				.then(res => {
					commit('SET_USER_NICKNAME', res.data.data.nickname)
				})
				.catch(err => console.log(err))
		},

		modifyNickName({ getters }, userNickName) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.getUserNickname(getters.userId),
				method: 'put',
				data: {
					nickname: userNickName,
				},
			})
				.then(res => {
					console.log(res)
				})
				.catch(err => {
					console.log(err)
				})
		},
		getImageConvert({ getters }, memberId) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.getImageConvert(memberId),
				method: 'get',
			})
				.then(res => console.log(res))
				.catch(err => console.log(err))
		},
	},
}
