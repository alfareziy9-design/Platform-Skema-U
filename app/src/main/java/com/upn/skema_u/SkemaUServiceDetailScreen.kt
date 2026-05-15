package com.upn.skema_u

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

private val ServiceDetailBackground = Color(0xFFF6FBEE)
private val ServiceDetailSurface = Color.White
private val ServiceDetailGreen = Color(0xFF106E09)
private val ServiceDetailText = Color(0xFF181D15)
private val ServiceDetailBody = Color(0xFF404A3B)
private val ServiceDetailMuted = Color(0xFF707A6A)
private val ServiceDetailBorder = Color(0xFFBFCAB7)
private val ServiceDetailPanel = Color(0xFFEAF0E2)
private val ServiceDetailReview = Color(0x4DC3EBB5)
private val ServiceDetailStar = Color(0xFFF59E0B)

@Composable
fun SkemaUServiceDetailScreen(
    modifier: Modifier = Modifier,
    @DrawableRes profileImageRes: Int = R.drawable.farah,
    @DrawableRes containerImageRes: Int = R.drawable.container,
    @DrawableRes portfolioOneImageRes: Int = R.drawable.logo,
    @DrawableRes portfolioTwoImageRes: Int = R.drawable.portofolio_2
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ServiceDetailBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ServiceDetailBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 105.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ServiceHero(containerImageRes)
                ServiceIdentity(profileImageRes)
                PortfolioSection(portfolioOneImageRes, portfolioTwoImageRes)
                ServiceDescription()
                ReviewRibbon()
                Spacer(Modifier.height(28.dp))
            }

            ServiceDetailTopBar(modifier = Modifier.align(Alignment.TopCenter))
            ServiceDetailBottomBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ServiceDetailTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ServiceDetailSurface)
            .border(1.dp, ServiceDetailBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            BackIcon(ServiceDetailBody, Modifier.size(20.dp))
            Text(
                text = "Skema-U",
                color = ServiceDetailGreen,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        SearchIcon(ServiceDetailGreen, Modifier.size(18.dp))
    }
}

@Composable
private fun ServiceHero(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .background(ServiceDetailPanel)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Gambar layanan",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(ServiceDetailGreen))
            Box(Modifier.size(8.dp).clip(CircleShape).background(ServiceDetailSurface.copy(alpha = 0.50f)))
            Box(Modifier.size(8.dp).clip(CircleShape).background(ServiceDetailSurface.copy(alpha = 0.50f)))
        }
    }
}

@Composable
private fun ServiceIdentity(@DrawableRes profileImageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "IDENTITAS & BRANDING",
                    color = ServiceDetailGreen,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.6.sp
                )
                Text(
                    text = "Desain Logo\nProfesional",
                    color = ServiceDetailText,
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            HeartIcon(ServiceDetailMuted, Modifier.width(20.dp).height(19.dp))
        }
        CreatorCard(profileImageRes)
    }
}

@Composable
private fun CreatorCard(@DrawableRes profileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ServiceDetailSurface)
            .border(1.dp, ServiceDetailBorder, RoundedCornerShape(12.dp))
            .padding(17.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(profileImageRes),
            contentDescription = "Foto kreator",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(1.dp, ServiceDetailBorder, CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(1f)) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Novia Farah",
                    color = ServiceDetailText,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                VerifiedIcon(ServiceDetailGreen, Modifier.size(13.dp))
            }
            Text(
                text = "Desain Komunikasi\nVisual, UI",
                color = ServiceDetailBody,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                StarIcon(ServiceDetailStar, Modifier.size(12.dp))
                Text(
                    text = "4.9",
                    color = ServiceDetailStar,
                    fontSize = 11.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = "124 proyek",
                color = ServiceDetailMuted,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
private fun PortfolioSection(
    @DrawableRes firstImageRes: Int,
    @DrawableRes secondImageRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Portofolio Unggulan",
                color = ServiceDetailText,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "LIHAT SEMUA",
                color = ServiceDetailGreen,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.6.sp
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PortfolioImage(firstImageRes, Modifier.weight(1f))
            PortfolioImage(secondImageRes, Modifier.weight(1f))
        }
    }
}

