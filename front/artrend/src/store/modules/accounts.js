export default {
	state: {
		token: '',
	},
	getters: {
		isLoggedIn: state => !!state.token,
	},
	mutations: {
		SET_TOKEN(state, data) {
			console.log(data)
			state.token = data
			console.log(state.token)
		},
	},
	actions: {},
}
