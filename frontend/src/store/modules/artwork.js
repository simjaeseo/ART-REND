import axios from 'axios'
import drf from '@/api/api'

export default {
	state: {
		detailData: [],
		orderByView: [],
		orderByLike: [],
		orderByTranslation: [],
		artistDetail: [],
		genreDetail: [],
		movementDetail: [],
		artistDetailBackImg: '',
		movementDetailBackImg: '',
		genreDetailBackImg: '',
		color1: false,
		color2: false,
		artistData: [],
		movementData: [],
		genreData: [],
		test: [],
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
		movementDetailBackImg: state => state.movementDetailBackImg,
		genreDetailBackImg: state => state.genreDetailBackImg,
		genreDetail: state => state.genreDetail,
		movementDetail: state => state.movementDetail,
		color1: state => state.color1,
		color2: state => state.color2,
		artistData: state => state.artistData,
		movementData: state => state.movementData,
		genreData: state => state.genreData,
	},
	mutations: {
		SET_DETAIL_DATA(state, data) {
			state.detailData = data
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
			state.artistDetailBackImg = data.paintings[0].url
		},
		SET_GENRE_DETAIL(state, data) {
			state.genreDetail = data
			state.genreDetailBackImg = data.paintings[1].url
		},
		SET_MOVEMENT_DETAIL(state, data) {
			state.movementDetail = data
			state.movementDetailBackImg = data.paintings[1].url
		},
		SET_COLOR1(state, value) {
			state.color1 = value
		},
		SET_COLOR2(state, value) {
			state.color2 = value
		},
		SET_ARTIST_LIST(state, data) {
			state.artistData = data
		},
		SET_MOVEMENT_LIST(state, data) {
			state.movementData = data
		},
		SET_GENRE_LIST(state, data) {
			state.genreData = data
		},
	},
	actions: {
		imageConvert({ getters }, payload) {
			const id = payload.get('artworkId')
			axios({
				headers: {
					'Content-Type': 'multipart/form-data',
					Authorization: getters.authHeader.Authorization,
				},
				url: drf.business.imageConvert(id),
				method: 'post',
				data: {
					file: payload.get('file'),
				},
			})
				.then(res => console.log(res))
				.catch(err => console.log(err))
		},
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
				.catch(err => {
					console.log(err)
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
			})
				.then(res => {
					console.log(res)
					dispatch('getArtWorkDetail', artworkId)
					dispatch('likeArtWorkList')
				})
				.catch(err => console.log(err))
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
					dispatch('likeArtWorkList')
				})
				.catch(err => console.log(err))
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
		getArtistList({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getArtistList(),
				method: 'get',
			}).then(res => {
				commit('SET_ARTIST_LIST', res.data.data)
			})
		},
		getMovementList({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getMovementList(),
				method: 'get',
			}).then(res => {
				commit('SET_MOVEMENT_LIST', res.data.data)
			})
		},
		getGenreList({ getters, commit }) {
			axios({
				headers: getters.authHeader,
				url: drf.business.getGenreList(),
				method: 'get',
			}).then(res => {
				commit('SET_GENRE_LIST', res.data.data)
			})
		},
		getArtistDetail({ commit }, name) {
			axios({
				url: drf.business.getDetail(),
				method: 'get',
				params: {
					artist: name,
					page: '0',
					size: '20',
					sort: 'DESC',
				},
			})
				.then(res => {
					commit('SET_ARTIST_DETAIL', res.data.data)
				})
				.catch(err => {
					console.log(err)
				})
		},
		getGenreDetail({ commit }, name) {
			axios({
				url: drf.business.getDetail(),
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
		getMovementDetail({ commit }, name) {
			axios({
				url: drf.business.getDetail(),
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
		getMainBasedOnActionLog({ getters, commit }) {
			console.log('액션로그ㅋ')
			axios({
				headers: getters.authHeader,
				url: drf.business.getMainBasedOnActionLog(),
				method: 'get',
			})
				.then(res => {
					console.log(res)
					commit('SET_MAIN_IMAGE', res.data)
				})
				.catch(err => {
					console.log(err)
				})
		},
		actionLog({ getters }, actionLog) {
			console.log(actionLog)
			axios({
				headers: getters.authHeader,
				url: drf.business.actionLog(),
				method: 'post',
				data: {
					memberId: getters.userId,
					paintingId: actionLog.paintingId,
					clickCount: actionLog.clickCnt,
					zoomCount: actionLog.zoomCnt,
					inTime: actionLog.inTime,
					outTime: actionLog.outTime,
					changeCount: actionLog.change,
				},
			})
				.then(res => {
					console.log(res)
				})
				.catch(err => {
					console.log(err)
				})
		},
	},
}
