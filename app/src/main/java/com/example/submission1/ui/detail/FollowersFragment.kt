package com.example.submission1.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.databinding.FragmentFollowBinding
import com.example.submission1.ui.main.UserAdapter

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = UserAdapter()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)

        viewModel = ViewModelProvider(this).get(FollowersViewModel::class.java)
        viewModel.getFollowersList().observe(viewLifecycleOwner, { followersList ->
            showLoading(false)
            if (followersList != null) {
                adapter.setList(followersList)
            }
        })

        viewModel.setListFollowers(username)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(stateBoolean: Boolean) {
        if (stateBoolean) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
