package com.cna.parde.detailActivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cna.parde.R
import com.cna.parde.model.NPMovie
import com.cna.parde.model.POPMovie
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.UCMovie

class DetailMovieActivity: AppCompatActivity() {

    companion object {
        const val POPMovie = "POPMovie"
        const val NPMovie = "NPMovie"
        const val TMovie = "TMovie"
        const val TRMovie = "TRMovie"
        const val UCMovie = "UCMovie"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w185/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieTitle = findViewById<TextView>(R.id.movie_title)

        val intent = intent
        if(intent != null){
            val popMovie = intent.getParcelableExtra<POPMovie>(POPMovie)
            val npMovie = intent.getParcelableExtra<NPMovie>(NPMovie)
            val tMovie = intent.getParcelableExtra<TMovie>(TMovie)
            val trMovie = intent.getParcelableExtra<TRMovie>(TRMovie)
            val ucMovie = intent.getParcelableExtra<UCMovie>(UCMovie)

            if (popMovie != null){
                movieTitle.text = popMovie.title
            }else if (npMovie != null) {
                movieTitle.text = npMovie.title

            }else if (tMovie != null) {
                movieTitle.text = tMovie.title

            }else if (trMovie != null) {
                movieTitle.text = trMovie.title

            }else if (ucMovie != null) {
                movieTitle.text = ucMovie.title
            }
        }
    }
}