package ru.damir.client.presentation.displayposts.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_news.*
import ru.damir.client.R
import ru.damir.client.presentation.createnewpost.view.CreateNewPostActivity
import ru.damir.client.presentation.displayposts.presenter.NewsPresenter
import ru.damir.client.repository.model.Post

class NewsFragment : MvpAppCompatFragment(), NewsView {
    private val newsRecyclerViewAdapter = NewsRecyclerViewAdapter()

    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter() = NewsPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun updateListPosts(list: List<Post>) {
        newsRecyclerViewAdapter.submitList(list)
    }

    override fun onStart() {
        super.onStart()

        fetchDataButton.setOnClickListener {
            presenter.fetchButtonClick()
        }

        newsRecyclerView.layoutManager = LinearLayoutManager(context)
        newsRecyclerView.adapter = newsRecyclerViewAdapter

        newPostButton.setOnClickListener {
            val intent = Intent(this.context, CreateNewPostActivity::class.java)
            startActivity(intent)
        }
    }
}