package com.herace.android_boilerplate.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.herace.android_boilerplate.R
import com.herace.android_boilerplate.util.setImageFromUrl

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:image_url")
    fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
        imageView.setImageFromUrl(imageUrl)
    }

    @JvmStatic
    @BindingAdapter("imageDetailUrl")
    fun imageDetailUrlAdapter(imageView: ImageView, imageUrl : String?) {
        /**
         * TODO quality 조정하기
         */
        if(imageUrl != null) {
            var tempImageUrl = "$imageUrl?d=1000"
            Glide.with(imageView.context)
                .load(tempImageUrl)
                .error(R.drawable.default_img)
                .into(imageView)
        }else {
            Glide.with(imageView.context)
                .load(R.drawable.default_img)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("imageModifyUrl")
    fun imageModifyUrlAdapter(imageView: ImageView, imageUrl : String?) {
        if(imageUrl != null) {
            val tempImageUrl = "$imageUrl?d=500"
            Glide.with(imageView.context)
                .load(tempImageUrl)
                .centerCrop()
                .error(R.drawable.default_img)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrlAdapter(imageView: ImageView, imageUrl : String?) {

        if(imageUrl != null) {
            var tempImageUrl = "$imageUrl?d=1000"

            /**
             * TODO quality 조정하기
             */


            Glide.with(imageView.context)
                .load(tempImageUrl)
                .centerCrop()
                .error(R.drawable.default_img)
                .into(imageView)
        }else {
            Glide.with(imageView.context)
                .load(R.drawable.default_img)
                .into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter("imageProfileUrl", "imageProfileId")
    fun imageProfileAdapter(imageView: ImageView, imageUrl : String? , id : Int) {
        println("imageProfileAdapter $id")

        var drawableImg : Int = -1
        when(id % 6) {
            0 -> drawableImg =  R.drawable.ic_profile_01
            1 -> drawableImg =  R.drawable.ic_profile_02
            2 -> drawableImg =  R.drawable.ic_profile_03
            3 -> drawableImg =  R.drawable.ic_profile_04
            4 -> drawableImg =  R.drawable.ic_profile_05
            5 -> drawableImg =  R.drawable.ic_profile_06
        }

        if(id !== 0) {
            var tempImageUrl = "$imageUrl?d=500"
            if(imageUrl != null) {
                Glide.with(imageView.context)
                    .load(tempImageUrl)
                    .error(drawableImg)
                    .into(imageView)
            }else {
                Glide.with(imageView.context)
                    .load(drawableImg)
                    .placeholder(R.drawable.default_img)
                    .into(imageView)
            }
        }
    }

}