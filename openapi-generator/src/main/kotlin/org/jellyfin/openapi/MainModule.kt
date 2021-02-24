package org.jellyfin.openapi

import org.jellyfin.openapi.builder.api.ApiBuilder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.builder.api.OperationBuilder
import org.jellyfin.openapi.builder.api.OperationUrlBuilder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.model.EmptyModelBuilder
import org.jellyfin.openapi.builder.model.EnumModelBuilder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.builder.model.ObjectModelBuilder
import org.jellyfin.openapi.builder.openapi.*
import org.koin.dsl.module

val mainModule = module {
	single { Generator(get(), get(), get(), get(), get()) }

	// OpenAPI
	single { OpenApiTypeBuilder(getAll()) }
	single { OpenApiReturnTypeBuilder(get()) }
	single { OpenApiModelBuilder(get(), get()) }
	single { OpenApiApiServicesBuilder(get(), get(), get(), getAll()) }
	single { OpenApiConstantsBuilder() }

	// API
	single { ApiNameBuilder() }
	single { OperationBuilder(get()) }
	single { OperationUrlBuilder(get()) }
	single { ApiBuilder(get(), get(), getAll()) }

	// Models
	single { ModelBuilder(get(), get(), get()) }
	single { EmptyModelBuilder(get()) }
	single { EnumModelBuilder(get()) }
	single { ObjectModelBuilder(get(), get()) }

	// Files
	single { FileSpecBuilder() }

	// Utilities
	single { DeprecatedAnnotationSpecBuilder() }
	single { TypeSerializerBuilder() }
}
