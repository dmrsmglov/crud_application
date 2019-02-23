package ru.damir.client.presentation.displayposts.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.repository.model.response.PostResponse
import ru.damir.client.presentation.displayposts.view.NewsView
import ru.damir.client.repository.api.ApiProvider

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    @Volatile
    var stopAutoUpdateExecution = false
    val api = ApiProvider.api

    @Synchronized
    private fun updatePostList() {

        api.getAllPosts().enqueue(object : Callback<List<PostResponse>> {
            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) =
                t.printStackTrace()

            override fun onResponse(call: Call<List<PostResponse>>, response: Response<List<PostResponse>>) =
                viewState.updateListPosts(response.body()!!)
        })
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
}
