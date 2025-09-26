import {defineConfig} from 'vitepress';

export default defineConfig({
	title: 'Jellyfin Kotlin SDK',
	description: 'Documentation for the Jellyfin Kotlin SDK.',

	head: [
		['link', { rel: 'icon', type: 'image/svg', href: '/jellyfin.svg' }],
	],

	themeConfig: {
		logo: 'jellyfin.svg',
		siteTitle: 'Kotlin SDK',
		socialLinks: [
			{
				icon: 'github',
				link: 'https://github.com/jellyfin/jellyfin-sdk-kotlin',
			}
		],
		footer: {
			message: 'Released under the LGPL-3.0 license.',
		},

		// Top navigation
		nav: [
			{
				text: 'Guide',
				link: '/guide/getting-started',
			},
			{
				text: 'Dokka',
				link: 'https://kotlin-sdk.jellyfin.org/dokka/',
			},
		],

		// Side navigation
		sidebar: [
			{
				text: 'Introduction',
				items: [
					{
						text: 'Getting started',
						link: '/guide/getting-started',
					},
				]
			},

			{
				text: 'Developing',
				items: [
					{
						text: 'Authentication',
						link: '/guide/authentication',
					},
					{
						text: 'WebSockets',
						link: '/guide/websockets',
					},
				]
			},

			{
				text: 'Migrations',
				items: [
					{
						text: 'Migrate to v1.1',
						link: '/migration/v1.1',
					},
					{
						text: 'Migrate to v1.2',
						link: '/migration/v1.2',
					},
					{
						text: 'Migrate to v1.4',
						link: '/migration/v1.4',
					},
					{
						text: 'Migrate to v1.5',
						link: '/migration/v1.5',
					},
					{
						text: 'Migrate to v1.6',
						link: '/migration/v1.6',
					},
					{
						text: 'Migrate to v1.7',
						link: '/migration/v1.7',
					},
				]
			}
		],
	},
});
