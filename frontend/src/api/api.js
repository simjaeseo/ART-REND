const HOST = 'http://localhost:8080/'

const AUTH = 'auth-service/'
const BUSINESS = 'business-service/'
// const RECOMMEND = 'recommend-service/'

export default {
	auth: {
		kakaoLogin: () => `${HOST + AUTH}oauth2/authorization/kakao`,
		googleLogin: () => `${HOST + AUTH}oauth2/authorization/google`,
		memberInfo: () => `${HOST + AUTH}api/signup/members/info`,
		nickNameForm: memberId => `${HOST + AUTH}api/signup/${memberId}/nickname`,
		nickNameCheck: memberId =>
			`${HOST + AUTH}api/signup/${memberId}/nickname/duplicate`,
		logout: () => `${HOST + AUTH}/logout`,
		getAllUsers: () => `${HOST + AUTH}api/members/id`,
		getUserNickname: memberId => `${HOST + AUTH}api/${memberId}/nickname`,
		beforeUnregister: memberId => `${HOST + AUTH}api/${memberId}/check`,
		unRegister: memberId => `${HOST + AUTH}api/withdrawal/${memberId}`,
	},
	business: {
		select: () => `${HOST + BUSINESS}api/select`,
		detail: (artworkId, memberId) =>
			`${HOST + BUSINESS}api/paintings/${artworkId}/${memberId}`,
		like: () => `${HOST + BUSINESS}api/like`,
		getHits: () => `${HOST + BUSINESS}api/paintings/sort`,
		getArtistList: () => `${HOST + BUSINESS}api/description/artist`,
		getGenreList: () => `${HOST + BUSINESS}api/description/genre`,
		getMovementList: () => `${HOST + BUSINESS}api/description/art-trend`,
		getDetail: () => `${HOST + BUSINESS}api/paintings/search`,
		actionLog: () => `${HOST + BUSINESS}api/log`,
		imageConvert: artworkId => `${HOST + BUSINESS}api/changing/${artworkId}`,
	},
}
