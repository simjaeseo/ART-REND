<template>
	<div class="external">
		<div class="horizontal-scroll-wrapper">
			<div class="title-wrapper1">
				<h2 id="title-text1">
					Art of Trend <br />
					<h4>The Artist</h4>
				</h2>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Raphael')">
						<img src="@/assets/artist/라파엘로.png" alt="image" />
						<div class="image-info">
							<div class="title">Raffaello Sanzio da Urbino</div>
							<div class="name">라파엘로 산치오 다 우르비노</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Claude Monet')">
						<img src="@/assets/artist/클로드모네.png" alt="image" />
						<div class="image-info">
							<div class="title">Oscar-Claude Monet</div>
							<div class="name">클로드 모네</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Pablo Picasso')">
						<img src="@/assets/artist/피카소.png" alt="image" />
						<div class="image-info">
							<div class="title">Pablo Picasso</div>
							<div class="name">파블로 피카소</div>
						</div>
					</div>
				</a>
			</div>
			<!-- <div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Gustav Courbet')">
						<img src="@/assets/artist/귀스타브쿠르베.png" alt="image" />
						<div class="image-info">
							<div class="title">Jean-Désiré Gustave Courbet</div>
							<div class="name">귀스타브 쿠르베</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Salvador Dali')">
						<img src="@/assets/artist/살바도르달리.png" alt="image" />
						<div class="image-info">
							<div class="title">Salvador Dalí</div>
							<div class="name">살바도르 달리</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Leonardo da Vinci')">
						<img src="@/assets/artist/다빈치.png" alt="image" />
						<div class="image-info">
							<div class="title">Leonardo da Vinci</div>
							<div class="name">레오나르도 다 빈치</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Rene Magritte')">
						<img src="@/assets/artist/르네.png" alt="image" />
						<div class="image-info">
							<div class="title">Rene Magritte</div>
							<div class="name">르네마그리트</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Édouard Manet')">
						<img src="@/assets/artist/마네.png" alt="image" />
						<div class="image-info">
							<div class="title">Édouard Manet</div>
							<div class="name">에두아르 마네</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Max Ernst')">
						<img src="@/assets/artist/막스.png" alt="image" />
						<div class="image-info">
							<div class="title">Max Ernst</div>
							<div class="name">막스 에른스트</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Edvard Munch')">
						<img src="@/assets/artist/뭉크.png" alt="image" />
						<div class="image-info">
							<div class="title">Edvard Munch</div>
							<div class="name">에드바르 뭉크</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div
						class="image-box"
						@click.prevent="goDetail('Michelangelo Buonarroti')"
					>
						<img src="@/assets/artist/미켈란젤로.png" alt="image" />
						<div class="image-info">
							<div class="title">Michelangelo Buonarroti</div>
							<div class="name">미켈란젤로 부오나로티</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Van Gogh')">
						<img src="@/assets/artist/반고흐.png" alt="image" />
						<div class="image-info">
							<div class="title">Vincent van Gogh</div>
							<div class="name">빈센트 반 고흐</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Alfred Sisley')">
						<img src="@/assets/artist/알프레드시슬레.png" alt="image" />
						<div class="image-info">
							<div class="title">Alfred Sisley</div>
							<div class="name">알프레드 시슬레</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Andy Warhol')">
						<img src="@/assets/artist/앤디워홀.png" alt="image" />
						<div class="image-info">
							<div class="title">Andy Warhol</div>
							<div class="name">앤디워홀</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Auguste Renoir')">
						<img src="@/assets/artist/르누아르.png" alt="image" />
						<div class="image-info">
							<div class="title">Auguste Renoir</div>
							<div class="name">오귀스트 르누아르</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Eugène Boudin')">
						<img src="@/assets/artist/외젠부댕.png" alt="image" />
						<div class="image-info">
							<div class="title">Eugène Boudin</div>
							<div class="name">외젠 부댕</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div
						class="image-box"
						@click.prevent="goDetail('Jean-Francois Millet')"
					>
						<img src="@/assets/artist/장프랑수아밀레.png" alt="image" />
						<div class="image-info">
							<div class="title">Jean-Francois Millet</div>
							<div class="name">장프랑수아 밀레</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Georges Braque')">
						<img src="@/assets/artist/조르주브라크.png" alt="image" />
						<div class="image-info">
							<div class="title">Georges Braque</div>
							<div class="name">조르주브라크</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div
						class="image-box"
						@click.prevent="goDetail('Georges-Pierre Seurat')"
					>
						<img src="@/assets/artist/조르주쇠라.png" alt="image" />
						<div class="image-info">
							<div class="title">Georges Seurat</div>
							<div class="name">조르주쇠라</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Paul Gauguin')">
						<img src="@/assets/artist/폴고갱.png" alt="image" />
						<div class="image-info">
							<div class="title">Paul Gauguin</div>
							<div class="name">폴 고갱</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Paul Cezanne')">
						<img src="@/assets/artist/폴세잔.png" alt="image" />
						<div class="image-info">
							<div class="title">Paul Cézanne</div>
							<div class="name">폴 세잔</div>
						</div>
					</div>
				</a>
			</div>
			<div class="img-wrapper">
				<a target="_blank" rel="noopener">
					<div class="image-box" @click.prevent="goDetail('Paul Signac')">
						<img src="@/assets/artist/폴시냑.png" alt="image" />
						<div class="image-info">
							<div class="title">Paul Signac</div>
							<div class="name">폴 시냐크</div>
						</div>
					</div>
				</a>
			</div> -->
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
	name: 'ArtistPage',
	setup() {
		const router = useRouter()
		const goDetail = function (name) {
			router.push({ name: 'ArtistDetailPage', params: { name: name } })
		}
		return {
			goDetail,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');

/* hide scrollbar */
::-webkit-scrollbar {
	width: 1px;
	height: 1px;
}

::-webkit-scrollbar-button {
	width: 1px;
	height: 1px;
}

.external {
	height: 100vh;
	background-image: url('https://www.transparenttextures.com/patterns/concrete-wall.png');
	min-width: 100vw;
	min-height: 100vh;
	background-color: #8e1e24;
}

.horizontal-scroll-wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 100vh;
	transform: rotate(-90deg) translate3d(10vh, -100vh, 0);
	transform-origin: right top;
	overflow-y: auto;
	overflow-x: hidden;
	padding: 0;
	height: 100vw;
	transform-style: preserve-3d;
	perspective: 1px;
	padding-bottom: 10rem;
	padding-right: 10rem;
}

.title-wrapper1 {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
	min-width: 900px;
	transform-origin: 50% 50%;
	transform: rotate(90deg) translateZ(0px) translateX(0px);
	transition: 1s;
	z-index: -1;
}

#title-text1 {
	font-size: 60px;
	position: relative;
	font-family: 'Playfair Display', serif;
	animation: fadeInLeft 2s ease-out;
	color: white;
}

