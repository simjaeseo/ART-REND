import axios from 'axios'
import drf from '@/api/api'

export default {
	state: {
		detailData: [],
		inTime: null,
		outTime: null,
		orderByView: [],
		orderByLike: [],
		orderByTranslation: [],
		artistDetail: [],
		genreDetail: [],
		movementDetail: [],
		artistDetailBackImg: '',
		color1: false,
		color2: false,
	},
	getters: {
		detailData: state => state.detailData,
		inTime: state => new Date(state.inTime),
		outTime: state => new Date(state.outTime),
		orderByView: state => state.orderByView,
		orderByLike: state => state.orderByLike,
		orderByTranslation: state => state.orderByTranslation,
		artistDetail: state => state.artistDetail,
		artistDetailBackImg: state => state.artistDetailBackImg,
		genreDetail: state => state.genreDetail,
		movementDetail: state => state.movementDetail,
		color1: state => state.color1,
		color2: state => state.color2,
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
			console.log(time)
		},
		SET_HITS_LIST(state, data) {
			state.orderByView = data
		},
		SET_LIKES_LIST(state, data) {
			state.orderByLike = data
		},
		SET_TRANS_LIST(state, data) {
			state.orderByTranslation = data
		},
		SET_ARTIST_DETAIL(state, data) {
			state.artistDetail = data
			state.artistDetailBackImg = data[1].url
			console.log('엥')
			console.log(state.artistDetailBackImg)
			console.log('엥')
		},
		SET_GENRE_DETAIL(state, data) {
			state.genreDetail = data
		},
		SET_MOVEMENT_DETAIL(state, data) {
			state.movementDetail = data
		},
		SET_COLOR1(state, value) {
			state.color1 = value
		},
		SET_COLOR2(state, value) {
			state.color2 = value
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
		getHits({ getters, commit }, hits) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getHits(),
				method: 'get',
				params: {
					type: hits,
				},
			}).then(res => {
				commit('SET_HITS_LIST', res.data.data)
			})
		},
		getLikes({ getters, commit }, hits) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getHits(),
				method: 'get',
				params: {
					type: hits,
				},
			}).then(res => {
				commit('SET_LIKES_LIST', res.data.data)
			})
		},
		getTrans({ getters, commit }, hits) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getHits(),
				method: 'get',
				params: {
					type: hits,
				},
			}).then(res => {
				commit('SET_TRANS_LIST', res.data.data)
			})
		},
		getArtisDetail({ getters, commit }, name) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getArtisDetail(),
				method: 'get',
				params: {
					artist: name,
					page: '0',
					size: '20',
					sort: 'DESC',
				},
			}).then(res => {
				commit('SET_ARTIST_DETAIL', res.data.data)
			})
				.then(res => {
					console.log(res)
					commit('SET_ARTIST_DETAIL', res.data.data)
				})
				.catch(err => {
					console.log(err)
				})
		},
		getGenreDetail({ getters, commit }, name) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getArtisDetail(),
				method: 'get',
				params: {
					genre: name,
					page: '0',
					size: '20',
					sort: 'DESC',
				},
			})
				.then(res => {
					console.log(res)
					commit('SET_GENRE_DETAIL', res.data.data)
				})
				.catch(err => {
					console.log(err)
				})
		},
		getMovementDetail({ getters, commit }, name) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getArtisDetail(),
				method: 'get',
				params: {
					artTrend: name,
					page: '0',
					size: '20',
					sort: 'DESC',
				},
			})
				.then(res => {
					console.log(res)
					commit('SET_MOVEMENT_DETAIL', res.data.data)
				})
				.catch(err => {
					console.log(err)
				})
		},
	},
}
