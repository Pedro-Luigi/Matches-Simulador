package com.meu.myproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.meu.myproject.databinding.ActivityDetailBinding
import com.meu.myproject.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            //Aqui eu vou pegar a imagem do meu local de partida no GitPage e renderizar em ivPlace.
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            //Atribuindo o nome do estádio na toolbar.
            supportActionBar?.title = it.place.name
            binding.tvDescription.text = it.description

            //Informações do time da casa.
            Glide.with(this).load(it.homeTeam.image).circleCrop().into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            if (it.homeTeam.score != null){
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }

            //Informações do time visitante.
            Glide.with(this).load(it.awayTeam.image).circleCrop().into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()
            if (it.awayTeam.score != null){
                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
        }
    }
}