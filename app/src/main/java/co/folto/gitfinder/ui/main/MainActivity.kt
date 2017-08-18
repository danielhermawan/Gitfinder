package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.folto.gitfinder.R
import co.folto.gitfinder.ui.favorite.FavoriteFragment
import co.folto.gitfinder.ui.language.LanguageFragment
import co.folto.gitfinder.ui.popular.PopularFragment
import co.folto.gitfinder.ui.trending.TrendingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupTabs()
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_browse -> {
                    toogleBrowse(true)
                }
                R.id.action_language -> {
                    toogleBrowse(false)
                    setActiveContent(LanguageFragment.newInstance())
                }
                R.id.action_favorite -> {
                    toogleBrowse(false)
                    setActiveContent(FavoriteFragment.newInstance())
                }
            }
            true
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

    fun setupTabs() {
        val adapter = ViewPageAdapter(supportFragmentManager)
        adapter.addFragment(TrendingFragment.newInstance(), "Trending")
        adapter.addFragment(PopularFragment.newInstance(), "Popular")
        viewpager.adapter = adapter
        tabs.setupWithViewPager(viewpager)
    }

    fun toogleBrowse(isBrowse: Boolean) {
        if(isBrowse) {
            contentFrame.visibility = View.GONE
            tabs.visibility = View.VISIBLE
            viewpager.visibility = View.VISIBLE
            toolbar.menu.findItem(R.id.item_search).setVisible(true)
        }
        else {
            tabs.visibility = View.GONE
            viewpager.visibility = View.GONE
            contentFrame.visibility = View.VISIBLE
            appbar.setExpanded(true, true)
            toolbar.menu.findItem(R.id.item_search).setVisible(false)
        }
    }

    private fun setActiveContent(fragment: Fragment) {
        val fm = supportFragmentManager
        fm.beginTransaction()
                .replace(R.id.contentFrame, fragment)
                .commit()
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
