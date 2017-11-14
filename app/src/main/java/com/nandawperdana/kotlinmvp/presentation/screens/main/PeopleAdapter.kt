package com.nandawperdana.kotlinmvp.presentation.screens.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nandawperdana.kotlinmvp.R
import com.nandawperdana.kotlinmvp.api.people.PeopleModel
import kotlinx.android.synthetic.main.item_people.view.*

/**
 * Created by nandawperdana.
 */
class PeopleAdapter(private val items: ArrayList<PeopleModel>) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private fun ViewGroup.inflate(layoutRes: Int): View =
            LayoutInflater.from(context).inflate(layoutRes, this, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_people)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: PeopleModel) = with(itemView) {
            textViewPeople.text = item.name
            textViewPeopleSub.text = item.email
        }
    }
}