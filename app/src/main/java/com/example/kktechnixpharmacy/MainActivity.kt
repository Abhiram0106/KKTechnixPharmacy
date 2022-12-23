package com.example.kktechnixpharmacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.kktechnixpharmacy.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        myDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(myDrawerToggle)
        myDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.nvNavigationDrawer.setNavigationItemSelectedListener {

//            binding.drawerLayout.closeDrawer(GravityCompat.START, true)

            when(it.itemId) {
                R.id.menu_myOrders -> { makeSnackbar("menu_myOrders")}
                R.id.menu_myPrescriptions -> { makeSnackbar("menu_myPrescriptions")}
                R.id.menu_cashWallet -> { makeSnackbar("menu_cashWallet")}
                R.id.menu_myAddresses -> { makeSnackbar("menu_myAddresses")}
                R.id.menu_premium -> { makeSnackbar("menu_premium")}
                R.id.menu_favouriteSeller -> { makeSnackbar("menu_favouriteSeller")}
                R.id.menu_consultancy -> { makeSnackbar("menu_consultancy")}
                R.id.menu_helpAndSupport -> { makeSnackbar("menu_helpAndSupport")}
                R.id.menu_termsAndConditions -> { makeSnackbar("menu_termsAndConditions")}
                R.id.menu_logout -> { makeSnackbar("menu_logout")}
            }
            true
        }

    }

//    Without this, none of the buttons (open nav drawer, the options within) work
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(myDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun makeSnackbar(message: String) {
        Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
    }
}