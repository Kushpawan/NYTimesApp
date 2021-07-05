package com.kush.nytimes.views.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.kush.nytimes.R
import com.kush.nytimes.extenstions.openInCustomTab
import com.kush.nytimes.views.ViewArticles
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var viewArticles: ViewArticles? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.extras != null) {
            viewArticles = intent.getParcelableExtra(EXTRA_DETAIL)
        }

        initView()

    }

    private fun initView() {
        viewArticles.apply {
            main_title.text = this?.title
            description.text = this?.abstract
            created_by.text = this?.byline
            source.text = this?.source

            this?.media?.let {
                if (it.isNotEmpty()) {
                    it[0].mediaMetadata?.get(2)?.url?.let { url ->
                        image1.load(url)
                    }
                }
            }

            more_detail.setOnClickListener {
                openInCustomTab(this?.url ?: "")
            }
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}