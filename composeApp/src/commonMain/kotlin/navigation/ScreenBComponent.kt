package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class ScreenBComponent(
    private val onBack: () -> Unit,
    componentContext: ComponentContext,
    initialText: String,
) : ComponentContext by componentContext {

    private val _text = MutableValue(initialText)
    val text: Value<String> = _text

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onEvent(event: ScreenBEvent) {
        when (event) {
            ScreenBEvent.ClickBackButton -> onBack()
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MISC
    ///////////////////////////////////////////////////////////////////////////

    sealed interface ScreenBEvent {
        data object ClickBackButton : ScreenBEvent
    }
}