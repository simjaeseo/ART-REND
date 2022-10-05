<template>
	<div>
		<div id="wrap1">
			<div id="wrap2">
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
import { computed } from 'vue'
import { useStore } from 'vuex'
import drf from '@/api/api'

export default {
	name: 'LoginForm',
	setup() {
		const store = useStore()

		const loginWithKakao = function () {
			window.open(drf.auth.kakaoLogin(), '_self')
		}

		const loginWithGoogle = function () {
			window.open(drf.auth.googleLogin(), '_self')
		}

		const isLoggedIn = computed(() => store.getters.isLoggedIn)
		return {
			loginWithKakao,
			loginWithGoogle,
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
	overflow-y: hidden;
}
</style>
