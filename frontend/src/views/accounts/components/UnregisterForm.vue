<template>
	<div>
		<div id="wrap1">
			<div id="wrap2">
				<div id="wrap3">
					<h2 id="text1">
						For Unregister your accounts, Please enter your Name And Birthday.
					</h2>
				</div>
				<div>
					<form @submit.prevent="signupForm">
						<label for="user-nick-name">
							<input
								type="text"
								id="user-nick-name"
								v-model="state.userName"
								placeholder="NAME"
								minlength="2"
								maxlength="5"
							/>
						</label>
						<label for="user-bd">
							<input
								type="text"
								id="user-bd"
								v-model="state.userBirthday"
								placeholder="YYYYMMDD"
								maxlength="8"
							/>
						</label>
						<div id="btn-wrap" v-if="!state.next">
							<button
								class="btn"
								id="nick-name-check-btn"
								type="button"
								@click.prevent="isSix"
							>
								CHECK
							</button>
						</div>
						<div id="btn-wrap" v-if="state.next" @click="memberInfo">
							<button class="btn" id="nick-name-check-btn" type="submit">
								DELETE
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed } from 'vue'
import axios from 'axios'
import drf from '@/api/api'

export default {
	name: 'InfoForm',
	setup() {
		const state = reactive({
			userName: '',
			userBirthday: '',
			next: false,
		})
		const store = useStore()
		const isSix = function () {
			const name_pattern = /^[가-힣]{2,5}$/
			const date_pattern =
				/^(19|20)\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[0-1])$/
			if (
				!date_pattern.test(state.userBirthday) ||
				!name_pattern.test(state.userName)
			) {
				alert(
					'이름이 올바르지 않거나 생년월일의 형식이 잘못되었습니다. 입력란을 확인해주세요.',
				)
				state.next = false
			} else if (
				date_pattern.test(state.userBirthday) &&
				name_pattern.test(state.userName)
			) {
				const next = true
				if (next == true) {
					state.next = true
				} else {
					state.next = false
				}
			}
		}

		const memberId = computed(() => store.getters.userId)
		const authHeader = computed(() => store.getters.authHeader)
		const memberInfo = function () {
			axios({
				headers: authHeader.value,
				url: drf.auth.beforeUnregister(memberId.value),
				method: 'post',
				data: {
					name: state.userName,
					birth: state.userBirthday,
				},
			})
				.then(() => {
					const result = confirm(
						'정말 탈퇴하시겠습니까? 삭제된 계정은 복구 할 수 없습니다.',
					)
					if (result == true) {
						axios({
							headers: authHeader.value,
							url: drf.auth.unRegister(memberId.value),
							method: 'delete',
						})
							.then(() => {
								alert('회원탈퇴되었습니다. 로그인페이지로 이동합니다.')
								window.location.href = 'http://j7c104.p.ssafy.io/'
							})
							.catch(() => {
								alert('서비스가 비정상적입니다. 다시 시도해주세요')
							})
					} else if (result == false) {
						alert('회원탈퇴를 취소합니다. 메인페이지로 돌아갑니다.')
						window.location.href = 'http://j7c104.p.ssafy.io/main'
					}
				})
				.catch(() => alert('서비스가 비정상적입니다. 다시 시도해주세요'))
		}
		return {
			isSix,
			state,
			memberInfo,
		}
	},
}
</script>

<style scoped>
#btn-wrap {
	width: 100%;
	text-align: end;
	margin-top: 20px;
}
#nick-name-check-btn {
	background: white;
	color: black;
	text-align: end;
}
input {
	background-color: transparent;
	border: none;
	border-bottom: 1px solid white;
	width: 30vw;
	color: white;
	font-size: 30px;
	display: block;
	margin-right: 100px;
}
input:focus {
	outline: none;
	background-color: transparent;
}
#wrap3 {
	text-align: start;
	width: 50vw;
}
#text1 {
	color: white;
	margin-bottom: 50px;
}
#wrap2 {
	margin: auto;
}
#wrap1 {
	height: 100vh;
	width: 100%;
	display: flex;
	overflow-y: hidden;
}
</style>
