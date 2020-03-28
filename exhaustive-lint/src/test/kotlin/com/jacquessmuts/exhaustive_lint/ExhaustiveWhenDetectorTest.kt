package com.jacquessmuts.exhaustive_lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import io.flatcircle.preferenceslint.ExhaustiveWhenDetector
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Tests for the [NamedArgumentsDetector] custom lint check.
 */
@RunWith(JUnit4::class)
class NamedArgumentsDetectorTest : LintDetectorTest() {

    override fun getIssues(): MutableList<Issue> =
        mutableListOf(ExhaustiveWhenDetector.ISSUE)

    override fun getDetector(): Detector = ExhaustiveWhenDetector()

    @Test
    fun testKotlin_expectPass() {
        lint()
            .allowMissingSdk()
            .files(
                kotlin(
                    """
package com.jacquessmuts.exhaustive_lint

fun main() {

    val input = "hello"

    when (input) {
        "hello" -> println("when hits")
        else -> println("elsey")
    }.let {  }
    
}
        """
                )
            )
            .run()
            .expectClean()
    }

    @Test
    fun testKotlin_expectFail() {
        lint()
            .allowMissingSdk()
            .files(
                kotlin(
                    """
package com.jacquessmuts.exhaustive_lint

fun main() {

    val input = "hello"

    when (input) {
        "hello" -> println("when hits")
    }
    
}
        """
                )
            )
            .run()
            .expect(
                """
src/com/jacquessmuts/exhaustive_lint/test.kt:8: Warning: Avoid using when statements. Consider adding .exhaustive [ExhaustiveWhenDetector]
    when (input) {
    ^
0 errors, 1 warnings
            """
            )
    }
}