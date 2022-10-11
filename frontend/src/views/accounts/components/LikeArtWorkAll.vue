<template>
	<div>
		<div class="like-count">
			You like {{ state.likeArtWorkList.length }} artworks.
		</div>
		<div class="masonry-container">
			<div
				v-for="image in state.likeArtWorkList"
				:key="image.id"
				class="masonry-item"
			>
				<div class="pseudo-img">
					<a target="_blank" rel="noopener">
						<div class="image-box">
							<button
								class="delete"
								@click.prevent="unlikeArtWork(image.id)"
								v-if="state.myPage"
							>
								delete
							</button>
							<img
								:src="image.url"
								alt="image"
								@click.prevent="goDetail(image.id)"
							/>
							<div class="image-info" @click.prevent="goDetail(image.id)">
								<div class="title">{{ image.title }}</div>
								<div class="name">{{ image.year }}</div>
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
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'

export default {
	name: 'MyPageArtWork',
	setup() {
		const route = useRoute()
		const store = useStore()
		const state = reactive({
			likeArtWorkList: [],
			myPage: false,
		})
		const memberId = route.params.memberId
		const userId = computed(() => store.getters.userId)
		if (memberId == userId.value) {
			state.myPage = true
		}

		state.likeArtWorkList = computed(() => store.getters.likeArtWorkList)

		// 좋아요 취소
		const unlikeArtWork = function (artworkId) {
			const next = confirm('좋아요를 취소하시겠습니까?')
			if (next == true) {
				store.dispatch('unlikeArtWork', artworkId)
			}
		}
		const goDetail = function (artworkId) {
			window.location.href = `http://localhost:3002/detail/${artworkId}`
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
@import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500;600&display=swap');

.masonry-container {
	--gap: 10px;

	display: grid;
	grid-template-columns: repeat(5, 1fr);
	column-gap: var(--gap);
	grid-auto-rows: 10px;
	margin: 10px 300px;
}

.like-count {
	margin: 0px 300px;
	text-align: end;
	color: rgba(0, 0, 0, 0.5);
	font-family: 'Playfair Display', serif;
	font-weight: 600;
}

@media screen and (max-width: 2100px) {
	.masonry-container {
		grid-template-columns: repeat(4, 1fr);
	}
}

@media screen and (max-width: 1900px) {
	.masonry-container {
		grid-template-columns: repeat(3, 1fr);
		margin: 10px 15vw;
	}
	.like-count {
		margin: 0 15vw;
	}
}

@media screen and (max-width: 1300px) {
	.masonry-container {
		grid-template-columns: repeat(2, 1fr);
		margin: 10px 13vw;
	}
	.like-count {
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
	cursor: pointer;
}

.image-box {
	transition: 0.5s;
	vertical-align: top;
	filter: grayscale(0%);
}

a {
	width: 100%;
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
