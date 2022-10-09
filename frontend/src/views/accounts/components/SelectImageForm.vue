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
			<div class="container">
				<div class="masonry">
					<div v-for="(img, index) in image" :key="index">
						<div
							class="mItem filter"
							@dblclick.prevent="selectedImage(img.id)"
							@mouseover="plusClass(img.id)"
							@mouseleave="deleteClass(img.id)"
							:id="img.id"
						>
							<img :src="img.url" alt="img146" :id="`${img.id}img`" />
							<img
								:id="`btn${img.id}`"
								class="zoom-btn"
								@click="zoomImage(img.id)"
								data-bs-toggle="modal"
								data-bs-target="#exampleModal"
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import { useStore } from 'vuex'
import { reactive, computed } from 'vue'

export default {
	name: 'SelectImage',
	setup() {
		const store = useStore()

		const state = reactive({
			selectedImages: [],
		})
		const image = computed(() => store.getters.selectList)
		const selectedImage = function (num) {
			if (state.selectedImages.includes(num)) {
				for (let i = 0; i < state.selectedImages.length; i += 1) {
					if (state.selectedImages[i] == num) {
						document
							.getElementById(state.selectedImages[i])
							.classList.add('filter')
						state.selectedImages.splice(i, 1)
						i--
					}
				}
			} else {
				state.selectedImages.push(num)
				const idx = state.selectedImages.indexOf(num)
				document
					.getElementById(state.selectedImages[idx])
					.classList.remove('filter')
			}
			if (state.selectedImages.length > 2) {
				document.getElementById(state.selectedImages[0]).classList.add('filter')
				state.selectedImages.shift()
			}
		}
		const zoomImage = function (num) {
			const modal = document.getElementById(`${num}img`)
			const img = modal.src
			const modalImg = document.getElementById('modal-inner')
			modal.style.display = 'block'
			modalImg.classList.add('cursor')
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
			store.commit('PREVENT_SELECT_PAGE', true)
		}
		return {
			deleteClass,
			plusClass,
			zoomImage,
			state,
			selectedImage,
			selectForm,
			image,
		}
	},
}
</script>

<style scoped>
.cursor {
	cursor: pointer;
}
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
	display: inline;
	height: 40px;
	margin-top: 11vh;
}
#wrap1 {
	display: flex;
	justify-content: space-between;
	/* height: 20vh; */

	height: 15vh;
}
#text1 {
	color: white;
	/* margin-bottom: 50px; */
	margin-top: 10vh;
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
	height: 90vh;
	overflow: auto;
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

@media (max-height: 1140px) {
	.container {
		max-width: 1140px;
	}
}

@media (max-height: 985px) {
	.container {
		max-width: 960px;
	}
}

@media (max-height: 825px) {
	.container {
		max-width: 720px;
	}
}

@media (max-height: 630px) {
	.container {
		max-width: 720px;
	}
}
</style>
