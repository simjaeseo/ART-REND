import axios from 'axios'
import drf from '@/api/api'
import router from '@/router/index'

export default {
	state: {
		token: localStorage.getItem('token') || '',
		userId: null,
		likeArtWorkList: [],
	},
	getters: {
		authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
		isLoggedIn: state => !!state.token,
		userId: state => state.userId,
		likeArtWorkList: state => state.likeArtWorkList,
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

		selectForm({ getters }, img) {
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
				})
				.catch(() => {
					alert('그림을 2개 선택해주세요.')
				})
		},

		likeArtWorkList({ getters, commit }, artworkId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.like(),
				method: 'get',
				data: {
					paintingId: artworkId,
					memberId: getters.userId,
				},
			})
				.then(res => {
					console.log(res.data.data.content)
					commit('SET_LIKE_ART_WORK_LIST', res.data.data.content)
				})
				.catch(() => {
					alert('에러가발생했다')
				})
		},
	},
}
