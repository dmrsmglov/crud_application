package ru.damir.client.presentation.displayposts.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.damir.client.repository.model.Post

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsView : MvpView {
    fun updateListPosts(list: List<Post>?)
    fun openPostDetails(post: Post)
}