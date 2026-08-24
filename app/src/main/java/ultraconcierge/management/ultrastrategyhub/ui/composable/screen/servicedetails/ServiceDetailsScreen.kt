package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.servicedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.data.model.ServiceModel
import ultraconcierge.management.ultrastrategyhub.ui.state.DataUiState
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.ServiceDetailsViewModel

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val state by viewModel.serviceState.collectAsState()
    LaunchedEffect(serviceId) { viewModel.observeServiceById(serviceId) }
    val service = (state as? DataUiState.Populated)?.data

    if (service == null) {
        Column(modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            Text("Loading service details…")
        }
    } else {
        ServiceDetails(service, onNavigateToCheckout, modifier)
    }
}

@Composable
private fun ServiceDetails(service: ServiceModel, onBook: (Int) -> Unit, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier =
                    Modifier.fillMaxWidth()
                        .height(280.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                contentScale = ContentScale.Crop,
            )
        }
        item {
            Column(Modifier.padding(20.dp)) {
                Text(
                    service.category.uppercase(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(service.name, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(
                    "From £${service.price.toInt()} · ${service.durationMinutes} min",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(16.dp))
                Text(service.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(24.dp))
                Text("What’s included", style = MaterialTheme.typography.titleMedium)
                service.features.forEach { feature ->
                    Row(
                        modifier = Modifier.padding(vertical = 7.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            null,
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(feature)
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text("Available times", style = MaterialTheme.typography.titleMedium)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(service.availableTime.orEmpty()) { time ->
                        AssistChip(
                            onClick = { onBook(service.id) },
                            label = { Text(time.toString()) },
                        )
                    }
                }
                Spacer(Modifier.height(22.dp))
                Button(
                    onClick = { onBook(service.id) },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                ) {
                    Text("Book Consultation")
                }
            }
        }
    }
}
