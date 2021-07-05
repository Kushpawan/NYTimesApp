package com.kush.nytimes.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kush.nytimes.R
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleRecycleAdapter(
    private var mGroupItem: MutableList<ViewArticles>,
    private val listener: (ViewArticles?) -> Unit
) :
    RecyclerView.Adapter<ArticleRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val contactView = inflater.inflate(R.layout.article_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mGroupItem[position], listener)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mGroupItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: ViewArticles,
            listener: (ViewArticles?) -> Unit
        ) = with(itemView) {

            itemView.article_title_text.text = item.title
            itemView.article_author_text.text = item.byline
            itemView.article_source_text.text = item.source
            itemView.article_date_text.text = item.published_date

            item.media?.let {
                if ( item.media.isNotEmpty()) {
                    it[0].mediaMetadata?.get(0)?.url?.let { url ->
                        itemView.article_image.load(url)
                    }
                }
            }

            itemView.setOnClickListener {
                listener.invoke(item)
            }
        }
    }

}