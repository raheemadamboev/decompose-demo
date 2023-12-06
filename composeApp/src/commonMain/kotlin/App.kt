import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import navigation.RootComponent
import screen.ScreenA
import screen.ScreenB

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val stack by root.childStack.subscribeAsState()

        Children(
            stack = stack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.ScreenA -> ScreenA(instance.component)
                is RootComponent.Child.ScreenB -> ScreenB(instance.component)
            }
        }
    }
}