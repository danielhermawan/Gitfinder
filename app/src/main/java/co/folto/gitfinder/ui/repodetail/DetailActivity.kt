package co.folto.gitfinder.ui.repodetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.util.addToActitivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: DetailPresenter

    companion object {
        private val REPO_ID = "REPO_ID"
        fun newIntent(context: Context, repoId: String)
                = Intent(context, DetailActivity::class.java).putExtra(REPO_ID, repoId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        title = intent.getStringExtra(REPO_ID)
        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
        ab?.setDisplayShowHomeEnabled(true)
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as? DetailFragment
        if(fragment == null){
            fragment = DetailFragment.newInstance(intent.getStringExtra(REPO_ID))
            supportFragmentManager.addToActitivity(fragment, R.id.contentFrame)
        }
        DaggerDetailComponent.builder()
                .dataComponent(GitfinderApplication.dataComponent)
                .detailPresenterModule(DetailPresenterModule(fragment, intent.getStringExtra(REPO_ID) ))
                .build()
                .inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
