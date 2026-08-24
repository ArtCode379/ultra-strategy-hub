package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.checkout

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ultraconcierge.management.ultrastrategyhub.data.entity.BookingEntity

@Composable
fun CheckoutDialog(booking: BookingEntity, selectedDate: String, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onConfirm,
        title = { Text("Consultation confirmed") },
        text = {
            Text(
                buildString {
                    append("Booking #${booking.bookingNumber}\n\n")
                    append("Preferred date: $selectedDate\n\n")
                    append("Your consultant will be waiting in the online conference or at the office ")
                    append("address provided in your confirmation at the appointed time.")
                }
            )
        },
        confirmButton = { TextButton(onClick = onConfirm) { Text("View bookings") } },
    )
}
