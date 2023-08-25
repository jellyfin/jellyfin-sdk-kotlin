package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.ContainerProfile
import org.jellyfin.sdk.model.api.DlnaProfileType
import org.jellyfin.sdk.model.api.ProfileCondition

@DeviceProfileBuilderDsl
public class ContainerProfileBuilder {
	private var containers = mutableListOf<String>()
	private var conditions = mutableListOf<ProfileCondition>()

	public fun container(vararg container: String) {
		containers.addAll(container)
	}

	public fun conditions(body: ProfileConditionsBuilder.() -> Unit) {
		conditions.addAll(ProfileConditionsBuilder().apply(body).build())
	}

	public fun build(): ContainerProfile = ContainerProfile(
		// Server only uses container profiles with video type so the builder only supports that
		type = DlnaProfileType.VIDEO,
		conditions = conditions,
		container = containers.joinToString(","),
	)
}

@DeviceProfileBuilderDsl
public fun buildContainerProfile(
	body: ContainerProfileBuilder.() -> Unit,
): ContainerProfile = ContainerProfileBuilder()
	.apply(body)
	.build()
