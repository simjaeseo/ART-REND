import axios from 'axios'
import drf from '@/api/api'

export default {
	state: {
		detailData: [],
	},
	getters: {
		detailData: state => state.detailData,
	},
	mutations: {
		SET_DETAIL_DATA(state, data) {
			state.detailData = data
		},
	},
	actions: {
		imageConvert(data) {
			console.log(data)
		},
		getArtWorkDetail({ getters, commit }, artworkId) {
			axios({
				headers: getters.authHeader,
				url: drf.business.detail(artworkId),
				method: 'get',
				data: {
					painting_id: artworkId,
				},
			})
				.then(res => commit('SET_DETAIL_DATA', res.data.data))
				.catch(() => {
					alert('에러가발생했다')
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
			})
				.then(res => {
					console.log(res)
					dispatch('getArtWorkDetail', artworkId)
				})
				.catch(err => {
					console.log(err)
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
			})
				.then(res => {
					console.log(res)
					dispatch('getArtWorkDetail', artworkId)
				})
				.catch(err => {
					console.log(err)
				})
		},
	},
}
