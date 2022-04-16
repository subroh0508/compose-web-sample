import com.arkivanov.decompose.Child
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.navigate
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.router.webhistory.DefaultWebHistoryController
import com.arkivanov.decompose.router.webhistory.WebHistoryController
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class RootComponent @OptIn(ExperimentalDecomposeApi::class) constructor(
    context: ComponentContext,
    pathname: String? = null,
    webHistoryController: WebHistoryController? = null,
) : ComponentContext by context {
    private val router = context.router(
        initialStack = { listOf(pathname?.let { Config(it) } ?: Config.Home) },
        handleBackButton = true,
        key = "router",
    ) { config, _ ->
        when (config) {
            is Config.Home -> Page.Home
            is Config.Contents -> Page.Contents
            is Config.About -> Page.About
        }
    }

    init {
        webHistoryController?.attach(
            router,
            getPath = { it.path },
            getConfiguration = Config::invoke,
        )
    }

    val routerState = router.state

    fun toHome() { push(Config.Home) }
    fun toContents() { push(Config.Contents) }
    fun toAbout() { push(Config.About) }

    private fun push(config: Config) {
        if (routerState.value.activeChild.configuration == config) {
            return
        }

        router.navigate { it.filterNot { c -> c == config } + config }
    }

    sealed class Page {
        object Home : Page()
        object Contents : Page()
        object About : Page()
    }

    sealed class Config : Parcelable {
        abstract val path: String

        @Parcelize
        object Home : Config() { override val path = "/" }
        @Parcelize
        object Contents : Config() { override val path = "content" }
        @Parcelize
        object About : Config() { override val path = "about" }

        companion object {
            operator fun invoke(path: String) = when(path) {
                "/${Contents.path}" -> Contents
                "/${About.path}" -> About
                else -> Home
            }
        }
    }
}