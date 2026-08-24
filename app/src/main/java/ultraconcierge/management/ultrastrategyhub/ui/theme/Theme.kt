package ultraconcierge.management.ultrastrategyhub.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = StrategyBlueLight,
        secondary = StrategyAmber,
        tertiary = StrategyChip,
        background = StrategyText,
        surface = Color(0xFF202A3D),
        onPrimary = Color.White,
        onSurface = Color.White,
        outline = StrategyMuted,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = StrategyBlue,
        secondary = StrategyAmber,
        tertiary = StrategyChip,
        background = StrategyBackground,
        surface = StrategySurface,
        onPrimary = Color.White,
        onSecondary = StrategyText,
        onBackground = StrategyText,
        onSurface = StrategyText,
        onSurfaceVariant = StrategyMuted,
        outline = StrategyBorder,
        error = StrategyWarning,
    )

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(colorScheme = colorScheme, typography = AppTypography, content = content)
}
