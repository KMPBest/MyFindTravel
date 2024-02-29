package navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import configs.uis.Silver_d8
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomNavigation(
    modifer: Modifier = Modifier,
    selectedNavItem: BottomNavItem,
    onNavigationItemSeleted: (BottomNavItem) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifer
    ) {
        val navItem = BottomNavItem.values()
        navItem.forEach { item ->
            val isSelected = item == selectedNavItem
            val iconTint = if (isSelected) MaterialTheme.colorScheme.secondary else Silver_d8
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = item.iconRes,
                        tint = iconTint
                    )
                },
                selected = isSelected,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = Silver_d8
                ),
                onClick = { onNavigationItemSeleted(item) }
            )
        }
    }
}