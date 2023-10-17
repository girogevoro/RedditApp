package com.girogevoro.redditapp.di

import android.content.Context
import androidx.room.Room
import com.girogevoro.redditapp.BuildConfig
import com.girogevoro.redditapp.data.RedditListingsDataSource
import com.girogevoro.redditapp.data.room.RedditListingsCacheDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory { provideRetrofit() }
    single { provideNetworkApi(get()) }

    single<RedditListingsCacheDatabase> { provideRedditListingsCacheDB(appContext = get()) }
    single { get<RedditListingsCacheDatabase>().dao()}

   // viewModel { MainViewModel(get(), get()) }
}

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return Retrofit.Builder()
        .baseUrl(BuildConfig.REDDIT_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): RedditListingsDataSource =
    retrofit.create(RedditListingsDataSource::class.java)

fun provideRedditListingsCacheDB(appContext: Context) =
    Room.databaseBuilder(
        appContext,
        RedditListingsCacheDatabase::class.java, "reddit-cache-database"
    ).build()