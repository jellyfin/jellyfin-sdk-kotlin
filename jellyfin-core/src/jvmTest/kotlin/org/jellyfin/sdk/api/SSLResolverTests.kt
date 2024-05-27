package org.jellyfin.sdk.api;

import io.kotest.assertions.retry
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.jellyfin.sdk.api.client.exception.SecureConnectionException
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import kotlin.time.Duration.Companion.minutes

class SSLResolverTests : FunSpec({
	fun getInstance() = createJellyfin {
		clientInfo = ClientInfo("Jellyfin Testing SSL Errors", "TEST")
		deviceInfo = DeviceInfo("test", "test")
	}

	xtest("should throw SecureConnectionException when calling an https endpoint with revoked certificate") {
		val api = getInstance().createApi(
			baseUrl = "https://revoked.badssl.com"
		)

		retry(3, 1.minutes) {
			shouldThrow<SecureConnectionException> {
				api.request(pathTemplate = "/")
			}
		}
	}

	xtest("should throw SecureConnectionException when wrong host is returned from https endpoint") {
		val api = getInstance().createApi(
			baseUrl = "https://wrong.host.badssl.com"
		)

		retry(3, 1.minutes) {
			shouldThrow<SecureConnectionException> {
				api.request(pathTemplate = "/")
			}
		}
	}

	xtest("should throw SecureConnectionException when using wrong https port") {
		val api = getInstance().createApi(
			baseUrl = "https://badssl.com:80"
		)

		retry(3, 1.minutes) {
			shouldThrow<SecureConnectionException> {
				api.request(pathTemplate = "/")
			}
		}
	}

	test("should throw SecureConnectionException when using self signed certificate") {
		val api = getInstance().createApi(
			baseUrl = "https://self-signed.badssl.com"
		)

		retry(3, 1.minutes) {
			shouldThrow<SecureConnectionException> {
				api.request(pathTemplate = "/")
			}
		}
	}
})
