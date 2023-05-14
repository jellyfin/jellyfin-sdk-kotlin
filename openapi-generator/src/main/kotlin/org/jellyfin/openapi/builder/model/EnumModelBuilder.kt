package org.jellyfin.openapi.builder.model

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import net.pearx.kasechange.CaseFormat
import net.pearx.kasechange.toPascalCase
import net.pearx.kasechange.toScreamingSnakeCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.DeprecatedAnnotationSpecBuilder
import org.jellyfin.openapi.builder.extra.DescriptionBuilder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.DescriptionType
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.JellyFile

class EnumModelBuilder(
	private val descriptionBuilder: DescriptionBuilder,
	private val deprecatedAnnotationSpecBuilder: DeprecatedAnnotationSpecBuilder,
) : Builder<EnumApiModel, JellyFile> {
	@Suppress("LongMethod")
	override fun build(data: EnumApiModel): JellyFile {
		return TypeSpec.enumBuilder(data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL))
			.apply {
				// Super
				data.interfaces.forEach { interfaceName ->
					addSuperinterface(
						ClassName(
							Packages.MODEL,
							interfaceName.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
						)
					)
				}

				// Constructor
				primaryConstructor(FunSpec.constructorBuilder().apply {
					addParameter("serialName", Types.STRING)
				}.build())
				addProperty(PropertySpec.builder("serialName", Types.STRING).apply {
					initializer("serialName")
				}.build())

				// toString function
				addFunction(FunSpec.builder("toString").apply {
					returns(Types.STRING)
					addKdoc("%L", Strings.MODEL_ENUM_TO_STRING_DESCRIPTION)
					addStatement("return serialName")
					addModifiers(KModifier.OVERRIDE)
				}.build())

				// Members
				data.constants.forEach {
					addEnumConstant(
						it.toScreamingSnakeCase(from = CaseFormat.CAPITALIZED_CAMEL),
						TypeSpec.anonymousClassBuilder().apply {
							addAnnotation(AnnotationSpec.builder(Types.SERIAL_NAME).addMember("%S", it).build())
							addSuperclassConstructorParameter("%S", it)
						}.build()
					)
				}

				// Header
				descriptionBuilder.build(DescriptionType.MODEL, data.description)?.let {
					addKdoc("%L", it)
				}
				if (data.deprecated) addAnnotation(deprecatedAnnotationSpecBuilder.build(Strings.DEPRECATED_CLASS))
				addAnnotation(Types.SERIALIZABLE)

				// fromString[orNull] functions
				addType(TypeSpec.companionObjectBuilder().apply {
					val enumClassType = ClassName(
						Packages.MODEL,
						data.name.toPascalCase(from = CaseFormat.CAPITALIZED_CAMEL)
					)

					// fromNameOrNull
					addFunction(FunSpec.builder("fromNameOrNull").apply {
						returns(enumClassType.copy(nullable = true))
						addKdoc("%L", Strings.MODEL_ENUM_FROM_NAME_OR_NULL_DESCRIPTION)
						addParameter("serialName", Types.STRING)

						beginControlFlow("return when·(%N)", "serialName")
						data.constants.forEach { member ->
							addStatement(
								"%S·->·%N",
								member,
								member.toScreamingSnakeCase(from = CaseFormat.CAPITALIZED_CAMEL)
							)
						}
						addStatement("else·->·null")
						endControlFlow()
					}.build())

					// fromName
					addFunction(FunSpec.builder("fromName").apply {
						returns(enumClassType)
						addKdoc("%L", Strings.MODEL_ENUM_FROM_NAME_DESCRIPTION)
						addParameter("serialName", Types.STRING)

						addStatement(
							"return %M(%N(%N))·{·%P·}",
							MemberName("kotlin", "requireNotNull"),
							"fromNameOrNull",
							"serialName",
							Strings.MODEL_ENUM_MEMBER_EXCEPTION_MESSAGE
						)
					}.build())
				}.build())
			}
			.build()
			.let { JellyFile(Packages.MODEL, emptySet(), it) }
	}
}
