package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.bookings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.ui.state.BookingUiState
import ultraconcierge.management.ultrastrategyhub.ui.state.DataUiState
import ultraconcierge.management.ultrastrategyhub.ui.theme.StrategySuccess
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.BookingViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val state by viewModel.bookingsState.collectAsState()
  var pendingCancellation by remember { mutableStateOf<String?>(null) }
  var browseRequested by remember { mutableStateOf(false) }

    pendingCancellation?.let { bookingNumber ->
        AlertDialog(
            onDismissRequest = { pendingCancellation = null },
            title = { Text("Cancel this booking?") },
            text = { Text("The session will be removed from your upcoming consultations.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(bookingNumber)
                        pendingCancellation = null
                    }
                ) {
                    Text("Cancel booking", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingCancellation = null }) { Text("Keep booking") }
            },
        )
    }

    val bookings = (state as? DataUiState.Populated)?.data.orEmpty()
    if (bookings.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Default.CalendarMonth,
                null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(10.dp),
            )
            Text("No bookings yet", style = MaterialTheme.typography.titleLarge)
      Text(
          if (browseRequested) "Choose Home below to browse all advisory services." else "Your confirmed strategy sessions will appear here.",
          color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
            Spacer(Modifier.height(20.dp))
      Button(onClick = { browseRequested = true }) { Text("Browse Services") }
        }
    } else {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item { Text("Upcoming consultations", style = MaterialTheme.typography.titleLarge) }
            items(bookings, key = { it.bookingNumber }) { booking ->
                BookingCard(booking) { pendingCancellation = booking.bookingNumber }
            }
        }
    }
}

@Composable
private fun BookingCard(booking: BookingUiState, onCancel: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    booking.serviceName,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                )
                Surface(
                    color = StrategySuccess.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(50),
                ) {
                    Text(
                        "Confirmed",
                        color = StrategySuccess,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                }
            }
            Text("Booking #${booking.bookingNumber}", color = MaterialTheme.colorScheme.primary)
            Text(booking.timestamp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(
                "Online conference or office session",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            TextButton(onClick = onCancel) {
                Text("Cancel", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
