const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
	transpileDependencies: true,
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
