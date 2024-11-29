package com.aliaklukin.imagestest.presentation.homescreen

import androidx.lifecycle.viewModelScope
import com.aliaklukin.imagestest.domain.usecase.GetHitsUseCase
import com.aliaklukin.imagestest.presentation.base.BaseViewModel
import com.aliaklukin.imagestest.presentation.mapper.HitLocalMapper
import com.aliaklukin.imagestest.presentation.model.HitLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val hitLocalMapper: HitLocalMapper,
    private val getHitsUseCase: GetHitsUseCase
) : BaseViewModel() {

    data class HomeScreenState(
        val isLoading: Boolean = false,
        val hits: List<HitLocal> = emptyList(),
        val currentPage: Int = 1,
        val scrollPosition: Int = 0
    )

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> get() = _state

    init {
        if (_state.value.hits.isEmpty()) {
            getHits(1)
        }
    }

    fun getHits(page: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val newHits = getDataByUseCase(getHitsUseCase.execute(page))?.hits?.map { hit ->
                hitLocalMapper.toHitLocal(hit)
            } ?: emptyList()

            _state.update {
                it.copy(
                    hits = it.hits + newHits,
                    currentPage = page
                )
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}