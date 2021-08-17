package ru.vdv.myapp.mygitapiapp.imageconverter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.vdv.myapp.mygitapiapp.App
import ru.vdv.myapp.mygitapiapp.databinding.FragmentImageConverterBinding
import ru.vdv.myapp.mygitapiapp.interfaces.BackButtonListener
import ru.vdv.myapp.mygitapiapp.interfaces.ImageConverterView
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var vb: FragmentImageConverterBinding? = null
    private var imageUri: Uri? = null
    private val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(App.instance.router)
    }

    val tempConvertedFile = File.createTempFile("tmpConvert", ".png")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentImageConverterBinding.inflate(inflater, container, false).also { vb = it }.root


    // методы интерфейсов
    override fun backPressed(): Boolean = presenter.backPressed()

    override fun init() {
        Log.d("Моя проверка", "временный файл создан$tempConvertedFile")
        vb?.btnImageSelection?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/jpg"
            Log.d("Моя проверка", "ПЕРЕД startActivityForResult сработал с параметром ")
            startActivityForResult(intent, 1000)
            Log.d("Моя проверка", "ПОСЛЕ startActivityForResult сработал с параметром ")
        }
        vb?.btnStartConverting?.setOnClickListener {
            imageUri?.let {
                Log.d("Моя проверка", "Нажата кнопка старта конвертации")
                val fos = FileOutputStream(tempConvertedFile)
                val bos = BufferedOutputStream(fos)
                val mim =
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
                mim.compress(Bitmap.CompressFormat.PNG, 100, bos)
                bos.close()
                fos.close()
                Log.d(
                    "Моя проверка",
                    "Вроде как все сработать должно " + tempConvertedFile.toUri()
                )
            }

            vb?.imgViewConvertedImg?.setImageURI(tempConvertedFile.toUri())
        }
        vb?.btnAbort?.setOnClickListener {
            Log.d("Моя проверка", "Нажата кнопка отмены")
            presenter.abortConvertImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Моя проверка", "onActivityResult сработал с параметром хоть както")
        Log.d("Моя проверка", data?.dataString.toString())
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            Log.d(
                "Моя проверка",
                "onActivityResult сработал с параметром " + data?.data?.path.toString()
            )
            imageUri = data?.data
            vb?.imgViewOriginalImg?.setImageURI(imageUri)
        }
    }

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