import androidx.compose.ui.window.ComposeUIViewController
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.MobileDb

fun MainViewController() = ComposeUIViewController {
    val sqlDriver = NativeSqliteDriver(MobileDb.Schema, "mobile.db")
    println("SqlDriver: $sqlDriver")
    App()
}
