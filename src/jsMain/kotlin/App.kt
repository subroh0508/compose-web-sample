import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.webhistory.DefaultWebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume



@Composable
fun App(
    pathname: String,
    content: @Composable (RootComponent) -> Unit,
) {
    val lifecycle = remember { LifecycleRegistry() }
    val root = remember {
        RootComponent(
            DefaultComponentContext(lifecycle = lifecycle),
            pathname,
            DefaultWebHistoryController(),
        )
    }

    DisposableEffect(true) {
        lifecycle.resume()

        onDispose {
            lifecycle.destroy()
        }
    }

    content(root)
}