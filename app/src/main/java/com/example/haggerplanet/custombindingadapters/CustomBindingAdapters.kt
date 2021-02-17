package com.example.haggerplanet.custombindingadapters

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.example.haggerplanet.R
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.example.haggerplanet.utils.MethodsUtil

import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

object CustomBindingAdapters {


    @BindingAdapter(value = ["setRecyclerAdapter"], requireAll = false)
    @JvmStatic
    fun setRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>
    ) {
        if (adapter != null) {
            recyclerView.adapter = adapter
        }
    }

    @BindingAdapter(value = ["setBottomNavAdapter"], requireAll = false)
    @JvmStatic
    fun setBottomNavAdapter(
        bottomNav: BottomNavigationView,
        position: ObservableField<Int>
    ) {

        bottomNav.menu.findItem(position.get()!!).isChecked = true
    }



/*    @BindingAdapter(value = ["walkThroughViewPager"], requireAll = false)
    @JvmStatic
    fun walkThroughViewPager(viewPager: ViewPager, adapter: WalkThroughAdapter) {
        viewPager.adapter = adapter
    }*/

   /* @BindingAdapter(value = ["setIndicator"], requireAll = false)
    @JvmStatic
    fun setIndicator(dotsIndicator: SpringDotsIndicator, viewPager: ViewPager) {
        dotsIndicator.setViewPager(viewPager)
    }*/

    @BindingAdapter(value = ["setWalkThroughImage"], requireAll = false)
    @JvmStatic
    fun setWalkThroughImage(imageView: ImageView, path: Drawable) {
        var context = imageView.context
        imageView.setImageDrawable(path)
    }

/*
    @BindingAdapter(value = ["setSpinnerAdapter", "setSpinnerListener"], requireAll = false)
    @JvmStatic
    fun setSpinnerAdapter(
        spinner: Spinner,
        adapter: SpinnerDropDownAdapter,
        listener: AdapterView.OnItemSelectedListener
    ) {
        if (adapter != null) {
            spinner.adapter = adapter
            spinner.onItemSelectedListener = listener
        }
    }
*/



/*    @BindingAdapter(value = ["setSpinnerAdapter", "setSpinnerListener"], requireAll = false)
    @JvmStatic
    fun setNoOfEmpSpinnerAdapter(
        spinner: Spinner,
        adapter: NoOfEmployeeAdapter,
        listener: AdapterView.OnItemSelectedListener
    ) {
        if (adapter != null) {
            spinner.adapter = adapter
            spinner.onItemSelectedListener = listener
        }
    }*/

    @BindingAdapter(value = ["setCheckboxListener"])
    @JvmStatic
    fun setCheckboxListener(
        checkBox: CheckBox,
        onCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    ) {

        checkBox.setOnCheckedChangeListener(onCheckedChangeListener)
    }

    @BindingAdapter(value = ["getCheckChange"])
    @JvmStatic
    fun getCheckChange(
        checkBox: CheckBox,
        observableBoolean: ObservableBoolean
    ) {

        if (observableBoolean.get()) {

            checkBox.isChecked = true

            Log.e("isChckked", "true" + observableBoolean)
        } else {
            checkBox.isChecked = false
        }

    }

    @BindingAdapter(value = ["navigationClick"], requireAll = false)
    @JvmStatic
    fun navigationClick(
        bottomNavigationView: BottomNavigationView,
        click: BottomNavigationView.OnNavigationItemSelectedListener
    ) {
        bottomNavigationView.setOnNavigationItemSelectedListener(click)
    }

    /*@BindingAdapter(value = ["onFocusChange"], requireAll = false)
    @JvmStatic
    fun onFocusChange(editText: EditText, listener: View.OnFocusChangeListener) {
        editText.onFocusChangeListener = listener
    }

    @BindingAdapter(value = ["onEnterPressed"], requireAll = false)
    @JvmStatic
    fun onEnterPressed(
        editText: EditText, listener: View.OnKeyListener
    ) {
        editText.setOnKeyListener(listener)
    }*/


