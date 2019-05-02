package ru.damir.client.presentation.postdetails.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.damir.client.data.model.Post
import ru.damir.client.data.repository.Repo
import ru.damir.client.presentation.postdetails.view.PostDetailsView

@InjectViewState
class PostDetailsPresenter : MvpPresenter<PostDetailsView>() {

    var post: Post? = null

    private val repo = Repo()

    fun findPostById(postId: Int?) {
        if (postId != null) {
            repo.findPostById(postId, this::setAndShowPost)
        }
    }

    fun setAndShowPost(post : Post?) {
        this.post = post
        viewState.injectPostDetailsInViews(post)
    }
    fun deletePost() {
        if (post != null) {
            repo.deletePost(post!!)
        }
    }

    fun updatePost() {
        if (post != null) {
            viewState.updatePost(post!!)
        }
    }
}