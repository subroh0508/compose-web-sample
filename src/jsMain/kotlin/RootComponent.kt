import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.navigate
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.router.webhistory.WebHistoryController
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class RootComponent @OptIn(ExperimentalDecomposeApi::class) constructor(
    context: ComponentContext,
    pathname: String? = null,
    query: String? = null,
    webHistoryController: WebHistoryController? = null,
) : ComponentContext by context {
    private val router = context.router(
        initialStack = { listOf(pathname?.let { Config(it, query) } ?: Config.Home) },
        handleBackButton = true,
        key = "router",
    ) { config, _ ->
        when (config) {
            is Config.Home -> Page.Home
            is Config.Content -> Page.Content(config.color)
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
    fun toAbout() { push(Config.About) }
    fun toContent(color: String? = null) {
        push(Config.Content(color))
    }

    private fun push(config: Config) {
        if (routerState.value.activeChild.configuration == config) {
            return
        }

        router.navigate { it.filterNot { c -> c == config } + config }
    }

    sealed class Page {
        object Home : Page()
        data class Content(val color: String?) : Page()
        object About : Page()
    }

    sealed class Config : Parcelable {
        abstract val path: String

        @Parcelize
        object Home : Config() { override val path = "/" }
        @Parcelize
        data class Content(val color: String?) : Config() {
            companion object {
                const val BASE = "content"

                fun fromQuery(query: String?) = Content(
                    query?.split("=")
                        ?.takeIf { it.size == 2 }
                        ?.last()
                        ?.takeIf(String::isNotBlank)
                )
            }

            override val path get() = "$BASE?color=${color ?: ""}"
        }
        @Parcelize
        object About : Config() { override val path = "about" }

        companion object {
            operator fun invoke(path: String, query: String? = null) = when {
                path.startsWith("/${Content.BASE}") -> Content.fromQuery(query)
                path == "/${About.path}" -> About
                else -> Home
            }
        }
    }
}