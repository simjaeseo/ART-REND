<template>
	<div class="detail">
		<div>
			<div id="img-wrap">
				<div class="topbutton">
					<img
						src="@/assets/left.png"
						class="leftButton"
						id="top"
						@click="toTop"
					/>
				</div>
				<div class="img-box">
					<img
						:src="detailData.painting.url"
						alt="img"
						id="selected-img"
						@click="getImageModal"
					/>
				</div>
				<div class="button-box">
					<img
						src="@/assets/upload.png"
						alt="upload-button"
						id="upload-button"
						data-bs-toggle="modal"
						data-bs-target="#pictureModal"
					/>
					<img
						v-if="detailData.painting.isLiked === true"
						src="@/assets/like.png"
						alt="unlike-button"
						id="unlike-button"
						@click.prevent="unlikeArtWork"
					/>
					<img
						v-if="detailData.painting.isLiked === false"
						src="@/assets/unlike.png"
						alt="like-button"
						id="like-button"
						@click.prevent="likeArtWork"
					/>
					<div>{{ detailData.painting.totalLikeCount }}</div>
				</div>
			</div>
			<div id="background-div">
				<div class="external">
					<div
						class="horizontal-scroll-wrapper"
						id="main"
						@scroll.prevent="getScroll()"
					>
						<div id="img-title-wrap">
							<div class="title-wrapper margin-left">
								<h1 id="img-title">{{ detailData.painting.title }}</h1>
								<h5 id="img-title2">{{ detailData.painting.koreanTitle }}</h5>
								<h4 id="img-title3">{{ detailData.painting.artist }}</h4>
								<hr />
								<div>
									<span id="description-cell">Place</span>
									<span id="description-inner"
										>{{ detailData.painting.place }}
									</span>
								</div>
								<div>
									<span id="description-cell">Genre</span>
									<span id="description-inner"
										>{{ detailData.painting.genre }}
									</span>
								</div>
								<div>
									<span id="description-cell">Date</span>
									<span id="description-inner">{{
										detailData.painting.year
									}}</span>
								</div>
								<div>
									<span id="description-cell">Oil on Canvas</span>
									<span id="description-inner"
										>{{ detailData.painting.medium }}
									</span>
								</div>
								<div>
									<span id="description-cell">Dimensions</span>
									<span id="description-inner">{{
										detailData.painting.dimensions
									}}</span>
								</div>
							</div>
							<div class="title-wrapper">
								<span id="description-cell2">Description</span>
								<p id="description-inner2">
									{{ detailData.painting.description }}
								</p>
							</div>
							<div class="title-wrapper2">
								<div id="recommend">Recommend</div>
								<div id="recommend">Artwork</div>
							</div>
							<detail-page-art-work />
						</div>
					</div>
				</div>
				<div class="btn-class">
					<button class="btn1" @click="goProfile">PROFILE</button>
					<button class="btn2" @click="goMain">MAIN</button>
				</div>
			</div>
		</div>
		<div id="myModal" class="modal">
			<img
				class="modal-content"
				id="img01"
				alt="Image"
				@click="outImageModal"
			/>
		</div>

		<!-- 사진변환 Modal -->
		<form @submit.prevent="onSubmit()">
			<div
				class="modal fade"
				id="pictureModal"
				tabindex="-1"
				aria-labelledby="exampleModalLabel"
				aria-hidden="true"
			>
				<div class="modal-dialog">
					<div class="modal-content content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">
								변환할 사진을 선택해 주세요.
							</h5>
						</div>
						<div class="modal-body">
							<div>
								<label
									htmlFor="input-file"
									@change="handleAddImage"
									class="image-input"
								>
									<input type="file" accept="image/*" id="input-file" />
									<input
										class="image-upload"
										:placeholder="state.imageName"
										readOnly
									/>
									<span>사진추가</span>
								</label>
								<div class="show-image">
									<div class="image-sample">
										<img :src="state.imageUrl" alt="" id="sample-imag" />
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="change-button">변환하기</button>
							<button
								type="button"
								class="change-button"
								data-bs-dismiss="modal"
							>
								닫기
							</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</template>

<script>
import DetailPageArtWork from '@/views/artwork/components/DetailPageArtWork.vue'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'

