package com.example.aplikasipertama

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class LayoutState {
    LINEAR,
    GRID
}
class ListViewModel : ViewModel() {
    private val _layoutState = MutableLiveData<LayoutState>(LayoutState.LINEAR)

    val layoutState: LiveData<LayoutState>
        get() = _layoutState

    fun changeLayout() {
        if (layoutState.value == LayoutState.LINEAR) {
            _layoutState.value = LayoutState.GRID
        } else  {
            _layoutState.value = LayoutState.LINEAR
        }
    }
}