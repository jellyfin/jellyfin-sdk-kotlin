package org.jellyfin.openapi

import org.jellyfin.openapi.builder.api.ApiBuilder
import org.jellyfin.openapi.builder.api.ApiClientExtensionsBuilder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.builder.api.OperationBuilder
import org.jellyfin.openapi.builder.api.OperationUrlBuilder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.model.EmptyModelBuilder
import org.jellyfin.openapi.builder.model.EnumModelBuilder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.builder.model.ObjectModelBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiApiServicesBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiConstantsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiDefaultValueBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiModelBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiReturnTypeBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.koin.dsl.module

val mainModule = module {
	single { Generator(get(), get(), get(), get(), get(), get()) }

	// OpenAPI
	single { OpenApiTypeBuilder(getAll()) }
	single { OpenApiReturnTypeBuilder(get()) }
	single { OpenApiModelBuilder(get(), get(), get()) }
	single { OpenApiApiServicesBuilder(get(), get(), get(), get(), getAll(), getAll()) }
	single { OpenApiConstantsBuilder() }
	single { OpenApiDefaultValueBuilder() }

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
