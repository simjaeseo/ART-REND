<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend, <br />
					<p id="h2-inner">Recommended for you</p>
				</h2>
			</div>
			<div class="title-wrapper2">
				<h2 id="title-text2">
					Over history, paintings travelled,changed owners and belonged to a
					variety of collections
				</h2>
			</div>
			<div class="title-wrapper3 masonry">
				<div v-for="image in state.imageNum" :key="image">
					<div class="mItem">
						<img
							:src="require(`@/assets/main-img/${image}.jpg`)"
							alt="image"
							id="m-image"
						/>
					</div>
				</div>
			</div>
			<div>
				<h5 id="text-saying">
					All the arts we practice are apprenticeship. The big art is our life.
					- M. C. Richards
				</h5>
			</div>
			<div id="title-wrapper4">
				<div v-for="image in state.imageNum" :key="image" class="img-wrapper">
					<a href="#" target="_blank" rel="noopener">
						<div class="image-box">
							<img
								:src="require(`@/assets/main-img/${image}.jpg`)"
								alt="image"
							/>
							<div class="image-info">
								<div class="title">Street Man Fighter</div>
								<div class="name">2022</div>
								<div class="name">Tom Smith</div>
							</div>
						</div>
					</a>
				</div>
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

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap');
#text-saying {
	font-family: 'Playfair Display', serif;
	color: white;
}
#title-wrapper4 {
	background-color: white;
	min-height: 15000px;
	min-width: 100%;
}
#m-image {
	width: 300px;
	height: auto;
}
.masonry {
	column-count: 5;
	margin-right: 20%;
}

.masonry .mItem {
	display: inline-block;
	width: 100%;
	cursor: pointer;
	margin-bottom: 10px;
}
#h2-inner {
	font-size: 25px;
	margin-top: 10px;
}
#title-text1 {
	font-size: 60px;
	color: white;
	position: relative;
	font-family: 'Playfair Display', serif;
	animation: fadeInLeft 5s ease-out;
}
#title-text1-inner {
	font-size: 40px;
	color: white;
	position: relative;
	font-family: 'Playfair Display', serif;
}
.title-wrapper1 {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
	min-width: 900px;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
	z-index: -1;
	background-color: black;
}
#title-text2 {
	font-size: 60px;
	color: white;
	position: relative;
	left: 400px;
	font-family: 'Playfair Display', serif;
}
.title-wrapper2 {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
	min-width: 1500px;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
	background-image: url('@/assets/main-img/12.jpg');
	z-index: -1;
	margin-top: 600px;
	margin-bottom: 600px;
}
.title-wrapper3 {
	transform: rotate(90deg);
	align-items: center;
	justify-content: center;
	min-height: 80vh;
	min-width: 1000px;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
	z-index: -1;
	margin-top: 600px;
	margin-bottom: 600px;
}
#main-text1 {
	height: 300px;
	width: 60%;
	background-color: black;
}
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
	animation: slideInRight 2s ease-out;
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
	box-shadow: 15px 15px 60px rgb(12, 12, 12);
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
	bottom: 20px;
	right: 30px;
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
	font-size: 1.5vh;
	font-weight: 200;
}
</style>
