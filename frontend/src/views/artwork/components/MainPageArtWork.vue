<template>
	<div class="external">
		<div
			class="horizontal-scroll-wrapper"
			id="main"
			@scroll.prevent="getScroll()"
		>
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend, <br />
					<p id="h2-inner">Recommended for you</p>
					<p></p>
				</h2>
			</div>
			<div class="title-wrapper2">
				<h2 id="title-text2">
					Over history, paintings travelled,changed owners and belonged to a
					variety of collections
				</h2>
			</div>
			<div class="title-wrapper3 masonry">
				<div v-for="(image, index) in mainImage.slice(0, 25)" :key="index">
					<div class="mItem">
						<img :src="image.url" alt="image" id="m-image" />
					</div>
				</div>
			</div>
			<div>
				<h5 id="text-saying">
					We analyze your action log and as a result of analyzing your taste,
					{{ mainImage.length }} artworks.
				</h5>
			</div>
			<div id="title-wrapper4">
				<div
					v-for="(image, index) in mainImage.slice(0, 30)"
					:key="index"
					class="img-wrapper"
				>
					<a target="_blank" rel="noopener">
						<div class="image-box" @click.prevent="goDetail(image.paintingId)">
							<img :src="image.url" alt="image" />
							<div class="image-info">
								<div class="title">{{ image.title }}</div>
								<div class="name">{{ image.year }}</div>
								<div class="name">{{ image.artist }}</div>
							</div>
						</div>
					</a>
				</div>
			</div>
			<div class="btn-class" id="black">
				<button class="btn1" @click="goProfile">PROFILE</button>
				<button class="btn2" @click="goArtworks">TOP20</button>
			</div>
		</div>
		<div class="topbutton">
			<img src="@/assets/left.png" class="leftButton" id="top" @click="toTop" />
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { computed, reactive } from 'vue'

export default {
	name: 'MainPageArtWork',
	setup() {
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		})
		const store = useStore()

		const userId = computed(() => store.getters.userId)
		const mainImage = computed(() => store.getters.mainImage)

		const getScroll = function () {
			const container = document.getElementById('main')
			const x = container.scrollTop
			const top = document.getElementById('top')
			if (x != 0) {
				top.classList.add('block')
			} else {
				top.classList.remove('block')
			}

			const windowWidth = window.innerWidth
			const white = document.getElementById('title-wrapper4')
			const whiteLocation = white.getBoundingClientRect().left
			const black = document.getElementById('black')
			const blackLocation = black.getBoundingClientRect().left
			if (whiteLocation <= 30) {
				store.commit('SET_COLOR1', true)
			} else {
				store.commit('SET_COLOR1', false)
			}
			if (whiteLocation <= windowWidth && blackLocation >= windowWidth) {
				store.commit('SET_COLOR2', true)
			} else {
				store.commit('SET_COLOR2', false)
			}
		}
		window.addEventListener('beforeunload', () => {
			store.commit('SET_COLOR1', false)
			store.commit('SET_COLOR2', false)
		})

		const goProfile = function () {
			window.location.href = `https://j7c104.p.ssafy.io/mypage/${userId.value}`
		}
		const goArtworks = function () {
			window.location.href = 'https://j7c104.p.ssafy.io/main'
		}

		const toTop = function () {
			const container = document.getElementById('main')
			container.scrollTo({ top: 0, behavior: 'smooth' })
		}
		const goDetail = function (artworkId) {
			window.location.href = `https://j7c104.p.ssafy.io/detail/${artworkId}`
		}

		return {
			state,
			getScroll,
			goProfile,
			goArtworks,
			toTop,
			mainImage,
			goDetail,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap');
#text-saying {
	font-family: 'Playfair Display', serif;
	color: white;
	font-size: 1vh;
}
#title-wrapper4 {
	background-color: white;
	/* min-height: 15000px; */
	min-width: 100%;
}
#m-image {
	width: 100%;
	height: auto;
}
.masonry {
	column-count: 6;
	margin-right: 20%;
}

.masonry .mItem {
	display: inline-block;
	width: 100%;
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
	/* min-width: 1000px; */
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
	width: 0px;
	height: 0px;
}

::-webkit-scrollbar-button {
	width: 0px;
	height: 0px;
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
	cursor: pointer;
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

.btn1 {
	background: none;
	color: white;
	cursor: pointer;
	border: none;
}
.btn2 {
	background: none;
	color: white;
	cursor: pointer;
	border: none;
}
.btn-class {
	cursor: pointer;
	width: 100%;
}

.topbutton {
	position: absolute;
	left: 30px;
	top: 50%;
}
.topbutton > button {
	border: none;
}
.leftButton {
	width: 2.6vh;
	height: 2.6vh;
	cursor: pointer;
	margin-right: 0.3vh;
	display: none;
}

.block {
	display: block;
}
</style>
