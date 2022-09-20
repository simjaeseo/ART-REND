<template>
	<div class="masorny">
		<div v-for="image in state.imageNum" :key="image" class="masorny-inner">
			<a href="#" target="_blank" rel="noopener">
				<div class="image-box">
					<button class="delete">delete</button>
					<img :src="require(`@/assets/main-img/${image}.jpg`)" alt="image" />
					<div class="image-info">
						<h3 class="title">Street Man Fighter, 2022</h3>
						<h5 class="name">Tom Smith</h5>
					</div>
				</div>
			</a>
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
		return {
			state,
		}
	},
}
</script>

<style scoped>
.masorny {
	display: flex;
	flex-flow: row wrap;
}

.masorny-inner {
	padding: 10px;
	margin: 10px;
	border: 1px solid black;
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
	font-size: 13px;
	align-items: center;
	background-color: rgba(255, 255, 255, 0.5);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
}
</style>
