package com.example.arunalunew

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
//import android.support.design.internal.NavigationMenuView
import androidx.appcompat.app.AlertDialog
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.internal.NavigationMenuView
import java.util.*


class MainActivity : AppCompatActivity() {
    // index to identify current nav menu item

    private val TAG_HOME = "home"


    private lateinit var drawer: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    var CURRENT_TAG = TAG_HOME
    private var mHandler: Handler? = null
    var navItemIndex = 0
    private var navigationView: NavigationView? = null
    var toolbar: Toolbar? = null
    lateinit var arrayString:ArrayList<String>
    lateinit var menuArrayString:ArrayList<ArrayList<String>>
    lateinit var defaultMenuArrayString:ArrayList<String>
    var number: Int = 0
    var menuId: String =""
    var mandatoryCount :Int =0
    lateinit var dialog: AlertDialog
    var loadTask=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bundle=intent.extras
        if (bundle!=null){
            if (bundle.containsKey("task")){
                loadTask=bundle.getInt("task")
            }


        }

        mHandler = Handler()
        val mActionBar = supportActionBar!!
        mActionBar.setDisplayShowHomeEnabled(false)
        mActionBar.setDisplayShowTitleEnabled(false)
        val mInflater = LayoutInflater.from(this)

        //Inititalize UI element
        toolbar = findViewById(R.id.toolbar_main)
        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val actionBar = mInflater.inflate(R.layout.custom_action_bar_home, null)
        val mTitleTextView = actionBar.findViewById(R.id.tv_title) as TextView
        val mMenuImageView = actionBar.findViewById(R.id.img_menu) as ImageView



        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.userName, R.string.userName)
        drawer.addDrawerListener(toggle)


        mTitleTextView.text = ""
        mActionBar.customView = actionBar
        mActionBar.setDisplayShowCustomEnabled(true)
        (actionBar.parent as Toolbar).setContentInsetsAbsolute(0, 0)


        arrayString =ArrayList<String>()
        arrayString.add("My Profile")
        arrayString.add("Support")
        arrayString.add("Settings")
        arrayString.add("Contact Us")
        arrayString.add("About Us")
        arrayString.add("Log Out")


        // val number: Int = 1
        mMenuImageView.setOnClickListener(View.OnClickListener {
            if(drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(Gravity.LEFT) //CLOSE Nav Drawer!
            }else{
                drawer.openDrawer(Gravity.LEFT) //OPEN Nav Drawer!
            }
        })
        setUpNavigationView()
        //addMenuItemInNavMenuDrawer()
        if (savedInstanceState == null) {
            when (number) {
                0 -> {

                    navItemIndex = 0

                }
                1 -> {

                    navItemIndex = 1
                }
                2 -> {

                    navItemIndex = 2
                }
                3 -> {

                    navItemIndex = 3
                }
                4 -> {

                    navItemIndex = 4
                }7 -> {

                navItemIndex = 7
            }
                8 -> {

                    navItemIndex = 8
                }

                9 -> {

                    navItemIndex = 9
                }
                6 -> {

                    navItemIndex = 6
                }
                5 -> {

                    navItemIndex = 5
                }
            }
            // navItemIndex = 0;
            CURRENT_TAG = TAG_HOME
            loadHomeFragment()
        }

    }




    private fun getHomeFragment(): androidx.fragment.app.Fragment? {

        //Start Location Tracker

        var frag: androidx.fragment.app.Fragment? = null
        frag = mainFragment()

        return frag
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private fun loadHomeFragment() {

        drawer.closeDrawers()

        //delay navigation drawer closing effect
        val handler = Handler()
        val runnable = Runnable {
            val fragment = getHomeFragment()
            if(fragment != null) {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frame, fragment)
                ft.commit()
            }else{
                if (navItemIndex==0){

                }

            }
        }


        handler.postDelayed(runnable, 500)
        // refresh toolbar menu
        invalidateOptionsMenu()
    }

    private fun setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView!!.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {

            // This method will trigger on item Click of navigation menu
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                var menuItemID=menuItem.itemId
                var menuItStr = menuItemID.toString()


                if(menuItStr.equals("1")){
                    navItemIndex = 1

                }
                else if(menuItStr.equals("2")){
                    navItemIndex = 2

                } else if(menuItStr.equals("3")){
                    navItemIndex = 3

                }
                else if(menuItStr.equals("4")){
                    navItemIndex = 4

                }
                else if(menuItStr.equals("5")){
                    navItemIndex = 5

                }
                else if(menuItStr.equals("6")){
                    navItemIndex = 6

                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked) {
                    menuItem.isChecked = false
                } else {
                    menuItem.isChecked = true
                }
                menuItem.isChecked = true

                loadHomeFragment()

                return true
            }
        })
    }


    override fun onBackPressed() {
        return
    }

}
