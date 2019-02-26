package ru.damir.client.presentation.createnewpost.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_create_new_post.*
import ru.damir.client.R
import ru.damir.client.presentation.createnewpost.presenter.CreateNewPostPresenter

class CreateNewPostFragment : MvpAppCompatFragment(), CreateNewPostView {

    var updateMode = false

    @InjectPresenter
    lateinit var presenter : CreateNewPostPresenter

    @ProvidePresenter
    fun providePresenter() = CreateNewPostPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new_post,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPostContent()
    }

    @SuppressLint("SetTextI18n")
    private fun setPostContent() {
        val intent = activity!!.intent
        if (intent!!.hasExtra("POST_ID")) {
            updateMode = true
            newPostContentMultilineText.text = SpannableStringBuilder(intent.getStringExtra("POST_CONTENT"))
            newPostTitlePlainText.text = SpannableStringBuilder(intent.getStringExtra("POST_TITLE"))
        }
        if (updateMode) {
            create_update_post_text_view.text = "Update post"
        }
    }

    override fun onStart() {
        super.onStart()

        postButton.setOnClickListener {
            if (isValid()) {
                if (updateMode) {
                    presenter.postUpdate(activity!!.intent.getIntExtra("POST_ID", 0), getTitle(), getContent())
                } else {
                    presenter.newPostCreate(getTitle(), getContent())
                }
                newPostContentMultilineText.text.clear()
                newPostTitlePlainText.text.clear()
                activity?.finish()
            }
        }
    }

    private fun isValid(): Boolean {
        return newPostContentMultilineText.text.toString() != "" && newPostTitlePlainText.text.toString() != ""
    }

    private fun getContent(): String {
        return newPostContentMultilineText.text.toString()
    }

    private fun getTitle(): String {
        return newPostTitlePlainText.text.toString()
    }
}