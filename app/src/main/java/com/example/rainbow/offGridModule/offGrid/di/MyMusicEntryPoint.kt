package com.example.rainbow.offGridModule.offGrid.di

import com.example.rainbow.offGridModule.offGrid.data.repo.OffGridMusicRepository
import com.example.rainbow.offGridModule.offGrid.data.repo.impl.OffGridMusicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyMusicEntryPoint {

    @Provides
    @Singleton
    fun providesOffGridMusicRepository(): OffGridMusicRepository{
        return OffGridMusicRepositoryImpl()
    }
}