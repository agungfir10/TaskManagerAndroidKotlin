package com.agungfir.taskmanager.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.ViewPager
import com.agungfir.taskmanager.R
import com.agungfir.taskmanager.ui.task.add.AddTaskFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {


    private lateinit var efabAddTask: ExtendedFloatingActionButton
    private lateinit var appBarMain: AppBarLayout
    private lateinit var collapsingToolbarMain: CollapsingToolbarLayout
    private lateinit var viewLayerMain: View
    private lateinit var vpMain: ViewPager
    private lateinit var tabsMain: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        efabAddTask = findViewById(R.id.efabAddTask)
        appBarMain = findViewById(R.id.appBarMain)
        collapsingToolbarMain = findViewById(R.id.collapsingToolbarMain)
        viewLayerMain = findViewById(R.id.viewLayerMain)
        vpMain = findViewById(R.id.vpMain)
        tabsMain = findViewById(R.id.tabsMain)

        setTabsAndViewPager()
        setShrinkAfterScrolling()
        onClick()
    }

    private fun onClick() {
        efabAddTask.setOnClickListener {
            val dialogFragment = AddTaskFragment()
            dialogFragment.show(supportFragmentManager, AddTaskFragment.TAG)

        }
    }

    private fun setShrinkAfterScrolling() {
        appBarMain.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (collapsingToolbarMain.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(
                    collapsingToolbarMain
                )
            ) {
                efabAddTask.shrink()
                viewLayerMain.visibility = View.GONE
            } else {
                efabAddTask.extend()
                viewLayerMain.visibility = View.VISIBLE
            }
        })
    }

    private fun setTabsAndViewPager() {
        vpMain.adapter = MainViewPagerAdapter(supportFragmentManager)
        tabsMain.setupWithViewPager(vpMain, false)
    }
}