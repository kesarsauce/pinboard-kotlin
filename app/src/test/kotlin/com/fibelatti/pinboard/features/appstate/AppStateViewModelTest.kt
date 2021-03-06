package com.fibelatti.pinboard.features.appstate

import com.fibelatti.core.archcomponents.extension.asLiveData
import com.fibelatti.core.archcomponents.test.extension.currentValueShouldBe
import com.fibelatti.core.archcomponents.test.extension.shouldNeverReceiveValues
import com.fibelatti.core.test.extension.mock
import com.fibelatti.core.test.extension.verifySuspend
import com.fibelatti.pinboard.BaseViewModelTest
import com.fibelatti.pinboard.allSealedSubclasses
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mockito

internal class AppStateViewModelTest : BaseViewModelTest() {

    private val mockAppStateRepository = mock<AppStateRepository>()

    private lateinit var appStateViewModel: AppStateViewModel

    @Test
    fun `WHEN getContent is called THEN repository content should be returned`() {
        // GIVEN
        val mockContent = mock<Content>()

        given(mockAppStateRepository.getContent())
            .willReturn(mockContent.asLiveData())

        appStateViewModel = AppStateViewModel(mockAppStateRepository)

        // THEN
        appStateViewModel.content.currentValueShouldBe(mockContent)
    }

    @Test
    fun `WHEN reset is called THEN repository should reset`() {
        appStateViewModel = AppStateViewModel(mockAppStateRepository)

        // WHEN
        appStateViewModel.reset()

        // THEN
        verify(mockAppStateRepository).reset()
    }

    @Test
    fun `WHEN runAction is called THEN repository should runAction`() {
        // GIVEN
        val mockAction = mock<Action>()

        appStateViewModel = AppStateViewModel(mockAppStateRepository)

        // WHEN
        appStateViewModel.runAction(mockAction)

        // THEN
        verifySuspend(mockAppStateRepository) { runAction(mockAction) }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class SpecificContentTypeTests {

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only post list content should be emitted to postListContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is PostListContent) {
                appStateViewModel.postListContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.postListContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only post detail content should be emitted to postDetailContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is PostDetailContent) {
                appStateViewModel.postDetailContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.postDetailContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only add post content should be emitted to addPostContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is AddPostContent) {
                appStateViewModel.addPostContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.addPostContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only edit post content should be emitted to editPostContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is EditPostContent) {
                appStateViewModel.editPostContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.editPostContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only search content should be emitted to searchContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is SearchContent) {
                appStateViewModel.searchContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.searchContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only tag list content should be emitted to tagListContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is TagListContent) {
                appStateViewModel.tagListContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.tagListContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only note list content should be emitted to noteListContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is NoteListContent) {
                appStateViewModel.noteListContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.noteListContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only note detail content should be emitted to noteDetailContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is NoteDetailContent) {
                appStateViewModel.noteDetailContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.noteDetailContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only popular posts content should be emitted to popularPostsContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is PopularPostsContent) {
                appStateViewModel.popularPostsContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.popularPostsContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only popular post details content should be emitted to popularPostDetailContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is PopularPostDetailContent) {
                appStateViewModel.popularPostDetailContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.popularPostDetailContent.shouldNeverReceiveValues()
            }
        }

        @ParameterizedTest
        @MethodSource("testCases")
        fun `Only user preferences content should be emitted to userPreferencesContent`(content: Content) {
            // GIVEN
            given(mockAppStateRepository.getContent())
                .willReturn(content.asLiveData())

            appStateViewModel = AppStateViewModel(mockAppStateRepository)

            // THEN
            if (content is UserPreferencesContent) {
                appStateViewModel.userPreferencesContent.currentValueShouldBe(content)
            } else {
                appStateViewModel.userPreferencesContent.shouldNeverReceiveValues()
            }
        }

        fun testCases(): List<Content> = mutableListOf<Content>().apply {
            Content::class.allSealedSubclasses
                .map { it.objectInstance ?: Mockito.mock(it.javaObjectType) }
                .forEach { add(it) }
        }
    }
}
