<template>
	<div class="detail">
		<div>
			<div id="img-wrap">
				<div class="img-box">
					<img
						src="@/assets/main-img/1.jpg"
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
						src="@/assets/unlike.png"
						alt="unlike-button"
						id="unlike-button"
					/>
				</div>
			</div>
			<div id="background-div">
				<div class="external">
					<div class="horizontal-scroll-wrapper">
						<div id="img-title-wrap">
							<div class="title-wrapper">
								<h1 id="img-title">Boby Notre-Dame</h1>
								<h5 id="img-title2">한글제목다그닥다그닥, 1985</h5>
								<h4 id="img-title3">Andy worhol</h4>
								<hr />
								<div>
									<span id="description-cell">Place</span>
									<span id="description-inner"
										>Saint-Remy-de-Provence(Object made in)
									</span>
								</div>
								<div>
									<span id="description-cell">Date</span>
									<span id="description-inner">1989</span>
								</div>
								<div>
									<span id="description-cell">Oil on Canvas</span>
									<span id="description-inner"
										>Saint-Remy-de-Provence(Object made in)
									</span>
								</div>
								<div>
									<span id="description-cell">Dimensions</span>
									<span id="description-inner"
										>73.6 x 92.3 cm (29 x 36 5/8 in)</span
									>
								</div>
							</div>
							<div class="title-wrapper">
								<sapn id="description-cell2">Description</sapn>
								<p id="description-inner2">
									국회는 헌법개정안이 공고된 날로부터 60일 이내에 의결하여야
									하며, 국회의 의결은 재적의원 3분의 2 이상의 찬성을 얻어야
									한다. 국가는 지역간의 균형있는 발전을 위하여 지역경제를 육성할
									의무를 진다. 근로자는 근로조건의 향상을 위하여 자주적인
									단결권·단체교섭권 및 단체행동권을 가진다. 국가는 전통문화의
									계승·발전과 민족문화의 창달에 노력하여야 한다. 누구든지
									병역의무의 이행으로 인하여 불이익한 처우를 받지 아니한다.
									재의의 요구가 있을 때에는 국회는 재의에 붙이고,
									재적의원과반수의 출석과 출석의원 3분의 2 이상의 찬성으로 전과
									같은 의결을 하면 그 법률안은 법률로서 확정된다. 대통령은
									필요하다고 인정할 때에는 외교·국방·통일 기타 국가안위에 관한
									중요정책을 국민투표에 붙일 수 있다. 모든 국민은 소급입법에
									의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다.
									대통령의 임기연장 또는 중임변경을 위한 헌법개정은 그 헌법개정
									제안 당시의 대통령에 대하여는 효력이 없다. 누구든지 체포 또는
									구속의 이유와 변호인의 조력을 받을 권리가 있음을 고지받지
									아니하고는 체포 또는 구속을 당하지 아니한다. 체포 또는 구속을
									당한 자의 가족등 법률이 정하는 자에게는 그 이유와 일시·장소가
									지체없이 통지되어야 한다. 제안된 헌법개정안은 대통령이 20일
									이상의 기간 이를 공고하여야 한다. 모든 국민은 행위시의 법률에
									의하여 범죄를 구성하지 아니하는 행위로 소추되지 아니하며,
									동일한 범죄에 대하여 거듭 처벌받지 아니한다. 법원은 최고법원인
									대법원과 각급법원으로 조직된다. 국회의원은 법률이 정하는 직을
									겸할 수 없다. 모든 국민은 헌법과 법률이 정한 법관에 의하여
									법률에 의한 재판을 받을 권리를 가진다. 원장은 국회의 동의를
									얻어 대통령이 임명하고, 그 임기는 4년으로 하며, 1차에 한하여
									중임할 수 있다. 군인 또는 군무원이 아닌 국민은 대한민국의
									영역안에서는 중대한 군사상
								</p>
							</div>
							<div class="title-wrapper">
								<div id="recommend">Recommend</div>
								<div id="recommend">Artwork</div>
							</div>
							<detail-page-art-work />
						</div>
					</div>
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
						<button type="button" class="change-button">변환하기</button>
						<button type="button" class="change-button" data-bs-dismiss="modal">
							닫기
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import DetailPageArtWork from '@/views/artwork/components/DetailPageArtWork.vue'
import { reactive } from 'vue'

export default {
	name: 'MainPageView',
	components: { DetailPageArtWork },
	setup() {
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
			imageName: '',
			imageUrl: '',
		})

		const getImageModal = function () {
			const modal = document.getElementById('myModal')
			const img = require('../../assets/main-img/1.jpg')
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
			state.imageName = image.name
			state.imageUrl = URL.createObjectURL(image)
		}

		return {
			state,
			getImageModal,
			outImageModal,
			handleAddImage,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+Georgian&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');

.button-box {
	color: white;
	transition: 1s;
	display: flex;
	justify-content: flex-end;
	margin-top: 1vw;
}
#upload-button {
	width: 2vw;
	height: 2vw;
	cursor: pointer;
	margin-right: 1vw;
}
#unlike-button {
	width: 2vw;
	height: 2vw;
	cursor: pointer;
}
#description-cell {
	font-family: 'Noto Sans', sans-serif;
	font-weight: 500;
	display: inline-block;
	font-size: 1vw;
	width: 15vw;
	/* font-size: 35px; */
	/* width: 300px; */
	/* margin-top: 50px; */
}
#description-cell2 {
	/* font-size: 35px; */
	/* width: 300px; */
	font-size: 1.5vw;
	width: 15vw;
	font-family: 'Noto Sans', sans-serif;
	font-weight: 500;
	display: inline-block;
	margin-top: 50px;
}
#description-inner2 {
	/* font-size: 23px; */
	font-size: 1vw;
	margin-top: 20px;
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 100;
}
#description-inner {
	/* font-size: 23px; */
	font-size: 1vw;
	font-family: 'Noto Sans', sans-serif;
	font-weight: 200;
}
#recommend {
	font-size: 4vw;
	font-family: 'Noto Sans', sans-serif;
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
	font-size: 1.5vw;
}
#img-title3 {
	font-family: 'Noto Serif Georgian', serif;
	font-size: 1.5vw;
}
#img-title {
	/* font-size: 70px; */
	/* margin-bottom: 50px; */
	font-size: 3.5vw;
	margin-bottom: 3vw;
	font-family: 'Noto Serif Georgian', serif;
}
#img-title-wrap {
	margin-left: 10%;
	margin-top: -10vw;
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
	overflow: hidden;
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
	padding-top: 100px; /* Location of the box */
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

	height: 50vw;
	display: flex;
	flex-direction: column;
	justify-content: center;
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
	height: 30vh;
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
</style>
