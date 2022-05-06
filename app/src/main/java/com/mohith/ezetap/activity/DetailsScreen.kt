package com.mohith.ezetap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.mohith.ezetap.R
import com.mohith.ezetap.viewmodel.DetailViewModel
import com.mohith.ezetap.widgets.WidgetGenerator
import com.mohith.network.model.UIData
import de.dlyt.yanndroid.oneui.layout.ToolbarLayout
import java.util.ArrayList

class DetailsScreen : AppCompatActivity() {
    companion object {
        const val UI_DATA_LIST = "uiDataList"
        val TAG: String = DetailsScreen::class.java.simpleName
    }

    private lateinit var detailsViewModel: DetailViewModel
    private lateinit var linearLayout: LinearLayout
    private lateinit var toobarLayout:ToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)
        this.bindIDs()
        this.setupToolBar()
        this.setupViewModel()
        this.loadData()
    }

    private fun setupToolBar() {
        this.toobarLayout.setNavigationButtonIcon(getDrawable(de.dlyt.yanndroid.oneui.R.drawable.ic_samsung_arrow_left))
        this.toobarLayout.setNavigationButtonOnClickListener {
            finish()
        }
    }

    private fun loadData() {
        val uiDataList: ArrayList<UIData>? =
            intent.getParcelableArrayListExtra(UI_DATA_LIST)
        this.detailsViewModel.setUIDataList(uiDataList)
        this.detailsViewModel.getUIDataList().observe(this) {
            Log.e(TAG, "loadData: $it")
            val view: View = WidgetGenerator.generateDetailView(this@DetailsScreen,it)
            linearLayout.addView(view)
        }

    }

    private fun setupViewModel() {
        this.detailsViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    private fun bindIDs() {
        this.linearLayout = findViewById(R.id.linear_layout)
        this.toobarLayout = findViewById(R.id.toolbar_layout)
    }
}