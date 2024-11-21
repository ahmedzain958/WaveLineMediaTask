package com.wavelinemedia.cookies.domain.usecases

import com.wavelinemedia.cookies.domain.Model
import com.wavelinemedia.cookies.domain.WaveLineMediaRepository
import javax.inject.Inject

class ToggleFavouriteStateUseCase @Inject constructor(
    private val waveLineMediaRepository: WaveLineMediaRepository,
    private val getSortedModelsUseCase: GetSortedModelsUseCase,
) {

    suspend operator fun invoke(id: Int, oldState: Boolean): List<Model> {
        val newState = oldState.not()
        waveLineMediaRepository.toggleFavouriteModel(id, newState)
        return getSortedModelsUseCase()
    }
}