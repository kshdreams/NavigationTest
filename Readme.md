purpose of this repository is reproduce some scrolling problem with below combination 

- Compose AndroidView + RecyclerView + SwipeDismissableNavHost


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

If you navigate with popupBackStack() then scroll works well....

```
nav.popBackStack()
nav.navigate("B")
```

This problem does not reproduce when use NavHost. 
```
@Composable
fun NavTest() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "A") {
        composable("A") {
            ScreenA(nav = nav)
        }
        composable("B") {
            ScreenB()
        }
    }
}
```
