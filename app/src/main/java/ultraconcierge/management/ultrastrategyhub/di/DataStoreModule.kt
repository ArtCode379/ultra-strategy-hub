package ultraconcierge.management.ultrastrategyhub.di

import ultraconcierge.management.ultrastrategyhub.data.datastore.VGQJJOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { VGQJJOnboardingPrefs(androidContext()) }
}