package com.example.submission1.ui.detail

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.submission1.R
import com.example.submission1.data.entity.FavUser
import com.example.submission1.databinding.ActivityDetailUserBinding
import com.google.android.material.snackbar.Snackbar

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private var isChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)
        val favUser = FavUser(login = username.orEmpty(), avatarUrl = avatar.orEmpty())
        viewModel = ViewModelProvider(this)[DetailUserViewModel::class.java]

        viewModel.getAllUser().observe(this){
            if(it.contains(favUser)){
                binding.fabFavorit.setImageResource(R.drawable.baseline_favorite_24)
                isChecked = true
            }
        }


        binding.fabFavorit.setOnClickListener {
            if (!isChecked) {
                binding.fabFavorit.setImageResource(R.drawable.baseline_favorite_24)
                isChecked = true

                if (!username.isNullOrEmpty()) {
                    viewModel.insert(favUser)
                    showSnackbar("Added to Favorites")
//                    Toast.makeText(this@DetailUserActivity, "Di Tambah", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.fabFavorit.setImageResource(R.drawable.ic_favorit)
                isChecked = false

                if (!username.isNullOrEmpty()) {
                    viewModel.delete(favUser)
                    showSnackbar("Removed from Favorites")
                    Log.d(TAG, "Delete $favUser")
//                    Toast.makeText(this, "Di Hapus", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this) {
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
        }

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

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

}
