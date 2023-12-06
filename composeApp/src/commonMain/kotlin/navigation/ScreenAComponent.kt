package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class ScreenAComponent(
    private val onNavigateToScreenB: (String) -> Unit,
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private var _text = MutableValue("")
    val text: Value<String> = _text

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onEvent(event: ScreenAEvent) {
        when (event) {
            ScreenAEvent.ClickButton -> onNavigateToScreenB(text.value)
            is ScreenAEvent.UpdateText -> _text.value = event.text
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MISC
    ///////////////////////////////////////////////////////////////////////////

    sealed interface ScreenAEvent {
        data object ClickButton : ScreenAEvent
        data class UpdateText(val text: String) : ScreenAEvent
    }
}