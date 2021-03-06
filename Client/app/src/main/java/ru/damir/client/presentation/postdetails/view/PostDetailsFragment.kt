package ru.damir.client.presentation.postdetails.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_post_details.*
import ru.damir.client.R
import ru.damir.client.presentation.createnewpost.view.CreateNewPostActivity
import ru.damir.client.presentation.postdetails.presenter.PostDetailsPresenter
import ru.damir.client.data.model.Post

class PostDetailsFragment : MvpAppCompatFragment(), PostDetailsView {

    override fun updatePost(post: Post) {
        val intent = Intent(context, CreateNewPostActivity::class.java)
        intent.putExtra("POST_ID", post.id)
        intent.putExtra("POST_TITLE", post.title)
        intent.putExtra("POST_CONTENT", post.content)
        startActivity(intent)
    }

    override fun injectPostDetailsInViews(post: Post?) {
        if (post != null) {
            title_post_details_textview.text = post.title
            content_post_details_textview.text = post.content
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.post_details_options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_post_optionsmenu_item) {
            presenter.deletePost()
        } else if (item?.itemId == R.id.update_post_optionsmenu_item) {
            presenter.updatePost()
        }
        activity?.finish()
        return super.onOptionsItemSelected(item)
    }

    @InjectPresenter
    lateinit var presenter: PostDetailsPresenter

    @ProvidePresenter
    fun providePresenter() = PostDetailsPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.findPostById(arguments?.getInt("POST_ID"))
    }

    companion object {
        fun newInstance(postResponse: Int): PostDetailsFragment {
            val postDetailsFragment = PostDetailsFragment()
            postDetailsFragment.arguments = Bundle().apply { putInt("POST_ID", postResponse) }
            return postDetailsFragment
        }
    }
}