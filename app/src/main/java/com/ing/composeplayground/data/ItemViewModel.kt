package com.ing.composeplayground.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ItemViewModel : ViewModel() {

    private var _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>> = _itemList

    fun onRefresh() {
        val amountOfItems = Random.nextInt(8, 20)
        val items = ArrayList<Item>(amountOfItems)
        for (i in 0..amountOfItems) {
            items.add(generateRandomItem())
        }
        _itemList.value = items.distinctBy { it.title }.sortedBy { it.title }
    }

    fun onItemClick(item: Item) {
        val tempList = _itemList.value?.toMutableList()
        tempList?.find { it == item }.also {
            item.value *= -1
            _itemList.value = emptyList()
            _itemList.value = tempList
        }
    }

    private fun generateRandomItem(): Item {
        return Item(randomTitle(), randomValue())
    }

    private fun randomTitle(): String {
        val randomIndex = (0..companyNames.lastIndex).random()
        return companyNames[randomIndex]
    }

    private fun randomValue(): Float {
        val minStockPrice = -10000.0f
        val maxStockPrice = 10000.0f

        val random = Random.Default
        return minStockPrice + random.nextFloat() * (maxStockPrice - minStockPrice)
    }

    private val companyNames = listOf(
        "Activision Blizzard Inc",
        "Adobe Inc",
        "Advanced Micro Devices Inc",
        "Alexion Pharmaceuticals Inc",
        "Align Technology Inc",
        "Alphabet Inc",
        "Amazon.com Inc",
        "American Airlines Group Inc",
        "Amgen Inc",
        "Analog Devices Inc",
        "ANSYS Inc",
        "Apple Inc",
        "Applied Materials Inc",
        "ASML Holding NV",
        "Autodesk Inc",
        "Automatic Data Processing Inc",
        "Baidu Inc",
        "Biogen Inc",
        "Booking Holdings Inc",
        "Broadcom Inc",
        "Cadence Design Systems Inc",
        "CDW Corp",
        "Cerner Corp",
        "Charter Communications Inc",
        "Check Point Software Technologies Ltd",
        "Cintas Corp",
        "Cisco Systems Inc",
        "Citrix Systems Inc",
        "Cognizant Technology Solutions Corp",
        "Comcast Corp",
        "Copart Inc",
        "Costco Wholesale Corp",
        "CSX Corp",
        "DocuSign Inc",
        "Dollar Tree Inc",
        "eBay Inc",
        "Electronic Arts Inc",
        "Equinix Inc",
        "Exelon Corp",
        "Expedia Group Inc",
        "Facebook Inc",
        "Fastenal Co",
        "Fiserv Inc",
        "Fox Corp",
        "Gilead Sciences Inc",
        "Hasbro Inc",
        "Henry Schein Inc",
        "Hologic Inc",
        "Idexx Laboratories Inc",
        "Illumina Inc",
        "Incyte Corp",
        "Intel Corp",
        "Intuit Inc",
        "Intuitive Surgical Inc",
        "JD.com Inc",
        "KLA Corp",
        "Kraft Heinz Co",
        "Lam Research Corp",
        "Liberty Global",
        "Liberty Latin America Ltd",
        "Lululemon Athletica Inc",
        "Marriott International Inc",
        "Match Group Inc",
        "MercadoLibre Inc",
        "Micron Technology Inc",
        "Microsoft Corp",
        "Moderna Inc",
        "Mondelez International Inc",
        "Monster Beverage Corp",
        "Mylan NV",
        "NetEase Inc ADR",
        "Netflix Inc",
        "Nvidia Corp",
        "NXP Semiconductors NV",
        "O'Reilly Automotive Inc",
        "PACCAR Inc",
        "Paychex Inc",
        "PayPal Holdings Inc",
        "Peloton Interactive Inc",
        "PepsiCo Inc",
        "Pinduoduo Inc",
        "Prologis Inc",
        "Qualcomm Inc",
        "Regeneron Pharmaceuticals Inc",
        "Ross Stores Inc",
        "Seattle Genetics Inc",
        "Sirius XM Holdings Inc",
        "Skyworks Solutions Inc",
        "Splunk Inc",
        "Starbucks Corp",
        "Synopsys Inc",
        "T-Mobile US Inc",
        "Take-Two Interactive Software Inc",
        "Tesla Inc",
        "Texas Instruments Inc",
        "The Trade Desk Inc",
        "Trip.com Group Ltd",
        "Ulta Beauty Inc",
        "Verisk Analytics Inc",
        "Vertex Pharmaceuticals Inc",
        "Walgreens Boots Alliance Inc",
        "Western Digital Corp",
        "Workday Inc",
        "Xcel Energy Inc",
        "Xilinx Inc",
        "Zoom Video Communications Inc"
    )
}