package ir.nwise.app.di


import com.apollographql.apollo.ApolloClient
import ir.nwise.app.common.Config
import ir.nwise.app.data.repository.AppRepository
import ir.nwise.app.data.repository.AppRepositoryImp
import okhttp3.OkHttpClient
import org.koin.dsl.module

val graphQlModule = module {
    single {
        val okHttpClient =
            OkHttpClient.Builder()
                .build()
        val apolloBuilder = ApolloClient.builder()
            .serverUrl(Config.BASE_URL)
            .okHttpClient(okHttpClient)
            .enableAutoPersistedQueries(true)
            .useHttpGetMethodForQueries(false)
            .useHttpGetMethodForPersistedQueries(true)

        apolloBuilder.build()
    }

    fun provideRepository(
        apolloClient: ApolloClient
    ): AppRepository {
        return AppRepositoryImp(apolloClient)
    }
    single { provideRepository(get()) }
}

