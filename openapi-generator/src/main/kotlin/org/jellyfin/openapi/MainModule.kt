package org.jellyfin.openapi

import org.jellyfin.openapi.builder.api.*
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.model.EmptyModelBuilder
import org.jellyfin.openapi.builder.model.EnumModelBuilder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.builder.model.ObjectModelBuilder
import org.jellyfin.openapi.builder.openapi.*
import org.koin.dsl.module

val mainModule = module {
	single { Generator(get(), get(), get(), get(), get(), get()) }

	// OpenAPI
	single { OpenApiTypeBuilder(getAll()) }
	single { OpenApiReturnTypeBuilder(get()) }
	single { OpenApiModelBuilder(get(), get()) }
	single { OpenApiApiServicesBuilder(get(), get(), get(), getAll(), getAll()) }
	single { OpenApiConstantsBuilder() }

	// API
	single { ApiNameBuilder() }
	single { OperationBuilder(get(), get()) }
	single { OperationUrlBuilder(get(), get()) }
	single { ApiBuilder(get(), get(), getAll()) }
	single { ApiClientExtensionsBuilder(get()) }

	// Models
	single { ModelBuilder(get(), get(), get()) }
	single { EmptyModelBuilder(get(), get()) }
	single { EnumModelBuilder(get(), get()) }
	single { ObjectModelBuilder(get(), get(), get()) }

	// Files
	single { FileSpecBuilder() }

	// Utilities
	single { DeprecatedAnnotationSpecBuilder() }
	single { TypeSerializerBuilder() }
	single { DescriptionBuilder() }
}