export default {
	name: 'MainPageView',
	components: { DetailPageArtWork },
	setup() {
		const store = useStore()
		const router = useRouter()
		const route = useRoute()
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
			imageName: '',
			imageUrl: '',
			image: null,
			payload: {
				img: null,
				artworkId: 0,
			},
			like: false,
			actionLog: {
				paintingId: 0,
				clickCnt: 0,
				zoomCnt: 0,
				inTime: null,
				outTime: null,
				change: 0,
			},
		})
		state.actionLog.clickCnt += 1
		const artworkId = { ...route }
		const payLoadId = artworkId.params.artworkId
		state.actionLog.paintingId = payLoadId
		state.payload.artworkId = payLoadId
		store.dispatch('getArtWorkDetail', payLoadId)
		const detailData = computed(() => store.getters.detailData)
		const userId = computed(() => store.getters.userId)

		const getImageModal = function () {
			state.actionLog.zoomCnt += 1
			const modal = document.getElementById('myModal')
			const img = detailData.value.painting.url
			const modalImg = document.getElementById('img01')
			modal.style.display = 'block'
			modalImg.src = img
		}
		const outImageModal = function () {
			const modalImg = document.getElementById('img01')
			modalImg.classList.add('out')
			setTimeout(function () {
				const modal = document.getElementById('myModal')
				modal.style.display = 'none'
				modalImg.className = 'modal-content'
			}, 400)
		}

		const handleAddImage = function (event) {
			const image = event.target.files[0]
			state.image = image
			state.imageName = image.name
			state.imageUrl = URL.createObjectURL(image)
		}

		let img = null
		const onSubmit = function () {
			img = state.image
			const formData = new FormData()
			formData.append('file', img)
			console.log(formData.get('file'))
			state.payload.img = formData
			console.log(state.payload.img)
			store.dispatch('imageConvert', state.payload)
		}
		const likeArtWork = function () {
			store.dispatch('likeArtWork', payLoadId)
		}
		const unlikeArtWork = function () {
			store.dispatch('unlikeArtWork', payLoadId)
		}
		state.actionLog.inTime = new Date()
		window.addEventListener('beforeunload', () => {
			state.actionLog.outTime = new Date()
			store.dispatch('actionLog', state.actionLog)
		})

		const goMain = function () {
			router.push({ name: 'Main' })
		}
		const goProfile = function () {
			location.href = `http://localhost:3002/mypage/${userId.value}`
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
			state,
			getImageModal,
			outImageModal,
			handleAddImage,
			onSubmit,
			artworkId,
			payLoadId,
			detailData,
			likeArtWork,
			unlikeArtWork,
			goMain,
			goProfile,
			getScroll,
			toTop,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+Georgian&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap');

#img01 {
	max-width: 90vw;
	max-height: 90vh;
	cursor: pointer;
}

.button-box {
	color: white;
	transition: 1s;
	display: flex;
	justify-content: flex-end;
	margin-top: 1vw;
}
.button-box > div {
	font-size: 2vh;
}
#upload-button {
	width: 3vh;
	height: 3vh;
	cursor: pointer;
	margin-right: 1vh;
}
#unlike-button {
	width: 2.6vh;
	height: 2.6vh;
	cursor: pointer;
	margin-right: 0.3vh;
}
#like-button {
	width: 2.6vh;
	height: 2.6vh;
	cursor: pointer;
	margin-right: 0.3vh;
}
#description-cell {
	font-family: 'Noto Sans', sans-serif;
	font-weight: 500;
	display: inline-block;
	font-size: 2vh;
	width: 15vw;
	/* font-size: 35px; */
	/* width: 300px; */
	/* margin-top: 50px; */
}
#description-cell2 {
	/* font-size: 35px; */
	/* width: 300px; */
	font-size: 2.5vh;
	width: 15vw;
	font-family: 'Noto Sans', sans-serif;
	font-weight: 500;
	display: inline-block;
	margin-top: 50px;
}
#description-inner2 {
	/* font-size: 23px; */
	font-size: 1.5vh;
	margin-top: 20px;
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 100;
}
#description-inner {
	/* font-size: 23px; */
	font-size: 1.5vh;
	font-family: 'Noto Sans', sans-serif;
	font-weight: 200;
}
#recommend {
	font-size: 5vh;
	font-family: 'Playfair Display', serif;
	font-weight: 500;
	display: flex;
	justify-content: center;
	align-items: center;
}
hr {
	background-color: white;
	width: 100px;
	height: 2px;
}
#img-title2 {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 400;
	font-size: 2.3vh;
}
#img-title3 {
	font-family: 'Noto Serif Georgian', serif;
	font-size: 2.3vh;
}
#img-title {
	/* font-size: 70px; */
	/* margin-bottom: 50px; */
	font-size: 5.5vh;
	margin-bottom: 3vw;
	font-family: 'Noto Serif Georgian', serif;
}

