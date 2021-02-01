package com.dnc.marketingapp.data.model

import com.dnc.marketingapp.R

sealed class ScreenSelectedState(
    var firstImageResourceId: Int = R.drawable.ic_screen_not_selected,
    var secondImageResourceId: Int = R.drawable.ic_screen_not_selected,
    var thirdImageResourceId: Int = R.drawable.ic_screen_not_selected,
    var forthImageResourceId: Int = R.drawable.ic_screen_not_selected
){
    class TargetingSpecificScreenSelected: ScreenSelectedState(
        firstImageResourceId = R.drawable.ic_screen_selected
    )

    class ChannelsScreenSelected: ScreenSelectedState(
        firstImageResourceId = R.drawable.ic_screen_selected,
        secondImageResourceId = R.drawable.ic_screen_selected
    )

    class CampaignsScreenSelected: ScreenSelectedState(
        firstImageResourceId = R.drawable.ic_screen_selected,
        secondImageResourceId = R.drawable.ic_screen_selected,
        thirdImageResourceId = R.drawable.ic_screen_selected
    )

    class ReviewCampaignScreenSelected: ScreenSelectedState(
        firstImageResourceId = R.drawable.ic_screen_selected,
        secondImageResourceId = R.drawable.ic_screen_selected,
        thirdImageResourceId = R.drawable.ic_screen_selected,
        forthImageResourceId = R.drawable.ic_screen_selected
    )
}