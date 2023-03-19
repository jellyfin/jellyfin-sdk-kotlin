package org.jellyfin.sdk.api;

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.jellyfin.sdk.api.client.exception.SecureConnectionException
import org.jellyfin.sdk.api.client.exception.ssl.HandShakeCertificateException
import org.jellyfin.sdk.api.client.exception.ssl.PeerNotAuthenticatedException
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.DeviceInfo

class SSLResolverTests : FunSpec({

	val jellyfin = createJellyfin {
		clientInfo = ClientInfo("Jellyfin Testing SSL Errors", "TEST")
		deviceInfo = DeviceInfo("test", "test")
	}

	test("should throw HandShakeCertificateException when calling an https endpoint with revoked certificate") {
		val api = jellyfin.createApi(
			baseUrl = "https://revoked.badssl.com"
		)

		shouldThrow<HandShakeCertificateException> {
			api.request(pathTemplate = "/")
		}
	}

	test("should throw PeerNotAuthenticatedException when wrong host is returned from https endpoint") {
		val api = jellyfin.createApi(
			baseUrl = "https://wrong.host.badssl.com"
		)

		shouldThrow<PeerNotAuthenticatedException> {
			api.request(pathTemplate = "/")
		}
	}

	test("should throw SecureConnectionException when using wrong https port") {
		val api = jellyfin.createApi(
			baseUrl = "https://badssl.com:80"
		)

		shouldThrow<SecureConnectionException> {
			api.request(pathTemplate = "/")
		}
	}
})
