<template>
	<div>
		<div id="wrap1">
			<div id="wrap2">
				{{ isLoggedIn }}
				{{ state.token }}
				<img
					src="@/assets/kakao_login/en/kakao_login_large_narrow.png"
					alt="kakao-login"
					id="kakao-btn"
					@click="loginWithKakao"
					@keydown="loginWithKakao"
				/>
				<img
					src="@/assets/google_login/google_login.png"
					alt="google-login"
					id="google-btn"
					@click="loginWithGoogle"
					@keydown="loginWithGoogle"
				/>
			</div>
		</div>
	</div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
export default {
	name: 'LoginForm',
	setup() {
		const store = useStore()
		const state = reactive({
			nickname: '',
			email: '',
			token: '',
			istoken: false,
		})

		const loginWithKakao = function () {
			console.log(window.Kakao)
			window.Kakao.Auth.login({
				scope: 'profile_nickname, account_email, gender, age_range',
				success: getKakaoAccount,
			})
		}
		const getKakaoAccount = function () {
			window.Kakao.API.request({
				url: '/v2/user/me',
				success: res => {
					const kakao_account = res.kakao_account
					state.nickname = kakao_account.profile.nickname
					state.email = kakao_account.email
					state.token = localStorage.getItem(
						'kakao_5c75e8f0bca47d584945e323bf5abf49' || '',
					)
				},
				fail: error => {
					console.log(error)
				},
			}).then(() => {
				if (state.token) {
					state.istoken = true
					store.commit('SET_TOKEN', state.token)
				} else {
					state.istoken = false
				}
				// console.log(state.istoken)
				// console.log(state.nickname)
				// console.log(state.email)
				// console.log(state.token)
			})
		}
		const isLoggedIn = computed(() => store.getters.isLoggedIn)
		return {
			state,
			getKakaoAccount,
			loginWithKakao,
			isLoggedIn,
		}
	},
}
</script>

<style scoped>
#google-btn {
	display: block;
	width: 70%;
	height: 70%;
}
#google-btn:hover {
	display: block;
	cursor: pointer;
}
#kakao-btn {
	display: block;
	margin-bottom: 10px;
	width: 70%;
	height: 70%;
}
#kakao-btn:hover {
	display: block;
	cursor: pointer;
}
#wrap2 {
	margin: auto;
}
#wrap1 {
	height: 100vh;
	width: 100%;
	display: flex;
}
</style>
