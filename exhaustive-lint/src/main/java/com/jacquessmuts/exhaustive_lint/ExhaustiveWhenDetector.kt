package io.flatcircle.preferenceslint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.openapi.components.ServiceManager
import org.jetbrains.kotlin.backend.common.CodegenUtil
import org.jetbrains.kotlin.psi.KtWhenExpression
import org.jetbrains.kotlin.resolve.bindingContextUtil.isUsedAsExpression
import org.jetbrains.uast.*
import org.jetbrains.uast.kotlin.KotlinUastResolveProviderService
import org.jetbrains.uast.util.isAssignment

class ExhaustiveWhenDetector : Detector(), SourceCodeScanner {

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(USwitchExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return ExhaustiveWhenHandler(context)
    }

    internal class ExhaustiveWhenHandler(private val context: JavaContext) : UElementHandler() {

        override fun visitSwitchExpression(node: USwitchExpression) {

            val sourcePsi = node.sourcePsi
            if (sourcePsi is KtWhenExpression) {
                val resolverProvider = ServiceManager.getService(sourcePsi.project, KotlinUastResolveProviderService::class.java)
                val bindingContext = resolverProvider.getBindingContext(sourcePsi)
                val isExpression = sourcePsi.isUsedAsExpression(bindingContext)

                if (!isExpression) {
                    context.report(
                        issue = ISSUE,
                        scope = node as UElement,
                        location = context.getLocation(node as UElement),
                        message = "Avoid using when statements. Consider adding .exhaustive"
                    )
                }
            }
        }
    }

    companion object {
        val ISSUE = Issue.create(
            id = "ExhaustiveWhenDetector",
            briefDescription = "Warns against using when statements instead of when expressions",
            explanation = "This project is using the ExhaustiveWhen library, which means all usages of when should be exhaustive",
            category = Category.CORRECTNESS,
            priority = 6,
            severity = Severity.WARNING,
            implementation = Implementation(
                ExhaustiveWhenDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )
    }

}
