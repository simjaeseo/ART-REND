<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend <br />
					<h4>The GENRE</h4>
				</h2>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('정물화')">
						<img src="@/assets/artist/라파엘로.png" alt="image" />
						<div class="image-info">
							<div class="title">Still life</div>
							<div class="name">정물화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('풍속화')">
						<img src="@/assets/artist/라파엘로.png" alt="image" />
						<div class="image-info">
							<div class="title">Genre Painting</div>
							<div class="name">풍속화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('풍경화')">
						<img src="@/assets/artist/라파엘로.png" alt="image" />
						<div class="image-info">
							<div class="title">Landscape</div>
							<div class="name">풍경화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('초상화')">
						<img src="@/assets/artist/클로드모네.png" alt="image" />
						<div class="image-info">
							<div class="title">Portrait</div>
							<div class="name">초상화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('역사화')">
						<img src="@/assets/artist/피카소.png" alt="image" />
						<div class="image-info">
							<div class="title">History</div>
							<div class="name">역사화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('나체화')">
						<img src="@/assets/artist/귀스타브쿠르베.png" alt="image" />
						<div class="image-info">
							<div class="title">Nudity</div>
							<div class="name">나체화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('자화상')">
						<img src="@/assets/artist/살바도르달리.png" alt="image" />
						<div class="image-info">
							<div class="title">self-portrait</div>
							<div class="name">자화상</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('종교화')">
						<img src="@/assets/artist/르네.png" alt="image" />
						<div class="image-info">
							<div class="title">Religious</div>
							<div class="name">종교화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('신화')">
						<img src="@/assets/artist/막스.png" alt="image" />
						<div class="image-info">
							<div class="title">mythological</div>
							<div class="name">신화</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('상징화')">
						<img src="@/assets/artist/뭉크.png" alt="image" />
						<div class="image-info">
							<div class="title">Representative</div>
							<div class="name">상징화</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
	name: 'GenrePage',
	setup() {
		const router = useRouter()
		const goDetail = function (name) {
			console.log(name)
			router.push({ name: 'GenreDetailPage', params: { name: name } })
		}
		return {
			goDetail,
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
	background-color: #00524c;
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
	align-items: center;
	justify-content: center;
	min-height: 60vh;
	transform-origin: 50% 50%;
	/* transform: rotate(90deg) translateZ(0px) translateX(0px); */
	transition: 1s;
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

/* a:hover .image-box {
	filter: grayscale(70%);
} */

a .image-info,
a .delete {
	display: block;
}

.image-info {
	position: absolute;
	bottom: -100px;
	text-align: center;
	color: white;
	transition: 1s;
	display: none;
	width: 100%;
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
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 1.5vh;
	font-weight: 500;
}
</style>
