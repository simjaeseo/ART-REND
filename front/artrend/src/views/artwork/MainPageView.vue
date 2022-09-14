<template>
	<div class="main-bg">
		<main-page-art-work />
	</div>
</template>

<script>
import MainPageArtWork from '@/views/artwork/components/MainPageArtWork.vue'

export default {
	name: 'MainPageView',
	components: { MainPageArtWork },
}
</script>

<style scoped>
.main-bg {
	background-color: black;
	height: 100vh;
	width: 100%;
	overflow-y: hidden;
}
</style>
