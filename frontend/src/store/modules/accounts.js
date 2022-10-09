import axios from 'axios'
import drf from '@/api/api'

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
		convertList: [],
		preventInfoPage: false,
		preventSignupPage: false,
		preventSelectPage: false,
		convertListAll: [],
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
		convertList: state => state.convertList,
		preventInfoPage: state => state.preventInfoPage,
		preventSelectPage: state => state.preventSelectPage,
		preventSignupPage: state => state.preventSignupPage,
		convertListAll: state => state.convertListAll,
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
		},
		SET_MAIN_IMAGE(state, data) {
			state.mainImage = data
		},
		SET_SELECT_LIST(state, data) {
			state.selectList = data
		},
		SET_CONVERT_LIST(state, data) {
			state.convertList = data
		},
		PREVENT_INFO_FORM_PAGE(state, value) {
			state.preventInfoPage = value
		},
		PREVENT_SELECT_PAGE(state, value) {
			state.preventSelectPage = value
		},
		PREVENT_SIGNUP_PAGE(state, value) {
			state.preventSignupPage = value
		},
		SET_CONVERT_LIST_ALL(state, data) {
			state.convertListAll = data
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
					window.location.href = 'http://localhost:3002/select'
				})
				.catch(() => {
					alert('서비스가 비정상적입니다. 다시 시도해주세요.')
				})
		},

		doubleCheck({ getters, dispatch, commit }, userNickName) {
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
					commit('PREVENT_SIGNUP_PAGE', true)
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
					commit('SET_SELECT_LIST', res.data.data)
				})
				.catch(() => alert('서비스가 비정상적입니다. 다시 시도해주세요.'))
		},
		getMainBasedOnSelected({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getMainBasedOnSelected(getters.userId),
				method: 'get',
			})
				.then(res => {
					commit('SET_MAIN_IMAGE', res.data.data)
				})
				.catch(() => alert('서비스가 비정상적입니다. 다시 시도해주세요.'))
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
					window.location.href = 'http://localhost:3002/main'
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
					alert('서비스가 비정상적입니다. 다시 시도해주세요.')
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
					const next = confirm('로그아웃하시겠습니까?')
					if (next) {
						dispatch('removeToken')
						commit('REMOVE_TOKEN')
						window.location.href = 'http://localhost:3002/'
						localStorage.setItem('vuex', '')
					}
				})
				.catch(() => alert('서비스가 비정상적입니다. 다시 시도해주세요.'))
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
				.catch(() => alert('서비스가 비정상적입니다. 다시 시도해주세요.'))
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
		},
		getImageConvert({ getters, commit }, memberId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getImageConvert(memberId),
				method: 'get',
			})
				.then(res => {
					commit('SET_CONVERT_LIST', res.data.data)
				})
				.catch(() => {
					alert('서비스가 비정상적입니다. 다시 시도해주세요.')
				})
		},
		getImageConvertAll({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getImageConvertAll(),
				method: 'get',
			})
				.then(res => {
					console.log(res)
					commit('SET_CONVERT_LIST_ALL', res.data.data)
				})
				.catch(() => {
					alert('서비스가 비정상적입니다. 다시 시도해주세요.')
				})
		},
	},
}
