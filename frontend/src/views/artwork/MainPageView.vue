<template>
	<div class="main-bg">
		<main-page-art-work />
	</div>
</template>

<script>
import MainPageArtWork from '@/views/artwork/components/MainPageArtWork.vue'
import { useStore } from 'vuex'
export default {
	name: 'MainPageView',
	components: { MainPageArtWork },
	setup() {
		const store = useStore()
		store.dispatch('getMainBasedOnActionLog')
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
