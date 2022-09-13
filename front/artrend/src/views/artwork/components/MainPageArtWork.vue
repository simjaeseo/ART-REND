<template>
	<div>
		<div class="image-list">
			<div v-for="image in state.imageNum" :key="image" class="main-image-box">
				<img
					:src="require(`@/assets/main-img/${image}.jpg`)"
					alt="image"
					class="main-image"
				/>
			</div>
		</div>
		<!-- <div id="scrollbar">
			<div class="wrapper">
				<div
					v-for="image in state.imageNum"
					:key="image"
					class="main-image-box item"
				>
					<img
						:src="require(`@/assets/main-img/${image}.jpg`)"
						alt="image"
						class="main-image"
					/>
				</div>
			</div>
		</div> -->
	</div>
</template>

<script>
import { reactive } from 'vue'
// import Scrollbar from 'smooth-scrollbar'
// import gsap from 'gsap'

export default {
	name: 'MainPageArtWork',
	setup() {
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		})
		return {
			state,
		}
	},
}
</script>

<style scoped>
.image-list {
	display: flex;
	flex-wrap: nowrap;
	align-items: flex-start;
}

.main-image-box {
	height: 70vh;
	padding: 0% 1.5%;
}

.main-image {
	display: block;
	height: 100%;
}
</style>
