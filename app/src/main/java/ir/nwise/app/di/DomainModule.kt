package ir.nwise.app.di

import ir.nwise.app.domain.usecase.GetAllPostUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllPostUseCase(get()) }
}