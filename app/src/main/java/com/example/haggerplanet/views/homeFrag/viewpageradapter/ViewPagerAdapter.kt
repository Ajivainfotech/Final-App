package com.example.haggerplanet.views.homeFrag.viewpageradapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.haggerplanet.R
import com.example.haggerplanet.databinding.HomeViewPagerAdapterBinding
import com.example.haggerplanet.views.homeFrag.HomeFragVM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_view_pager_adapter.view.*
import java.lang.reflect.Array.get

class ViewPagerAdapter(var context: Context)  : PagerAdapter() {

    var homeViewPagerBinding: HomeViewPagerAdapterBinding? = null


    var splashList= ArrayList<HomeFragVM.BannerModel>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {

        return splashList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        homeViewPagerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.home_view_pager_adapter,
            container,
            false
        )
        var imageview=homeViewPagerBinding!!.root.ivSlider
        container.addView(homeViewPagerBinding!!.root)

        if (splashList.size != 0) {
            Log.e("splashImages", splashList.size.toString())
           /* Glide.with(context).load(splashList[position].image)
                .into(imageview)*/
            Picasso.get().load(splashList[position].image).resize(400, 300)
                .into(imageview)
        }

        return homeViewPagerBinding!!.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun addDataInList(list2: ArrayList<HomeFragVM.BannerModel>) {

        if (list2 != null) {
            splashList.clear()
            splashList.addAll(list2)
            Log.e("listSizeeeeeeeee", splashList.size.toString())
            notifyDataSetChanged()
        }
    }
}