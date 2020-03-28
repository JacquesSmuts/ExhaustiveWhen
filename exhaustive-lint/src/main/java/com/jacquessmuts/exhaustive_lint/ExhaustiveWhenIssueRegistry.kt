package com.jacquessmuts.exhaustive_lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import io.flatcircle.preferenceslint.ExhaustiveWhenDetector

class ExhaustiveWhenIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(ExhaustiveWhenDetector.ISSUE)

    override val api: Int
        get() = CURRENT_API
}