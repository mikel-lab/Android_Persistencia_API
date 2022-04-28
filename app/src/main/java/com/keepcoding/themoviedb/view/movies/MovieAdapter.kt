package com.keepcoding.themoviedb.view.movies

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keepcoding.themoviedb.R
import com.keepcoding.themoviedb.model.MoviePresentation

class MovieAdapter(private var data: ArrayList<MoviePresentation>,
                   private val clickListener: (MoviePresentation)->(Unit)): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val movieTitle = itemView.findViewById<TextView>(R.id.movie_title)
        private val movieVoteCount = itemView.findViewById<TextView>(R.id.movie_vote_count)
        private val movieImage = itemView.findViewById<ImageView>(R.id.movie_image)

        private var movie: MoviePresentation? = null

        init {
            itemView.setOnClickListener {
                if (movie != null){
                    clickListener(movie!!)
                }

                // Opción 2
/*                movie?.also {
                    clickListener(it)
                }*/
            }
        }

        fun bind(movie: MoviePresentation, position: Int){
            // pongo el texto
            this.movie = movie
            movieTitle.text = movie.title
            val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
            Glide.with(movieImage.context).load(imageUrl).into(movieImage)
            movieVoteCount.text = movie.voteCount.toString()
        }
    }

    fun updateMovies(movies: List<MoviePresentation>){
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        //val binding = ItemMovieBinding.bind(view)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val element = data[position]
        holder.bind(element, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}