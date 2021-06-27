package ir.nwise.app.di


import com.apollographql.apollo.ApolloClient
import ir.nwise.app.common.Config
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.data.repository.AppRepositoryImp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val graphQlModule = module {
    single {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val apolloBuilder = ApolloClient.builder()
            .serverUrl(Config.BASE_URL)
            .okHttpClient(okHttpClient)

        apolloBuilder.build()
    }

    fun provideRepository(
        apolloClient: ApolloClient
    ): AppRepository {
        return AppRepositoryImp(apolloClient)
    }
    single { provideRepository(get()) }
}

