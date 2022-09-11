import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import accounts from '@/store/modules/accounts';
import artwork from '@/store/modules/artwork';

export default createStore({
  modules: {
    accounts,
    artwork,
  },
  plugins: [
    createPersistedState({
      paths: [
        'accounts',
        'artwork',
      ],
    }),
  ],
});
