package com.wavelinemedia.cookies.domain.usecases

import com.wavelinemedia.cookies.domain.Model
import com.wavelinemedia.cookies.domain.WaveLineMediaRepository
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