import axios from 'axios'
import drf from '@/api/api'
import router from '@/router/index'

export default {
	state: {
		token: localStorage.getItem('token') || '',
		userId: null,
	},
	getters: {
		authHeader: state => ({ Authorization: `Token ${state.token}` }),
		isLoggedIn: state => !!state.token,
		userId: state => state.userId,
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token
			console.log('뮤테이션이다!')
		},
		SET_USER_ID(state, id) {
			state.userId = id
		},
	},
	actions: {
		saveToken({ commit }, token) {
			commit('SET_TOKEN', token)
			localStorage.setItem('token', token)
			console.log('로컬스토리지저장했다!')
		},

		nickNameForm({ getters }, userNickName) {
			axios({
				headers: getters.authHeader,
				url: drf.auth.nickNameForm(getters.userId),
				method: 'put',
				data: {
					nickname: userNickName,
				},
			}).then(() => {
				router.push({ name: 'SelectImage' })
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
	},
}
