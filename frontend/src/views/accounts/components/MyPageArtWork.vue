<template>
	<div>
		<div class="external">
			<div class="horizontal-scroll-wrapper">
				<div class="title-wrapper1">
					<h2 id="title-text1">
						Art of Trend <br />
						<h4>My Own Exhibition</h4>
						<hr />
						<h5 class="nickname">{{ state.nickName }}</h5>
					</h2>
				</div>
				<div v-for="image in state.imageNum" :key="image" class="img-wrapper">
					<a href="#" target="_blank" rel="noopener">
						<div class="image-box">
							<button class="delete" v-if="state.myPage">delete</button>
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

		return {
			state,
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
