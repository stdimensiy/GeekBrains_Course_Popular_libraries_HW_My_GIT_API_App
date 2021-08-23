package ru.vdv.myapp.mygitapiapp.repositoryinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.App
import ru.vdv.myapp.mygitapiapp.databinding.FragmentRepositoryInfoBinding
import ru.vdv.myapp.mygitapiapp.glide.GlideImageLoader
import ru.vdv.myapp.mygitapiapp.interfaces.BackButtonListener
import ru.vdv.myapp.mygitapiapp.interfaces.RepoInfoView
import ru.vdv.myapp.mygitapiapp.model.RetrofitGitHubUserRepo
import ru.vdv.myapp.mygitapiapp.retrofit.GitHubApiFactory

class RepoInfoFragment : MvpAppCompatFragment(), RepoInfoView, BackButtonListener {

    companion object {
        private const val ARG_REPO = "ARG_REPO_URL"

        fun newInstance(repositoryUrl: String) =
            RepoInfoFragment().apply { arguments = bundleOf(ARG_REPO to repositoryUrl) }
    }

    private val repositoryUrl: String? by lazy {
        arguments?.getString(ARG_REPO, "stdimensiy")
    }

    private var vb: FragmentRepositoryInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepositoryInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    val presenter: RepoInfoPresenter by moxyPresenter {
        RepoInfoPresenter(
            repositoryUrl,
            RetrofitGitHubUserRepo(GitHubApiFactory.create()),
            App.instance.router
        )
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun setImageAvatar(url: String): Unit = with(vb) {
        this?.ivUserAvatar?.let { GlideImageLoader().loadInfo(url, it) }
    }

    override fun showNameRepository(text: String) {
        vb?.tvNameRepository?.text = text
    }

    override fun showDescriptionRepository(text: String) {
        vb?.tvDescription?.text = text
    }

    override fun showCountFork(count: String) {
        vb?.tvCountOfForks?.text = count
    }

    override fun init() {
        showCountFork("Количество форков: 0")
        showProgressBar()
        showNameRepository("Загрузка...")
    }

    override fun showProgressBar() {
        this.vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        this.vb?.progressBar?.visibility = View.GONE
    }

    override fun showErrorBar() {
        this.vb?.imageViewError?.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.vb?.imageViewError?.visibility = View.GONE
    }
}