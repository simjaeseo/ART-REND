<template>
	<div>
		<div id="wrap1" class="container">
			<h2 id="text1">Choose 2 pictures you like.</h2>
		</div>
		<div class="container">
			<div class="masonry">
				<div class="mItem">
					<img src="@/assets/login-bg/1.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/2.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/3.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/4.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/5.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/6.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/7.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/8.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/2.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/3.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/4.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/5.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/1.png" alt="img1" />
				</div>
				<div class="mItem">
					<img src="@/assets/login-bg/2.png" alt="img1" />
				</div>
			</div>
			<div id="btn-wrap">
				<button class="btn" id="nick-name-check-btn" type="submit">
					SELECT
				</button>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: 'SelectImage',
}
</script>

<style scoped>
#btn-wrap {
	text-align: end;
}
#nick-name-check-btn {
	background: white;
	color: black;
	text-align: end;
}
#wrap1 {
	display: flex;
	align-items: flex-end;
	height: 15vh;
}
#text1 {
	color: white;
	margin-bottom: 50px;
}
img {
	width: 100%;
	border-radius: 5px;
}

.masonry {
	column-count: 4;
	column-gap: 16px;
}

.masonry .mItem {
	display: inline-block;
	margin-bottom: 16px;
	width: 100%;
	filter: brightness(40%);
	cursor: pointer;
}
.masonry .mItem:hover {
	display: inline-block;
	margin-bottom: 16px;
	width: 100%;
	filter: brightness(100%);
	cursor: pointer;
}

@media (max-width: 1199px) {
	.masonry {
		column-count: 3;
	}
}

@media (max-width: 991px) {
	.masonry {
		column-count: 2;
	}
}

@media (max-width: 767px) {
	.masonry {
		column-count: 1;
	}
}
</style>
