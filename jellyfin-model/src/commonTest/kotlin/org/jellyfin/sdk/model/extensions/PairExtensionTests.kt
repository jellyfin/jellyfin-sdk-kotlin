package org.jellyfin.sdk.model.extensions

import org.jellyfin.sdk.model.api.NameIdPair
import org.jellyfin.sdk.model.api.NameValuePair
import org.jellyfin.sdk.model.api.XmlAttribute
import kotlin.test.Test
import kotlin.test.assertEquals

class PairExtensionTests {
	@Test
	fun `NameIdPair to Pair`() {
		assertEquals(Pair("id", "name"), NameIdPair(id = "id", name = "name").toPair())
		assertEquals(Pair("id", null), NameIdPair(id = "id", name = null).toPair())
		assertEquals(Pair(null, "name"), NameIdPair(id = null, name = "name").toPair())
		assertEquals(Pair(null, null), NameIdPair(id = null, name = null).toPair())
		assertEquals(Pair("id", "name"), NameIdPair("name", "id").toPair())
	}

	@Test
	fun `Pair to NameIdPair`() {
		assertEquals(NameIdPair(id = "id", name = "name"), Pair("id", "name").toNameIdPair())
		assertEquals(NameIdPair(id = "id", name = null), Pair("id", null).toNameIdPair())
		assertEquals(NameIdPair(id = null, name = "name"), Pair(null, "name").toNameIdPair())
		assertEquals(NameIdPair(id = null, name = null), Pair(null, null).toNameIdPair())
		assertEquals(NameIdPair("name", "id"), Pair("id", "name").toNameIdPair())
	}

	@Test
	fun `NameValuePair to Pair`() {
		assertEquals(Pair("name", "value"), NameValuePair(name = "name", value = "value").toPair())
		assertEquals(Pair("name", null), NameValuePair(name = "name", value = null).toPair())
		assertEquals(Pair(null, "value"), NameValuePair(name = null, value = "value").toPair())
		assertEquals(Pair(null, null), NameValuePair(name = null, value = null).toPair())
		assertEquals(Pair("name", "value"), NameValuePair("name", "value").toPair())
	}

	@Test
	fun `Pair to NameValuePair`() {
		assertEquals(NameValuePair(name = "name", value = "value"), Pair("name", "value").toNameValuePair())
		assertEquals(NameValuePair(name = "name", value = null), Pair("name", null).toNameValuePair())
		assertEquals(NameValuePair(name = null, value = "value"), Pair(null, "value").toNameValuePair())
		assertEquals(NameValuePair(name = null, value = null), Pair(null, null).toNameValuePair())
		assertEquals(NameValuePair("name", "value"), Pair("name", "value").toNameValuePair())
	}

	@Test
	fun `XmlAttribute to Pair`() {
		assertEquals(Pair("name", "value"), XmlAttribute(name = "name", value = "value").toPair())
		assertEquals(Pair("name", null), XmlAttribute(name = "name", value = null).toPair())
		assertEquals(Pair(null, "value"), XmlAttribute(name = null, value = "value").toPair())
		assertEquals(Pair(null, null), XmlAttribute(name = null, value = null).toPair())
		assertEquals(Pair("name", "value"), XmlAttribute("name", "value").toPair())
	}

	@Test
	fun `Pair to XmlAttribute`() {
		assertEquals(XmlAttribute(name = "name", value = "value"), Pair("name", "value").toXmlAttribute())
		assertEquals(XmlAttribute(name = "name", value = null), Pair("name", null).toXmlAttribute())
		assertEquals(XmlAttribute(name = null, value = "value"), Pair(null, "value").toXmlAttribute())
		assertEquals(XmlAttribute(name = null, value = null), Pair(null, null).toXmlAttribute())
		assertEquals(XmlAttribute("name", "value"), Pair("name", "value").toXmlAttribute())
	}
}
