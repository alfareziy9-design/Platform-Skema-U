package com.upn.skema_u

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

private val CatalogBackground = Color(0xFFF6FBEE)
private val CatalogSurface = Color.White
private val CatalogSurfaceMuted = Color(0xFFEAF0E2)
private val CatalogGreen = Color(0xFF106E09)
private val CatalogGreenDeep = Color(0xFF45673C)
private val CatalogTextPrimary = Color(0xFF181D15)
private val CatalogTextSecondary = Color(0xFF404A3B)
private val CatalogMuted = Color(0xFF707A6A)
private val CatalogPlaceholder = Color(0xFF6B7280)
private val CatalogBorder = Color(0xFFBFCAB7)
private val CatalogStar = Color(0xFFFFA000)

data class CatalogServiceItem(
    val badge: String?,
    val category: String,
    val title: String,
    val description: String,
    val rating: String,
    val provider: String,
    val price: String,
    @DrawableRes val coverRes: Int,
    @DrawableRes val profileRes: Int
)

@Composable
fun SkemaUCatalogScreen(
    modifier: Modifier = Modifier,
    @DrawableRes userProfileImageRes: Int = R.drawable.profil_client,
    services: List<CatalogServiceItem> = defaultCatalogServices()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CatalogBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 430.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(CatalogBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SearchAndFilters()
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    services.forEach { CatalogServiceCard(it) }
                }
                CatalogTrustRibbon()
            }

            CatalogTopAppBar(userProfileImageRes = userProfileImageRes)
            CatalogBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun CatalogTopAppBar(@DrawableRes userProfileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(CatalogSurface)
            .border(1.dp, CatalogBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(userProfileImageRes),
                contentDescription = "Foto profil",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(CatalogSurfaceMuted)
                    .border(1.dp, CatalogBorder, CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Skema-U",
                color = CatalogGreen,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            SearchIcon(CatalogTextSecondary, Modifier.size(18.dp))
            BellIcon(CatalogTextSecondary, Modifier.size(18.dp))
        }
    }
}

@Composable
private fun SearchAndFilters() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CatalogSurface)
                .border(1.dp, CatalogBorder, RoundedCornerShape(8.dp))
                .padding(start = 16.dp, end = 17.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchIcon(CatalogMuted, Modifier.size(18.dp))
            Text(
                text = "Cari layanan...",
                color = CatalogPlaceholder,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            FilterChip("Semua Layanan", selected = true)
            FilterChip("Desain Logo")
            FilterChip("Edit Video")
            FilterChip("Pengembangan")
        }
    }
}

@Composable
private fun FilterChip(text: String, selected: Boolean = false) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .clip(CircleShape)
            .background(if (selected) CatalogGreen else CatalogSurface)
            .then(if (selected) Modifier else Modifier.border(1.dp, CatalogBorder, CircleShape))
            .padding(horizontal = if (selected) 16.dp else 17.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) CatalogSurface else CatalogTextSecondary,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CatalogServiceCard(item: CatalogServiceItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CatalogSurface)
            .border(1.dp, CatalogBorder, RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Image(
                painter = painterResource(item.coverRes),
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            item.badge?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 12.dp, top = 12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(CatalogGreen)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = it,
                        color = CatalogSurface,
                        fontSize = 11.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = item.category,
                        color = CatalogGreen,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.title,
                        color = CatalogTextPrimary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(CatalogSurfaceMuted)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarIcon(CatalogStar, Modifier.size(12.dp))
                    Text(
                        text = item.rating,
                        color = CatalogTextPrimary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                }
            }

            Text(
                text = item.description,
                color = CatalogTextSecondary,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Transparent)
                    .padding(top = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(item.profileRes),
                        contentDescription = item.provider,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(CatalogSurfaceMuted),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = item.provider,
                        color = CatalogTextPrimary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "MULAI DARI",
                        color = CatalogGreenDeep,
                        fontSize = 10.sp,
                        lineHeight = 15.sp
                    )
                    Text(
                        text = item.price,
                        color = CatalogGreen,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogTrustRibbon() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CatalogGreen)
            .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "TERPERCAYA BAGI UMKM",
                color = CatalogSurface.copy(alpha = 0.80f),
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Lebih dari 1,200 proyek sukses\ndiselesaikan bulan ini.",
                color = CatalogSurface,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        CheckCircleOutlineIcon(CatalogSurface, Modifier.size(22.dp))
    }
}

@Composable
private fun CatalogBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(CatalogSurface.copy(alpha = 0.95f))
            .border(1.dp, CatalogBorder)
            .shadow(8.dp, ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
            .padding(start = 20.dp, end = 20.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CatalogNavItem("BERANDA", selected = false) { HomeIcon(CatalogMuted, Modifier.size(18.dp)) }
        CatalogNavItem("KATALOG", selected = true) { GridIcon(CatalogGreen, Modifier.size(18.dp)) }
        CatalogNavItem("PESAN", selected = false) { MessageIcon(CatalogMuted, Modifier.size(20.dp)) }
        CatalogNavItem("STATUS", selected = false) { ClipboardIcon(CatalogMuted, Modifier.size(20.dp)) }
        CatalogNavItem("PROFIL", selected = false) { UserIcon(CatalogMuted, Modifier.size(18.dp)) }
    }
}

@Composable
private fun CatalogNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) CatalogGreen else CatalogMuted,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

