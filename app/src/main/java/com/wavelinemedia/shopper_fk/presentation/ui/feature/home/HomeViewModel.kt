package com.wavelinemedia.shopper_fk.presentation.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavelinemedia.shopper_fk.domain.model.Product
import com.wavelinemedia.shopper_fk.domain.network.ResultWrapper
import com.wavelinemedia.shopper_fk.domain.usecase.GetCategoriesUseCase
import com.wavelinemedia.shopper_fk.domain.usecase.GetProductUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState: StateFlow<HomeScreenUIEvents> = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts(1)
            val popularProducts = getProducts(2)
            val categories = getCategory()
            if (featured.isEmpty() && popularProducts.isEmpty() && categories.isNotEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured, popularProducts, categories)
        }
    }



    private suspend fun getProducts(category: Int?): List<Product> {
        getProductUseCase.execute(category).let { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    return (result).value.products
                }

                is ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }

    private suspend fun getCategory(): List<String> {
        getCategoriesUseCase.execute().let { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    return (result).value.categories.map { it.title }
                }

                is ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()
    object NavigateToProductDetail : HomeScreenUIEvents()
    data class Success(
        val featured: List<Product>, val popularProducts: List<Product>,
        val categories: List<String>,
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
