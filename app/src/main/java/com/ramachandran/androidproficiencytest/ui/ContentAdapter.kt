package com.ramachandran.androidproficiencytest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramachandran.androidproficiencytest.R
import com.ramachandran.androidproficiencytest.databinding.ContentadapterBinding
import com.ramachandran.androidproficiencytest.network.model.Row
import com.squareup.picasso.Picasso
import java.lang.Exception

class ContentAdapter(context : Context) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
    val mContext=context
    private lateinit var contentadapterBinding: ContentadapterBinding
    companion object{
        const val dummyImage : String ="https://via.placeholder.com/300.png/09f/fff"
       @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImageUrl: String) {
            Picasso.with(view.context)
                .load(profileImageUrl)
                .placeholder(ContextCompat.getDrawable(view.context,R.mipmap.ic_launcher_round))
                .into(view)
        }
    }

    var rowsList : MutableList<Row>? =null
    fun setList(rowsList : MutableList<Row>){
        this.rowsList =rowsList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        contentadapterBinding = ContentadapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViewHolder(contentadapterBinding,mContext)
    }

    override fun getItemCount(): Int {
        return rowsList!!.size
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
       holder.bindData(rowsList!!.get(position))
    }
    class ContentViewHolder(contentBinding : ContentadapterBinding,ctx : Context) : RecyclerView.ViewHolder(contentBinding.root) {
        var contentBinder = contentBinding
        val coxt=ctx
        fun bindData(row : Row){
            try {
                contentBinder.rowInstance = row
                contentBinder.dummyImg = dummyImage
                if(row.title == null){
                    contentBinder.textView.visibility= View.GONE
                }
                if(row.description == null ){
                    contentBinder.textView2.visibility= View.GONE
                }
                if(row.imageHref == null){
                    contentBinder.imageU.visibility =View.GONE
                }
                contentBinder.executePendingBindings()
            }
            catch (e : ClassCastException){
                e.printStackTrace()
            }

        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    fun clear(){
        rowsList!!.clear()
        notifyDataSetChanged()
    }

}