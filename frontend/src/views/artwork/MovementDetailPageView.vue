<template>
	<div class="detail-bg">
		<div class="external">
			<div class="horizontal-scroll-wrapper">
				<div class="title-wrapper1">
					<h2 id="title-text1">{{ name }}, <br /></h2>
				</div>
				<div class="title-wrapper3 masonry">
					<div v-for="(image, index) in detail" :key="index">
						<div class="mItem">
							<img :src="image.url" alt="image" id="m-image" />
						</div>
					</div>
				</div>
				<div>
					<h5 id="text-saying">
						All the arts we practice are apprenticeship. The big art is our
						life. - M. C. Richards
					</h5>
				</div>
				<div id="title-wrapper4">
					<div
						v-for="(image, index) in detail"
						:key="index"
						class="img-wrapper"
					>
						<a target="_blank" rel="noopener">
							<div class="image-box" @click.prevent="goDetail(image.id)">
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
				<div class="btn-class">
					<button class="btn1" @click="goArtist">BACK</button>
					<button class="btn2" @click="goMain">MAIN</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useRoute, useRouter } from 'vue-router'
import { computed, reactive } from 'vue'
import { useStore } from 'vuex'
export default {
	name: 'ArtistDetailPage',
	setup() {
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		})
		const route = useRoute()
		const router = useRouter()
		const name = route.params.name
		const store = useStore()
		store.dispatch('getMovementDetail', name)
		const detail = computed(() => store.getters.movementDetail)
		const goArtist = function () {
			router.push({ name: 'ArtistPage' })
		}
		const goMain = function () {
			router.push({ name: 'Main' })
		}
		const goDetail = function (artworkId) {
			router.push({ name: 'Detail', params: { artworkId: artworkId } })
		}
		return {
			name,
			state,
			detail,
			goArtist,
			goMain,
			goDetail,
		}
	},
}
</script>

<style scoped>
.detail-bg {
	background-color: rgb(0, 0, 0);
	height: 100vh;
	width: 100%;
	overflow-y: hidden;
}
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap');
#text-saying {
	font-family: 'Playfair Display', serif;
	color: white;
	font-size: 1vh;
}
#title-wrapper4 {
	background-color: white;
	min-width: 100%;
}
#m-image {
	width: 100%;
	height: auto;
}
.masonry {
	column-count: 5;
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
	margin-top: 800px;
	margin-bottom: 1000px;
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
}
.btn2 {
	background: none;
	color: white;
	cursor: pointer;
}
.btn-class {
	cursor: pointer;
	width: 100%;
}
</style>
