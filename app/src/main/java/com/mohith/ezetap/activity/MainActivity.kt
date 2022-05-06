package com.mohith.ezetap.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.mohith.ezetap.R
import com.mohith.ezetap.viewmodel.UIViewModel
import com.mohith.ezetap.widgets.WidgetGenerator
import com.mohith.network.model.UIData
import com.mohith.network.model.UITYPE
import de.dlyt.yanndroid.oneui.layout.DrawerLayout
import de.dlyt.yanndroid.oneui.layout.ToolbarLayout
import de.dlyt.yanndroid.oneui.menu.MenuItem
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var uiViewModel: UIViewModel
    private lateinit var logo: ImageView
    private lateinit var linearLayout: LinearLayout
    private lateinit var progressBar:ProgressBar

    private lateinit var drawer: DrawerLayout

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.bindIDs()
        this.setupToolBar()
        this.setupViewModel()
        this.loadData()
        this.setLogo()
    }

    private fun setupToolBar() {
        val toolbarLayout:ToolbarLayout = this.drawer.toolbarLayout
      toolbarLayout.inflateToolbarMenu(R.menu.main)

        toolbarLayout.setOnToolbarMenuItemClickListener(ToolbarLayout.OnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.info -> {
                    startActivity(
                        Intent().setClass(this@MainActivity, AboutActivity::class.java)
                    )
                    item.badge = item.badge + 1
                }

            }
            true
        })
    }

    private fun bindIDs() {
        drawer = findViewById(R.id.drawer_container)
        logo = findViewById(R.id.logo)
        linearLayout = findViewById(R.id.linear_layout)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun setLogo() {
        uiViewModel.getLogo().observe(this) {
            logo.setImageBitmap(it)
            Log.e(TAG, "setLogo: " + it)
        }
    }


    private fun loadData() {
        uiViewModel.getData().observe(this) {
            Log.e(TAG, "loadData: $it")
            it.headingText?.let { it1 -> setHeader(it1) }
            it.uidata?.let { it1 -> generateWidget(it1) }
            progressBar.visibility = View.GONE
        }
    }

    private fun setHeader(headingText: String) {
        this.drawer.setToolbarTitle(headingText)
       this.drawer.setToolbarExpanded(true, true)
    }

    private fun generateWidget(uiDataList: List<UIData>) {
        val widgetMap: Map<UIData, View> = WidgetGenerator.generate(this, uiDataList)
        this.uiViewModel.setUIData(widgetMap)
        this.uiViewModel.getUIData().observe(this){
            if (widgetMap.isNotEmpty()) {
                linearLayout.removeAllViews()
                widgetMap.forEach {
                    linearLayout.addView(it.value)
                    if (it.value is MaterialButton) {
                        val button: MaterialButton = it.value as MaterialButton
                        if (button.text.toString().lowercase(Locale.getDefault()) == "submit") {
                            setupSubmit(button)
                        }
                    }
                }
            }
        }
    }

    private fun setupSubmit(button: MaterialButton) {
        button.setOnClickListener {
            val uiDataList: ArrayList<UIData> = ArrayList()
            uiViewModel.getUIData().value?.forEach {
                val uiData: UIData = it.key
                if (uiData.uiType == UITYPE.edittext && it.value is EditText) {
                    val value = (it.value as EditText).text.toString()
                    uiData.value = value
                    uiDataList.add(uiData)
                }
            }
            val intent = Intent(this@MainActivity, DetailsScreen::class.java)
            intent.putExtra(DetailsScreen.UI_DATA_LIST, uiDataList)
            startActivity(intent)
        }

    }

    private fun setupViewModel() {
        uiViewModel = ViewModelProvider(this).get(UIViewModel::class.java)
    }

}