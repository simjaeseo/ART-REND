<template>
	<div class="main-bg">
		<main-page-art-work />
	</div>
</template>

<script>
import MainPageArtWork from '@/views/artwork/components/MainPageArtWork.vue'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
	name: 'MainPageView',
	components: { MainPageArtWork },
	setup() {
		// 머문시간 확인 : 나중에 없애기
		const store = useStore()
		const inTime = computed(() => store.getters.inTime)
		const outTime = computed(() => store.getters.outTime)
		console.log(inTime.value)
		console.log(outTime.value)
		return {}
	},
}
</script>

<style scoped>
.main-bg {
	background-color: rgb(0, 0, 0);
	height: 100vh;
	width: 100%;
	overflow-y: hidden;
}

/* hide scrollbar */
::-webkit-scrollbar {
	width: 0px;
	height: 0px;
}

::-webkit-scrollbar-button {
	width: 0px;
	height: 0px;
}
</style>
