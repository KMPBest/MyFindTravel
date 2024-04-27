package screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.common.TitleDescription
import components.more.ExpandableBoxItem
import configs.uis.Orange_55
import configs.uis.Red_48
import configs.uis.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import utils.AppVersion

@Composable
fun AboutScreen(
) {
    var uiState by remember { mutableStateOf(AboutScreenUiState()) }
    val systemBarPaddingValues = WindowInsets.systemBars.asPaddingValues()
    val appVersion = koinInject<AppVersion>()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(
                start = 30.dp,
                end = 30.dp,
                top = systemBarPaddingValues.calculateTopPadding() + 30.dp
            )
    ) {
        items(uiState.items, key = { it }){ item ->
            ExpandableBoxItem(
                text = item,
                isExpanded = uiState.expandedItem == item,
                onToggle = { uiState = uiState.copy(expandedItem = item) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 5.dp)
            ) {
                when (item){
                   Strings.about_findtravelnow -> AboutFindTravelNowExpandedContent()
                   Strings.contact_details -> ContactDetailsExpandedContent()
                   Strings.app_details -> AppDetailsExpandedContent(appVersion.name())
                }
            }
        }
    }
}

@Composable
private fun AboutFindTravelNowExpandedContent(){
    Text(
        text = Strings.about_findtravelnow_text,
        color = Color.Black,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
private fun ContactDetailsExpandedContent(){
    Column {
        ImageTitleDescription(
            imageRes = "drawable/ic_email.xml",
            title = Strings.email_address_title,
            description = Strings.contact_email_address
        )
    }
}

@Composable
private fun AppDetailsExpandedContent(appVersionName: String){
    Column {
        ImageTitleDescription(
            imageRes = "drawable/ic_developer.xml",
            title = Strings.developer_title,
            description = Strings.developer_description
        )
        Spacer(modifier = Modifier.height(16.dp))
        ImageTitleDescription(
            imageRes = "drawable/ic_app_version.xml",
            title = Strings.app_version_title,
            description = appVersionName
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ImageTitleDescription(
    modifier: Modifier = Modifier,
    imageRes: String,
    title: String,
    description: String
){
    Row(modifier = modifier) {
        val gradientBg = Brush.radialGradient(
            0f to Red_48,
            1f to Orange_55
        )
        val imageShape = RoundedCornerShape(6.dp)
        Image(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(32.dp)
                .clip(imageShape)
                .background(gradientBg, shape = imageShape),
            painter = painterResource(imageRes),
            contentDescription = null
        )

        TitleDescription(
            modifier = Modifier.padding(start = 14.dp),
            title = title,
            description = description
        )
    }
}