package com.cna.parde.detailActivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cna.parde.R
import com.cna.parde.model.NPMovie
import com.cna.parde.model.OTATv
import com.cna.parde.model.POPMovie
import com.cna.parde.model.POPTv
import com.cna.parde.model.TMovie
import com.cna.parde.model.TRMovie
import com.cna.parde.model.TRTv
import com.cna.parde.model.TTv
import com.cna.parde.model.UCMovie

class DetailTvActivity: AppCompatActivity() {

    companion object {
        const val OTATv = "OTATv"
        const val TTv = "TTv"
        const val POPTv = "POPTv"
        const val TRTv = "TRTv"
        const val IMG_URL = "https://image.tmdb.org/t/p/w185/"
    }

    private lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_detail)

        tvTitle = findViewById(R.id.tv_title)

        val intent = intent
        if(intent != null){
            val otaTv = intent.getParcelableExtra<OTATv>(OTATv)
            val tTv = intent.getParcelableExtra<TTv>(TTv)
            val popTv = intent.getParcelableExtra<POPTv>(POPTv)
            val trTv = intent.getParcelableExtra<TRTv>(TRTv)

            if (otaTv != null){
                tvTitle.text = otaTv.name
            }else if (tTv != null) {
                tvTitle.text = tTv.name

            }else if (popTv != null) {
                tvTitle.text = popTv.name

            }else if (trTv != null) {
                tvTitle.text = trTv.name

            }
        }
    }
}