package ru.vdv.myapp.mygitapiapp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.databinding.ActivityMainBinding
import ru.vdv.myapp.mygitapiapp.interfaces.MainView
import ru.vdv.myapp.mygitapiapp.model.GithubUsersRepo

class MainActivity : MvpAppCompatActivity(), MainView {
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter? = null
    private var vb: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}