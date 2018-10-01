package com.rashikaranpuria.wanderee.ui.variantSelector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.data.api.model.VariationsItem
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.variant_group_item.view.*
import kotlinx.android.synthetic.main.variation_item.view.*

class VariantsAdapter(val mContext: Context, var variantGroups: List<VariantGroupsItem>): ExpandableRecyclerViewAdapter<VariantsAdapter.VariantGroupViewHolder, VariantsAdapter.VariantItemViewholder>(variantGroups.map { it.toExpandableGroup() }) {

    override fun onBindGroupViewHolder(holder: VariantGroupViewHolder, flatPosition: Int, group: ExpandableGroup<*>) {
        holder.bind(group)
    }

    override fun onBindChildViewHolder(holder: VariantItemViewholder, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int) {
        holder.bind(group.items[childIndex] as VariationsItem)
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): VariantGroupViewHolder {
        return VariantGroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.variant_group_item, parent, false))
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): VariantItemViewholder {
        return VariantItemViewholder(LayoutInflater.from(parent.context).inflate(R.layout.variation_item, parent, false))
    }

    class VariantGroupViewHolder(view: View): GroupViewHolder(view) {

        val sectionTitle: TextView = view.variant_section_title

        fun bind(variantGroupsItem: ExpandableGroup<*>) {
            sectionTitle.text = variantGroupsItem.title
        }
    }

    inner class VariantItemViewholder(childView: View): ChildViewHolder(childView) {
        val variantTitle: TextView = childView.variant_title
        val veg_mark: ImageView = childView.veg_icon
        val checkBox: RadioButton = childView.checkbox
        val price: TextView = childView.price

        fun bind(item: VariationsItem) {
            variantTitle.text = item.name
            if (item.isVeg == 1) {
                veg_mark.setImageResource(R.drawable.ic_veg_mark)
            }
            else{
                veg_mark.setImageResource(R.drawable.ic_nonveg_mark)
            }
            price.text = String.format(mContext.resources.getString(R.string.price_string), item.price)
            if (item.inStock != 1) {
                variantTitle.setTextAppearance(mContext, R.style.low_lighted_text)
                checkBox.isEnabled = false
            }
            else {
                variantTitle.setTextAppearance(mContext, R.style.default_text)
            }
        }
    }
}