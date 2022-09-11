<template>
  <div>
    <h1>{{ state.randomNum }}</h1>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  name: 'LoginView',
  setup() {
    const state = reactive({
      randomNum: 'ㅇㅇㅇ',
    })
    return {
      state,
    }
  },
}
</script>

<style>

</style>
