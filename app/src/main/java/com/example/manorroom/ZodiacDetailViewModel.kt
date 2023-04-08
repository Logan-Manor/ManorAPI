package com.example.manorroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ZodiacDetailViewModel(zodiacId: Int) : ViewModel(){
    private val zodiacRepository = ZodiacRepository.get()

    private val _zodiac: MutableStateFlow<Zodiac?> = MutableStateFlow(null)
    val zodiac: StateFlow<Zodiac?> = _zodiac.asStateFlow()

    init {
        viewModelScope.launch {
            _zodiac.value = zodiacRepository.getZodiac(zodiacId)
        }
    }
}

class ZodiacDetailViewModelFactory(
    private val zodiacId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ZodiacDetailViewModel(zodiacId) as T
    }
}