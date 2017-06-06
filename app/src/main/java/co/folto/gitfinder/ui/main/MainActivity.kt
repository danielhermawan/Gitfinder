package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.folto.gitfinder.R
import co.folto.gitfinder.ui.popular.PopularFragment
import co.folto.gitfinder.ui.trending.TrendingFragment
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
        adapter.addFragment(TrendingFragment.newInstance(), "Trending")
        adapter.addFragment(PopularFragment.newInstance(), "Popular")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_search -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
