package com.cerridan.temperaturemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cerridan.temperaturemonitor.adapter.MainEpoxyController
import com.cerridan.temperaturemonitor.util.bindView
import io.reactivex.Observable
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MainActivity : AppCompatActivity() {
    private val refreshLayout: SwipeRefreshLayout by bindView(R.id.swl_main_container)
    private val recyclerView: RecyclerView by bindView(R.id.rv_main_items)

    private lateinit var viewModel: MainViewModel
    private lateinit var epoxyController: MainEpoxyController

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        epoxyController = MainEpoxyController(resources)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = epoxyController.adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }

    override fun onResume() {
        super.onResume()

        viewModel.epoxyModels
            .doOnSubscribe { refreshLayout.isRefreshing = true }
            .doOnNext { refreshLayout.isRefreshing = false }
            .subscribe(epoxyController::setData)
            .let(disposables::add)

        refreshLayout.setOnRefreshListener { viewModel.refresh() }
    }

    override fun onPause() {
        refreshLayout.setOnRefreshListener(null)
        disposables.clear()
        super.onPause()
    }
}
