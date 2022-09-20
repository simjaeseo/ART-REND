export default {
	state: {
		token: localStorage.getItem('token') || '',
	},
	getters: {
		isLoggedIn: state => !!state.token,
	},
	mutations: {
		SET_TOKEN(state, token) {
			state.token = token
			console.log('뮤테이션이다!')
		},
	},
	actions: {
		saveToken({ commit }, token) {
			commit('SET_TOKEN', token)
			localStorage.setItem('token', token)
			console.log('로컬스토리지저장했다!')
		},
	},
}
