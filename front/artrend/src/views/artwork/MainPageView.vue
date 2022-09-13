<template>
	<div class="main-bg">
		<div class="artwork">
			<main-page-art-work />
		</div>
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
}

.artwork {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}
</style>
