package com.rie.mystore.model

import com.rie.mystore.data.VivoRepository
//Noor Saputri
object Injection {
    fun provideRepository(): VivoRepository {
        return VivoRepository.getInstance()
    }
}
