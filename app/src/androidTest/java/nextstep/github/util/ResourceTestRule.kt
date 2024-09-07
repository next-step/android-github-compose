package nextstep.github.util

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ResourceTestRule : TestRule {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun apply(base: Statement, description: Description): Statement {
        return base
    }
}
