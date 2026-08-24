package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.VGQJJOnboardingVM

private data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
)

private val pages =
    listOf(
        OnboardingPage(
            "Strategy built for action",
            "Explore focused advisory services for growth, organisation, performance, and operational excellence.",
            Icons.Default.AccountTree,
        ),
        OnboardingPage(
            "Expert insight, on demand",
            "Learn from concise leadership and change articles, plus evidence from completed transformation programmes.",
            Icons.Default.AutoStories,
        ),
        OnboardingPage(
            "Book with confidence",
            "Choose a service, select a preferred date, and receive a clear session reference and meeting guidance.",
            Icons.Default.CalendarMonth,
        ),
    )

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: VGQJJOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(completed) {
        if (completed) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Surface(
                    modifier = Modifier.size(112.dp),
                    shape = RoundedCornerShape(28.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                ) {
                    Icon(
                        page.icon,
                        null,
                        Modifier.padding(25.dp),
                        MaterialTheme.colorScheme.primary,
                    )
                }
                Spacer(Modifier.height(36.dp))
                Text(page.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(12.dp))
                Text(
                    page.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier.size(if (index == pagerState.currentPage) 22.dp else 8.dp, 8.dp)
                        .background(
                            if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline,
                            CircleShape,
                        )
                )
            }
        }
        Spacer(Modifier.height(28.dp))
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth().height(54.dp),
        ) {
            Text(if (pagerState.currentPage == pages.lastIndex) "Get Started" else "Continue")
        }
    }
}
