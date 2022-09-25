<template>
	<div>
		<div
			class="modal fade"
			id="exampleModal"
			tabindex="-1"
			aria-labelledby="exampleModalLabel"
			aria-hidden="true"
		>
			<div class="modal-dialog modal-lg modal-dialog-centered">
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
						@dblclick.prevent="selectedImage(147)"
						@mouseover="plusClass(147)"
						@mouseleave="deleteClass(147)"
						id="147"
					>
						<img src="@/assets/select-img/147.jpg" alt="img147" />
						<img
							id="btn147"
							class="zoom-btn"
							@click="zoomImage(147)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(101)"
						@mouseover="plusClass(101)"
						@mouseleave="deleteClass(101)"
						id="101"
					>
						<img src="@/assets/select-img/101.jpg" alt="img101" />
						<img
							id="btn101"
							class="zoom-btn"
							@click="zoomImage(101)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(110)"
						@mouseover="plusClass(110)"
						@mouseleave="deleteClass(110)"
						id="110"
					>
						<img src="@/assets/select-img/110.jpg" alt="img110" />
						<img
							id="btn110"
							class="zoom-btn"
							@click="zoomImage(110)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(12)"
						@mouseover="plusClass(12)"
						@mouseleave="deleteClass(12)"
						id="12"
					>
						<img src="@/assets/select-img/12.jpg" alt="img12" />
						<img
							id="btn12"
							class="zoom-btn"
							@click="zoomImage(12)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(65)"
						@mouseover="plusClass(65)"
						@mouseleave="deleteClass(65)"
						id="65"
					>
						<img src="@/assets/select-img/65.jpg" alt="img65" />
						<img
							id="btn65"
							class="zoom-btn"
							@click="zoomImage(65)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(32)"
						@mouseover="plusClass(32)"
						@mouseleave="deleteClass(32)"
						id="32"
					>
						<img src="@/assets/select-img/32.jpg" alt="img32" />
						<img
							id="btn32"
							class="zoom-btn"
							@click="zoomImage(32)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(97)"
						@mouseover="plusClass(97)"
						@mouseleave="deleteClass(97)"
						id="97"
					>
						<img src="@/assets/select-img/97.jpg" alt="img97" />
						<img
							id="btn97"
							class="zoom-btn"
							@click="zoomImage(97)"
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
						<img src="@/assets/select-img/4.jpg" alt="img4" />
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
						@dblclick.prevent="selectedImage(52)"
						@mouseover="plusClass(52)"
						@mouseleave="deleteClass(52)"
						id="52"
					>
						<img src="@/assets/select-img/52.jpg" alt="img52" />
						<img
							id="btn52"
							class="zoom-btn"
							@click="zoomImage(52)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
					<div
						class="mItem filter"
						@dblclick.prevent="selectedImage(124)"
						@mouseover="plusClass(124)"
						@mouseleave="deleteClass(124)"
						id="124"
					>
						<img src="@/assets/select-img/124.jpg" alt="img124" />
						<img
							id="btn124"
							class="zoom-btn"
							@click="zoomImage(124)"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal"
						/>
					</div>
				</div>
				<div id="btn-wrap">
					<button
						class="btn"
						id="nick-name-check-btn"
						type="submit"
						v-if="state.selectedImages.length == 2"
						@click.prevent="selectForm"
					>
						SELECT
					</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive } from 'vue'

export default {
	name: 'SelectImage',
	setup() {
		const store = useStore()

		const state = reactive({
			selectedImages: [],
		})

		const selectedImage = function (num) {
			// 배열에 이미 선택한 사진이 있으면
			if (state.selectedImages.includes(num)) {
				// 포문을 돌면서 배열에 들어있는 선택한 값을 제거?
				for (let i = 0; i < state.selectedImages.length; i += 1) {
					if (state.selectedImages[i] == num) {
						document
							.getElementById(state.selectedImages[i])
							.classList.add('filter')
						state.selectedImages.splice(i, 1)
						i--
					}
				}
				// 배열에 선택한 사진이 없으면 새롭게 추가
			} else {
				state.selectedImages.push(num)
				const idx = state.selectedImages.indexOf(num)
				document
					.getElementById(state.selectedImages[idx])
					.classList.remove('filter')
			}
			// 두개넘어가면 첫번째 삭제
			if (state.selectedImages.length > 2) {
				document.getElementById(state.selectedImages[0]).classList.add('filter')
				state.selectedImages.shift()
			}
		}
		const zoomImage = function (num) {
			const modal = document.getElementById(`${num}`)
			const img = require(`../../../assets/select-img/${num}.jpg`)
			const modalImg = document.getElementById('modal-inner')
			// const modalInner = document.getElementById('modal-title')
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
		const selectForm = function () {
			const selectedImages = JSON.parse(JSON.stringify(state.selectedImages))
			store.dispatch('selectForm', selectedImages)
		}
		return {
			deleteClass,
			plusClass,
			zoomImage,
			state,
			selectedImage,
			selectForm,
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
	/* height: 20vh; */

	height: 15vh;
}
#text1 {
	color: white;
	/* margin-bottom: 50px; */

	margin-bottom: 20px;
}
img {
	width: 100%;
	border-radius: 5px;
}

.masonry {
	column-count: 4;
	cursor: pointer;
	margin-bottom: 16px;

	height: 70vh;
	overflow-y: hidden;
}

.masonry .mItem {
	margin-bottom: 16px;
	/* width: 100%;
	cursor: pointer; */
}

.masonry .mItem:hover {
	filter: brightness(100%);
	cursor: pointer;
}
.filter {
	filter: brightness(60%);
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
	z-index: var(--bs-modal-zindex);
	display: none;
	width: 100%;
	height: 100%;
	overflow-y: auto;
	outline: 0;
}
.modal-dialog {
	width: auto;
	height: 50vh;
}

.modal-body {
	display: flex;
	justify-content: center;
}
.modal-body > img {
	width: auto;
	max-width: 100%;
	max-height: 90vh;
}

/* scroll */
.masonry::-webkit-scrollbar {
	height: 10px;
}

.masonry::-webkit-scrollbar-thumb {
	background-color: rgb(255, 255, 255, 0.7);
	border-radius: 10px;
	background-clip: padding-box;
	border: 2px solid transparent;
}

.masonry::-webkit-scrollbar-track {
	background-color: rgba(255, 255, 255, 0.2);
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px rgb(0, 0, 0);
}
</style>
