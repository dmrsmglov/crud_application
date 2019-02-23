package ru.damir.client.presentation.createnewpost.view

import android.os.Bundle
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

    @InjectPresenter
    lateinit var presenter : CreateNewPostPresenter

    @ProvidePresenter
    fun providePresenter() = CreateNewPostPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_new_post,container, false)
    }

    override fun onStart() {
        super.onStart()

        postButton.setOnClickListener {

            if (isValid()) {
                presenter.newPostCreate(getTitle(), getContent())
            }

        }
    }

    private fun isValid(): Boolean {
        return newPostTitlePlainText.isDirty && newPostContentMultilineText.isDirty
    }

    private fun getContent(): String {
        return newPostContentMultilineText.text.toString()
    }

    private fun getTitle(): String {
        return newPostTitlePlainText.text.toString()
    }
}