/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.test.navigation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = rememberSwipeDismissableNavController()
            SwipeDismissableNavHost(
                navController = nav,
                startDestination = "first" // "second" working fine, but "first" does not work
            ) {
                composable("first") {
                    BoxWithConstraints(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Button(
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {
                                //                    nav.popBackStack()
                                nav.navigate("second")
                            }
                        ) {
                            Text("click")
                        }
                    }

                }
                composable("second") {
                    BoxWithConstraints(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        RecyclerColumn(
                            modifier = Modifier.align(Companion.Center),
                            items = listOf(
                                "one",
                                "two",
                                "three",
                                "four",
                                "five",
                                "six",
                                "seven",
                                "eight",
                                "nine",
                                "ten"
                            )
                        ) { index, item ->

                        }
                    }
                }
            }
        }
    }
}
