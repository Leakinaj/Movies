package com.example.movies.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movies.adapter.MovieAdapter
import com.example.movies.databinding.FragmentMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieFragment : Fragment(), CoroutineScope {

    private lateinit var binding: FragmentMovieBinding
    lateinit var movieAdapter: MovieAdapter
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPosts()
    }

    private fun getPosts() {
        launch {
            var response = RetrofitService.getFilmApi(movieId).getFilmList()
            if (response.isSuccessful) {
                movieAdapter = MovieAdapter(list = response.body()?.results)
                binding.recyclerView.adapter = movieAdapter
                movieAdapter.itemClickListener = object : MovieAdapter.RecyclerViewItemClick{
                    override fun itemClick(movieId: String) {
                        val action = MovieFragmentDirections.actionMovieFragmentToDetailFragment(movieId)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}