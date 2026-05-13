package com.example.serinidadedesons.Screens

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.serinidadedesons.R

@Composable
fun EstudarScreen(navController: NavController) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    
    // ESTADOS DO ÁUDIO
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var somAtual by remember { mutableStateOf<Int?>(null) }

    // ESTADOS DOS MENUS
    var isFocoExpanded by remember { mutableStateOf(false) }
    var isNaturezaExpanded by remember { mutableStateOf(false) }
    var isRuidosExpanded by remember { mutableStateOf(false) }

    fun toggleAudio(audioResId: Int) {
        if (somAtual == audioResId && isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, audioResId).apply {
                isLooping = true
                start()
            }
            somAtual = audioResId
            isPlaying = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        bottomBar = { CustomBottomBar(navController) },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {

            // Botão de Voltar
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = IconGreenColor,
                    modifier = Modifier.size(40.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagem Principal - Formato Pílula
                Image(
                    painter = painterResource(id = R.drawable.estudar_serenidade),
                    contentDescription = "Imagem de pessoa estudando",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(130.dp)
                        .clip(RoundedCornerShape(100.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Logo Menor
                Image(
                    painter = painterResource(id = R.drawable.logo_serenidade),
                    contentDescription = "Logo Serenidade",
                    modifier = Modifier.size(230.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // GRUPO 1: Música para Foco
                SoundListItem(
                    title = "Música para Foco",
                    icon = if (isFocoExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isFocoExpanded = !isFocoExpanded }
                )

                AnimatedVisibility(visible = isFocoExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "Leitura",
                            iconDrawable = if (isPlaying && somAtual == R.raw.musica_leitura) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.musica_leitura) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.musica_leitura) }
                        )
                        SoundListItem(
                            title = "Foco Ambiente",
                            iconDrawable = if (isPlaying && somAtual == R.raw.musica_ambiente) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.musica_ambiente) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.musica_ambiente) }
                        )
                    }
                }

                // GRUPO 2: Sons da Natureza
                SoundListItem(
                    title = "Sons da Natureza",
                    icon = if (isNaturezaExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                    iconTint = IconGreenColor,
                    onClick = { isNaturezaExpanded = !isNaturezaExpanded }
                )

                AnimatedVisibility(visible = isNaturezaExpanded) {
                    Column(modifier = Modifier.padding(start = 24.dp)) {
                        SoundListItem(
                            title = "Floresta Norturna",
                            iconDrawable = if (isPlaying && somAtual == R.raw.natureza_noite) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.natureza_noite) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.natureza_noite) }
                        )

                        SoundListItem(
                            title = "Floresta Clara",
                            iconDrawable = if (isPlaying && somAtual == R.raw.natureza_dia) R.drawable.pause else null,
                            icon = if (isPlaying && somAtual == R.raw.natureza_dia) null else Icons.Rounded.PlayArrow,
                            iconTint = IconGreenColor,
                            onClick = { toggleAudio(R.raw.natureza_dia) }
                        )

                    }
                }
                
                // Espaçador final para garantir que o conteúdo não fique colado na barra inferior
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
