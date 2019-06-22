package com.fibelatti.pinboard.features.appstate

import com.fibelatti.pinboard.features.posts.domain.model.Post
import com.fibelatti.pinboard.features.tags.domain.model.Tag

// region Action
sealed class Action

// region NavigationAction
sealed class NavigationAction : Action()

object NavigateBack : NavigationAction()
sealed class ViewCategory : NavigationAction()
data class ViewPost(val post: Post) : NavigationAction()
object ViewSearch : NavigationAction()
object AddPost : NavigationAction()

// region ViewCategory
object All : ViewCategory()
object Recent : ViewCategory()
object Public : ViewCategory()
object Private : ViewCategory()
object Unread : ViewCategory()
object Untagged : ViewCategory()
object AllTags : ViewCategory()
data class PostsForTag(val tagName: String) : ViewCategory()
// endregion
// endregion

// region PostAction
sealed class PostAction : Action()

data class SetPosts(val posts: List<Post>) : PostAction()
object ToggleSorting : PostAction()
// endregion

// region SearchAction
sealed class SearchAction : Action()

data class SetSearchTags(val tags: List<Tag>) : SearchAction()
data class AddSearchTag(val tag: Tag) : SearchAction()
data class RemoveSearchTag(val tag: Tag) : SearchAction()
data class Search(val term: String) : SearchAction()
object ClearSearch : SearchAction()
// endregion
// endregion
