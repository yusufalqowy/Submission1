package com.example.submission1.ui.detail

import UserRepository
import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.submission1.R
import com.example.submission1.data.entity.FavUser
import com.example.submission1.databinding.ActivityDetailUserBinding
import com.example.submission1.ui.main.FavUserActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private var isChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        viewModel.getAllUser().observe(this) { favUserList ->
            isChecked = favUserList.isNullOrEmpty()
            if (!isChecked) {
                binding.fabFavorit.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.baseline_favorite_24
                    )
                )
            } else {
                binding.fabFavorit.setImageDrawable(
                    ContextCompat.getDrawable(
                        this, R.drawable.ic_favorit
                    )
                )
            }
        }

        if ()

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
                showLoading(false)
            }
        })

        val sectionPagerAdapter = SectionPagerAdapeter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
