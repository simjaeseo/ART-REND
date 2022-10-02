<template>
	<div>
		<div class="fixed-top">
			<div class="navbar">
				<a href="http://j7c104.p.ssafy.io/main" id="brad-text">
					<h1 :style="[oneBlack ? { color: '#000000' } : { color: '#ffffff' }]">
						ART-REND
					</h1>
				</a>
				<button
					v-if="isLoggedIn"
					class="navbar-toggler"
					type="button"
					data-bs-toggle="offcanvas"
					data-bs-target="#offcanvasNavbar"
					aria-controls="offcanvasNavbar"
				>
					<span>
						<img
							v-if="twoBlack === false"
							src="@/assets/button.png"
							alt="button"
							id="button-img"
						/>
						<img
							v-if="twoBlack === true"
							src="@/assets/buttonblack.png"
							alt="button"
							id="button-img"
						/>
					</span>
				</button>
				<div
					class="offcanvas offcanvas-end"
					tabindex="-1"
					id="offcanvasNavbar"
					aria-labelledby="offcanvasNavbarLabel"
				>
					<div class="offcanvas-header">
						<p
							type="button"
							data-bs-dismiss="offcanvas"
							aria-label="Close"
							id="close-btn"
						>
							X
						</p>
					</div>
					<div class="offcanvas-body">
						<h2 id="logo-title">ART-REND</h2>
						<div id="menu-text">MENU</div>
						<ul class="navbar-nav justify-content-end">
							<li class="nav-item">
								<a href="http://j7c104.p.ssafy.io/main">
									<div>HOME</div>
								</a>
							</li>
							<li class="nav-item">
								<a :href="`http://j7c104.p.ssafy.io/mypage/${userId}`">
									<div>PROFILE</div>
								</a>
							</li>
							<li class="nav-item">
								<a href="http://j7c104.p.ssafy.io/artworks">
									<div>TOP ARTWORKS</div>
								</a>
							</li>
							<li class="nav-item">
								<a href="http://j7c104.p.ssafy.io/artist">
									<div>ARTIST</div>
								</a>
							</li>
							<li class="nav-item">
								<a href="http://j7c104.p.ssafy.io/genre">
									<div>GENRE</div>
								</a>
							</li>
							<li class="nav-item">
								<a href="http://j7c104.p.ssafy.io/movement">
									<div>MOVEMENT</div>
								</a>
							</li>
							<li class="nav-item2">
								<div @click="logout()">LOGOUT</div>
							</li>
							<li>
								<a href="http://j7c104.p.ssafy.io/unregister" id="withdraw">
									<div>WITHDRAW</div>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<router-view :key="$route.fullPath" />
	</div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
