package mk.ukim.finki.energymk.model.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mk.ukim.finki.energymk.model.service.EnergyResourceService
import mk.ukim.finki.energymk.model.service.impl.EnergyResourceServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideEnergyResourceService(impl: EnergyResourceServiceImpl): EnergyResourceService
}