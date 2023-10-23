package com.test.navigation.presentation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import androidx.wear.widget.WearableRecyclerView

@Composable
fun RecyclerColumn(
    modifier: Modifier = Modifier,
    items: List<String>,
    onSelected: (Int, String) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WearableRecyclerView(context).apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val snapHelper = LinearSnapHelper().also {
                    it.attachToRecyclerView(this)
                }

                adapter = TestRecyclerAdapter(items)
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    private var selectedPos = -1
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = layoutManager ?: return
                        val centerView = snapHelper.findSnapView(layoutManager) ?: return
                        val pos = layoutManager.getPosition(centerView)
                        if (selectedPos != pos) {
                            selectedPos = pos
                            onSelected(pos, items[pos])
                        }
                    }
                })
            }
        })
}

@Preview(device = Devices.WEAR_OS_LARGE_ROUND, showSystemUi = true)
@Composable
fun PreviewRecyclerColumn() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        RecyclerColumn(
            modifier = Modifier.align(Alignment.Center),
            items = listOf("1", "2", "3", "4", "5")) { index, item ->

        }
    }

}