package navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation: StackNavigation<Configuration> = StackNavigation()

    val childStack: Value<ChildStack<Configuration, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.ScreenA -> Child.ScreenA(
                ScreenAComponent(
                    onNavigateToScreenB = { text ->
                        navigation.pushNew(Configuration.ScreenB(text))
                    },
                    componentContext = context
                )
            )

            is Configuration.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    onBack = navigation::pop,
                    componentContext = context,
                    initialText = config.text
                )
            )
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // MISC
    ///////////////////////////////////////////////////////////////////////////

    sealed interface Child {
        data class ScreenA(val component: ScreenAComponent) : Child
        data class ScreenB(val component: ScreenBComponent) : Child
    }

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object ScreenA : Configuration

        @Serializable
        data class ScreenB(val text: String) : Configuration
    }
}