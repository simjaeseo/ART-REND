<template>
  <div>
    <img
      :src="require(`@/assets/login-bg/${state.randomNum}.png`)"
      alt="img"
      id="background-img"
    />
    <login-form />
  </div>
</template>

<script>
import { reactive, onBeforeMount } from 'vue'
import LoginForm from '@/views/accounts/components/LoginForm.vue'

export default {
  name: 'LoginView',
  components: { LoginForm },
  setup() {
    const state = reactive({
      randomNum: null,
    })
    onBeforeMount(() => {
      state.randomNum = Math.floor(Math.random() * (9 - 1) + 1)
    })
    return {
      state,
    }
  },
}
</script>

<style scoped>
#background-img {
  height: 110vh;
  width: 100%;
  filter: brightness(40%);
  position: absolute;
  z-index: -1;
}
</style>
