package com.vam.jetpackcomposehomework.homework

object DummyData {
    val items: List<OverflowItem> =
        listOf(
            OverflowItem(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
           isOverflowing = true
                ),
            OverflowItem(text = "Lorem ipsum dolor sit"),
            OverflowItem(text = "Lorem ipsum dolor sit amet consectetur adipiscing elit."),
            OverflowItem(text = "Lorem ipsum dolor sit amet consectetur adipiscing elit. Dolor sit amet consectetur adipiscing elit quisque faucibus.", isOverflowing = true)
        )
}