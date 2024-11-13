package com.wavelinemedia.cleanarchihilt.domain.usecases

import com.wavelinemedia.cleanarchihilt.domain.Model
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
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