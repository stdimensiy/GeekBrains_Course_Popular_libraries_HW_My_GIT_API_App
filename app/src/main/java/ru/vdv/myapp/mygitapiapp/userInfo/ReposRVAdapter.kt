package ru.vdv.myapp.mygitapiapp.userInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vdv.myapp.mygitapiapp.databinding.ReposListItemBinding
import ru.vdv.myapp.mygitapiapp.interfaces.IReposListPresenter
import ru.vdv.myapp.mygitapiapp.interfaces.RepoItemView

class ReposRVAdapter(
    val presenter: IReposListPresenter
) : RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(

        ReposListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(val vb: ReposListItemBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override fun setName(text: String) = with(vb) {
            tvName.text = text
        }

        override fun setDescription(text: String) = with(vb) {
            tvDescription.text = text
        }

        override var pos = -1
    }
}