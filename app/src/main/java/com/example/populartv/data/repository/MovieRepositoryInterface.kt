package com.example.populartv.data.repository

import androidx.paging.PagingData
import com.example.populartv.data.model.BaseModel
import com.example.populartv.data.model.Genres
import com.example.populartv.data.model.MovieItem
import com.example.populartv.data.model.artist.Artist
import com.example.populartv.data.model.artist.ArtistDetail
import com.example.populartv.data.model.moviedetail.MovieDetail
import com.example.populartv.utils.network.DataState
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {
    suspend fun movieDetail(movieId: Int): Flow<DataState<MovieDetail>>
    suspend fun recommendedMovie(movieId: Int, page: Int): Flow<DataState<BaseModel>>
    suspend fun search(searchKey: String): Flow<DataState<BaseModel>>
    suspend fun genreList(): Flow<DataState<Genres>>
    suspend fun movieCredit(movieId: Int): Flow<DataState<Artist>>
    suspend fun artistDetail(personId: Int): Flow<DataState<ArtistDetail>>
    fun popularPagingDataSource(genreId: String?): Flow<PagingData<MovieItem>>

}