.img-box {
	height: 70%;
	display: flex;
	justify-content: flex-end;
}
#selected-img {
	position: relative;
	cursor: pointer;
	height: 100%;
}
#img-wrap {
	height: 100%;
	animation: slideInRight 2s ease-out;
	position: fixed;
	width: 30vw;
	display: flex;
	flex-direction: column;
	justify-content: center;
}
#background-div {
	margin-left: 20%;
	padding-left: 10vw;
	width: 80%;
	background-color: rgb(0, 0, 0);
	height: 100vh;
	animation: fadeInLeft 1s ease-out;
	position: absolute;
	z-index: -1;

	overflow: hidden;
}
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	/* z-index: 2; Sit on top */
	padding-top: 5vh;
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100vh; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
	margin: auto;
	display: block;
	height: 100vh;
	width: auto;
	/* max-width: 75%; */
}

/* Caption of Modal Image */
#caption {
	margin: auto;
	display: block;
	width: 80%;
	max-width: 700px;
	text-align: center;
	color: #ccc;
	padding: 10px 0;
	height: 150px;
}

/* Add Animation */
.out {
	animation-name: zoom-out;
	animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
	from {
		-webkit-transform: scale(1);
	}
	to {
		-webkit-transform: scale(2);
	}
}

@keyframes zoom {
	from {
		transform: scale(0.4);
	}
	to {
		transform: scale(1);
	}
}

@keyframes zoom-out {
	from {
		transform: scale(1);
	}
	to {
		transform: scale(0);
	}
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
	.modal-content {
		width: 100%;
	}
}

/* 가로스크롤 */
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
	color: white;
	/* height: 100vw; */
	/* margin: 100px 0px; */
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
	height: 80vw;
	transform-style: preserve-3d;
	perspective: 1px;
	padding-bottom: 10rem;
}

.title-wrapper {
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(200px);
	transition: 1s;
	min-height: 100vh;

	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
}
.margin-left {
	margin-top: -10%;
}

.title-wrapper2 {
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(200px);
	transition: 1s;
	min-height: 100vh;

	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-end;
	padding-right: 10%;
	margin-top: -20%;
}

/* Modal */
.modal-dialog {
	width: 40vw;
	max-width: 40vw;
}

.content {
	height: auto;
	background-color: white;
	padding: 20px;
	border-radius: 20px;
}

.image-input {
	display: flex;
	justify-content: center;
	width: 100%;
	margin: 10px 0;
}

.image-input input[type='file'] {
	display: none;
}

.image-input > .image-upload {
	display: inline-block;
	height: 40px;
	padding: 0 10px;
	margin: 0px 10px;
	border: 1px solid rgb(200, 200, 200);
	color: rgb(200, 200, 200);
	width: 85%;
}

.image-input > span {
	display: inline-block;
	padding: 7px 20px;
	color: #fff;
	background-color: rgb(200, 200, 200);
	cursor: pointer;
	height: 40px;
	margin: 0 20px 0 0;
	white-space: nowrap;
	border-radius: 10px;
}

.image-input > span:hover {
	background-color: rgb(0, 0, 0);
}

.show-image {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.image-sample {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	margin: 10px;
	width: 80%;
}

.image-sample > img {
	max-width: 100%;
	max-height: 30vh;
}

.change-button {
	border: none;
	border-radius: 10px;
	background-color: rgb(200, 200, 200);
	color: white;
	padding: 5px 10px;
}

.change-button:hover {
	background-color: rgb(0, 0, 0);
}

/* go main */
.btn1 {
	background: none;
	color: rgb(150, 150, 150);
	font-size: 20px;
	margin: 20px;
	cursor: pointer;
	border: none;
}
.btn2 {
	background: none;
	color: rgb(150, 150, 150);
	font-size: 20px;
	margin: 20px;
	cursor: pointer;
	border: none;
}
.btn1:hover,
.btn2:hover {
	color: rgb(255, 255, 255);
}
.btn-class {
	cursor: pointer;
	margin-left: -20%;
	width: 80%;
	position: fixed;
	bottom: 10px;
	text-align: center;
}

/* top button */
.topbutton {
	position: absolute;
	right: -50px;
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
