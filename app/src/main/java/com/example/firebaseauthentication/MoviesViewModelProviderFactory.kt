package com.example.firebaseauthentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.utils.UNCHECKED_CAST
import com.example.firebaseauthentication.utils.UNKNOWN_MODEL_CLASS
import javax.inject.Inject
import javax.inject.Provider

class MoviesViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    @Suppress(UNCHECKED_CAST)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException(UNKNOWN_MODEL_CLASS + modelClass)
        return creator.get() as T
    }
}