package com.example.populartv.ui.screens.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.populartv.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {
    fun moviesByGenre(genreId: String) =
        repo.genrePagingDataSource(genreId).cachedIn(viewModelScope)
}