<template>
	<div class="mypage">
		<div class="mypage-top">
			<h1 class="title">Mypage</h1>
			<div class="button-box">
				<button class="button">수정</button>
				<button class="button">탈퇴</button>
			</div>
		</div>
		<input type="radio" name="tabmenu" id="my-picture" checked="true" />
		<input type="radio" name="tabmenu" id="like-picture" />
		<div class="tabmenu">
			<label for="my-picture" class="my-picture">
				<div>PHOTO CARD</div>
			</label>
			<label for="like-picture" class="like-picture">
				<div>LIKE PICTURE</div>
			</label>
		</div>
		<div class="tab-inner">
			<div class="tabs">
				<div class="tab">
					<my-page-art-work />
				</div>
				<div class="tab">
					<my-page-art-work />
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import MyPageArtWork from '@/views/accounts/components/MyPageArtWork.vue'

export default {
	name: 'MyPageView',
	components: { MyPageArtWork },
}
</script>

<style scoped>
.mypage {
	padding-top: 80px;
	height: 100vh;
	overflow: hidden;
}

.mypage-top {
	width: 100%;
	height: 20%;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	justify-content: center;
	padding-left: 10%;
}

.title {
	font-weight: bold;
}

input[name='tabmenu'] {
	display: none;
}

.tabmenu {
	width: 100%;
	display: flex;
	justify-content: space-evenly;
	margin: 1%;
}

.tabmenu > label {
	cursor: pointer;
	align-items: center;
	color: rgba(0, 0, 0, 0.2);
}

.my-picture,
.like-picture {
	text-align: center;
	font-weight: bold;
	font-size: 20px;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='like-picture']:checked ~ .tabmenu .like-picture {
	color: rgb(0, 0, 0);
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	font-size: 25px;
}

.tab-inner {
	position: relative;
	overflow: hidden;
	height: 75%;
}

.tabs {
	height: 200%;
	display: flex;
	flex-direction: column;
	transition: 0.5s;
	position: relative;
	top: 0%;
}

.tab {
	height: 50%;
}

input[id='my-picture']:checked ~ .tab-inner .tabs {
	top: 0;
}

input[id='like-picture']:checked ~ .tab-inner .tabs {
	top: -100%;
}

.button {
	color: rgb(255, 255, 255);
	font-size: 13px;
	align-items: center;
	background-color: rgba(0, 0, 0, 0.3);
	border: none;
	border-radius: 10px;
	padding: 2px 10px;
	margin-top: 10px;
	margin-right: 10px;
}
</style>
