<template>
	<div>
		{{ isLoggedIn }}
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { computed } from 'vue'
import jwt_decode from 'jwt-decode'
export default {
	name: 'KakaoAuthView',
	setup() {
		const store = useStore()
		const isLoggedIn = computed(() => store.getters.isLoggedIn)
		const url = new URLSearchParams(location.search)
		const token = url.get('accessToken')
		const decodeAccessToken = jwt_decode(token)
		console.log(decodeAccessToken)
		console.log(decodeAccessToken.id)
		console.log(token)
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

<style></style>
