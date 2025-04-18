package org.example.krona

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import android.net.Uri
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppWithSplash()
        }
    }
}

@Composable
actual fun PlayStartupVideo(onVideoEnd: () -> Unit) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF000000)

    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://${context.packageName}/raw/krona")
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    useController = false
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }

    exoPlayer.addListener(object : com.google.android.exoplayer2.Player.Listener {
        override fun onPlaybackStateChanged(state: Int) {
            if (state == com.google.android.exoplayer2.Player.STATE_ENDED) {
                onVideoEnd()
                val statusBarColor = Color(0xFF0F1C2E)

                systemUiController.setStatusBarColor(
                    color = statusBarColor,
                    darkIcons = false
                )
            }
        }
    })
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppWithSplash()
}