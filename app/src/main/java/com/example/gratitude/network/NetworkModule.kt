package com.example.gratitude.network

import android.content.Context
import androidx.room.Room
import com.example.gratitude.ui.fragments.dashboard.screens.journal.JournalDao
import com.example.gratitude.ui.fragments.dashboard.screens.journal.JournalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://raaho-boost-app.wiremockapi.cloud/"

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): APINetworkOperations {
        return retrofit.create(APINetworkOperations::class.java)
    }

    @Provides
    @Singleton
    fun provideJournalDatabase(@ApplicationContext context: Context): JournalDatabase {
        return Room.databaseBuilder(
            context,
            JournalDatabase::class.java,
            "journal_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideJournalDao(db: JournalDatabase): JournalDao {
        return db.journalDao()
    }
}
