package com.wavelinemedia.cleanarchihilt.domain.usecases

import com.wavelinemedia.cleanarchihilt.domain.Model
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
import javax.inject.Inject

class GetInitialModelsUseCase @Inject constructor(
    private val waveLineMediaRepository: WaveLineMediaRepository,
    private val getSortedModelsUseCase: GetSortedModelsUseCase,
) {


    suspend operator fun invoke(): List<Model> {
        waveLineMediaRepository.loadModels()
        return getSortedModelsUseCase()
    }
}