/*    @BindingAdapter(value = ["manageOrderAdapter", "viewPagerTab"], requireAll = false)
    @JvmStatic
    fun manageOrderAdapter(
        viewPager: ViewPager,
        manageOrderAdapter: ManageOrderAdapter, tabLayout: TabLayout
    ) {
        viewPager.adapter = manageOrderAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }*/


    @BindingAdapter(value = ["changeTextColor"])
    @JvmStatic
    fun changeTextColor(
        textView: TextView,
        observableBoolean: ObservableBoolean
    ) {
        if (observableBoolean.get()) {

            textView.setTextColor(textView.context.resources.getColor(R.color.white))

        } else {

            textView.setTextColor(textView.context.resources.getColor(R.color.black))
        }

    }

    @BindingAdapter(value = ["changeTextColor2"])
    @JvmStatic
    fun changeTextColor2(
        textView: TextView,
        observableBoolean: ObservableBoolean
    ) {
        if (observableBoolean.get()) {

            textView.setTextColor(textView.context.resources.getColor(R.color.grey))

        } else {

            textView.setTextColor(textView.context.resources.getColor(R.color.white))
        }

    }


    @BindingAdapter(value = ["changeCardBaground"])
    @JvmStatic
    fun changeCardBaground(
        cardView: CardView,
        observableBoolean: ObservableBoolean
    ) {
        if (observableBoolean.get()) {

            cardView.setCardBackgroundColor(cardView.context.resources.getColor(R.color.colorPrimary))

        } else {

            cardView.setCardBackgroundColor(cardView.context.resources.getColor(R.color.white))
        }

    }
    @BindingAdapter(value = ["changeCardBaground2"])
    @JvmStatic
    fun changeCardBaground2(
        cardView: CardView,
        observableBoolean: ObservableBoolean
    ) {
        if (observableBoolean.get()) {

            cardView.setCardBackgroundColor(cardView.context.resources.getColor(R.color.colorPrimary))

        } else {

            cardView.setCardBackgroundColor(cardView.context.resources.getColor(R.color.colorPrimary))
        }

    }

    @BindingAdapter(value = ["setAutoCompleteItemClick"])
    @JvmStatic
    fun setAutoCompleteItemClick(
        appCompatAutoCompleteTextView: AppCompatAutoCompleteTextView,
        onItemClickListener: AdapterView.OnItemClickListener
    ) {
        appCompatAutoCompleteTextView.onItemClickListener = onItemClickListener
    }

/*    @BindingAdapter(value = ["setAutocompleteAdapter"])
    @JvmStatic
    fun setAutocompleteAdapter(
        appCompatAutoCompleteTextView: AppCompatAutoCompleteTextView,
        placesAutoCompleteAdapter: PlacesAutoCompleteAdapter
    ) {
        appCompatAutoCompleteTextView.setAdapter(placesAutoCompleteAdapter)

    }*/

    @BindingAdapter(value = ["setNormalFont"])
    @JvmStatic
    fun setNormalFont(view: View, type: String) {

        when (type) {
            "TextView" -> {
                val normalGautam =
                    Typeface.createFromAsset((view.context).assets, "fonts/GothamLight.ttf")
                (view as TextView).setTypeface(normalGautam, Typeface.NORMAL)
            }

        }


    }

    @BindingAdapter(value = ["changeImageFilter"])
    @JvmStatic
    fun changeImageFilter(
        imageView: ImageView,
        observableBoolean: ObservableBoolean
    ) {
        if (observableBoolean.get()) {
            imageView.setImageResource(R.drawable.ic_bag)
        } else {
            imageView.setImageResource(R.drawable.ic_bag)
        }
    }

    @BindingAdapter(value = ["setMediumFont"])
    @JvmStatic
    fun setMediumFont(view: View, type: String) {

        when (type) {

            "TextView" -> {
                val mediumgautam =
                    Typeface.createFromAsset((view.context).assets, "fonts/GothamMedium_1.ttf")
                (view as TextView).setTypeface(mediumgautam, Typeface.NORMAL)
            }
        }
    }


    @BindingAdapter(value = ["setWebView"])
    @JvmStatic
    fun setWebView(webView: WebView, content: String?) {
        Log.e("ContentIs", content!!)
        webView.setBackgroundColor(0)

        if (!content.isNullOrEmpty()) {


            var text = MethodsUtil.preHtml + content + MethodsUtil.postHtml
            Log.e("FullText", text)

            webView.loadData(text, "text/html; charset=utf-8", "UTF-8")
        }
    }


    @BindingAdapter(value = ["loadCircleImage"], requireAll = false)
    @JvmStatic
    fun loadCircleImage(imageView: CircleImageView, url: String?) {
        // Log.e("pathIs", url)
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).resize(400, 400).placeholder(R.drawable.dummy)
                .into(imageView)
        }
    }

    @BindingAdapter(value = ["setCircleImageUri"])
    @JvmStatic
    fun setCircleImageUri(imageView: CircleImageView, imageUri: Uri?) {
        if (imageUri!=null) {
            if(!imageUri.path.isNullOrEmpty())
            {
                Log.e("ImageUriIs==,", "==$imageUri")
                imageView.setImageURI(imageUri)
            }
        }
    }

    @BindingAdapter(value = ["getCheckChange"])
    @JvmStatic
    fun getCheckChange(
        radioButton: RadioButton,
        observableBoolean: ObservableBoolean
    ) {

        if (observableBoolean.get()) {

            radioButton.isChecked = true

            Log.e("isChckked", "true" + observableBoolean)
        } else {
            radioButton.isChecked = false
        }

    }
    @BindingAdapter(value = ["setPaginationRecyclerAdapter", "scrollListener"], requireAll = false)
    @JvmStatic
    fun setPaginationRecyclerAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>, listener: RecyclerView.OnScrollListener
    ) {
        if (adapter != null) {
            recyclerView.adapter = adapter
            recyclerView.addOnScrollListener(listener)
        }
    }

    @BindingAdapter(value = ["loadImage"], requireAll = false)
    @JvmStatic
    fun loadImage(imageView: ImageView, path: String) {

        if (!path.isNullOrEmpty()) {
            Log.e("pathIs", path)
            Picasso.get().load(path).resize(400, 400).placeholder(R.drawable.dummy)
                .into(imageView)
        }
    }

    @BindingAdapter(value = ["setFocusChangeListener"])
    @JvmStatic
    fun setFocusChangeListener(
        editText: EditText, onFocusChangeListener: View.OnFocusChangeListener
    ) {
        editText.onFocusChangeListener = onFocusChangeListener
    }

