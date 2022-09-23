import axios from 'axios'
import drf from '@/api/api'

export default {
	state: {},
	getters: {},
	mutations: {},
	actions: {
		imageConvert(data) {
			console.log(data)
		},
		getArtWorkDetail({ getters }, artworkId) {
			console.log(artworkId)
			axios({
				headers: getters.authHeader,
				url: drf.business.detail(artworkId),
				method: 'get',
				data: {
					painting_id: artworkId,
				},
			}).then(res => console.log(res))
		},
	},
}
