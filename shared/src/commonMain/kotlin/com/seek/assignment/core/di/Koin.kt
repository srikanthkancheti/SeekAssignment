package com.seek.assignment.core.di

import com.seek.assignment.common.seek.data.mapper.JobPostingsMapper
import com.seek.assignment.common.seek.data.mapper.JobPostingsMapperImpl
import com.seek.assignment.common.seek.data.network.SeekAPI
import com.seek.assignment.common.seek.data.network.SeekAPIImpl
import com.seek.assignment.common.seek.data.repository.SeekRepoImpl
import com.seek.assignment.common.seek.domain.repository.SeekRepo
import com.seek.assignment.common.seek.domain.usecase.JobPostingsUseCase
import com.seek.assignment.common.seek.domain.usecase.JobPostingsUseCaseImpl
import com.seek.assignment.core.service.LanguageService
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@OptIn(
    ExperimentalTime::class,
    ExperimentalSerializationApi::class
)
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
    modules(mapperModule)
    modules(repoModule)
    modules(apiModule)
    modules(useCaseModule)
}

val commonModule = module {
    single { LanguageService() }
}

@ExperimentalSerializationApi
val mapperModule = module {
    factory<JobPostingsMapper> { JobPostingsMapperImpl() }
}

@ExperimentalTime
val repoModule = module {
    factory<SeekRepo> { SeekRepoImpl(get())}
}

val apiModule = module {
    factory<SeekAPI> { SeekAPIImpl() }
}

val useCaseModule = module {
    factory<JobPostingsUseCase> { JobPostingsUseCaseImpl(get()) }
}

inline fun <reified T> getKoinInstance(): T {
    return object : KoinComponent {
        val value: T by inject()
    }.value
}