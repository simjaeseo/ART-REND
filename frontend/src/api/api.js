const HOST = 'http://localhost:8080/'

const AUTH = 'auth-service/'
// const BUSSiNESS = 'bussiness-service/'
// const RECOMMEND = 'recommend-service/'

export default {
	auth: {
		kakaoLogin: () => `${HOST + AUTH}oauth2/authorization/kakao`,
		nickNameForm: memberId => `${HOST + AUTH}signup/${memberId}/nickname`,
		nickNameCheck: memberId =>
			`${HOST + AUTH}signup/${memberId}/nickname/duplicate`,
	},
}
