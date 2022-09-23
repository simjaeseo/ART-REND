const AUTH = 'auth-service/'
const BUSiNESS = 'business-service/'
// const RECOMMEND = 'recommend-service/'

export default {
	auth: {
		kakaoLogin: () => `${AUTH}oauth2/authorization/kakao`,
		nickNameForm: memberId => `${AUTH}signup/${memberId}/nickname`,
		nickNameCheck: memberId => `${AUTH}signup/${memberId}/nickname/duplicate`,
	},
	business: {
		select: () => `${BUSiNESS}api/select`,
	},
}
