package com.rie.mystore.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rie.mystore.data.VivoRepository

class VMFactory(private val repos: VivoRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeVmodel::class.java)) {
            return HomeVmodel(repos) as T
        } else if (modelClass.isAssignableFrom(DetailVmodel::class.java)) {
            return DetailVmodel(repos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
