package org.jellyfin.openapi.cli

import com.github.ajalt.clikt.core.CliktCommand
import org.koin.core.component.KoinComponent

@Suppress("UnnecessaryAbstractClass")
abstract class BaseCommand : CliktCommand(), KoinComponent
