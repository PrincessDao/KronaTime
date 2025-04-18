package org.example.krona


import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.UIKitView
import platform.AVFoundation.AVPlayer
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL
import platform.Foundation.NSBundle

fun MainViewController() = ComposeUIViewController { AppWithSplash() }

@Composable
actual fun PlayStartupVideo(onVideoEnd: () -> Unit) {
    UIKitView(
        factory = {
            val playerController = AVPlayerViewController()
            val videoPath = NSBundle.mainBundle.pathForResource("startup_video", "mp4") ?: ""
            val player = AVPlayer(NSURL.fileURLWithPath(videoPath))

            playerController.player = player
            player.play()

            player.actionAtItemEnd = {
                onVideoEnd()
            }

            playerController.view
        }
    )
}