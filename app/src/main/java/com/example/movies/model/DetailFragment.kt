package com.example.movies.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.adapter.MovieAdapter
import com.example.movies.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class DetailFragment : Fragment(),CoroutineScope {

    private lateinit var binding:FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFilm(args.toString())
    }

    private fun getFilm(id: String?){
        launch {
            val response = RetrofitService.getFilmApi(movieId).getFilmById(id)
            if (response.isSuccessful) {
                val film = response.body()
                if (film != null) {
                    binding.tvTitle.text = film.title
                    binding.tvOverview.text = film.overview
                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + film.poster_path)
                        .into(binding.imView2);
                }
            }
        }

    }
}




//        binding.swipeRefresh.isRefreshing = true
//        RetrofitService.getFilmApi().getFilmList().enqueue(object : Callback<Result> {
//            override fun onResponse(call: Call<Result>, response: Response<Result>) {
//                Log.d("My_movie_list", response.body().toString())
//                binding.swipeRefresh.isRefreshing = false
//                if (response.isSuccessful){
//                    val list = response.body()!!.movies
//                    (binding.recyclerView.adapter as MovieAdapter).submitList(list)
//                }
//            }
//
//            override fun onFailure(call: Call<Result>, t: Throwable) {
//                binding.swipeRefresh.isRefreshing = false
//            }
//
//        })
//
//    }
//
//    private fun setAdapter(){
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        binding.recyclerView.adapter = MovieAdapter(object : MovieAdapter.RecyclerViewItemClick{
//
//            override fun itemClick(position: Int, item: Result) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
    //}