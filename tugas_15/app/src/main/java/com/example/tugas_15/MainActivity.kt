package com.example.tugas_15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas_15.models.Movie
import com.example.tugas_15.models.MovieResponse
import com.example.tugas_15.services.MovieApiService
import com.example.tugas_15.services.MovieApiinterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_film_list.layoutManager = LinearLayoutManager(this)
        getMovieData {
            movie : List<Movie> ->
            rv_film_list.adapter = MovieAdapter(movie)
        }
    }
    private fun getMovieData(callback:(List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiinterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
               return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

        })
    }
}