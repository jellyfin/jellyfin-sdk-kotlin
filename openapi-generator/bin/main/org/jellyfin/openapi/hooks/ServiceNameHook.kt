package org.jellyfin.openapi.hooks

import io.swagger.v3.oas.models.Operation

interface ServiceNameHook {
	/**
	 * Modify the chosen service names for operations.
	 * If an empty set is returned the operation will never be added to any service.
	 */
	fun mapServiceNames(operation: Operation, serviceNames: Set<String>): Set<String>
}
