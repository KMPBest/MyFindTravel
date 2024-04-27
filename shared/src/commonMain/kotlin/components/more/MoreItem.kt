package components.more

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import configs.uis.Alabaster
import configs.uis.Black_22
import configs.uis.Gray
import configs.uis.Orange_alpha_50
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun ExpandableBoxItem(
    modifier: Modifier = Modifier,
    text: String,
    isExpanded: Boolean,
    boxListItemColors: BoxListItemColor = BoxListItemColor(),
    onToggle: () -> Unit,
    content: @Composable () -> Unit
){
    val shape =  RoundedCornerShape(10.dp)
    val bgColor by animateColorAsState(
        if(isExpanded) boxListItemColors.expandedBackgroundColor
        else boxListItemColors.backgroundColor)
    val borderColor = if (isExpanded) boxListItemColors.expandedBorderColor else boxListItemColors.borderColor
    val iconRotation by animateFloatAsState(if(isExpanded) 90f else 0f)

    ButtonDefaults.buttonColors()
    Column(
        modifier = modifier.border(
            width = 1.dp,
            color = borderColor,
            shape = shape
        )
            .animateContentSize()
            .clip(shape)
            .drawBehind {
                drawRect(bgColor)
            }
            .clickable {  }
    ) {
        TextIconRow(
            text = text,
            modifier = Modifier
                .clickable { onToggle() }
                .padding(start = 22.dp, end = 19.dp, top = 19.dp, bottom = 14.dp),
            iconRotation = iconRotation
        )
        if(isExpanded) {
            Divider(color = Gray, modifier = Modifier.padding(start = 22.dp, end = 19.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.padding(start = 22.dp, end = 19.dp, bottom = 19.dp)) {
                content()
            }
        }
    }
}
@OptIn(ExperimentalResourceApi::class)
@Composable
public fun MoreItem(modifier: Modifier = Modifier, value: String, onClick: () -> Unit) {
    Box (
        modifier = modifier
            .border(1.dp, Gray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(14.dp)

    ) {
        TextIconRow(
            text = value
        )
    }
}

@Composable
private fun TextIconRow(
    modifier: Modifier = Modifier,
    text: String,
    imageVector: ImageVector = Icons.Default.KeyboardArrowRight,
    iconRotation: Float = 0f
){
    Row (modifier = modifier){
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = imageVector,
            tint = Black_22,
            modifier = Modifier.rotate(iconRotation),
            contentDescription = null
        )
    }
}

data class BoxListItemColor(
    val expandedBackgroundColor: Color = Orange_alpha_50.compositeOver(Color.White),
    val expandedBorderColor: Color = Orange_alpha_50,
    val backgroundColor: Color = Color.White,
    val borderColor: Color = Alabaster,
)