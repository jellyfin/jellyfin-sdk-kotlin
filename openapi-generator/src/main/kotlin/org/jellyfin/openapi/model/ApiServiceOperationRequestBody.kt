package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName
import org.jellyfin.openapi.constants.Types

sealed interface ApiServiceOperationRequestBody {
	val type: TypeName

	data class Json(
		override val type: TypeName
	) : ApiServiceOperationRequestBody

	data object Text : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.STRING
	}

	data class Binary(val mediaType: String) : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.FILE_INFO
	}

	data object None : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.NONE
	}
}
