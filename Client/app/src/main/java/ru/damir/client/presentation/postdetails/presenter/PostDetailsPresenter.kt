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
        post = repo.findPostById(postId)
        if (post != null) {
            viewState.injectPostDetailsInViews(post!!)
        }
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