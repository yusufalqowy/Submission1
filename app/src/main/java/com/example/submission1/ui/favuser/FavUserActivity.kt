package com.example.submission1.ui.favuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.submission1.data.entity.FavUser
import com.example.submission1.databinding.ActivityFavUserBinding
import com.example.submission1.ui.detail.DetailUserActivity
import com.example.submission1.ui.main.UserAdapter

class FavUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavUserBinding
    private lateinit var favViewModel: FavUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
        favViewModel = ViewModelProvider(this)[FavUserViewModel::class.java]
        favViewModel.getAllUser().observe(this) { listUser ->
            if (listUser.isEmpty()){
                setListData(listUser)
            } else {
                binding?.rvFavuser?.visibility = View.GONE
                binding?.tvFaveuserNone?.visibility = View.VISIBLE
            }
        }
    }

    private fun setListData(listUser: List<FavUser>) {
        val adapter = UserAdapter()
        adapter.setList(listUser)
        binding?.rvFavuser?.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FavUser) {
                sendSelectedUser(data)
            }
        })
    }

    private fun sendSelectedUser(data: FavUser){
        val intent = Intent(this, DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
        intent.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatarUrl)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
   }
}