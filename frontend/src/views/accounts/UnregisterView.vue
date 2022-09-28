<template>
	<div id="wrap1">
		<img
			:src="require(`@/assets/login-bg/${state.randomNum}.png`)"
			alt="img"
			id="background-img"
		/>
		<UnregisterForm />
	</div>
</template>

<script>
import { reactive, onBeforeMount } from 'vue'
import UnregisterForm from '@/views/accounts/components/UnregisterForm.vue'

export default {
	name: 'UnregisterView',
	components: { UnregisterForm },
	setup() {
		const state = reactive({
			randomNum: null,
		})
		onBeforeMount(() => {
			state.randomNum = Math.floor(Math.random() * (9 - 1) + 1)
		})

		return {
			state,
		}
	},
}
</script>

<style scoped>
#wrap1 {
	height: 100vh;
}
#background-img {
	height: 100vh;
	width: 100%;
	filter: brightness(20%);
	position: absolute;
	z-index: -1;
}
</style>
