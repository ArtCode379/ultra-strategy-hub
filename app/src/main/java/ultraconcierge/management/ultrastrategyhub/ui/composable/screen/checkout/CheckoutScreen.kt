package ultraconcierge.management.ultrastrategyhub.ui.composable.screen.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import org.koin.androidx.compose.koinViewModel
import ultraconcierge.management.ultrastrategyhub.data.entity.BookingEntity
import ultraconcierge.management.ultrastrategyhub.ui.state.DataUiState
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.CheckoutViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToBookingsScreen: () -> Unit,
) {
    val bookingState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val datePickerState = androidx.compose.material3.rememberDatePickerState()
    var phone by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            selectedDate =
                                Instant.ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                    .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("Use date")
                }
            },
            dismissButton = { TextButton(onClick = { showDatePicker = false }) { Text("Cancel") } },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (bookingState is DataUiState.Populated) {
        CheckoutDialog(
            booking = (bookingState as DataUiState.Populated<BookingEntity>).data,
            selectedDate = selectedDate,
            onConfirm = onNavigateToBookingsScreen,
        )
    }

    val isComplete =
        viewModel.customerFirstName.isNotBlank() &&
            viewModel.customerLastName.isNotBlank() &&
            viewModel.customerEmail.isNotBlank() &&
            phone.isNotBlank() &&
            selectedDate.isNotBlank()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text("Reserve your consultation", style = MaterialTheme.typography.titleLarge)
        Text(
            "Tell us how to reach you and choose a preferred session date.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(Modifier.padding(18.dp)) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "CONSULTATION",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        "Selected advisory service #$serviceId",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                Text("Confirmed after review", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        CheckoutTextField(
            viewModel.customerFirstName,
            viewModel::updateCustomerFirstName,
            "First name",
            Modifier.fillMaxWidth(),
        )
        CheckoutTextField(
            viewModel.customerLastName,
            viewModel::updateCustomerLastName,
            "Last name",
            Modifier.fillMaxWidth(),
        )
        CheckoutTextField(
            viewModel.customerEmail,
            viewModel::updateCustomerEmail,
            "Email",
            Modifier.fillMaxWidth(),
            isError = emailInvalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )
        CheckoutTextField(
            phone,
            { phone = it },
            "Phone",
            Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        )
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
            readOnly = true,
            enabled = false,
            label = { Text("Preferred date") },
            trailingIcon = { Icon(Icons.Default.CalendarMonth, null) },
        )
        OutlinedButton(onClick = { showDatePicker = true }, modifier = Modifier.fillMaxWidth()) {
            Text(if (selectedDate.isBlank()) "Choose preferred date" else selectedDate)
        }
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            modifier = Modifier.fillMaxWidth().height(110.dp),
            label = { Text("Objectives or notes") },
        )
        Spacer(Modifier.height(4.dp))
        Button(
            onClick = { viewModel.placeBooking(serviceId) },
            enabled = isComplete,
            modifier = Modifier.fillMaxWidth().height(54.dp),
        ) {
            Text("Confirm Booking")
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        singleLine = true,
    )
}
