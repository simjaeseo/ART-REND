const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
	transpileDependencies: true,
	devServer: {
		proxy: {
			// 이부분 추가
			'/auth-service': {
				target: 'http://localhost:8080/', // 요청할 서버 주소
				changeOrigin: true,
				logLevel: 'debug', // 터미널에 proxy 로그가 찍힌다.
			},
			'/business-service': {
				target: 'http://localhost:8080/', // 요청할 서버 주소
				changeOrigin: true,
				logLevel: 'debug', // 터미널에 proxy 로그가 찍힌다.
			},
		},
	},
})

// module.exports = {
// 	css: {
// 		loaderOptions: {
// 			sass: {
// 				data: `
// 						@import "@/style/style.scss";
// 						//@는 /src 와 같다
// 				`,
// 			},
// 		},
// 	},
// }
