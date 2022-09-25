const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
	transpileDependencies: true,
	devServer: {
		proxy: {
			'/auth-service': {
				target: 'http://j7c104.p.ssafy.io:8080/',
				changeOrigin: true,
				logLevel: 'debug',
			},
			'/business-service': {
				target: 'http://j7c104.p.ssafy.io:8080/',
				pathRewrite: { '^/detail': '/' },
				changeOrigin: true,
				logLevel: 'debug',
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
