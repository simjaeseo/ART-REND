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
				<div class="btn-box">
					<button
						class="move-button"
						alt="upload-button"
						id="upload-button"
						data-bs-toggle="modal"
						data-bs-target="#pictureModal"
						v-if="state.myPage"
					>
						닉네임 수정
					</button>
					<button class="move-button" @click="goOtherProfile">파도타기</button>
				</div>
			</div>
			<label for="my-picture-all" class="my-picture-all">
				<div>ALL PHOTO CARD</div>
			</label>
			<label for="my-picture" class="my-picture-top">
				<div class="top">ᐱ</div>
				<div>PHOTO CARD</div>
			</label>
			<label for="like-picture-all" class="like-picture-all">
				<div>ALL Favorite Artwork</div>
			</label>
			<label for="like-picture" class="like-picture-top">
				<div class="top">ᐱ</div>
				<div>Favorite Artwork</div>
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

		<!-- 사진변환 Modal -->
		<form>
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
							<h5 class="modal-title" id="exampleModalLabel">닉네임 수정</h5>
						</div>
						<div class="modal-body">
							<form action="">
								<input
									type="text"
									id="user-nick-name"
									:placeholder="state.nickName"
									v-model="state.modifyNickName"
								/>
								<button
									class="btn"
									id="nick-name-check-btn"
									type="submit"
									@click.prevent="doubleCheck"
								>
									CHECK
								</button>
							</form>
						</div>
						<div class="modal-footer">
							<button
								type="submit"
								class="change-button"
								@click="modify"
								v-if="state.success"
							>
								수정하기
							</button>
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
import { useRoute } from 'vue-router'
import { reactive, computed } from 'vue'
import MyPageArtWork from '@/views/accounts/components/MyPageArtWork.vue'
import MyPageArtWorkAll from '@/views/accounts/components/MyPageArtWorkAll.vue'
import LikeArtWork from '@/views/accounts/components/LikeArtWork.vue'
import LikeArtWorkAll from '@/views/accounts/components/LikeArtWorkAll.vue'
import { useStore } from 'vuex'
import axios from 'axios'
import drf from '@/api/api'

export default {
	name: 'MyPageView',
	components: { MyPageArtWork, LikeArtWork, MyPageArtWorkAll, LikeArtWorkAll },
	setup() {
		const store = useStore()
		const route = useRoute()
		const state = reactive({
			nickName: '',
			modifyNickName: null,
			success: false,
			myPage: false,
		})
		const memberId = route.params.memberId
		const userId = computed(() => store.getters.userId)
		if (memberId == userId.value) {
			state.myPage = true
		}

		store.dispatch('getAllUsers')
		const allUsersData = computed(() => store.getters.allUsers).value.data
		const allUsers = JSON.parse(JSON.stringify(allUsersData)) // Proxy -> 리스트
		const members = allUsers.filter(item => item !== userId.value) // 내 id 값 삭제
		const goOtherProfile = function () {
			const random = Math.floor(Math.random() * members.length)
			location.href = `http://localhost:3002/mypage/${members[random]}`
		}

		store.dispatch('likeArtWorkList', memberId)
		store.dispatch('getUserNickname', memberId)
		state.nickName = computed(() => store.getters.userNickName)
		const header = computed(() => store.getters.authHeader)
		store.dispatch('getImageConvert', memberId)
		// 닉네임 중복검사
		const doubleCheck = function () {
			console.log(state.modifyNickName)
			axios({
				headers: header.value,
				url: drf.auth.nickNameCheck(memberId),
				method: 'post',
				data: {
					nickname: state.modifyNickName,
				},
			})
				.then(() => {
					alert('사용할 수 있는 닉네임입니다.')
					state.success = true
				})
				.catch(err => {
					console.log(err)
					alert('사용할 수 없는 닉네임입니다.')
				})
		}

		const modify = function () {
			store.dispatch('modifyNickName', state.modifyNickName)
		}

		return {
			state,
			goOtherProfile,
			doubleCheck,
			modify,
		}
	},
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
	margin-right: 10px;
	align-items: center;
	background-color: transparent;
	border: 1px solid rgb(0, 0, 0, 0.2);
	padding: 2px 12px;
	border-radius: 10px;
	/* border: none; */

	font-family: 'Noto Sans KR', sans-serif;
	color: rgba(0, 0, 0, 0.2);
	text-align: center;
	font-weight: 400;
	font-size: 15px;
	white-space: nowrap;
}

.move-button:hover {
	text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.25);
	box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.25);
	color: rgba(0, 0, 0);
	border: 1px solid rgb(0, 0, 0);
}

.btn-box {
	margin-right: 10px;
}

.my-picture-all,
.like-picture-all,
.my-picture-top,
.like-picture-top {
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
.like-picture-all:hover,
.my-picture-top:hover,
.like-picture-top:hover {
	color: rgb(0, 0, 0);
	cursor: pointer;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='my-picture-all']:checked ~ .tabmenu .my-picture,
input[id='like-picture']:checked ~ .tabmenu .like-picture,
input[id='like-picture-all']:checked ~ .tabmenu .like-picture {
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

input[id='my-picture-all']:checked ~ .my-picture-top {
	display: block;
}

input[id='like-picture']:checked ~ .like-picture-all {
	display: block;
}

input[id='like-picture-all']:checked ~ .like-picture-top {
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
