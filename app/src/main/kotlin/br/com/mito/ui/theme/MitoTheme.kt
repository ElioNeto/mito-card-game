package br.com.mito.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Paleta temática — folclore brasileiro, tons terrosos e dourados
val MitoGold = Color(0xFFD4A843)
val MitoRed = Color(0xFFC94040)
val MitoGreen = Color(0xFF4A9C5A)
val MitoTeal = Color(0xFF3A9C8A)
val MitoBgDark = Color(0xFF0F0D0A)
val MitoSurface = Color(0xFF181510)
val MitoSurface2 = Color(0xFF1E1B14)
val MitoText = Color(0xFFE8E0CC)
val MitoTextMuted = Color(0xFF9A9080)

private val MitoDarkColorScheme = darkColorScheme(
    primary = MitoGold,
    onPrimary = MitoBgDark,
    secondary = MitoTeal,
    onSecondary = MitoBgDark,
    error = MitoRed,
    background = MitoBgDark,
    surface = MitoSurface,
    onBackground = MitoText,
    onSurface = MitoText,
)

@Composable
fun MitoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MitoDarkColorScheme,
        typography = MitoTypography,
        content = content,
    )
}
