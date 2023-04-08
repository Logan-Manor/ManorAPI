package com.example.manorroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ZodiacListViewModel : ViewModel() {
    private val zodiacRepository = ZodiacRepository.get()

    private val _zodiacs: MutableStateFlow<List<Zodiac>> = MutableStateFlow(emptyList())
    val zodiacs: StateFlow<List<Zodiac>>
        get() = _zodiacs.asStateFlow()

    init {
        viewModelScope.launch {
            zodiacRepository.getZodiacs().collect {
                _zodiacs.value = it
            }
        }
    }
}