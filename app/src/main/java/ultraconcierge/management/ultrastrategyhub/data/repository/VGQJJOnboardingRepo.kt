package ultraconcierge.management.ultrastrategyhub.data.repository

import ultraconcierge.management.ultrastrategyhub.data.datastore.VGQJJOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class VGQJJOnboardingRepo(
    private val vgqjjOnboardingStoreManager: VGQJJOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return vgqjjOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            vgqjjOnboardingStoreManager.setOnboardedState(state)
        }
    }
}