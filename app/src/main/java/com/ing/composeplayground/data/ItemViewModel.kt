package com.ing.composeplayground.data

import androidx.annotation.DrawableRes
import com.ing.composeplayground.R

data class Item(@DrawableRes val imageRes: Int, val name: String)
object ItemsViewModel {
    val list = listOf(
        Item(R.drawable.board, "Board"),
        Item(R.drawable.calculator, "Calculator"),
        Item(R.drawable.clock, "Clock"),
        Item(R.drawable.notes, "Notes"),
        Item(R.drawable.pencil, "Pencil"),
        Item(R.drawable.science, "Science"),
        Item(R.drawable.backpack, "Backpack"),
        Item(R.drawable.plane, "Plane")
    )
}