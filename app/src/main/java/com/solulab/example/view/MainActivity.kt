package com.solulab.example.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.solulab.example.R

import com.solulab.example.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up ActionBar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
//            actionBar.setLogo(R.drawable.v_ic_star)
            actionBar.setHomeAsUpIndicator(R.drawable.v_ic_menu)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        navController = findNavController(R.id.navHostFragment)
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.fragmentHome, R.id.fragmentDetail,
            R.id.fragmentBag, R.id.fragmentProfile
        ).setDrawerLayout(drawer).build()

        /*setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )*/

        visibilityNavElements(navController)
    }



    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentHome -> {
                    showNavigation()
                }
                R.id.fragmentDetail -> {
                    showNavigation()
                }
                R.id.fragmentBag -> {
                    showNavigation()
                }
                R.id.fragmentProfile ->{
                    showNavigation()
                }
                else -> hideNavigation()
            }
        }
    }

    private fun hideNavigation() { //Hide both drawer and bottom navigation bar
        navigationView.visibility = View.GONE
    }

    private fun showNavigation() {
        navigationView.visibility = View.VISIBLE
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        navigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    fun exitApp() { //To exit the application call this function from fragment
        this.finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when {
            drawer.isDrawerOpen(GravityCompat.START) -> {
                drawer.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}
