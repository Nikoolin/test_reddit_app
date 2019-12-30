package com.orange.nikoolin.reddit.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orange.nikoolin.reddit.R
import com.orange.nikoolin.reddit.ui.posts.PostsListActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        useCoroutinesButton.setOnClickListener {
            show(PostsListActivity.ThreadHandlerType.COROUTINES)
        }
        useRxJavaButton.setOnClickListener {
            show(PostsListActivity.ThreadHandlerType.RXJAVA)
        }
    }

    private fun show(type: PostsListActivity.ThreadHandlerType) {
        val intent = PostsListActivity.init(this, type)
        startActivity(intent)
    }
}