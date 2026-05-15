package com.upn.skema_u

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryGreen = Color(0xFF0B7A1B)
private val SecondaryGreen = Color(0xFF4F7F4E)
private val LightGreen = Color(0xFFEAF8E8)
private val BorderGreen = Color(0xFFE3ECE1)

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        SplashGlowBackground()

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-20).dp)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LogoCard()

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Skema-U",
                color = PrimaryGreen,
                fontSize = 31.sp,
                lineHeight = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Temukan Jasa Kreatif Mahasiswa",
                color = SecondaryGreen,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(34.dp))

            EducationDivider()
        }

        DotsIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 34.dp),
            activeIndex = 1,
        )
    }
}

@Composable
private fun SplashGlowBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        GlowBlob(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-112).dp, y = (-104).dp)
                .size(292.dp),
        )

        GlowBlob(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-18).dp, y = 34.dp)
                .size(210.dp),
            alpha = 0.52f,
        )

        GlowBlob(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 118.dp, y = 64.dp)
                .size(360.dp),
            alpha = 0.62f,
        )
    }
}

@Composable
private fun GlowBlob(
    modifier: Modifier = Modifier,
    alpha: Float = 0.74f,
) {
    Box(
        modifier = modifier
            .blur(46.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        LightGreen.copy(alpha = alpha),
                        LightGreen.copy(alpha = 0.0f),
                    ),
                ),
                shape = CircleShape,
            ),
    )
}

@Composable
private fun LogoCard(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .size(120.dp)
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(18.dp),
                spotColor = PrimaryGreen.copy(alpha = 0.16f),
                ambientColor = PrimaryGreen.copy(alpha = 0.08f),
            )
            .border(
                width = 0.8.dp,
                color = BorderGreen,
                shape = RoundedCornerShape(18.dp),
            ),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Skema-U Logo",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Inside,
            )

            Spacer(modifier = Modifier.height(2.dp))

        }
    }
}

@Composable
private fun EducationDivider(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        DividerLine()

        Canvas(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(16.dp),
        ) {
            drawGraduateCap(
                center = Offset(size.width / 2f, size.height * 0.46f),
                width = size.width * 0.70f,
                color = PrimaryGreen,
                strokeWidth = 1.25.dp.toPx(),
            )
        }

        DividerLine()
    }
}

@Composable
private fun DividerLine() {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(1.dp)
            .background(Color(0xFFD4DED1)),
    )
}

@Composable
private fun DotsIndicator(
    activeIndex: Int,
    modifier: Modifier = Modifier,
    count: Int = 3,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == activeIndex) 6.dp else 5.dp)
                    .background(
                        color = if (index == activeIndex) PrimaryGreen else Color(0xFFA7D4A5),
                        shape = CircleShape,
                    ),
            )
        }
    }
}

private fun DrawScope.drawGraduateCap(
    center: Offset,
    width: Float,
    color: Color,
    strokeWidth: Float,
) {
    val capHeight = width * 0.28f
    val top = center.y - capHeight * 0.55f
    val left = center.x - width / 2f
    val right = center.x + width / 2f
    val midY = center.y

    val diamond = Path().apply {
        moveTo(center.x, top)
        lineTo(right, midY)
        lineTo(center.x, midY + capHeight)
        lineTo(left, midY)
        close()
    }

    drawPath(
        path = diamond,
        color = color,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
    )
    drawArc(
        color = color,
        startAngle = 0f,
        sweepAngle = 180f,
        useCenter = false,
        topLeft = Offset(center.x - width * 0.22f, midY + capHeight * 0.10f),
        size = Size(width * 0.44f, capHeight * 0.70f),
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
    )
    drawLine(
        color = color,
        start = Offset(right, midY),
        end = Offset(right + width * 0.18f, midY + capHeight * 0.24f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
    drawCircle(
        color = color,
        radius = strokeWidth * 0.95f,
        center = Offset(right + width * 0.18f, midY + capHeight * 0.30f),
    )
}

@Preview(
    name = "Splash Screen",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 390,
    heightDp = 844,
)
@Composable
private fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}
