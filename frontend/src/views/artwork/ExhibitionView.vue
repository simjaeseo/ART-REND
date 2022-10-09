<template>
	<div>
		<div class="wrap-div1">
			<div class="masonry-container">
				<div
					v-for="(image, index) in convertList"
					:key="index"
					class="masonry-item"
				>
					<div class="pseudo-img">
						<a target="_blank" rel="noopener">
							<div class="image-box">
								<img
									:src="image.url"
									alt="image"
									@click.prevent="goDetail(image.id)"
								/>
								<div
									class="image-info"
									@click.prevent="goDetail(image.memberId)"
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
	</div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

export default {
	name: 'MyPageArtWork',
	setup() {
		const store = useStore()
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		})
		const convertList = computed(() => store.getters.convertListAll)

		store.dispatch('getImageConvertAll')
		window.onload = function () {
			function masonryLayout() {
				const masonryContainerStyle = getComputedStyle(
					document.querySelector('.masonry-container'),
				)
				const columnGap = parseInt(
					masonryContainerStyle.getPropertyValue('column-gap'),
				)
				const autoRows = parseInt(
					masonryContainerStyle.getPropertyValue('grid-auto-rows'),
				)

				document.querySelectorAll('.masonry-item').forEach(elt => {
					elt.style.gridRowEnd = `span ${Math.ceil(
						elt.querySelector('.pseudo-img').scrollHeight / autoRows +
							columnGap / autoRows,
					)}`
				})
			}

			masonryLayout()
			window.addEventListener('resize', masonryLayout)
		}
		const goDetail = function (userId) {
			window.location.href = `http://j7c104.p.ssafy.io/mypage/${userId}`
		}
		return {
			state,
			convertList,
			goDetail,
		}
	},
}
</script>

<style scoped>
#click-btn {
	display: inline;
	width: 50px;
	height: 50px;
}
.get-btn {
	text-align: center;
	cursor: pointer;
}
.get-btn:hover {
	text-align: center;
	cursor: pointer;
	color: gray;
}
.wrap-div1 {
	padding-top: 170px;
}
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
.masonry-container {
	--gap: 10px;

	display: grid;
	grid-template-columns: repeat(7, 1fr);
	column-gap: var(--gap);
	grid-auto-rows: 10px;
	margin: 0px 300px;
}

@media screen and (max-width: 2100px) {
	.masonry-container {
		grid-template-columns: repeat(7, 1fr);
	}
}

@media screen and (max-width: 1900px) {
	.masonry-container {
		grid-template-columns: repeat(5, 1fr);
		margin: 0 15vw;
	}
}

@media screen and (max-width: 1300px) {
	.masonry-container {
		grid-template-columns: repeat(4, 1fr);
		margin: 0 13vw;
	}
}

@media screen and (max-width: 800px) {
	.masonry-container {
		display: block;
	}

	.masonry-item {
		margin-bottom: var(--gap);
	}
}

/* ignore this */
.pseudo-img {
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 2rem;
}

.image-box {
	transition: 0.5s;
	vertical-align: top;
	filter: grayscale(0%);
	cursor: pointer;
}

img {
	width: 100%;
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
</style>
