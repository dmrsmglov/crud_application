package ru.damir.client.presentation.displayposts.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damir.client.repository.model.Post
import ru.damir.client.presentation.displayposts.view.NewsView
import ru.damir.client.repository.api.ApiProvider

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    val api = ApiProvider.api

    fun fetchButtonClick() {
        api.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) =
                t.printStackTrace()

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) =
                viewState.updateListPosts(response.body()!!)
        })
    }
}
