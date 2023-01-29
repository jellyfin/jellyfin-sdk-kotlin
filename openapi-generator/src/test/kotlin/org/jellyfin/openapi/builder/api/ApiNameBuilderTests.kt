package org.jellyfin.openapi.builder.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith

class ApiNameBuilderTests : FunSpec({
	test("Names end with Api") {
		val instance = ApiNameBuilder()

		instance.build("test") shouldEndWith "Api"
		instance.build("api") shouldEndWith "Api"
		instance.build("testApi") shouldEndWith "Api"
		instance.build("fourSeemsLikeEnoughAsserts") shouldEndWith "Api"
	}

	test("camelCase becomes PascalCase") {
		val instance = ApiNameBuilder()

		instance.build("misc") shouldBe "MiscApi"
		instance.build("library") shouldBe "LibraryApi"
		instance.build("mediaInfo") shouldBe "MediaInfoApi"
		instance.build("user") shouldBe "UserApi"
	}

	test("PascalCase stays PascalCase") {
		val instance = ApiNameBuilder()

		instance.build("Misc") shouldBe "MiscApi"
		instance.build("Library") shouldBe "LibraryApi"
		instance.build("MediaInfo") shouldBe "MediaInfoApi"
		instance.build("User") shouldBe "UserApi"
	}
})
