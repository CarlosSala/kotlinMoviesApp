package com.example.moviesapp.framework.main

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PermissionChecker
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.framework.detail.DetailActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // a delegate create one instance of viewModel because is a singleton
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {

        private const val ACCESS_COARSE_LOCATION =
            android.Manifest.permission.ACCESS_COARSE_LOCATION

        const val DEFAULT_REGION = "US"
    }

    // this launcher is used to request permissions if these are not granted
    // since permissionsStatus()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            when {
                isGranted -> {
                    // permission granted, do something
                    requestPopularMovies()
                }

                shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION) -> {
                    // permission denied, show explanation
                    Toast.makeText(
                        this,
                        "This permissions is necessary for this app",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    // permission denied, show explanation
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // for to get location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        permissionsStatus()

        initRecyclerView()

        lifecycleScope.launch {
            viewModel.listPopularMovies.collect {
                moviesAdapter.setListMovies(it)
                moviesAdapter.notifyDataSetChanged()
            }
        }

        lifecycleScope.launch {
            viewModel.progressVisible.collect {
                binding.pbMovies.isVisible = it
            }
        }

        // is implemented the interface
        /*        object : MovieClickListener {
                override fun onMovieClicked(movie: Movie) {
                    Toast.makeText(
                        this@CourseActivity,
                        movie.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }*/

    }

    private fun permissionsStatus() {

        // check if permission is already granted
        if (checkStatusPermission()) {
            // permission granted, do something
            requestPopularMovies()

        } else {
            // permission denied, launch permission request
            requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
        }
    }

    private fun initRecyclerView() {

        binding.rv.apply {

            setHasFixedSize(true)
            layoutManager = GridLayoutManager(
                this@MainActivity,
                3,
                RecyclerView.VERTICAL,
                false
            )
        }

        moviesAdapter = MoviesAdapter(emptyList()) {

            navigate(it)
        }

        binding.rv.adapter = moviesAdapter
    }

    private fun navigate(movie: Movie) {

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }

    private fun checkStatusPermission(): Boolean {

        // check if permission is already granted
        val checkPermissions = PermissionChecker.checkSelfPermission(
            this,
            ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED

        return checkPermissions
    }

    @SuppressLint("MissingPermission")
    private fun requestPopularMovies() {

        // request lastLocation to GPS
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {

            if (it.result != null) {

                viewModel.requestPopularMovies(getRegionFromLocation(it.result))

            } else {
                viewModel.requestPopularMovies(DEFAULT_REGION)
            }
        }
    }

    private fun getRegionFromLocation(location: Location): String {

        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        return result?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }
}