package com.example.submission1.ui.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.R
import com.example.submission1.data.response.User
import com.example.submission1.databinding.ActivityMainBinding
import com.example.submission1.ui.detail.DetailUserActivity
import com.example.submission1.ui.detail.ThemeSettingPreferences
import com.example.submission1.ui.detail.ThemeViewModel
import com.example.submission1.ui.detail.ViewThemeModelFactory
import com.example.submission1.ui.detail.dataStore
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        val pref = ThemeSettingPreferences.getInstance(application.dataStore)
        val themeViewModel = ViewModelProvider(this, ViewThemeModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        themeViewModel.getThemeSetting().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            themeViewModel.saveThemeSetting(isChecked)

        }

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun OnItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener{
                searchUser()
            }

            etQuery.setOnKeyListener{ v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.getSearchUser().observe(this, {
            if (it!=null)
                adapter.setList(it)
                showLoading(false)
        })
    }
    private fun searchUser(){
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
    private fun showLoading(stateBoolean: Boolean){
        if (stateBoolean){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.GONE
        }
    }
}