@Composable
private fun PortfolioImage(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = "Portofolio",
        modifier = modifier
            .height(127.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ServiceDetailPanel)
            .border(1.dp, ServiceDetailBorder, RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ServiceDescription() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Detail Layanan",
            color = ServiceDetailText,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Ubah identitas bisnis Anda dengan logo\n" +
                "kustom berkonsep tinggi yang dirancang oleh\n" +
                "mahasiswa desain visual terbaik. Layanan ini\n" +
                "mencakup tiga konsep awal, revisi tanpa\n" +
                "batas untuk arah yang dipilih, dan panduan\n" +
                "gaya merek lengkap termasuk tipografi dan\n" +
                "palet warna.",
            color = ServiceDetailBody,
            fontSize = 16.sp,
            lineHeight = 26.sp
        )
        Column(
            modifier = Modifier.padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BenefitRow("Pengerjaan 3 - 5 Hari") { ClockIcon(ServiceDetailGreen, Modifier.size(20.dp)) }
            BenefitRow("Format Vektor (AI, SVG, PDF)") { LinkIcon(ServiceDetailGreen, Modifier.width(19.dp).height(18.dp)) }
            BenefitRow("Hak Kepemilikan Penuh") { ShieldIcon(ServiceDetailGreen, Modifier.width(16.dp).height(20.dp)) }
        }
    }
}

@Composable
private fun BenefitRow(text: String, icon: @Composable () -> Unit) {
    Row(
        modifier = Modifier.height(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = text,
            color = ServiceDetailBody,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun ReviewRibbon() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ServiceDetailReview)
            .border(1.dp, Color(0xFFC3EBB5))
            .padding(horizontal = 20.dp, vertical = 25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Umpan Balik Klien",
                color = ServiceDetailText,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                Box(Modifier.size(24.dp).clip(CircleShape).background(Color(0xFFE2E8F0)).border(2.dp, ServiceDetailSurface, CircleShape))
                Box(Modifier.size(24.dp).clip(CircleShape).background(Color(0xFFCBD5E1)).border(2.dp, ServiceDetailSurface, CircleShape))
                Box(Modifier.size(24.dp).clip(CircleShape).background(Color(0xFF94A3B8)).border(2.dp, ServiceDetailSurface, CircleShape))
            }
        }
        Text(
            text = "\"Bekerja dengan Novia adalah pengalaman yang\n" +
                "luar biasa. Logo yang dia berikan untuk kedai kopi\n" +
                "saya melampaui ekspektasi saya dalam hal\n" +
                "kreativitas maupun profesionalisme.\"",
            color = ServiceDetailBody,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "- Budi, Pemilik Kopi Kampus",
            color = ServiceDetailGreen,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.6.sp
        )
    }
}

