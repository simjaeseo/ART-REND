<template>
	<div id="div-back">
		<div class="mypage">
			<input type="radio" name="tabmenu" id="my-picture" checked="true" />
			<input type="radio" name="tabmenu" id="my-picture-all" />
			<label for="my-picture-all" class="my-picture-all">
				<div>ALL PHOTO CARD</div>
			</label>
			<label for="my-picture" class="my-picture-top">
				<div class="top">ᐱ</div>
				<div>PHOTO CARD</div>
			</label>
			<div class="tab-inner">
				<div class="tabs">
					<div class="tab">
						<exhibition-row />
					</div>
					<div class="tab2">
						<exhibition-all />
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import ExhibitionRow from '@/views/artwork/components/ExhibitionRow.vue'
import ExhibitionAll from '@/views/artwork/components/ExhibitionAll.vue'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
	name: 'ExhibitionView',
	components: { ExhibitionRow, ExhibitionAll },
	setup() {
		const store = useStore()
		store.dispatch('getImageConvertAll')
		const convertList = computed(() => store.getters.convertListAll)
		return {
			convertList,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
#div-back {
	background-color: #000000;
	background-image: url('https://www.transparenttextures.com/patterns/concrete-wall.png');
}
.mypage {
	padding-top: 80px;
	height: 100vh;
	overflow: hidden;
}

input[name='tabmenu'] {
	display: none;
}

.my-picture {
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

.my-picture-all,
.my-picture-top {
	display: none;
	text-align: center;
	position: absolute;
	bottom: 30px;
	z-index: 1;
	width: 100%;

	font-family: 'Noto Sans', sans-serif;
	font-weight: 600;
	font-size: 20px;
	color: rgb(220, 220, 220);
}

.top {
	margin-bottom: -7px;
}

.my-picture-all:hover,
.my-picture-top:hover {
	color: rgb(190, 190, 190);
	cursor: pointer;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='my-picture-all']:checked ~ .tabmenu .my-picture {
	color: rgb(0, 0, 0);
	text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.25);
	border-bottom: 2px solid black;
}

.tab-inner {
	position: relative;
	overflow: hidden;
	height: 100%;
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
	/* margin-bottom: -3vh; */
}

.tab2 {
	height: 50%;
	overflow-y: scroll;
	margin-top: 100px;
}

input[id='my-picture']:checked ~ .my-picture-all {
	display: block;
}

input[id='my-picture-all']:checked ~ .my-picture-top {
	display: block;
}

input[id='my-picture']:checked ~ .tab-inner .tabs {
	top: 0;
}

input[id='my-picture-all']:checked ~ .tab-inner .tabs {
	top: -100%;
}

/* scroll */
.tab2::-webkit-scrollbar {
	width: 10px;
}

.tab2::-webkit-scrollbar-thumb {
	background-color: rgb(255, 255, 255, 0.7);
	border-radius: 10px;
	background-clip: padding-box;
	border: 2px solid transparent;
}

.tab2::-webkit-scrollbar-track {
	background-color: rgba(255, 255, 255, 0.2);
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px rgb(0, 0, 0);
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

.modal-title {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 600;
}

.modal-body {
	padding: 50px;
}
.modal-body > form {
	display: flex;
}

#user-nick-name {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 20px;
	width: 90%;
	background-color: transparent;
	border: none;
	border-bottom: 1px solid rgb(0, 0, 0);
	color: rgb(0, 0, 0);
}
#user-nick-name::placeholder {
	color: rgb(200, 200, 200);
	font-weight: 200;
	font-size: 18px;
}
#user-nick-name:focus {
	outline: none;
	background-color: transparent;
}

#user-nick-name:placeholder-shown ~ #nick-name-check-btn {
	visibility: hidden;
}
#nick-name-check-btn {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 200;
	background: white;
	color: black;
	padding: 0px 10px;
	text-align: center;
}
#nick-name-check-btn:hover {
	color: black;
	border: 1px solid black;
}

.change-button {
	font-family: 'Noto Sans KR', sans-serif;
	font-weight: 400;
	border: 1px solid rgb(255, 255, 255);
	border-radius: 10px;
	background-color: white;
	color: rgb(200, 200, 200);
	padding: 2px 10px;
}

.change-button:hover {
	font-weight: 400;
	color: rgb(0, 0, 0);
	border: 1px solid black;
}
</style>
