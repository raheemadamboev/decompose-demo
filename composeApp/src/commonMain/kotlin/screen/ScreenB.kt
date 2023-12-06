package screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.ScreenBComponent

@Composable
fun ScreenB(
    component: ScreenBComponent
) {
    val text by component.text.subscribeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Screen B: $text"
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick = {
                component.onEvent(ScreenBComponent.ScreenBEvent.ClickBackButton)
            }
        ) {
            Text("Go back")
        }
    }
}