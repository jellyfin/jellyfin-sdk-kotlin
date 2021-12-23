package org.jellyfin.sdk.model.extensions

import org.jellyfin.sdk.model.api.NameGuidPair
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class PairExtensionTestsJvm {
	@Test
	fun `NameGuidPair to Pair`() {
		val uuid = UUID.randomUUID()

		assertEquals(Pair(uuid, "name"), NameGuidPair(id = uuid, name = "name").toPair())
		assertEquals(Pair(uuid, null), NameGuidPair(id = uuid, name = null).toPair())
		assertEquals(Pair(uuid, "name"), NameGuidPair("name", uuid).toPair())
	}

	@Test
	fun `Pair to NameGuidPair`() {
		val uuid = UUID.randomUUID()

		assertEquals(NameGuidPair(id = uuid, name = "name"), Pair(uuid, "name").toNameGuidPair())
		assertEquals(NameGuidPair(id = uuid, name = null), Pair(uuid, null).toNameGuidPair())
		assertEquals(NameGuidPair("name", uuid), Pair(uuid, "name").toNameGuidPair())
	}
}
