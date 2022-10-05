<template>
	<div>
		<div class="external">
			<div class="horizontal-scroll-wrapper" id="main">
				<div class="title-wrapper1">
					<h2 id="title-text1">
						Art of Trend <br />
						<h4>My Own Exhibition</h4>
						<hr />
						<h5 class="nickname">{{ state.nickName }}</h5>
					</h2>
				</div>
				<div v-if="!convertList.length" class="text-wrapper">
					Make your photo card!
				</div>
				<div
					v-for="(image, index) in convertList"
					:key="index"
					class="img-wrapper"
				>
					<a target="_blank" rel="noopener">
						<div class="image-box">
							<button
								class="delete"
								v-if="state.myPage"
								@click.prevent="deleteConvert(image.id)"
							>
								delete
							</button>
							<img
								:src="image.url"
								alt="image"
								@click.prevent="goDetail(image.originalId)"
							/>
							<div
								class="image-info"
								@click.prevent="goDetail(image.originalId)"
							>
								<div class="title">{{ image.title }}</div>
								<div class="name">{{ image.koreanTitle }}</div>
								<div class="name">{{ image.artist }}</div>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'

export default {
	name: 'MyPageArtWork',
	setup() {
		const store = useStore()
		const route = useRoute()
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
			nickName: '',
			myPage: false,
		})
		const memberId = route.params.memberId
		const userId = computed(() => store.getters.userId)
		if (memberId == userId.value) {
			state.myPage = true
		}

		state.nickName = computed(() => store.getters.userNickName)

		const convertList = computed(() => store.getters.convertList)
		const goDetail = function (artworkId) {
			window.location.href = `https://j7c104.p.ssafy.io/detail/${artworkId}`
		}

		const deleteConvert = function (artworkId) {
			const next = confirm('삭제하시겠습니까?')
			if (next == true) {
				store.dispatch('deleteConvert', artworkId)
			}
		}
		return {
			state,
			convertList,
			goDetail,
			deleteConvert,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
.text-wrapper {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 60vh;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
	font-family: 'Playfair Display', serif;
	font-size: 20px;
	color: rgba(0, 0, 0, 0.3);
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

.nickname {
	font-family: 'Noto Sans KR', sans-serif;
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
	cursor: pointer;
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
	position: fixed;
	top: 20px;
	right: 20px;
	color: rgb(255, 255, 255);
	font-family: 'Noto Sans', sans-serif;
	font-size: 13px;
	align-items: center;
	background-color: rgb(173, 173, 173);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
	z-index: 1;
}
.delete:hover {
	position: fixed;
	top: 20px;
	right: 20px;
	color: rgb(255, 255, 255);
	font-family: 'Noto Sans', sans-serif;
	font-size: 13px;
	align-items: center;
	background-color: rgb(173, 173, 173);
	background-color: rgba(255, 255, 255, 0.5);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
	z-index: 1;
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

/* top button */
.topbutton {
	position: fixed;
	left: 30px;
	bottom: 50%;
	z-index: 1;
}
.topbutton > button {
	border: none;
}
.leftButton {
	width: 3vh;
	height: 3vh;
	cursor: pointer;
	margin-right: 0.3vh;
	display: none;
}
.block {
	display: block;
}
</style>
