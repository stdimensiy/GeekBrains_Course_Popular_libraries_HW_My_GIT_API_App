package ru.vdv.myapp.mygitapiapp.userInfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.App
import ru.vdv.myapp.mygitapiapp.databinding.FragmentUserInfoBinding
import ru.vdv.myapp.mygitapiapp.glide.GlideImageLoader
import ru.vdv.myapp.mygitapiapp.interfaces.BackButtonListener
import ru.vdv.myapp.mygitapiapp.interfaces.UserInfoView
import ru.vdv.myapp.mygitapiapp.model.RetrofitGitHubUserRepo
import ru.vdv.myapp.mygitapiapp.retrofit.GitHubApiFactory

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    companion object {
        private const val ARG_USER = "ARG_USER_LOGIN"

        fun newInstance(userLogin: String) =
            UserInfoFragment().apply { arguments = bundleOf(ARG_USER to userLogin) }
    }

    private val userLogin: String? by lazy {
        arguments?.getString(ARG_USER, "stdimensiy")
    }

    private var vb: FragmentUserInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserInfoBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(
            userLogin,
            RetrofitGitHubUserRepo(GitHubApiFactory.create()),
            App.instance.router
        )
    }

    var adapter: ReposRVAdapter? = null

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        adapter = ReposRVAdapter(presenter.reposListPresenter)
//        vb?.rvUserRepos?.adapter = adapter
//        Log.d("Моя проверка", "Во фрагменте адаптер назначен")
//    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showLogin(text: String) {
        vb?.tvLogin?.text = text
    }

    override fun setImageAvatar(url: String): Unit = with(vb) {
        this?.imageViewUserAvatar?.let { GlideImageLoader().loadInfo(url, it) }
    }

    override fun showTopString(text: String) {
        vb?.textViewTopString?.text = text
    }

    override fun showCenterString(text: String) {
        vb?.textViewCenterString?.text = text
    }

    override fun showBottomString(text: String) {
        vb?.textViewBottomString?.text = text
    }

    override fun init() {
        Log.d("Моя проверка", "Начата инициализация")
        vb?.rvUserRepos?.layoutManager = LinearLayoutManager(context)
        adapter = ReposRVAdapter(presenter.reposListPresenter)
        vb?.rvUserRepos?.adapter = adapter
        Log.d("Моя проверка", "Во фрагменте адаптер назначен")
    }

    override fun updateList() {
        Log.d(
            "Моя проверка",
            "Обновление списка вызван. Элементов в адаптере: " + presenter.reposListPresenter.repositories.size
        )
        adapter?.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        this.vb?.progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        vb?.progressBar?.visibility = View.GONE
    }

    override fun showErrorBar() {
        this.vb?.imageViewError?.visibility = View.VISIBLE
    }

    override fun hideErrorBar() {
        this.vb?.imageViewError?.visibility = View.GONE
    }

}