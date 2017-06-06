package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import co.folto.gitfinder.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupTabs()
    }

    fun setupTabs() {
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment.newInstance(), "Trending")
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

    class ViewPageAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val fragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment
                = fragmentList[position]

        override fun getCount(): Int
                = fragmentList.size

        override fun getPageTitle(position: Int): CharSequence
                = fragmentTitleList[position]

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            fragmentTitleList.add(title)
        }
    }

}
