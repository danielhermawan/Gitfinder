package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.base.BaseActivity
import co.folto.gitfinder.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity: BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresenter

    private val repoList by lazy {
        rvRepos.adapter = MainAdapter(ArrayList<Repo>(0), {
            presenter.clickRepo(it)
        })
        rvRepos.setHasFixedSize(true)
        rvRepos.layoutManager = LinearLayoutManager(this)
        rvRepos
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        DaggerMainComponent.builder()
                .dataComponent(GitfinderApplication.dataComponent)
                .mainPresenterModule(MainPresenterModule(this))
                .build()
                .inject(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe();
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setLoading(active: Boolean) {

    }

    override fun showRepos(repos: List<Repo>) {

    }

    override fun showError(message: String) = coordinatorLayout.showToast(message)

    override fun showNoRepo() {

    }

    override fun goToDetailRepo(id: Long) {

    }

}
