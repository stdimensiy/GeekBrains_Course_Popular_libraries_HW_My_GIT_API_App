package ru.vdv.myapp.mygitapiapp.imageconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.App
import ru.vdv.myapp.mygitapiapp.databinding.FragmentImageConverterBinding
import ru.vdv.myapp.mygitapiapp.interfaces.BackButtonListener
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView

class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var vb: FragmentImageConverterBinding? = null
    val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentImageConverterBinding.inflate(inflater, container, false).also { vb = it }.root

    // методы интерфейсов
    override fun backPressed(): Boolean = presenter.backPressed()

    override fun showOriginImage(pathImageFile: String) {
        //TODO("Not yet implemented")
    }

    override fun showConvertedImage(pathImageFile: String) {
        //TODO("Not yet implemented")
    }

    override fun showMessage(text: String) {
        //TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        //TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        //TODO("Not yet implemented")
    }

    override fun showErrorBar() {
        //TODO("Not yet implemented")
    }

    override fun hideErrorBar() {
        //TODO("Not yet implemented")
    }

}