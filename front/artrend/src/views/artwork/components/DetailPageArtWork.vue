<template>
	<div>
		<div v-for="image in state.imageNum" :key="image" class="img-wrapper">
			<a href="#" target="_blank" rel="noopener">
				<div class="image-box">
					<img :src="require(`@/assets/main-img/${image}.jpg`)" alt="image" />
					<div class="image-info">
						<div class="title">Street Man Fighter</div>
						<div class="name">2022</div>
						<div class="name">Tom Smith</div>
					</div>
				</div>
			</a>
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

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');

.img-wrapper {
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 60vh;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(200px);
	transition: 1s;
}

.img-wrapper:hover {
	min-height: 75vh;
	transform: rotate(90deg) translateZ(0px) translateX(200px) scale(1.2);
}

.img-wrapper a {
	overflow: hidden;
	display: block;
	box-shadow: 15px 15px 60px rgb(255, 255, 255, 0.3);
	/* background-color: rgba(255, 255, 255, 0.8);
	padding: 0.5vh; */
}

.image-box {
	transition: 0.5s;
	vertical-align: top;
	filter: grayscale(70%);
}

img {
	max-width: 45vh;
	max-height: 50vh;
}

a:hover .image-box {
	filter: none;
}

a:hover .image-info {
	display: none;
}

.image-info {
	position: absolute;
	bottom: 15px;
	right: 20px;
	text-align: end;
	color: white;
	transition: 1s;
}
.title {
	font-family: 'Noto Sans', sans-serif;
	font-size: 2vh;
	font-weight: 600;
}

.name {
	font-family: 'Noto Sans', sans-serif;
	font-size: 1.2vh;
	font-weight: 200;
}
</style>
