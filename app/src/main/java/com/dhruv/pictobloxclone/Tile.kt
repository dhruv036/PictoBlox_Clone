package com.dhruv.pictobloxclone

data class Tile(
    var itemId: Int=-1,
    var itemTitle: String ="",
    var itemLocation:String="",
    var itemBg: String="",
    var itemType: ItemType=ItemType.PLAIN,
    var videoUrl: String?=null
)