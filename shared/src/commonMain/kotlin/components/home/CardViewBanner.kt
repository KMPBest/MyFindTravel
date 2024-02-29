package components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import configs.uis.Black_10
import configs.uis.Orange_55
import configs.uis.Red_48
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CardViewBanner(modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(9.dp)
    val gradient = Brush.verticalGradient(0f to Red_48, 1f to Orange_55)
    Box(
        modifier = Modifier.fillMaxWidth().clip(shape).background(gradient).padding(start = 16.dp)
    ) {
        Box(
            modifier = Modifier.align(Alignment.TopEnd).fillMaxWidth(0.5f)
        ){
            Image(
                painter = painterResource("drawable/ic_ellipse.xml"),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 4.dp, bottom = 11.dp),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = painterResource("drawable/img_travel_person.png"),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 19.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column (
            modifier = Modifier.fillMaxWidth(0.58f).padding(vertical = 30.dp)
        ) {
            Text(
                text = "Get cheap flights from 100s of airlines and travel agents",
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
            val buttonShape = RoundedCornerShape(8.dp)
            val emptyFunction: () -> Unit = {}
            Button(
                modifier = Modifier.padding(top = 16.dp).clip(buttonShape)
                    .background(Black_10, buttonShape),
                shape = buttonShape,
                onClick = emptyFunction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black_10
                )
            ){
                Text(
                    text = "Book Now",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}