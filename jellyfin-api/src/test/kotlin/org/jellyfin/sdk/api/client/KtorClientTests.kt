package org.jellyfin.sdk.api.client

import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import org.jellyfin.sdk.model.api.ItemFields
import kotlin.test.Test
import kotlin.test.assertEquals

public class KtorClientTests {
	private fun createClient() = KtorClient(
		baseUrl = "https://demo.jellyfin.org/stable/",
		accessToken = null,
		clientInfo = ClientInfo("Test", "0"),
		deviceInfo = DeviceInfo("test", "test"),
		httpClientOptions = HttpClientOptions()
	)

	@Test
	public fun `createUrl appends query parameters`() {
		val instance = createClient()
		val parameters = mapOf(
			"a" to "b",
			"c" to "2"
		)

		assertEquals(instance.baseUrl + "test?a=b&c=2", instance.createUrl("test", queryParameters = parameters))
	}

	@Test
	public fun `createUrl removes null values`() {
		val instance = createClient()
		val parameters = mapOf(
			"a" to "b",
			"c" to null,
			"d" to "2"
		)

		assertEquals(instance.baseUrl + "test?a=b&d=2", instance.createUrl("test", queryParameters = parameters))
	}

	@Test
	public fun `createUrl stringifies values`() {
		val instance = createClient()
		val parameters = mapOf(
			"one" to 1,
			"bool" to true,
			"str" to "str"
		)

		assertEquals(instance.baseUrl + "test?one=1&bool=true&str=str", instance.createUrl("test", queryParameters = parameters))
	}

	@Test
	public fun `createUrl stringifies enum values`() {
		val instance = createClient()
		val parameters = mapOf(
			"field1" to ItemFields.CHAPTERS,
			"field2" to ItemFields.DATE_CREATED,
		)

		assertEquals(instance.baseUrl + "test?field1=Chapters&field2=DateCreated", instance.createUrl("test", queryParameters = parameters))
	}
}
