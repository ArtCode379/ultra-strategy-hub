package ultraconcierge.management.ultrastrategyhub.di

import androidx.room.Room
import ultraconcierge.management.ultrastrategyhub.data.database.VGQJJDatabase
import org.koin.dsl.module

private const val DB_NAME = "vgqjj_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = VGQJJDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<VGQJJDatabase>().bookingDao()}

}