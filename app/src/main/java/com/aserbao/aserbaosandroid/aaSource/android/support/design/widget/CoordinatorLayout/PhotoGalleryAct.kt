package com.aserbao.aserbaosandroid.aaSource.android.support.design.widget.CoordinatorLayout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.aserbao.aserbaosandroid.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_photo_gallery.*

class PhotoGalleryAct : AppCompatActivity() {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, PhotoGalleryAct::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_gallery)
        initView()
    }

    private fun initView() {
        toolbar.apply {
            setSupportActionBar(this)
        }
        app_bar_layout?.apply {
            var lastOffset = 0;
            addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

                var fixHeight = this.height - toolbar.height
                 var percent = (fixHeight + verticalOffset).toFloat() / fixHeight.toFloat()
                percent = if(percent > 0.4) 1.0f else percent
                percent = percent * 2.5f
                hideFl.alpha = percent;
                nameTv.alpha = (1 - percent);

                Log.e("TAG", ": initView $verticalOffset" );
                lastOffset = verticalOffset
            })
        }


    }
}
