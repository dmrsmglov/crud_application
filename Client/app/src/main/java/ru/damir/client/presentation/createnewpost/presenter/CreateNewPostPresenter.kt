package ru.damir.client.presentation.createnewpost.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.damir.client.data.model.Post
import ru.damir.client.data.repository.Repo
import ru.damir.client.presentation.createnewpost.view.CreateNewPostView

@InjectViewState
class CreateNewPostPresenter : MvpPresenter<CreateNewPostView>() {

    private val repo = Repo()

    fun newPostCreate(title: String, content: String) {
        repo.sendPost(Post(0, title, content))
    }

    fun postUpdate(id: Int, title: String, content: String) {
        repo.updatePost(Post(id, title, content))
    }
}
