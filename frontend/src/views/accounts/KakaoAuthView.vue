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
		const provider = url.get('provider')
		const providerId = url.get('providerId')
		const isExisted = url.get('isExisted')
		const isNickName = url.get('isNickname')
		const isSelected = url.get('isSelectPainting')

		const data = {
			isExisted: isExisted,
			isNickName: isNickName,
			isSelected: isSelected,
		}
		store.commit('SET_USER_IS_EXISTED', data)
		store.commit('SET_PROVIDER', provider)
		store.commit('SET_PROVIDER_ID', providerId)

		if (isExisted == 'false') {
			router.push({ name: 'UserInfo' })
		} else if (isNickName == 'false') {
			router.push({ name: 'SignUp' })
		} else if (isSelected == 'false') {
			router.push({ name: 'SelectImage' })
		} else if (
			isExisted == 'true' &&
			isNickName == 'true' &&
			isSelected == 'true'
		) {
			const token = url.get('accessToken')
			const decodeAccessToken = jwt_decode(token)
			store.commit('SET_USER_ID', decodeAccessToken.id)
			store.dispatch('saveToken', token)
			window.location.href = 'http://localhost:3002/main'
		}
		return {
			isLoggedIn,
			url,
		}
	},
}
</script>

<style scoped></style>
