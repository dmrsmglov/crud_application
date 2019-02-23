package ru.damit.client.usecases.displayposts.view

import ru.damit.client.SingleFragmentActivity

class NewsActivity : SingleFragmentActivity() {

    override fun createFragment() = NewsFragment()
}
