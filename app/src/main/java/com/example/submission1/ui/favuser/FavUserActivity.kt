package com.example.submission1.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.submission1.R
import com.example.submission1.data.entity.FavUser
import com.example.submission1.databinding.ActivityDetailUserBinding
import com.example.submission1.databinding.ActivityFavUserBinding
import com.example.submission1.ui.detail.DetailUserActivity
import com.example.submission1.ui.detail.DetailUserViewModel
import com.example.submission1.ui.favuser.FavUserViewModel

class FavUserActivity : AppCompatActivity() {
    private lateinit var favUserViewModel: FavUserViewModel

    private lateinit var binding: ActivityFavUserBinding
    private lateinit var favViewModel: FavUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        favViewModel = obtainViewModel(this@FavUserActivity)
//        favViewModel.getAllUser().observe(this) { listUser ->
//            if (listUser.isEmpty()){
//                setListData(listUser)
//            } else {
//                binding?.rvFavuser?.visibility = View.GONE
//                binding?.tvFaveuserNone?.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    private fun setListData(listUser: List<FavUser>) {
//        val adapter = UserAdapter(listUser)
//        binding?.rvFavuser?.adapter = adapter
//
//        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: FavUser) {
//                sendSelectedUser(data)
//            }
//        })
//    }
//
//    private fun sendSelectedUser(data: FavUser){
//        val intent = Intent(this, DetailUserActivity::class.java)
//        intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data)
//        startActivity(intent)
//    }
//
//    private fun obtainViewModel(activity: AppCompatActivity): FavUserViewModel {
//        val factory = FavUserFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory)[FavUserViewModel::class.java]
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
   }
}