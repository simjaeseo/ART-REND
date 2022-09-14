<template>
  <div>
    <div id="wrap1">
      <div id="wrap2">
        <img
          src="@/assets/kakao_login/en/kakao_login_large_narrow.png"
          alt="kakao-login"
          id="kakao-btn"
          @click="loginWithKakao"
          @keydown="loginWithKakao"
        />
        <img
          src="@/assets/google_login/google_login.png"
          alt="google-login"
          id="google-btn"
          @click="loginWithGoogle"
          @keydown="loginWithGoogle"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  setup() {
    const loginWithKakao = function () {
      const params = {
        redirectUri: 'http://localhost:8080/auth',
      }
      window.Kakao.Auth.authorize(params)
    }

    return {
      loginWithKakao,
    }
  },
  data() {
    return {
      user: '',
    }
  },
  methods: {
    async loginWithGoogle() {
      try {
        const googleUser = await this.$gAuth.signIn()

        if (!googleUser) {
          return null
        }

        this.user = googleUser.getBasicProfile().getEmail()
      } catch (error) {
        console.log(error)
        return null
      }
      return null
    },
  },
}
</script>

<style scoped>
#google-btn {
  display: block;
  width: 70%;
  height: 70%;
}
#google-btn:hover {
  display: block;
  cursor: pointer;
}
#kakao-btn {
  display: block;
  margin-bottom: 10px;
  width: 70%;
  height: 70%;
}
#kakao-btn:hover {
  display: block;
  cursor: pointer;
}
#wrap2 {
  margin: auto;
}
#wrap1 {
  height: 100vh;
  width: 100%;
  display: flex;
}
</style>
