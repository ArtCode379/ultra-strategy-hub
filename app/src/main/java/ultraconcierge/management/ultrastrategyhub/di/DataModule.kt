package ultraconcierge.management.ultrastrategyhub.di

import ultraconcierge.management.ultrastrategyhub.data.repository.BookingRepository
import ultraconcierge.management.ultrastrategyhub.data.repository.VGQJJOnboardingRepo
import ultraconcierge.management.ultrastrategyhub.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        VGQJJOnboardingRepo(
            vgqjjOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}