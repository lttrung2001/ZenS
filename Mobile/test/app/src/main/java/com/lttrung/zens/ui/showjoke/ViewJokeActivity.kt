package com.lttrung.zens.ui.showjoke

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.lttrung.zens.databinding.ActivityViewJokeBinding
import com.lttrung.zens.domain.locals.JokeLocals
import com.lttrung.zens.domain.locals.room.entities.Joke
import com.lttrung.zens.utils.AppConstants.JOKE
import com.lttrung.zens.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ViewJokeActivity : AppCompatActivity() {

    @Inject
    lateinit var jokeLocals: JokeLocals

    private val viewBinding: ActivityViewJokeBinding by lazy {
        ActivityViewJokeBinding.inflate(layoutInflater)
    }
    private val viewJokeViewModel: ViewJokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            if (jokeLocals.getAll().blockingGet().isEmpty()) {
                jokeLocals.insertSampleData()
            }
        }

        setupListeners()
        setupObservers()

        viewJokeViewModel.viewJoke()
    }

    private fun setupListeners() {
        viewBinding.buttonFunny.setOnClickListener {
            val joke = intent.getSerializableExtra(JOKE) as Joke
            viewJokeViewModel.voteJoke(joke.id, true)
        }

        viewBinding.buttonNotFunny.setOnClickListener {
            val joke = intent.getSerializableExtra(JOKE) as Joke
            viewJokeViewModel.voteJoke(joke.id, false)
        }
    }

    private fun setupObservers() {
        viewJokeViewModel.viewJokeLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val joke = resource.data
                    intent.putExtra(JOKE, joke)
                    viewBinding.jokeContent.text = joke.content
                }
                is Resource.Error -> {
                    viewBinding.buttonFunny.visibility = View.GONE
                    viewBinding.buttonNotFunny.visibility = View.GONE
                    viewBinding.jokeContent.visibility = View.GONE
                    viewBinding.message.visibility = View.VISIBLE
                    viewBinding.message.text =
                        "That's all the jokes for today! Come back another day!"
                }
            }
        }

        viewJokeViewModel.voteJokeLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    viewJokeViewModel.viewJoke()
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this@ViewJokeActivity,
                        resource.t.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}