private fun defaultCatalogServices(): List<CatalogServiceItem> {
    return listOf(
        CatalogServiceItem(
            badge = "Keahlian Terverifikasi",
            category = "DESAIN GRAFIS",
            title = "Desain Logo Minimalis",
            description = "Logo vektor berkualitas tinggi yang\ndisesuaikan untuk startup teknologi...",
            rating = "4.9",
            provider = "Novia Farah",
            price = "Rp 15k",
            coverRes = R.drawable.container_1,
            profileRes = R.drawable.farah
        ),
        CatalogServiceItem(
            badge = "Penjual Pro",
            category = "VIDEO & ANIMASI",
            title = "Video Produk Sinematik",
            description = "Tingkatkan merek Anda dengan etalase\nproduk 4K dan iklan media sosial yang...",
            rating = "5.0",
            provider = "Zerlina Anggun",
            price = "Rp 15k",
            coverRes = R.drawable.container_2,
            profileRes = R.drawable.profil_student_2
        ),
        CatalogServiceItem(
            badge = "Peringkat Teratas",
            category = "PEMROGRAMAN",
            title = "Aplikasi Mobile MVP",
            description = "Aplikasi React Native atau Flutter skalabel\nyang dibuat untuk performa lintas...",
            rating = "4.8",
            provider = "Yusuf Alfarezi",
            price = "Rp 15k",
            coverRes = R.drawable.container_3,
            profileRes = R.drawable.profil_student_3
        ),
        CatalogServiceItem(
            badge = null,
            category = "PEMASARAN",
            title = "Strategi Media Sosial",
            description = "Rencana pertumbuhan digital lengkap\nuntuk bisnis lokal termasuk kalender...",
            rating = "4.7",
            provider = "Rafli Saputra",
            price = "Rp 15k",
            coverRes = R.drawable.container_4,
            profileRes = R.drawable.profil_student_4
        )
    )
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable
private fun SearchIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.7.dp.toPx()

    drawCircle(
        color = color,
        radius = size.minDimension * 0.30f,
        center = Offset(size.width * 0.44f, size.height * 0.42f),
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.64f, size.height * 0.64f),
        end = Offset(size.width * 0.82f, size.height * 0.82f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
private fun BellIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.6.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    val bellPath = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.18f)

        cubicTo(
            size.width * 0.34f, size.height * 0.18f,
            size.width * 0.24f, size.height * 0.32f,
            size.width * 0.24f, size.height * 0.48f
        )

        lineTo(size.width * 0.24f, size.height * 0.62f)

        cubicTo(
            size.width * 0.24f, size.height * 0.70f,
            size.width * 0.18f, size.height * 0.72f,
            size.width * 0.18f, size.height * 0.76f
        )

        lineTo(size.width * 0.82f, size.height * 0.76f)

        cubicTo(
            size.width * 0.82f, size.height * 0.72f,
            size.width * 0.76f, size.height * 0.70f,
            size.width * 0.76f, size.height * 0.62f
        )

        lineTo(size.width * 0.76f, size.height * 0.48f)

        cubicTo(
            size.width * 0.76f, size.height * 0.32f,
            size.width * 0.66f, size.height * 0.18f,
            size.width * 0.50f, size.height * 0.18f
        )
    }

    drawPath(
        path = bellPath,
        color = color,
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.42f, size.height * 0.84f),
        end = Offset(size.width * 0.58f, size.height * 0.84f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawCircle(
        color = color,
        radius = 1.5.dp.toPx(),
        center = Offset(size.width / 2f, size.height * 0.84f)
    )
}


@Composable private fun StarIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path()
    val cx = size.width / 2f
    val cy = size.height / 2f
    val outer = size.minDimension * 0.48f
    val inner = outer * 0.48f
    repeat(10) { i ->
        val angle = Math.toRadians((i * 36 - 90).toDouble())
        val r = if (i % 2 == 0) outer else inner
        val x = cx + cos(angle).toFloat() * r
        val y = cy + sin(angle).toFloat() * r
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
    }
    p.close()
    drawPath(p, color)
}

@Composable private fun CheckCircleOutlineIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.5.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.5.dp.toPx(), StrokeCap.Round)
}

@Composable private fun HomeIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path().apply {
        moveTo(size.width * 0.15f, size.height * 0.48f)
        lineTo(size.width * 0.5f, size.height * 0.18f)
        lineTo(size.width * 0.85f, size.height * 0.48f)
        lineTo(size.width * 0.78f, size.height * 0.86f)
        lineTo(size.width * 0.22f, size.height * 0.86f)
        close()
    }
    drawPath(p, color, style = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

@Composable private fun GridIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(2) { x ->
        repeat(2) { y ->
            drawRect(color, Offset((3 + x * 7).dp.toPx(), (3 + y * 7).dp.toPx()), Size(4.dp.toPx(), 4.dp.toPx()))
        }
    }
}

@Composable private fun MessageIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()),
        CornerRadius(2.dp.toPx()), style = Stroke(1.5.dp.toPx()))
}

@Composable
private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.5.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawRoundRect(
        color = color,
        topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
        size = Size(12.dp.toPx(), 14.dp.toPx()),
        cornerRadius = CornerRadius(1.5.dp.toPx()),
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(7.dp.toPx(), 8.dp.toPx()),
        end = Offset(13.dp.toPx(), 8.dp.toPx()),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(7.dp.toPx(), 12.dp.toPx()),
        end = Offset(13.dp.toPx(), 12.dp.toPx()),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}


@Composable private fun UserIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, radius = 3.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f), style = Stroke(1.5.dp.toPx()))
    drawArc(color, startAngle = 205f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.24f, size.height * 0.52f), size = Size(size.width * 0.52f, size.height * 0.36f), style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round))
}

@Preview(showBackground = true, widthDp = 430, heightDp = 2111)
@Composable
private fun SkemaUCatalogScreenPreview() {
    SkemaUCatalogScreen()
}
