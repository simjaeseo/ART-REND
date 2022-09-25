import axios from 'axios'
import drf from '@/api/api'

export default {
	state: {
		detailData: [],
		inTime: '',
		outTime: '',
	},
	getters: {
		detailData: state => state.detailData,
	},
	mutations: {
		SET_DETAIL_DATA(state, data) {
			state.detailData = data
		},
		SET_IN_TIME_DATA(state, time) {
			state.inTime = time
		},
		SET_OUT_TIME_DATA(state, time) {
			state.outTime = time
		},
	},
	actions: {
		// imageConvert(data) {
		// 	console.log(data)
		// },
		getArtWorkDetail({ getters, commit }, artworkId) {
			const memberId = getters.userId
			axios({
				headers: getters.authHeader,
				url: drf.business.detail(artworkId, memberId),
				method: 'get',
			})
				.then(res => {
					commit('SET_DETAIL_DATA', res.data.data)
				})
				.catch(() => {
					alert('존재하지 않는 데이터입니다.')
				})
		},
		likeArtWork({ getters, dispatch }, artworkId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.like(),
				method: 'post',
				data: {
					paintingId: artworkId,
					memberId: getters.userId,
				},
			}).then(() => {
				dispatch('getArtWorkDetail', artworkId)
				dispatch('likeArtWorkList')
			})
		},
		unlikeArtWork({ getters, dispatch }, artworkId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.like(),
				method: 'delete',
				data: {
					paintingId: artworkId,
					memberId: getters.userId,
				},
			}).then(() => {
				dispatch('getArtWorkDetail', artworkId)
				dispatch('likeArtWorkList')
			})
		},
		leave({ commit }, time) {
			commit('SET_IN_TIME_DATA', time.inTime)
			commit('SET_OUT_TIME_DATA', time.outTime)
		},
	},
}
