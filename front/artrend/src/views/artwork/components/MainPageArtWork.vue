<template>
  <div>
    <h1>화이팅</h1>
    <div class="image-list">
      <div v-for="image in state.imageNum" :key="image" class="main-image">
        <img :src="require(`@/assets/main-img/${image}.jpg`)" alt="image" />
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'

export default {
  name: 'MainPageArtWork',
  setup() {
    const state = reactive({
      imageNum: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    })
    return {
      state,
    }
  },
}
</script>

<style scoped>
img {
  width: 100%;
}

.image-list {
  display: flex;
}

.main-image {
  width: 100%;
}
</style>
