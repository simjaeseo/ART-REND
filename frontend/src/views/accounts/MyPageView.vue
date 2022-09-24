<template>
	<div>
		<div class="mypage">
			<input type="radio" name="tabmenu" id="my-picture" checked="true" />
			<input type="radio" name="tabmenu" id="my-picture-all" />
			<input type="radio" name="tabmenu" id="like-picture" />
			<input type="radio" name="tabmenu" id="like-picture-all" />
			<div class="tabmenu">
				<div>
					<label for="my-picture" class="my-picture">
						<div>PHOTO CARD</div>
					</label>
					<label for="like-picture" class="like-picture">
						<div>Favorite Artwork</div>
					</label>
				</div>
				<button class="move-button">파도타기</button>
			</div>
			<label for="my-picture-all" class="my-picture-all">
				<div>ALL PHOTO CARD</div>
			</label>
			<label for="like-picture-all" class="like-picture-all">
				<div>ALL Favorite Artwork</div>
			</label>
			<div class="tab-inner">
				<div class="tabs">
					<div class="tab">
						<my-page-art-work />
					</div>
					<div class="tab2">
						<my-page-art-work-all />
					</div>
					<div class="tab">
						<like-art-work />
					</div>
					<div class="tab2">
						<like-art-work-all />
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import MyPageArtWork from '@/views/accounts/components/MyPageArtWork.vue'
import MyPageArtWorkAll from '@/views/accounts/components/MyPageArtWorkAll.vue'
import LikeArtWork from '@/views/accounts/components/LikeArtWork.vue'
import LikeArtWorkAll from '@/views/accounts/components/LikeArtWorkAll.vue'

export default {
	name: 'MyPageView',
	components: { MyPageArtWork, LikeArtWork, MyPageArtWorkAll, LikeArtWorkAll },
	setup() {},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');

.mypage {
	padding-top: 80px;
	height: 100vh;
	overflow: hidden;
}

input[name='tabmenu'] {
	display: none;
}

.tabmenu {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: absolute;
	z-index: 1;
}

.my-picture,
.like-picture {
	text-align: center;
	font-weight: 600;
	font-size: 20px;
	font-family: 'Noto Sans', sans-serif;

	cursor: pointer;
	align-items: center;
	color: rgba(0, 0, 0, 0.2);
	margin-left: 25px;
	white-space: nowrap;
}

.move-button {
	margin-right: 25px;
	align-items: center;
	background-color: transparent;
	border: 2px solid rgb(0, 0, 0, 0.2);
	padding: 2px 12px;
	border-radius: 20px;

	font-family: 'Noto Sans KR', sans-serif;
	color: rgba(0, 0, 0, 0.2);
	text-align: center;
	font-weight: 600;
	font-size: 15px;
	white-space: nowrap;
}

.move-button:hover {
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	color: rgba(0, 0, 0);
	border: 2px solid rgb(0, 0, 0);
}

.my-picture-all,
.like-picture-all {
	display: none;
	width: 100%;
	text-align: center;
	position: absolute;
	bottom: 30px;
	z-index: 1;

	font-family: 'Noto Sans', sans-serif;
	font-weight: 600;
	font-size: 20px;
	color: rgb(0, 0, 0, 0.2);
}

.my-picture-all:hover,
.like-picture-all:hover {
	color: rgb(0, 0, 0);
	cursor: pointer;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='my-picture-all']:checked ~ .tabmenu .my-picture,
input[id='like-picture']:checked ~ .tabmenu .like-picture,
input[id='like-picture-all']:checked ~ .tabmenu .like-picture {
	color: rgb(0, 0, 0);
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-bottom: 2px solid black;
}

.tab-inner {
	position: relative;
	overflow: hidden;
	height: 100%;
}

.tabs {
	height: 400%;
	display: flex;
	flex-direction: column;
	transition: 0.5s;
	position: relative;
	top: 0%;
}

.tab {
	height: 50%;
	margin-bottom: -3vh;
}

.tab2 {
	height: 50%;
	overflow-y: scroll;
}

input[id='my-picture']:checked ~ .my-picture-all {
	display: block;
}

input[id='like-picture']:checked ~ .like-picture-all {
	display: block;
}

input[id='my-picture']:checked ~ .tab-inner .tabs {
	top: 0;
}

input[id='my-picture-all']:checked ~ .tab-inner .tabs {
	top: -100%;
}

input[id='like-picture']:checked ~ .tab-inner .tabs {
	top: -200%;
}

input[id='like-picture-all']:checked ~ .tab-inner .tabs {
	top: -300%;
}

/* scroll */
.tab2::-webkit-scrollbar {
	width: 10px;
}

.tab2::-webkit-scrollbar-thumb {
	background-color: rgb(0, 0, 0);
	border-radius: 10px;
	background-clip: padding-box;
	border: 2px solid transparent;
}

.tab2::-webkit-scrollbar-track {
	background-color: rgb(0, 0, 0, 0.2);
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px white;
}
</style>
