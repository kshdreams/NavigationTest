purpose of this repository is reproduce some scrolling problem with below combination 

- compose + AndroidView + RecyclerView + navigation


there are two screens. "A" and "B" 
If you navigate from A to B, scroll does not work in B screen.
If you launch screen B directly, scroll works well.

```
        setContent {
            val nav = rememberSwipeDismissableNavController()
            SwipeDismissableNavHost(
                navController = nav,
                startDestination = "A" // "B" working fine, but "A" does not work
            ) {
                composable("A") {
                    // Some Screen A
                    // navigate to Screen B when click some button
                }
                composable("B") {
                    // Some Screen B using AndroidView + RecyclerView
                    AndroidView(factory = { context ->
                        RecyclerView(context)
                    })
                }
            }
        }
```
