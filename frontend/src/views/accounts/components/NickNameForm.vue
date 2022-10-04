<template>
	<div>
		<div id="wrap1">
			<div id="wrap2">
				<div id="wrap3">
					<h2 id="text1">
						Please enter your nickname between 2 and 7 letters.
					</h2>
				</div>
				<div>
					<form action="">
						<input
							type="text"
							id="user-nick-name"
							v-model="state.userNickName"
							placeholder=" "
							maxlength="7"
						/>
						<div id="btn-wrap">
							<button
								class="btn"
								id="nick-name-check-btn"
								type="submit"
								@click.prevent="doubleCheck"
							>
								CHECK
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
import { reactive } from 'vue'

export default {
	name: 'NickNameForm',
	setup() {
		const state = reactive({
			userNickName: null,
		})
		const store = useStore()

		const doubleCheck = function () {
			const name_pattern = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]{1,7}$/
			if (name_pattern.test(state.userNickName)) {
				const next = confirm(`${state.userNickName}(이)가 맞습니까?`)
				if (next) {
					store.dispatch('doubleCheck', state.userNickName)
				}
			} else {
				alert('닉네임은 2-7글자 사이의 한/영만 가능합니다.')
			}
		}
		return {
			doubleCheck,
			state,
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
	width: 50vw;
	color: white;
	font-size: 30px;
}
input:focus {
	outline: none;
	background-color: transparent;
}
input:placeholder-shown ~ #btn-wrap {
	visibility: hidden;
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
