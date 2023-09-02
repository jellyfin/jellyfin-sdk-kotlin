package org.jellyfin.sdk.api;

import io.kotest.assertions.retry
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.jellyfin.sdk.api.client.exception.SecureConnectionException
import org.jellyfin.sdk.api.client.exception.ssl.HandshakeCertificateException
import org.jellyfin.sdk.api.client.exception.ssl.PeerNotAuthenticatedException
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo
import kotlin.time.Duration.Companion.minutes

class SSLResolverTests : FunSpec({
	fun getInstance() = createJellyfin {
		clientInfo = ClientInfo("Jellyfin Testing SSL Errors", "TEST")
		deviceInfo = DeviceInfo("test", "test")
	}

	test("should throw HandShakeCertificateException when calling an https endpoint with revoked certificate") {
		val api = getInstance().createApi(
			baseUrl = "https://revoked.badssl.com"
		)

		retry(3, 1.minutes) {
			shouldThrow<HandshakeCertificateException> {
				api.request(pathTemplate = "/")
			}
		}
	}

	test("should throw PeerNotAuthenticatedException when wrong host is returned from https endpoint") {
		val api = getInstance().createApi(
			baseUrl = "https://wrong.host.badssl.com"
		)

		retry(3, 1.minutes) {
			shouldThrow<PeerNotAuthenticatedException> {
				api.request(pathTemplate = "/")
			}
		}
	}

	test("should throw SecureConnectionException when using wrong https port") {
		val api = getInstance().createApi(
			baseUrl = "https://badssl.com:80"
		)

		retry(3, 1.minutes) {
			shouldThrow<SecureConnectionException> {
				api.request(pathTemplate = "/")
			}
		}
	}
})
