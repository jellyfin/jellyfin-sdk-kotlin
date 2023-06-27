package org.jellyfin.openapi

import com.github.ajalt.clikt.core.CliktCommand
import org.jellyfin.openapi.builder.api.ApiBuilder
import org.jellyfin.openapi.builder.api.ApiClientExtensionsBuilder
import org.jellyfin.openapi.builder.api.ApiNameBuilder
import org.jellyfin.openapi.builder.api.ApisBuilder
import org.jellyfin.openapi.builder.api.OperationBuilder
import org.jellyfin.openapi.builder.api.OperationParameterModelBuilder
import org.jellyfin.openapi.builder.api.OperationUrlBuilder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.builder.extra.TypeSerializerBuilder
import org.jellyfin.openapi.builder.model.EnumModelBuilder
import org.jellyfin.openapi.builder.model.InterfaceModelBuilder
import org.jellyfin.openapi.builder.model.ModelBuilder
import org.jellyfin.openapi.builder.model.ModelsBuilder
import org.jellyfin.openapi.builder.model.ObjectModelBuilder
import org.jellyfin.openapi.builder.model.RequestModelBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiApiServicesBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiConstantsBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiDefaultValueBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiModelBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiReturnTypeBuilder
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.cli.CompareCommand
import org.jellyfin.openapi.cli.GenerateCommand
import org.jellyfin.openapi.cli.MainCommand
import org.jellyfin.openapi.cli.VerifyCommand
import org.jellyfin.openapi.compare.InfoComparator
import org.jellyfin.openapi.compare.ModelComparator
import org.jellyfin.openapi.compare.OperationComparator
import org.jellyfin.openapi.compare.reporter.CompareReporter
import org.jellyfin.openapi.compare.reporter.JsonCompareReporter
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {
	single { Generator() }

	// Comparators
	single { InfoComparator() }
	single { OperationComparator() }
	single { ModelComparator() }

	// Compare reporters
	single { JsonCompareReporter() } bind CompareReporter::class

	// OpenAPI
	single { OpenApiTypeBuilder(getAll()) }
	single { OpenApiReturnTypeBuilder(get()) }
	single { OpenApiModelBuilder(get(), get()) }
	single { OpenApiApiServicesBuilder(get(), get(), get(), get(), getAll(), getAll()) }
	single { OpenApiConstantsBuilder(get()) }
	single { OpenApiDefaultValueBuilder() }

	// API
	single { ApiNameBuilder() }
	single { OperationBuilder(get(), get()) }
	single { OperationUrlBuilder(get(), get()) }
	single { OperationParameterModelBuilder(get(), get()) }
	single { ApiBuilder(get(), get(), get(), get(), getAll(), getAll(), get()) }
	single { ApisBuilder(get()) }
	single { ApiClientExtensionsBuilder(get()) }

	// Models
	single { ModelBuilder(get(), get(), get()) }
	single { ModelsBuilder(get(), get()) }
	single { EnumModelBuilder(get(), get()) }
	single { ObjectModelBuilder(get(), get(), get()) }
	single { InterfaceModelBuilder(get(), get()) }
	single { RequestModelBuilder(get()) }

	// Files
	single { FileSpecBuilder() }

	// Utilities
	single { DeprecatedAnnotationSpecBuilder() }
	single { TypeSerializerBuilder() }
	single { DescriptionBuilder(getAll()) }

	// CLI
	single { MainCommand() }
	single { GenerateCommand() } bind CliktCommand::class
	single { VerifyCommand() } bind CliktCommand::class
	single { CompareCommand() } bind CliktCommand::class
}
