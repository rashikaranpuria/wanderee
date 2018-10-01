package com.rashikaranpuria.wanderee.ui.variantSelector

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.rashikaranpuria.wanderee.R
import com.rashikaranpuria.wanderee.data.api.model.ExcludeListItem
import com.rashikaranpuria.wanderee.data.api.model.VariantGroupsItem
import com.rashikaranpuria.wanderee.data.api.model.VariationsItem
import kotlinx.android.synthetic.main.variant_group_item.view.*
import kotlinx.android.synthetic.main.variation_item.view.*


class VariantsAdapter(val mContext: Context, var variantGroups: List<VariantGroupsItem>, val exclusionList: Map<ExcludeListItem, List<ExcludeListItem>>): BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any = variantGroups[groupPosition]

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val holder: VariantGroupHeaderViewHolder
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.variant_group_item, parent,false)
            holder = VariantGroupHeaderViewHolder(view)
            view.tag = holder
        } else {
            holder = view.tag as VariantGroupHeaderViewHolder
        }

        holder.bind(getGroup(groupPosition) as VariantGroupsItem)

        return view!!
    }

    override fun getChildrenCount(groupPosition: Int): Int = variantGroups[groupPosition].variations.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any = variantGroups[groupPosition].variations[childPosition]

    override fun getGroupId(groupPosition: Int): Long = variantGroups[groupPosition].groupId.toLong()

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val holder: VariantItemViewholder
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.variation_item, parent, false)
            holder = VariantItemViewholder(view)
            view.tag = holder
        } else {
            holder = view.tag as VariantItemViewholder
        }

        holder.bind(getChild(groupPosition, childPosition) as VariationsItem)

        return view!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = variantGroups[groupPosition].variations[childPosition].id.toLong()

    override fun getGroupCount(): Int = variantGroups.size


    class VariantGroupHeaderViewHolder(view: View) {

        val sectionTitle: TextView = view.variant_section_title

        fun bind(variantGroupsItem: VariantGroupsItem) {
            sectionTitle.text = variantGroupsItem.name
        }
    }

    inner class VariantItemViewholder(childView: View){
        val variantTitle: TextView = childView.variant_title
        val veg_mark: ImageView = childView.veg_icon
        val checkBox: RadioButton = childView.checkbox
        val price: TextView = childView.price

        fun bind(item: VariationsItem) {
            // name of variant
            variantTitle.text = item.name

            // veg non veg type of variant
            if (item.isVeg == 1) {
                veg_mark.setImageResource(R.drawable.ic_veg_mark)
            }
            else{
                veg_mark.setImageResource(R.drawable.ic_nonveg_mark)
            }
//            checkBox.isChecked = true
//            checkBox.isEnabled = false
            // price of variant
            price.text = String.format(mContext.resources.getString(R.string.price_string), item.price)

            // default selection of variant
            checkBox.isChecked = item.jsonMemberDefault == 1

            // changing appearance if item is in stock
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