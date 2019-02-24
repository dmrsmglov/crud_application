package ru.damir.client.presentation.postdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_post_details.*
import ru.damir.client.R
import ru.damir.client.presentation.postdetails.presenter.PostDetailsPresenter
import ru.damir.client.repository.model.Post

class PostDetailsFragment : MvpAppCompatFragment(), PostDetailsView{

    override fun injectPostDetailsInViews(post: Post) {
        title_post_details_textview.text = post.title
        content_post_details_textview.text = post.content
    }

    @InjectPresenter
    lateinit var presenter : PostDetailsPresenter

    @ProvidePresenter
    fun providePresenter() = PostDetailsPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.findPostById(arguments?.getInt("POST_ID"))
    }

    companion object {
        fun newInstance(postResponse: Int) : PostDetailsFragment {
            val postDetailsFragment = PostDetailsFragment()
            postDetailsFragment.arguments = Bundle().apply { putInt("POST_ID", postResponse) }
            return postDetailsFragment
        }
    }
}