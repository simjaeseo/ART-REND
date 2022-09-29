<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend <br />
					<h4>The MOVEMENT</h4>
				</h2>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('인상주의')">
						<img src="@/assets/artist/라파엘로.png" alt="image" />
						<div class="image-info">
							<div class="title">Impressionism</div>
							<div class="name">인상주의</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('후기 인상주의')">
						<img src="@/assets/artist/클로드모네.png" alt="image" />
						<div class="image-info">
							<div class="title">Post-Impressionism</div>
							<div class="name">후기 인상주의</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('점묘법')">
						<img src="@/assets/artist/피카소.png" alt="image" />
						<div class="image-info">
							<div class="title">Pointage</div>
							<div class="name">점묘법</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('표현주의')">
						<img src="@/assets/artist/귀스타브쿠르베.png" alt="image" />
						<div class="image-info">
							<div class="title">Expressionismus</div>
							<div class="name">표현주의</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('초현실주의')">
						<img src="@/assets/artist/귀스타브쿠르베.png" alt="image" />
						<div class="image-info">
							<div class="title">Surrealism</div>
							<div class="name">초현실주의</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('르네상스')">
						<img src="@/assets/artist/살바도르달리.png" alt="image" />
						<div class="image-info">
							<div class="title">Renaissance</div>
							<div class="name">르네상스</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('입체주의')">
						<img src="@/assets/artist/르네.png" alt="image" />
						<div class="image-info">
							<div class="title">Cubism</div>
							<div class="name">입체주의</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('팝아트')">
						<img src="@/assets/artist/르네.png" alt="image" />
						<div class="image-info">
							<div class="title">POP ART</div>
							<div class="name">팝아트</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('사실주의')">
						<img src="@/assets/artist/마네.png" alt="image" />
						<div class="image-info">
							<div class="title">Realism</div>
							<div class="name">사실주의</div>
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
			router.push({ name: 'MovementDetailPage', params: { name: name } })
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
	background-color: #233243;
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
