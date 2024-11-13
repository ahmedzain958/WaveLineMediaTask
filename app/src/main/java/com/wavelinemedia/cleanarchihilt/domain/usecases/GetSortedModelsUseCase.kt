package com.wavelinemedia.cleanarchihilt.domain.usecases

import com.wavelinemedia.cleanarchihilt.domain.Model
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
import javax.inject.Inject

class GetSortedModelsUseCase @Inject constructor(
    private val waveLineMediaRepository: WaveLineMediaRepository,
) {


    suspend operator fun invoke(): List<Model> {
        return waveLineMediaRepository.getModels().sortedBy {
            it.name
        }
    }
}