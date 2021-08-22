package ru.vdv.myapp.mygitapiapp.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.App
import ru.vdv.myapp.mygitapiapp.databinding.FragmentUsersBinding
import ru.vdv.myapp.mygitapiapp.glide.GlideImageLoader
import ru.vdv.myapp.mygitapiapp.interfaces.BackButtonListener
import ru.vdv.myapp.mygitapiapp.interfaces.UsersView
import ru.vdv.myapp.mygitapiapp.model.RetrofitGitHubUserRepo

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            RetrofitGitHubUserRepo(ru.vdv.myapp.mygitapiapp.retrofit.GitHubApiFactory.create()),
            App.instance.router
        )
    }
    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root


    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter
        vb?.btnGoToImgConverter?.setOnClickListener { presenter.goToImageConverter() }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        this.vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        vb?.progressBar?.visibility = View.GONE
    }


    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}