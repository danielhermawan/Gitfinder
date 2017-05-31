package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.util.addToActitivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity(){

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        var mainFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as? MainFragment
        if(mainFragment == null){
            mainFragment = MainFragment.newInstance()
            supportFragmentManager.addToActitivity(mainFragment, R.id.contentFrame)
        }
        DaggerMainComponent.builder()
                .dataComponent(GitfinderApplication.dataComponent)
                .mainPresenterModule(MainPresenterModule(mainFragment))
                .build()
                .inject(this)
    }

}
