package ru.damir.client.presentation.postdetails.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.damir.client.repository.model.Post


@StateStrategyType(AddToEndSingleStrategy::class)
interface PostDetailsView : MvpView {
    fun injectPostDetailsInViews(post : Post)
}