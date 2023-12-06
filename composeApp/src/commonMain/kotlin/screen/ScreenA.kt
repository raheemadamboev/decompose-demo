package screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.ScreenAComponent

@Composable
fun ScreenA(
    component: ScreenAComponent
) {
    val text by component.text.subscribeAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Screen A"
        )
        OutlinedTextField(
            value = text,
            onValueChange = { currentText ->
                component.onEvent(ScreenAComponent.ScreenAEvent.UpdateText(currentText))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(
            onClick = {
                component.onEvent(ScreenAComponent.ScreenAEvent.ClickButton)
            }
        ) {
            Text(
                text = "Go to Screen B"
            )
        }
    }
}