export default {
	name: 'App',
	setup() {
		const store = useStore()
		const isLoggedIn = computed(() => store.getters.isLoggedIn)
		const userId = computed(() => store.getters.userId)
		const color1 = computed(() => store.getters.color1)
		const color2 = computed(() => store.getters.color2)
		const cnt = computed(() => store.getters.cnt)
		let oneBlack = false
		let twoBlack = false
		let now = window.location.href
		if (now.includes('detail')) {
			oneBlack = true
		} else if (now.includes('mypage') || now.includes('artworks')) {
			oneBlack = true
			twoBlack = true
		} else if (
			(now.includes('main') && color1 && color2) ||
			(now.includes('artist') && color1 && color2) ||
			(now.includes('genre') && color1 && color2) ||
			(now.includes('movement') && color1 && color2)
		) {
			oneBlack = color1
			twoBlack = color2
		} else {
			oneBlack = false
			twoBlack = false
		}
		const logout = function () {
			store.dispatch('logout')
			document.getElementById('close-btn').click()
		}
		return {
			now,
			oneBlack,
			twoBlack,
			isLoggedIn,
			userId,
			logout,
			cnt,
		}
	},
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');
.fixed-bottom {
	text-align: end;
}
#brad-text {
	margin-top: 10px;
	margin-left: 20px;
	font-weight: 400;
	font-size: 100px;
	text-decoration: none;
	color: white;
	font-family: 'DM Serif Display', serif;
}
#button-img {
	widows: 20px;
	height: 20px;
	margin-right: 10px;
}
.navbar-brand {
	color: white;
	font-weight: 600;
	font-size: 30px;
	margin-left: 30px;
}
.offcanvas-header {
	justify-content: end;
}
#close-btn {
	color: gray;
	font-size: 30px;
}
#close-btn:hover {
	color: white;
}
.nav-item2 {
	margin-top: 4.5vh;
	font-weight: 900;
	color: gray;
	cursor: pointer;
	font-size: 4.5vh;
	/* font-size: 80px; */
}
.nav-item {
	font-weight: 900;
	font-size: 6vh;
	/* font-size: 80px; */
}
#menu-text {
	white-space: nowrap;
	color: gray;
	font-weight: 900;
	font-size: 2.5vh;
	/* font-size: 30px; */
}
#logo-title {
	/* margin-top: 100px; */
	/* margin-bottom: 50px; */
	margin-bottom: 4.5vh;
	font-weight: 400;
	font-family: 'DM Serif Display', serif;
	font-size: 7.5vh;
	/* font-size: 100px; */
}
.nav-link {
	color: white;
}
.offcanvas-body {
	text-align: center;
	/* overflow: hidden; */
}
.offcanvas-body {
	color: white;
	padding: 0;
	overflow-x: hidden;
}
.offcanvas,
.offcanvas-lg,
.offcanvas-md,
.offcanvas-sm,
.offcanvas-xl,
.offcanvas-xxl {
	--bs-offcanvas-width: 100%;
	--bs-offcanvas-height: 30vh;
	--bs-offcanvas-padding-x: 1rem;
	--bs-offcanvas-padding-y: 1rem;
	--bs-offcanvas-color: ;
	--bs-offcanvas-bg: rgb(0, 0, 0);
	--bs-offcanvas-border-width: 0px;
	--bs-offcanvas-border-color: var(--bs-border-color-translucent);
	--bs-offcanvas-box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
}
.navbar {
	--bs-navbar-padding-x: 0;
	--bs-navbar-padding-y: 0.5rem;
	--bs-navbar-color: rgba(0, 0, 0, 0.55);
	--bs-navbar-hover-color: rgba(0, 0, 0, 0.7);
	--bs-navbar-disabled-color: rgba(0, 0, 0, 0.3);
	--bs-navbar-active-color: rgba(0, 0, 0, 0.9);
	--bs-navbar-brand-padding-y: 0.3125rem;
	--bs-navbar-brand-margin-end: 1rem;
	--bs-navbar-brand-font-size: 1.25rem;
	--bs-navbar-brand-color: rgba(0, 0, 0, 0.9);
	--bs-navbar-brand-hover-color: rgba(0, 0, 0, 0.9);
	--bs-navbar-nav-link-padding-x: 0.5rem;
	--bs-navbar-toggler-padding-y: 0.25rem;
	--bs-navbar-toggler-padding-x: 0.75rem;
	--bs-navbar-toggler-font-size: 1.25rem;
	--bs-navbar-toggler-border-color: none;
	--bs-navbar-toggler-border-radius: 0.375rem;
	--bs-navbar-toggler-focus-width: 0rem;
	--bs-navbar-toggler-transition: box-shadow 0.3s ease-in-out;
	position: relative;
	display: flex;
	flex-wrap: wrap;
	align-items: center;
	justify-content: space-between;
	padding: var(--bs-navbar-padding-y) var(--bs-navbar-padding-x);
}
a {
	text-decoration: none;
	color: white;
}
#withdraw {
	color: gray;
	font-weight: 900;
	cursor: pointer;
	font-size: 1.5vh;
	/* font-size: 20px; */
}

/* scroll */
.offcanvas-body::-webkit-scrollbar {
	width: 10px;
}

.offcanvas-body::-webkit-scrollbar-thumb {
	background-color: rgb(255, 255, 255, 0.3);
	border-radius: 10px;
	background-clip: padding-box;
	border: 2px solid transparent;
}

.offcanvas-body::-webkit-scrollbar-track {
	background-color: rgba(255, 255, 255, 0.2);
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px rgb(0, 0, 0);
}
</style>