@Composable
private fun ServiceDetailBottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ServiceDetailSurface)
            .border(1.dp, ServiceDetailBorder)
            .padding(start = 20.dp, end = 20.dp, top = 17.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "MULAI DARI",
                color = ServiceDetailMuted,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.6.sp
            )
            Text(
                text = "Rp 15.000",
                color = ServiceDetailGreen,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(8.dp), ambientColor = Color(0x33106E09), spotColor = Color(0x33106E09))
                .clip(RoundedCornerShape(8.dp))
                .background(ServiceDetailGreen)
                .padding(horizontal = 32.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Pesan Sekarang",
                color = ServiceDetailSurface,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable
private fun BackIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.8.dp.toPx()

    drawLine(
        color = color,
        start = Offset(size.width * 0.78f, size.height * 0.50f),
        end = Offset(size.width * 0.20f, size.height * 0.50f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.20f, size.height * 0.50f),
        end = Offset(size.width * 0.46f, size.height * 0.24f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.20f, size.height * 0.50f),
        end = Offset(size.width * 0.46f, size.height * 0.76f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}


@Composable
private fun SearchIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.8.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round)

    drawCircle(
        color = color,
        radius = size.minDimension * 0.28f,
        center = Offset(size.width * 0.42f, size.height * 0.42f),
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.63f, size.height * 0.63f),
        end = Offset(size.width * 0.84f, size.height * 0.84f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}


@Composable private fun HeartIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val path = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.86f)
        cubicTo(size.width * 0.10f, size.height * 0.58f, size.width * 0.05f, size.height * 0.18f, size.width * 0.32f, size.height * 0.16f)
        cubicTo(size.width * 0.43f, size.height * 0.15f, size.width * 0.49f, size.height * 0.22f, size.width * 0.50f, size.height * 0.30f)
        cubicTo(size.width * 0.58f, size.height * 0.16f, size.width * 0.85f, size.height * 0.12f, size.width * 0.94f, size.height * 0.36f)
        cubicTo(size.width * 1.02f, size.height * 0.58f, size.width * 0.78f, size.height * 0.72f, size.width * 0.50f, size.height * 0.86f)
    }
    drawPath(path, color, style = stroke)
}

@Composable private fun VerifiedIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, size.minDimension * 0.45f, center = Offset(size.width / 2f, size.height / 2f))
    drawLine(
        color = ServiceDetailSurface,
        start = Offset(size.width * 0.30f, size.height * 0.50f),
        end = Offset(size.width * 0.45f, size.height * 0.64f),
        strokeWidth = 1.3.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = ServiceDetailSurface,
        start = Offset(size.width * 0.45f, size.height * 0.64f),
        end = Offset(size.width * 0.72f, size.height * 0.36f),
        strokeWidth = 1.3.dp.toPx(),
        cap = StrokeCap.Round
    )
}

@Composable private fun StarIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val path = Path()
    val center = Offset(size.width / 2f, size.height / 2f)
    repeat(10) { i ->
        val radius = if (i % 2 == 0) size.minDimension * 0.46f else size.minDimension * 0.20f
        val angle = Math.toRadians((i * 36 - 90).toDouble())
        val point = Offset(
            center.x + cos(angle).toFloat() * radius,
            center.y + sin(angle).toFloat() * radius
        )
        if (i == 0) path.moveTo(point.x, point.y) else path.lineTo(point.x, point.y)
    }
    path.close()
    drawPath(path, color)
}

@Composable
private fun ClockIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.6.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawCircle(
        color = color,
        radius = size.minDimension * 0.40f,
        center = Offset(size.width / 2f, size.height / 2f),
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.50f, size.height * 0.28f),
        end = Offset(size.width * 0.50f, size.height * 0.52f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.50f, size.height * 0.52f),
        end = Offset(size.width * 0.66f, size.height * 0.62f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
private fun LinkIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.7.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawLine(
        color = color,
        start = Offset(size.width * 0.30f, size.height * 0.66f),
        end = Offset(size.width * 0.70f, size.height * 0.26f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.08f, size.height * 0.46f),
        size = Size(size.width * 0.38f, size.height * 0.38f),
        cornerRadius = CornerRadius(4.dp.toPx()),
        style = stroke
    )

    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.54f, size.height * 0.14f),
        size = Size(size.width * 0.38f, size.height * 0.38f),
        cornerRadius = CornerRadius(4.dp.toPx()),
        style = stroke
    )
}


@Composable private fun ShieldIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val path = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.06f)
        lineTo(size.width * 0.86f, size.height * 0.22f)
        lineTo(size.width * 0.78f, size.height * 0.68f)
        lineTo(size.width * 0.50f, size.height * 0.94f)
        lineTo(size.width * 0.22f, size.height * 0.68f)
        lineTo(size.width * 0.14f, size.height * 0.22f)
        close()
    }
    drawPath(path, color, style = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1466)
@Composable
private fun SkemaUServiceDetailScreenPreview() {
    SkemaUServiceDetailScreen()
}
