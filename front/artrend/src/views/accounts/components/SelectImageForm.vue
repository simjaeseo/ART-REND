<template>
	<div>
		<div id="wrap1" class="container">
			<h2 id="text1">Click 2 pictures you like.</h2>
		</div>
		<div class="container">
			<div class="masonry">
				<div class="mItem filter" @click.prevent="selectedImage(1)" id="1">
					<img src="@/assets/login-bg/1.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(2)" id="2">
					<img src="@/assets/login-bg/2.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(3)" id="3">
					<img src="@/assets/login-bg/3.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(4)" id="4">
					<img src="@/assets/login-bg/4.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(5)" id="5">
					<img src="@/assets/login-bg/5.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(6)" id="6">
					<img src="@/assets/login-bg/6.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(7)" id="7">
					<img src="@/assets/login-bg/7.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(8)" id="8">
					<img src="@/assets/login-bg/8.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(9)" id="9">
					<img src="@/assets/login-bg/2.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(10)" id="10">
					<img src="@/assets/login-bg/3.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(11)" id="11">
					<img src="@/assets/login-bg/4.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(12)" id="12">
					<img src="@/assets/login-bg/5.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(13)" id="13">
					<img src="@/assets/login-bg/1.png" alt="img1" />
				</div>
				<div class="mItem filter" @click.prevent="selectedImage(14)" id="14">
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
	setup() {
		const selectedImages = []
		const selectedImage = function (num) {
			// 배열에 이미 선택한 사진이 있으면
			if (selectedImages.includes(num)) {
				// 포문을 돌면서 배열에 들어있는 선택한 값을 제거?
				for (let i = 0; i < selectedImages.length; i += 1) {
					if (selectedImages[i] == num) {
						document.getElementById(selectedImages[i]).classList.add('filter')
						selectedImages.splice(i, 1)
						i--
					}
				}
				// 배열에 선택한 사진이 없으면 새롭게 추가
			} else {
				selectedImages.push(num)
				const idx = selectedImages.indexOf(num)
				document.getElementById(selectedImages[idx]).classList.remove('filter')
			}
			// 두개넘어가면 첫번째 삭제
			if (selectedImages.length > 2) {
				document.getElementById(selectedImages[0]).classList.add('filter')
				selectedImages.shift()
			}

			console.log(selectedImages)
		}
		return {
			selectedImages,
			selectedImage,
		}
	},
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
	height: 20vh;
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
	cursor: pointer;
}
.masonry .mItem:hover {
	display: inline-block;
	margin-bottom: 16px;
	width: 100%;
	filter: brightness(100%);
	cursor: pointer;
}
.filter {
	filter: brightness(40%);
}
@media (max-width: 1199px) {
	.masonry {
		column-count: 3;
	}
}
</style>
