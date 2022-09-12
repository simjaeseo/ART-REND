<template>
  <div>
    <img :src="require(`@/assets/login-bg/${state.randomNum}.png`)" alt="img" id="background-img">
  </div>
</template>

<script>
import { reactive, onBeforeMount } from 'vue';

export default {
  name: 'LoginView',
  setup() {
    const state = reactive({
      randomNum: null,
    });
    onBeforeMount(() => {
      state.randomNum = Math.floor(Math.random() * (9 - 1) + 1);
    });
    return {
      state,
    };
  },
};
</script>

<style scoped>
#background-img {
  height: 100vh;
  width: 100%;
  filter: brightness(40%);
}
</style>
