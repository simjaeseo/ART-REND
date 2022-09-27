const HOST = 'http://localhost:8080/'

const AUTH = 'auth-service/'
const BUSINESS = 'business-service/'
// const RECOMMEND = 'recommend-service/'

export default {
	auth: {
		kakaoLogin: () => `${HOST + AUTH}oauth2/authorization/kakao`,
		googleLogin: () => `${HOST + AUTH}oauth2/authorization/google`,
		nickNameForm: memberId => `${HOST + AUTH}signup/${memberId}/nickname`,
		nickNameCheck: memberId =>
			`${HOST + AUTH}signup/${memberId}/nickname/duplicate`,
		logout: () => `${HOST + AUTH}logout`,
		getUsersNumber: () => `${HOST + AUTH}members/count`,
	},
	business: {
		select: () => `${HOST + BUSINESS}api/select`,
		detail: (artworkId, memberId) =>
			`${HOST + BUSINESS}api/paintings/${artworkId}/${memberId}`,
		like: () => `${HOST + BUSINESS}api/like`,
		getHits: () => `${HOST + BUSINESS}api/paintings/sort`,
		getArtisDetail: () => `${HOST + BUSINESS}api/paintings/search`,
	},
}
