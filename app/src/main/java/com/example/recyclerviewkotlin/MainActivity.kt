package com.example.recyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.recyclerviewkotlin.models.SampleData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null
    val wikiApiServe by lazy {
        WikiApiService.create()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var manager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvList.layoutManager = manager

        loadData();


    }




    private fun loadData() {
        disposable =
                wikiApiServe.getSampleData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult( result) },
                                { error -> showError(error.message) }
                        )
    }

    private fun showResult(result: List<SampleData>) {


        rvList.adapter = ListAdapter(result)


    }

    private fun showError(message: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()

    }


}
