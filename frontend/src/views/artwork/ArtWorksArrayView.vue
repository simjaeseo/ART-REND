<template>
	<div class="mypage">
		<input type="radio" name="tabmenu" id="my-picture" checked="true" />
		<input type="radio" name="tabmenu" id="like-picture" />
		<input type="radio" name="tabmenu" id="trans-picture" />
		<div class="tabmenu">
			<label for="my-picture" class="my-picture">
				<div>Views TOP 20</div>
			</label>
			<label for="like-picture" class="like-picture">
				<div @click="getLikes">Likes TOP 20</div>
			</label>
			<label for="trans-picture" class="trans-picture">
				<div @click="getTrans">Translations TOP 20</div>
			</label>
		</div>
		<div class="tab-inner">
			<div class="tabs">
				<div class="tab">
					<order-by-view />
				</div>
				<div class="tab">
					<order-by-like />
				</div>
				<div class="tab">
					<order-by-trans />
				</div>
			</div>
		</div>
		<div class="btn-class">
			<button class="btn2" @click="goMain">MAIN</button>
			<button class="btn1" @click="goProfile">PROFILE</button>
		</div>
	</div>
</template>

<script>
import OrderByLike from '@/views/artwork/components/OrderByLike.vue'
import OrderByView from '@/views/artwork/components/OrderByView.vue'
import OrderByTrans from '@/views/artwork/components/OrderByTrans.vue'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
	name: 'MyPageView',
	components: { OrderByLike, OrderByView, OrderByTrans },
	setup() {
		const store = useStore()

		const userId = computed(() => store.getters.userId)

		store.dispatch('getHits', 'hits')
		const getLikes = function () {
			store.dispatch('getLikes', 'totalLikeCount')
		}
		const getTrans = function () {
			store.dispatch('getTrans', 'totalChangeCount')
		}

		const goMain = function () {
			window.location.href = 'http://j7c104.p.ssafy.io/main'
		}
		const goProfile = function () {
			location.href = `http://j7c104.p.ssafy.io/mypage/${userId.value}`
		}
		return {
			getLikes,
			getTrans,
			goMain,
			goProfile,
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
.like-picture,
.trans-picture {
	text-align: center;
	font-weight: 600;
	font-size: 20px;
	font-family: 'Noto Sans', sans-serif;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='like-picture']:checked ~ .tabmenu .like-picture,
input[id='trans-picture']:checked ~ .tabmenu .trans-picture {
	color: rgb(0, 0, 0);
	text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	font-size: 25px;
	/* border-bottom: 2px solid black; */
}

.tab-inner {
	position: relative;
	overflow: hidden;
	height: 90%;
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
input[id='trans-picture']:checked ~ .tab-inner .tabs {
	top: -200%;
}

.btn1 {
	background: none;
	color: rgb(150, 150, 150);
	cursor: pointer;
	border: none;
	margin: 0 20px;
	font-size: 20px;
}
.btn2 {
	background: none;
	color: rgb(150, 150, 150);
	cursor: pointer;
	border: none;
	margin: 0 20px;
	font-size: 20px;
}
.btn1:hover,
.btn2:hover {
	color: black;
}
.btn-class {
	cursor: pointer;
	width: 100%;
	text-align: center;
	position: absolute;
	bottom: 3vh;
}
</style>
