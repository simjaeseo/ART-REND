<template>
	<div class="mypage">
		<input type="radio" name="tabmenu" id="my-picture" checked="true" />
		<input type="radio" name="tabmenu" id="like-picture" />
		<input type="radio" name="tabmenu" id="trans-picture" />
		<div class="tabmenu">
			<label for="my-picture" class="my-picture">
				<div>View</div>
			</label>
			<label for="like-picture" class="like-picture">
				<div>Like</div>
			</label>
			<label for="trans-picture" class="trans-picture">
				<div>Translation</div>
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
	</div>
</template>

<script>
import OrderByLike from '@/views/artwork/components/OrderByLike.vue'
import OrderByView from '@/views/artwork/components/OrderByView.vue'
import OrderByTrans from '@/views/artwork/components/OrderByTrans.vue'

export default {
	name: 'MyPageView',
	components: { OrderByLike, OrderByView, OrderByTrans },
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
.like-picture,
.trans-picture {
	text-align: center;
	font-weight: bold;
	font-size: 20px;
}

input[id='my-picture']:checked ~ .tabmenu .my-picture,
input[id='like-picture']:checked ~ .tabmenu .like-picture,
input[id='trans-picture']:checked ~ .tabmenu .trans-picture {
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
input[id='trans-picture']:checked ~ .tab-inner .tabs {
	top: -200%;
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
