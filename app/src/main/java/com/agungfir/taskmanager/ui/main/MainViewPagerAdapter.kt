package com.agungfir.taskmanager.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.agungfir.taskmanager.ui.main.done.TaskDoneFragment
import com.agungfir.taskmanager.ui.main.today.TaskTodayFragment
import com.agungfir.taskmanager.ui.main.upcoming.TaskUpcomingFragment

class MainViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        val TABS = listOf<String>("Today", "Coming", "Done")
    }

    override fun getCount(): Int = 3
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return TaskTodayFragment()
            }
            1 -> {
                return TaskUpcomingFragment()
            }
            2 -> {
                return TaskDoneFragment()
            }
            else -> {
                return TaskTodayFragment()
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return TABS[position]
    }
}