module.exports = {
	// 현재 eslintrc 파일을 기준으로 ESLint 규칙을 적용
	root: true,
	// 추가적인 규칙들을 적용
	env: {
		node: true,
		es2021: true,
	},
	extends: [
		'eslint:recommended',
		'plugin:vue/essential',
		'prettier',
		'plugin:prettier/recommended',
	],
	// 코드 정리 플러그인 추가
	plugins: ['prettier'],
	// 사용자 편의 규칙 추가
	rules: {
		'prettier/prettier': [
			'error',
			// 아래 규칙들은 개인 선호에 따라 prettier 문법 적용
			// https://prettier.io/docs/en/options.html
			{
				arrowParens: 'avoid', // 화살표 함수 괄호 사용 방식
				bracketSpacing: true, // 객체 리터럴의 괄호 사이에 공백 출력
				endOfLine: 'auto', // 개행문자 CRLF/LF 자동 설정
				printWidth: 80, // 줄바꿈 길이 설정
				semi: false, // 명령문 끝에 세미콜론 추가
				singleQuote: true, // 작은 따옴표 사용
				tabWidth: 2, // 들여쓰기 공백 수 설정
				trailingComma: 'all', // 후행 쉼표 추가
				useTabs: true, // tab 사용
			},
		],
		semi: ['error', 'never'],
		quotes: ['error', 'single'],

		'vue/html-self-closing': [
			'error',
			{
				html: {
					void: 'always',
					normal: 'never',
					component: 'always',
				},
				svg: 'always',
				math: 'always',
			},
		],
		'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
	},
}
