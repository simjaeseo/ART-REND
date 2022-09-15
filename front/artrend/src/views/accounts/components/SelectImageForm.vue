<template>
	<div>
		<div
			class="modal fade"
			id="exampleModal"
			tabindex="-1"
			aria-labelledby="exampleModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog modal-xl">
				<div class="modal-content">
					<div class="modal-body">
						<img alt="" id="modal-inner" data-bs-dismiss="modal" />
					</div>
				</div>
			</div>
		</div>
		<div>
			<div id="wrap1" class="container">
				<h2 id="text1">Double-click the 2 pictures you like.</h2>
			</div>
			<div class="container">
				<div class="masonry">
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(1)"
						@mouseover="plusClass(1)"
						@mouseleave="deleteClass(1)"
						id="1"
					>
						<img src="@/assets/select-img/1.jpg" alt="img1" />
						<img
							id="btn1"
							class="zoom-btn"
							@click="zoomImage(1)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(2)"
						@mouseover="plusClass(2)"
						@mouseleave="deleteClass(2)"
						id="2"
					>
						<img src="@/assets/select-img/2.jpg" alt="img1" />
						<img
							id="btn2"
							class="zoom-btn"
							@click="zoomImage(2)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(3)"
						@mouseover="plusClass(3)"
						@mouseleave="deleteClass(3)"
						id="3"
					>
						<img src="@/assets/select-img/3.jpg" alt="img1" />
						<img
							id="btn3"
							class="zoom-btn"
							@click="zoomImage(3)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(4)"
						@mouseover="plusClass(4)"
						@mouseleave="deleteClass(4)"
						id="4"
					>
						<img src="@/assets/select-img/4.jpg" alt="img1" />
						<img
							id="btn4"
							class="zoom-btn"
							@click="zoomImage(4)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(5)"
						@mouseover="plusClass(5)"
						@mouseleave="deleteClass(5)"
						id="5"
					>
						<img src="@/assets/select-img/5.jpg" alt="img1" />
						<img
							id="btn5"
							class="zoom-btn"
							@click="zoomImage(5)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(6)"
						@mouseover="plusClass(6)"
						@mouseleave="deleteClass(6)"
						id="6"
					>
						<img src="@/assets/select-img/6.jpg" alt="img1" />
						<img
							id="btn6"
							class="zoom-btn"
							@click="zoomImage(6)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(7)"
						@mouseover="plusClass(7)"
						@mouseleave="deleteClass(7)"
						id="7"
					>
						<img src="@/assets/select-img/7.jpg" alt="img1" />
						<img
							id="btn7"
							class="zoom-btn"
							@click="zoomImage(7)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(8)"
						@mouseover="plusClass(8)"
						@mouseleave="deleteClass(8)"
						id="8"
					>
						<img src="@/assets/select-img/8.jpg" alt="img1" />
						<img
							id="btn8"
							class="zoom-btn"
							@click="zoomImage(8)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(9)"
						@mouseover="plusClass(9)"
						@mouseleave="deleteClass(9)"
						id="9"
					>
						<img src="@/assets/select-img/9.jpg" alt="img1" />
						<img
							id="btn9"
							class="zoom-btn"
							@click="zoomImage(9)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(10)"
						@mouseover="plusClass(10)"
						@mouseleave="deleteClass(10)"
						id="10"
					>
						<img src="@/assets/select-img/10.jpg" alt="img1" />
						<img
							id="btn10"
							class="zoom-btn"
							@click="zoomImage(10)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
				</div>
				<div id="btn-wrap">
					<button class="btn" id="nick-name-check-btn" type="submit">
						SELECT
					</button>
				</div>
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
			console.log('더블클릭했다!')
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
		}
		const zoomImage = function (num) {
			const modal = document.getElementById(`${num}`)
			const img = require(`../../../assets/select-img/${num}.jpg`)
			const modalImg = document.getElementById('modal-inner')
			const modalInner = document.getElementById('modal-title')
			console.log(modal)
			console.log(modalInner)
			modal.style.display = 'block'
			modalImg.src = img
		}
		const plusClass = function (num) {
			const img = document.getElementById(`btn${num}`)
			img.src = require('../../../assets/expand.png')
			img.classList.add('zoom-btn')
		}
		const deleteClass = function (num) {
			const img = document.getElementById(`btn${num}`)
			img.src = ''
			img.classList.remove('zoom-btn')
		}
		return {
			deleteClass,
			plusClass,
			zoomImage,
			selectedImages,
			selectedImage,
		}
	},
}
</script>

<style scoped>
#modall {
	height: 100%;
}
.zoom-btn {
	position: absolute;
	right: 0px;
	top: 0px;
	width: 30px;
	height: 30px;
}
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
	filter: brightness(100%);
	cursor: pointer;
	position: relative;
}
.filter {
	filter: brightness(40%);
}
.modal {
	--bs-modal-zindex: 1055;
	--bs-modal-width: 500px;
	--bs-modal-padding: 1rem;
	--bs-modal-margin: 0.5rem;
	--bs-modal-color: ;
	--bs-modal-bg: none;
	--bs-modal-border-color: var(--bs-border-color-translucent);
	--bs-modal-border-width: 1px;
	--bs-modal-border-radius: 0.5rem;
	--bs-modal-box-shadow: 0 0.125rem 0.25remrgba (0, 0, 0, 0.075);
	--bs-modal-inner-border-radius: calc(0.5rem - 1px);
	--bs-modal-header-padding-x: 1rem;
	--bs-modal-header-padding-y: 1rem;
	--bs-modal-header-padding: 1rem 1rem;
	--bs-modal-header-border-color: var(--bs-border-color);
	--bs-modal-header-border-width: 1px;
	--bs-modal-title-line-height: 1.5;
	--bs-modal-footer-gap: 0.5rem;
	--bs-modal-footer-bg: ;
	--bs-modal-footer-border-color: var(--bs-border-color);
	--bs-modal-footer-border-width: 1px;
	position: fixed;
	top: 0;
	left: 0;
	z-index: var(--bs-modal-zindex);
	display: none;
	width: 100%;
	height: 100%;
	overflow-x: hidden;
	overflow-y: auto;
	outline: 0;
}
</style>
