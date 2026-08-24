package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.ui.theme.StrategyBlueDark
import ultraconcierge.management.ultrastrategyhub.ui.theme.StrategyBlueLight
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.VGQJJSplashVM

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: VGQJJSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        progress.animateTo(1f, tween(800))
        delay(700)
        if (onboarded) {
            onNavigateToHomeScreen()
        } else {
            onNavigateToOnboarding()
        }
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(StrategyBlueDark, StrategyBlueLight))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier =
                Modifier.scale(0.8f + progress.value * 0.2f)
                    .alpha(progress.value)
                    .size(104.dp)
                    .background(Color.White, RoundedCornerShape(28.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Default.Insights, null, Modifier.size(58.dp), StrategyBlueDark)
        }
        Text("Ultra Strategy Hub", color = Color.White, style = MaterialTheme.typography.titleLarge)
        Text(
            "Clarity. Alignment. Momentum.",
            color = Color.White.copy(alpha = 0.78f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
