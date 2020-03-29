package com.cerridan.temperaturemonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cerridan.temperaturemonitor.adapter.MainEpoxyController
import com.cerridan.temperaturemonitor.util.bindView
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by bindView(R.id.rv_main_items)

    private lateinit var viewModel: MainViewModel

    private val epoxyController = MainEpoxyController()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = epoxyController.adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.epoxyModels
            .subscribe(epoxyController::setData)
            .let(disposables::add)
    }

    override fun onPause() {
        disposables.clear()
        super.onPause()
    }
}
