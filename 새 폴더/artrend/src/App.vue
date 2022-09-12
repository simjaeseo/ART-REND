<template>
  <div>
    <div class="fixed-top">
      <div class="navbar">
        <a class="navbar-brand" href="#">ART-REND</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
          <span>
            <img src="@/assets/button.png" alt="button" id="button-img">
          </span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
        aria-labelledby="offcanvasNavbarLabel">
          <div class="offcanvas-header">
            <p type="button" data-bs-dismiss="offcanvas" aria-label="Close" id="close-btn">X</p>
          </div>
          <div class="offcanvas-body">
            <h2 id="logo-title">ART-REND</h2>
            <h5 id="menu-text">MENU</h5>
            <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
              <li class="nav-item">
                <p>HOME</p>
              </li>
              <li class="nav-item">
                <p>PROFILE</p>
              </li>
              <li class="nav-item">
                <p>PAINTING</p>
              </li>
              <li class="nav-item2">
                <p>LOGOUT</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=DM+Serif+Display&display=swap');
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
  margin-top: 50px;
  font-weight: 900;
  font-size: 80px;
  color: gray;
}
.nav-item {
  font-weight: 900;
  font-size: 80px;
}
#menu-text {
  color: gray;
  font-weight: 900;
  font-size: 30px;
}
#logo-title {
  margin-bottom: 50px;
  margin-top: 100px;
  font-weight: 400;
  font-size: 100px;
  font-family: 'DM Serif Display', serif;
}
.nav-link {
  color: white;
}
.offcanvas-body {
  text-align: center;
}
.offcanvas-body {
  color: white;
}
.offcanvas, .offcanvas-lg, .offcanvas-md, .offcanvas-sm, .offcanvas-xl, .offcanvas-xxl {
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
</style>
