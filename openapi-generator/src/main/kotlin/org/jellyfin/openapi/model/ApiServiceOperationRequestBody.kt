package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.TypeName
import org.jellyfin.openapi.constants.Types

sealed interface ApiServiceOperationRequestBody {
	val type: TypeName

	data class Json(
		override val type: TypeName
	) : ApiServiceOperationRequestBody

	data object String : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.STRING
	}

	data object Binary : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.BYTE_ARRAY
	}

	data object None : ApiServiceOperationRequestBody {
		override val type: TypeName = Types.NONE
	}
}
