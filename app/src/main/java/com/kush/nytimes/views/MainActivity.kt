package com.kush.nytimes.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kush.nytimes.R
import com.kush.nytimes.extenstions.openActivity
import com.kush.nytimes.extenstions.showToast
import com.kush.nytimes.networking.Status
import com.kush.nytimes.views.detail.DetailActivity
import com.kush.nytimes.views.detail.DetailActivity.Companion.EXTRA_DETAIL
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private var articles: MutableList<ViewArticles> = mutableListOf()
    private lateinit var articleRecycleAdapter: ArticleRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initRecycle()

        observeData()
        viewModel.getArticleCall()
    }

    private fun observeData() {
        viewModel.articlesLiveData.observe(this) { response ->
            when (response.status) {
                Status.SUCCESS -> response.data?.let {
                    it.results?.let { list ->
                        articles = list
                        initRecycle()
                    } ?: run {
                        // show no result
                    }
                }
                Status.ERROR -> {
                    response.message?.detail?.let {
                        this.showToast(it)
                    }
                }
                Status.LOADING -> {
                    // show a loaded here}
                }
            }
        }
    }

    private fun initRecycle() {
        articleRecycleAdapter = ArticleRecycleAdapter(articles) {
            openDetailActivity(it)
        }
        article_recycle_view.adapter = articleRecycleAdapter
        article_recycle_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        article_recycle_view.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun openDetailActivity(viewArticles: ViewArticles?) {
        openActivity(DetailActivity::class.java) {
            putParcelable(EXTRA_DETAIL, viewArticles)
        }
    }

}