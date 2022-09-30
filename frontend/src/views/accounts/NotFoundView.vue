<template>
	<div id="back">
		<main id="banksy">
			<header>
				<h1 id="text1">404</h1>
				<h4 id="text2">PAGE NOT FOUND</h4>
			</header>
			<div id="piece" @click="goMain">
				<div id="holder">
					<div id="painting">
						<div id="shredded">
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
							<div class="shred"></div>
						</div>
						<div id="original"></div>
					</div>
				</div>
				<div id="frame"></div>
			</div>
			<input ref="art" type="file" accept="image/*" @change="artSelected" />
		</main>
	</div>
</template>

<script>
import anime from 'animejs/lib/anime.es.js'
export default {
	name: 'NotFoundView',
	data() {
		return {
			shredding: null,
			dropping: null,
		}
	},
	methods: {
		goMain() {
			this.$router.push({ name: 'Main' })
		},
		shred() {
			this.shredding = anime({
				targets: '#original',
				height: 0,
				duration: 10000,
				easing: 'linear',
			})

			this.dropping = anime({
				targets: '#painting',
				translateY: '101%',
				duration: 10000,
				easing: 'linear',
			})
		},
	},
	mounted() {
		this.shred()
	},
}
</script>

<style scoped>
#back {
	background-color: black;
	min-width: 100%;
	min-height: 150vh;
}
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');
#text1 {
	font-size: 150px;
	text-align: center;
	margin-top: 50px;
	font-family: 'DM Serif Display', serif;
	color: white;
}
#text2 {
	text-align: center;
	font-family: 'DM Serif Display', serif;
	color: white;
}
html,
body,
main {
	height: 100%;
	overflow: hidden;
	position: fixed;
	width: 100%;
}

main {
	align-items: center;
	display: flex;
	justify-content: center;
}

#piece {
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.25);
	cursor: pointer;
	height: 50vh;
	max-height: 820px;
	position: relative;
	width: calc(50vh * 0.774);
}

#frame {
	background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/141041/banksy-frame.png);
	background-size: cover;
	height: 100%;
	position: absolute;
	width: 100%;
}

#holder {
	background: url('https://artrend.s3.ap-northeast-2.amazonaws.com/FRONTEND/notfound.png');
	background-size: cover;
	height: calc(50vh * 0.69);
	left: 18.9%;
	position: absolute;
	top: 15.4%;
	width: calc(50vh * 0.774 * 0.617);
}

#painting {
	height: 100%;
	position: absolute;
	transform: translateY(0%);
	width: 100%;
}

#original,
.shred {
	background: url(https://artrend.s3.ap-northeast-2.amazonaws.com/Leonardo+da+Vinci/Leonardo+da+Vinci_Mona+Lisa.jpg);
	background-size: cover;
}

#original {
	height: 100%;
	position: absolute;
	top: 0;
	width: 100%;
}

#shredded {
	bottom: 0;
	display: grid;
	grid-column-gap: 3px;
	grid-template-columns: repeat(11, 1fr);
	height: 100%;
	position: absolute;
	width: 100%;
}

#shredded .shred {
	background-position: top center;
}

#shredded .shred:nth-child(1) {
	background-position: 0% 0%;
}

#shredded .shred:nth-child(2) {
	background-position: 10% 0%;
}

#shredded .shred:nth-child(3) {
	background-position: 20% 0%;
}

#shredded .shred:nth-child(4) {
	background-position: 30% 0%;
}

#shredded .shred:nth-child(5) {
	background-position: 40% 0%;
}

#shredded .shred:nth-child(6) {
	background-position: 50% 0%;
}

#shredded .shred:nth-child(7) {
	background-position: 60% 0%;
}

#shredded .shred:nth-child(8) {
	background-position: 70% 0%;
}

#shredded .shred:nth-child(9) {
	background-position: 80% 0%;
}

#shredded .shred:nth-child(10) {
	background-position: 90% 0%;
}

#shredded .shred:nth-child(11) {
	background-position: 100% 0%;
}

input {
	display: none;
}

header {
	position: absolute;
	top: 2em;
}

header a {
	color: black;
}
</style>
