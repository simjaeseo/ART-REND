<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div v-for="image in state.imageNum" :key="image" class="img-wrapper">
				<a href="#" target="_blank" rel="noopener">
					<div class="image-box">
						<button class="delete">delete</button>
						<img :src="require(`@/assets/main-img/${image}.jpg`)" alt="image" />
						<div class="image-info">
							<h3 class="title">Street Man Fighter, 2022</h3>
							<h5 class="name">Tom Smith</h5>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
import { reactive } from 'vue'

export default {
	name: 'MyPageArtWork',
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
	transform: rotate(-90deg) translate3d(15vh, -100vh, 0);
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
	min-height: 60vh;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
}

.img-wrapper:hover {
	min-height: 75vh;
	transform: rotate(90deg) translateZ(0px) translateX(0px) scale(1.2);
}

.img-wrapper a {
	overflow: hidden;
	display: block;
	box-shadow: 15px 15px 60px rgba(0, 0, 0, 1);
	/* background-color: rgba(255, 255, 255, 0.8);
	padding: 0.5vh; */
}

.image-box {
	transition: 0.5s;
	vertical-align: top;
	filter: grayscale(0%);
}

img {
	max-width: 45vh;
	max-height: 50vh;
}

a:hover .image-box {
	filter: grayscale(70%);
}

a:hover .image-info,
a:hover .delete {
	display: block;
}

.image-info {
	position: absolute;
	bottom: 20px;
	right: 30px;
	text-align: end;
	color: white;
	transition: 1s;
	display: none;
}

.delete {
	position: absolute;
	top: 20px;
	right: 20px;
	color: rgb(255, 255, 255);
	font-size: 13px;
	align-items: center;
	background-color: rgba(255, 255, 255, 0.5);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
}
</style>
