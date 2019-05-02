package ru.damir.client.presentation.displayposts.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.damir.client.data.model.Post
import ru.damir.client.data.repository.Repo
import ru.damir.client.presentation.displayposts.view.NewsView

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    @Volatile
    private var stopAutoUpdateExecution = false
    private val repo = Repo()

    @Synchronized
    private fun updatePostList() {
        repo.updatePostList(viewState::updateListPosts)
    }


    private fun autoUpdate() {
        Thread {
            while (!stopAutoUpdateExecution) {
                updatePostList()
                Thread.sleep(1000)
            }
        }.start()
    }

    fun fetchButtonClick() {
        updatePostList()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        autoUpdate()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAutoUpdateExecution = true
    }

    fun postItemClicked(post: Post) {
        viewState.openPostDetails(post)
    }
}