.img-wrapper {
	transform: rotate(90deg);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 60vh;
	transform-origin: 50% 50%;
	/* transform: rotate(90deg) translateZ(0px) translateX(0px); */
	transition: 1s;
}

.img-wrapper:hover {
	min-height: 75vh;
	transform: rotate(90deg) translateZ(0px) translateX(0px) scale(1.2);
}

.img-wrapper a {
	display: block;
	box-shadow: 15px 15px 60px rgba(0, 0, 0, 1);
	background-color: rgba(14, 10, 10, 0.514);
	padding: 0;
	cursor: pointer;
}

.image-box {
	transition: 0.5s;
	vertical-align: top;
	filter: grayscale(0%);
}

img {
	max-width: 45vh;
	max-height: 50vh;
}

/* a:hover .image-box {
	filter: grayscale(70%);
} */

a .image-info,
a .delete {
	display: block;
}

.image-info {
	position: absolute;
	bottom: -100px;
	text-align: center;
	color: white;
	transition: 1s;
	display: none;
	width: 100%;
}

.delete {
	position: absolute;
	top: 20px;
	right: 20px;
	color: rgb(255, 255, 255);
	font-family: 'Noto Sans', sans-serif;
	font-size: 13px;
	align-items: center;
	background-color: rgba(255, 255, 255, 0.5);
	border: none;
	border-radius: 20px;
	padding: 2px 10px;
	transition: 1s;
	display: none;
}

.title {
	font-family: 'Noto Sans', sans-serif;
	font-size: 2vh;
	font-weight: 600;
}

.name {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 1.5vh;
	font-weight: 500;
}
</style>
