package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.data.model.ServiceModel
import ultraconcierge.management.ultrastrategyhub.ui.state.DataUiState
import ultraconcierge.management.ultrastrategyhub.ui.theme.StrategyBlueDark
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.ServiceViewModel

private val categories =
    listOf(
        "Strategy" to Icons.Default.AutoGraph,
        "People" to Icons.Default.Groups,
        "Processes" to Icons.Default.SettingsSuggest,
        "Change" to Icons.Default.AccountTree,
    )

private val articles =
    listOf(
        "Leading through uncertainty" to
            "Five practices that keep decisions moving when conditions change.",
        "Making change stick" to
            "Turn stakeholder awareness into sustained adoption and ownership.",
        "The operating rhythm advantage" to
            "Use focused reviews to improve accountability without adding bureaucracy.",
    )

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val state by viewModel.servicesState.collectAsState()
    val services = (state as? DataUiState.Populated)?.data.orEmpty()

    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            ) {
                Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).then(Modifier)) {
                    Box(
                        modifier =
                            Modifier.fillMaxWidth().height(148.dp).clip(RoundedCornerShape(18.dp))
                    ) {
                        Surface(modifier = Modifier.matchParentSize(), color = StrategyBlueDark) {}
                        Box(
                            Modifier.matchParentSize().clickable {
                                services.firstOrNull()?.let { onNavigateToServiceDetails(it.id) }
                            }
                        )
                        Column(Modifier.padding(22.dp)) {
                            Text(
                                "NEXT AVAILABLE",
                                color = Color.White.copy(alpha = 0.72f),
                                style = MaterialTheme.typography.labelLarge,
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "Strategy session",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Text(
                                "Tomorrow · 09:00 · Online",
                                color = Color.White.copy(alpha = 0.86f),
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Reserve a focused 90-minute session →",
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
        item {
            SectionTitle("Consulting areas", "Explore by outcome")
            LazyRow(
                contentPadding =
                    androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(categories) { category ->
                    Card(shape = RoundedCornerShape(16.dp)) {
                        Column(
                            modifier = Modifier.padding(14.dp).size(width = 92.dp, height = 78.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Icon(category.second, null, tint = MaterialTheme.colorScheme.primary)
                            Text(category.first, style = MaterialTheme.typography.labelLarge)
                        }
                    }
                }
            }
        }
        item { SectionTitle("Advisory services", "Designed around practical outcomes") }
        items(services, key = { it.id }) { service ->
            ServiceCard(service, onNavigateToServiceDetails)
        }
        item {
            SectionTitle("Selected impact", "Recent transformation outcomes")
            LazyRow(
                contentPadding =
                    androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    listOf("32% faster decisions", "18% lower process cost", "4.6× change adoption")
                ) { result ->
                    Card(modifier = Modifier.size(210.dp, 96.dp)) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                result,
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                "Measured client outcome",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }
            }
        }
        item { SectionTitle("Knowledge base", "Ideas for stronger organisations") }
        items(articles) { article ->
            Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Text(article.first, style = MaterialTheme.typography.titleMedium)
                    Text(article.second, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(
                        "5 min read",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        item { Spacer(Modifier.height(12.dp)) }
    }
}

@Composable
private fun SectionTitle(title: String, subtitle: String) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(title, style = MaterialTheme.typography.titleLarge)
        Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun ServiceCard(service: ServiceModel, onClick: (Int) -> Unit) {
    Card(
        modifier =
            Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable { onClick(service.id) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier.size(108.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    service.category.uppercase(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(service.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    service.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "From £${service.price.toInt()}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        "Book Now",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
