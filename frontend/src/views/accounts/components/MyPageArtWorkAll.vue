<template>
	<div class="masonry-container masonry">
		<div v-for="image in state.imageNum" :key="image" class="masonry-item">
			<div class="pseudo-img">
				<a href="#" target="_blank" rel="noopener">
					<div cl ass="image-box">
						<button class="delete">delete</button>
						<img :src="require(`@/assets/main-img/${image}.jpg`)" alt="image" />
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
</template>

<script>
import { reactive } from 'vue'

export default {
	name: 'MyPageArtWork',
	setup() {
		const state = reactive({
			imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
		})

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
		return {
			state,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');

.masonry-container {
	--gap: 10px;

	display: grid;
	grid-template-columns: repeat(3, 1fr);
	column-gap: var(--gap);
	grid-auto-rows: 10px;
}

@media screen and (max-width: 720px) {
	.masonry-container {
		grid-template-columns: repeat(2, 1fr);
	}
}

@media screen and (max-width: 400px) {
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
