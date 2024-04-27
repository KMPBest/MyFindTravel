package navigation

enum class BottomNavItem(val iconRes: String){
    Home(iconRes = "drawable/ic_bottom_nav_home.xml"),
    TopFlights(iconRes = "drawable/ic_bottom_nav_top5flights.xml"),
    Profile(iconRes = "drawable/ic_bottom_nav_profile.xml"),
    More(iconRes = "drawable/ic_bottom_nav_more.xml");

    fun asTopLevel(): IScreens {
        return when (this) {
            Home -> IScreens.Home
            TopFlights -> IScreens.TopFlight
            Profile -> IScreens.Signin
            More -> IScreens.More
        }
    }
}

fun IScreens.asBottomNavItem(): BottomNavItem {
    return when (this) {
        IScreens.Home -> BottomNavItem.Home
        IScreens.More -> BottomNavItem.More
        IScreens.Signin -> BottomNavItem.Profile
        IScreens.TopFlight -> BottomNavItem.TopFlights
        else -> BottomNavItem.Home
    }
}