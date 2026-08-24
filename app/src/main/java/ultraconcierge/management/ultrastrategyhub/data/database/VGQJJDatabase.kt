package ultraconcierge.management.ultrastrategyhub.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ultraconcierge.management.ultrastrategyhub.data.dao.BookingDao
import ultraconcierge.management.ultrastrategyhub.data.database.converter.Converters
import ultraconcierge.management.ultrastrategyhub.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VGQJJDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

