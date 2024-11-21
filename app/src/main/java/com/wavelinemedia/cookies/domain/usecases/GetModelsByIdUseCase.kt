package com.wavelinemedia.cookies.domain.usecases

import com.wavelinemedia.cookies.data.remote.RemoteDTO
import com.wavelinemedia.cookies.domain.WaveLineMediaRepository
import javax.inject.Inject

class GetModelsByIdUseCase @Inject
constructor(private val waveLineMediaRepository: WaveLineMediaRepository) {
    suspend operator fun invoke(id: Int): Map<String, RemoteDTO> {
        return waveLineMediaRepository.getModelById(id)
    }
}