/*    @BindingAdapter(value = ["setRoundImageUri"])
    @JvmStatic
    fun setRoundImageUri(imageView: RoundedImageView, imageUri: Uri?) {
        if (imageUri!=null) {
            if(!imageUri.path.isNullOrEmpty())
            {
                imageView.setImageURI(imageUri)
            }
        }
    }*/

    @BindingAdapter(value = ["setRadioListener"])
    @JvmStatic
    fun setRadioListener(
        radioButton: RadioButton,
        onCheckedChangeListener: CompoundButton.OnCheckedChangeListener
    ) {

        radioButton.setOnCheckedChangeListener(onCheckedChangeListener)
    }


/*
    @BindingAdapter(value = ["setSearchChangeListener"])
    @JvmStatic
    fun setSearchChangeListener(editText: EditText, homeActivityViewModel: HomeActivityViewModel
    )
    {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?)
            {
                homeActivityViewModel.getSearchData(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {

            }
        })
    }
*/

/*
    @BindingAdapter(value = ["setSimpleImage"], requireAll = false)
    @JvmStatic
    fun setSimpleImage(image: ImageView, imageName: String?) {
        Log.e("Image", "Val$imageName")
        if (!imageName.isNullOrEmpty()) {
            Log.e("Image", "ValInner$imageName")
            Glide.with(image.context).load(WebApiKeys.PRODUCT_IMAGE_URL + imageName).into(image)
        }
    }
*/


/*    @BindingAdapter(value = ["loadImagePdf"], requireAll = false)
    @JvmStatic
    fun loadImagePdf(imageView: RoundedImageView, path: String) {

        if (!path.isNullOrEmpty()) {

            if(path.contains(".pdf"))
            {
                imageView.setImageResource(R.drawable.pdf)
            }
            else
            {
                Log.e("pathIs", path)
                Glide.with(imageView.context).load(WebApiKeys.LICENSE_UEL + path).into(imageView)
            }

        }
    }

    @BindingAdapter(value = ["loadImagePdfCert"], requireAll = false)
    @JvmStatic
    fun loadImageCerti(imageView: RoundedImageView, path: String) {

        if (!path.isNullOrEmpty()) {

            if(path.contains(".pdf"))
            {
                imageView.setImageResource(R.drawable.pdf)
            }
            else
            {
                Log.e("pathIs", path)
                Glide.with(imageView.context).load(WebApiKeys.CERTIFICATE_URL + path).into(imageView)
            }

        }
    }*/
    @BindingAdapter(value = ["changeTextColorProduct"])
    @JvmStatic
    fun changeTextColorProduct(
        textView: TextView,
        observableBoolean: Int
    ) {
        if (observableBoolean==0) {
            textView.setTextColor(textView.context.resources.getColor(R.color.colorPrimary))
        } else {
            textView.setTextColor(textView.context.resources.getColor(R.color.colorPrimary))
        }
    }

    @BindingAdapter(value = ["changeTextColorProductDetails"])
    @JvmStatic
    fun changeTextColorProductDetails(
        textView: TextView,
        observableBoolean: Boolean
    ) {
        if (observableBoolean) {
            textView.setTextColor(textView.context.resources.getColor(R.color.colorPrimary))
        } else {
            textView.setTextColor(textView.context.resources.getColor(R.color.colorPrimary))
        }
    }

}