<template>
	<div>
		{{ isLoggedIn }}
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import jwt_decode from 'jwt-decode'

export default {
	name: 'KakaoAuthView',
	setup() {
		const router = useRouter()
		const store = useStore()
		const isLoggedIn = computed(() => store.getters.isLoggedIn)
		const url = new URLSearchParams(location.search)
		const token = url.get('accessToken')
		const decodeAccessToken = jwt_decode(token)
		const nickName = url.get('isNickname')
		const selected = url.get('isPainting')

		if (nickName == 'false') {
			router.push({ name: 'SignUp' })
		} else if (selected == 'false') {
			router.push({ name: 'SelectImage' })
		} else {
			router.push({ name: 'Main' })
		}

		// console.log(decodeAccessToken)
		// console.log(nickName)
		// console.log(selected)

		store.commit('SET_USER_ID', decodeAccessToken.id)
		store.dispatch('saveToken', token)

		return {
			isLoggedIn,
			decodeAccessToken,
			token,
			url,
		}
	},
}
</script>

<style scoped></style>
