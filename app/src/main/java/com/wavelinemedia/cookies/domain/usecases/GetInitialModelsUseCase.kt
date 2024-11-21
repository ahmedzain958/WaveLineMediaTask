package com.wavelinemedia.cookies.domain.usecases

import com.wavelinemedia.cookies.domain.Model
import com.wavelinemedia.cookies.domain.WaveLineMediaRepository
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