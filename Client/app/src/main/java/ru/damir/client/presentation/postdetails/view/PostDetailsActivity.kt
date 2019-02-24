package ru.damir.client.presentation.postdetails.view

import ru.damir.client.SingleFragmentActivity

class PostDetailsActivity : SingleFragmentActivity() {
    override fun createFragment() = PostDetailsFragment.newInstance(
        intent.getIntExtra(
            "POST_ID",
            0
        )
    )
}