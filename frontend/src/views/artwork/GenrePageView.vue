<template>
	<div class="external">
		<div class="topbutton">
			<img src="@/assets/left.png" class="leftButton" id="top" @click="toTop" />
		</div>
		<div
			class="horizontal-scroll-wrapper"
			id="main"
			@scroll.prevent="getScroll()"
		>
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend <br />
					<h4>The GENRE</h4>
				</h2>
			</div>
			<div v-for="(image, index) in genreData" :key="index" class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail(image.name)">
						<img :src="image.url" alt="image" />
					</div>
				</a>
				<div class="image-info">
					<div class="title">{{ image.name }}</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
	name: 'GenrePage',
	setup() {
		const router = useRouter()
		const store = useStore()
		store.dispatch('getGenreList')

		const genreData = computed(() => store.getters.genreData)

		const goDetail = function (name) {
			router.push({ name: 'GenreDetailPage', params: { name: name } })
		}

		// 스크롤 가져오기
		const getScroll = function () {
			const container = document.getElementById('main')
			const x = container.scrollTop
			// top 버튼
			const top = document.getElementById('top')
			if (x != 0) {
				top.classList.add('block')
			} else {
				top.classList.remove('block')
			}
		}
		// top 버튼
		const toTop = function () {
			const container = document.getElementById('main')
			container.scrollTo({ top: 0, behavior: 'smooth' })
		}

		return {
			goDetail,
			genreData,
			getScroll,
			toTop,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');

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
	background-image: url('https://www.transparenttextures.com/patterns/concrete-wall.png');
	min-width: 100vw;
	min-height: 100vh;
	background-color: #233243;
}

.horizontal-scroll-wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vh;
	transform: rotate(-90deg) translate3d(2vh, -100vh, 0);
	transform-origin: right top;
	overflow-y: auto;
	overflow-x: hidden;
	padding: 0;
	height: 100vw;
	transform-style: preserve-3d;
	perspective: 1px;
	padding-bottom: 10rem;
	padding-right: 10rem;
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
}

#title-text1 {
	font-size: 60px;
	position: relative;
	font-family: 'Playfair Display', serif;
	animation: fadeInLeft 2s ease-out;
	color: white;
}

.img-wrapper {
	transform: rotate(90deg);
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	min-height: 60vh;
	transform-origin: 50% 50%;
	transition: 1s;
	padding-bottom: 10vh;
}

.img-wrapper:hover {
	min-height: 75vh;
	transform: rotate(90deg) translateZ(0px) translateX(0px) scale(1.2);
}

.img-wrapper a {
	display: block;
	box-shadow: 15px 15px 60px rgba(0, 0, 0, 1);
	background-color: rgba(14, 10, 10, 0.514);
	padding: 0;
	cursor: pointer;
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

.image-info {
	padding-top: 2vh;
	text-align: center;
	color: white;
	transition: 1s;
	display: block;
	width: 100%;
}

.title {
	font-family: 'Noto Sans', sans-serif;
	font-size: 2vh;
	font-weight: 600;
}

.name {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 1.5vh;
	font-weight: 500;
}

/* top button */
.topbutton {
	position: absolute;
	left: 30px;
	top: 50%;
	z-index: 1;
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
