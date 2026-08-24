package ultraconcierge.management.ultrastrategyhub.di

import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.BookingViewModel
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.CheckoutViewModel
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.VGQJJOnboardingVM
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.ServiceDetailsViewModel
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.ServiceViewModel
import ultraconcierge.management.ultrastrategyhub.ui.viewmodel.VGQJJSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        VGQJJSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        VGQJJOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}