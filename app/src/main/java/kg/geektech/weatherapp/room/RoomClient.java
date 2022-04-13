package kg.geektech.weatherapp.room;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public WeatherDataBase provideDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                WeatherDataBase.class,
                "weather_database"
        )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
