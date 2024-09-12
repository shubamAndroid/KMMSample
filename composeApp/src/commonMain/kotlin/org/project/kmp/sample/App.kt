package org.project.kmp.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.jetbrains.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    val viewModel = viewModel { MainVm() }
    val context= LocalPlatformContext.current
    setSingletonImageLoaderFactory {
        getAsyncImageLoader(context)
    }
    MaterialTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.images) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalPlatformContext.current)
                            .data(it)
                            .placeholderMemoryCacheKey(it)
                            .build() ,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
fun getAsyncImageLoader(context: PlatformContext)=
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()