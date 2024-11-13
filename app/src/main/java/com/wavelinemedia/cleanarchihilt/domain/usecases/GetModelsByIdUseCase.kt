package com.wavelinemedia.cleanarchihilt.domain.usecases

import com.wavelinemedia.cleanarchihilt.data.remote.RemoteDTO
import com.wavelinemedia.cleanarchihilt.domain.WaveLineMediaRepository
import javax.inject.Inject

class GetModelsByIdUseCase @Inject
constructor(private val waveLineMediaRepository: WaveLineMediaRepository) {
    suspend operator fun invoke(id: Int): Map<String, RemoteDTO> {
        return waveLineMediaRepository.getModelById(id)
    }
}