<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div v-for="image in state.imageNum" :key="image" class="img-wrapper">
				<a href="#" target="_blank" rel="noopener">
					<img
						:src="require(`@/assets/main-img/${image}.jpg`)"
						alt="image"
						class="main-image"
					/>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
import { reactive } from 'vue'

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

<style lang="scss" scoped>
/* hide scrollbar */
::-webkit-scrollbar {
	width: 1px;
	height: 1px;
}

::-webkit-scrollbar-button {
	width: 1px;
	height: 1px;
}

.external {
	height: 100vh;
}

.horizontal-scroll-wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vh;
	transform: rotate(-90deg) translate3d(0, -100vh, 0);
	transform-origin: right top;
	overflow-y: auto;
	overflow-x: hidden;
	padding: 0;
	height: 100vw;
	transform-style: preserve-3d;
	perspective: 1px;
	padding-bottom: 10rem;
}
.img-wrapper {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	// min-height: 70vh;
	min-height: 40vh;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0.1px) translateX(0px) translateY(-3vh);
	transition: 1s;
}

.img-wrapper:hover {
	// min-height: 90vh;
	min-height: 65vh;
	// transform: rotate(90deg) scale(1.1);
}

.img-wrapper a {
	overflow: hidden;
	display: block;
	box-shadow: 0px 0px 80px rgb(255, 255, 255, 0.25);
	padding: 1vh;
}

img {
	max-height: 60vh;
	transition: 0.5s;
	vertical-align: top;
	max-width: 45vh;
	filter: saturate(40%) sepia(30%) hue-rotate(5deg);
}

a:hover img {
	filter: none;
}
</style>
