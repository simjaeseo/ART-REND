<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend <br />
					<h4>Favorite Artwork</h4>
				</h2>
			</div>
			<div
				v-for="image in state.likeArtWorkList"
				:key="image.id"
				class="img-wrapper"
			>
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail(image.id)">
						<button class="delete" @click.prevent="unlikeArtWork(image.id)">
							delete
						</button>
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
	</div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter, useRoute } from 'vue-router'

export default {
	name: 'MyPageArtWork',
	setup() {
		const router = useRouter()
		const route = useRoute()
		const store = useStore()
		const state = reactive({
			likeArtWorkList: [],
		})
		const memberId = route.params.memberId
		store.dispatch('likeArtWorkList', memberId)

		state.likeArtWorkList = computed(() => store.getters.likeArtWorkList)

		// 좋아요 취소
		const unlikeArtWork = function (artworkId) {
			store.dispatch('unlikeArtWork', artworkId)
		}
		const goDetail = function (artworkId) {
			router.push({ name: 'Detail', params: { artworkId: artworkId } })
		}
		return {
			state,
			unlikeArtWork,
			goDetail,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');

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
	transform: rotate(-90deg) translate3d(10vh, -100vh, 0);
	transform-origin: right top;
	overflow-y: auto;
	overflow-x: hidden;
	padding: 0;
	height: 100vw;
	transform-style: preserve-3d;
	perspective: 1px;
	padding-bottom: 10rem;
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
	animation: fadeInLeft 5s ease-out;
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
	font-family: 'Noto Sans', sans-serif;
	font-size: 13px;
	align-items: center;
	background-color: rgba(255, 255, 255, 0.5);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
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
