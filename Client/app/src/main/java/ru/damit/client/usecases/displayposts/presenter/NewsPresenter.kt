package ru.damit.client.usecases.displayposts.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.damit.client.data.Post
import ru.damit.client.usecases.displayposts.view.NewsView
import ru.damit.client.utils.ApiProvider

@InjectViewState
class NewsPresenter : MvpPresenter<NewsView>() {

    val api = ApiProvider.api

    fun fetchButtonClick(callback: (List<Post>) -> Unit) {
        api.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                System.out.println(response.body()!!.size)
                callback(response.body()!!)
            }
        })
    }
}
