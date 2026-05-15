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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
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

private val ServicesBackground = Color(0xFFF6FBEE)
private val ServicesSurface = Color.White
private val ServicesSurfaceMuted = Color(0xFFF0F6E8)
private val ServicesBorder = Color(0x4DBFCAB7)
private val ServicesBorderStrong = Color(0x80BFCAB7)
private val ServicesTextPrimary = Color(0xFF181D15)
private val ServicesTextSecondary = Color(0xFF404A3B)
private val ServicesTextMuted = Color(0xFF707A6A)
private val ServicesGreen = Color(0xFF106E09)
private val ServicesDanger = Color(0xFFBA1A1A)
private val ServicesGrayIcon = Color(0xFF9CA3AF)
private val ServicesStar = Color(0xFFFFA000)

data class SkemaServiceItem(
    val title: String,
    val description: String,
    val rating: String,
    val price: String,
    val active: Boolean,
    @DrawableRes val imageRes: Int
)

@Composable
fun SkemaUServicesScreen(
    modifier: Modifier = Modifier,
    services: List<SkemaServiceItem> = defaultSkemaServices()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ServicesBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ServicesBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AddServiceButton()
                ServiceStatsRow()
                ServiceListSection(services = services)
            }

            ServicesTopAppBar()
            ServicesBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun AddServiceButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x18000000),
                spotColor = Color(0x18000000)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(ServicesGreen)
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlusCircleIcon(color = ServicesSurface, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Tambah Jasa Baru",
            color = ServicesSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ServiceStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatOverviewCard(
            modifier = Modifier.weight(1f),
            label = "TOTAL PENDAPATAN",
            value = "Rp 360.000"
        )
        StatOverviewCard(
            modifier = Modifier.weight(1f),
            label = "PESANAN AKTIF",
            value = "3",
            showDot = true,
            fadedValue = true
        )
    }
}

@Composable
private fun StatOverviewCard(
    modifier: Modifier,
    label: String,
    value: String,
    showDot: Boolean = false,
    fadedValue: Boolean = false
) {
    Column(
        modifier = modifier
            .height(86.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ServicesSurfaceMuted)
            .border(1.dp, ServicesBorder, RoundedCornerShape(8.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = ServicesTextSecondary,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showDot) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(ServicesGreen)
                )
            }
            Text(
                text = value,
                color = ServicesGreen.copy(alpha = if (fadedValue) 0.30f else 1f),
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun ServiceListSection(services: List<SkemaServiceItem>) {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "DAFTAR LAYANAN AKTIF",
            color = ServicesTextSecondary,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold
        )
        services.forEach { service ->
            ServiceCard(service = service)
        }
    }
}

@Composable
private fun ServiceCard(service: SkemaServiceItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (service.active) 1f else 0.70f)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x14000000),
                spotColor = Color(0x14000000)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(ServicesSurface)
            .border(1.dp, ServicesBorderStrong, RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            Image(
                painter = painterResource(service.imageRes),
                contentDescription = service.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            if (!service.active) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0.18f))
                )
            }
            StatusBadge(
                text = if (service.active) "AKTIF" else "NON-AKTIF",
                color = if (service.active) ServicesGreen.copy(alpha = 0.90f) else ServicesTextMuted,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 12.dp, top = 8.dp)
            )
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
                Text(
                    text = service.title,
                    color = ServicesTextPrimary,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarIcon(color = ServicesStar, modifier = Modifier.size(13.dp))
                    Text(
                        text = service.rating,
                        color = ServicesTextPrimary,
                        fontSize = 11.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Text(
                text = service.description,
                color = ServicesTextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0x33BFCAB7))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "MULAI DARI",
                        color = ServicesTextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = service.price,
                        color = if (service.active) ServicesGreen else ServicesTextSecondary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButtonFrame(borderColor = Color(0xFFBFCAB7)) {
                        EditIcon(color = ServicesGreen, modifier = Modifier.size(15.dp))
                    }
                    IconButtonFrame(borderColor = ServicesDanger.copy(alpha = 0.20f)) {
                        TrashIcon(color = ServicesDanger, modifier = Modifier.size(15.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .padding(horizontal = 12.dp, vertical = 3.5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = ServicesSurface,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun IconButtonFrame(
    borderColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ServicesSurface)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun ServicesTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ServicesSurface)
            .border(1.dp, ServicesBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFFE2E8F0), CircleShape)
                    .background(Brush.linearGradient(listOf(Color(0xFFFF637D), Color(0xFFBDF0CF)))),
                contentAlignment = Alignment.Center
            ) {
                Text("N", color = ServicesSurface, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                text = "Skema-U",
                color = ServicesGreen,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(32.dp), contentAlignment = Alignment.Center) {
                BellIcon(color = ServicesTextSecondary, modifier = Modifier.size(20.dp))
            }
            Box(Modifier.size(34.dp), contentAlignment = Alignment.Center) {
                SearchIcon(color = ServicesTextSecondary, modifier = Modifier.size(19.dp))
            }
        }
    }
}

@Composable
private fun ServicesBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(ServicesSurface)
            .border(1.dp, Color(0xFFF3F4F6))
            .padding(start = 12.dp, end = 12.dp, top = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem("BERANDA", selected = false, modifier = Modifier.weight(1f)) { HomeIcon(ServicesGrayIcon, Modifier.size(18.dp)) }
        BottomNavItem("JASA SAYA", selected = true, modifier = Modifier.weight(1f)) { GridIcon(ServicesGreen, Modifier.size(18.dp)) }
        BottomNavItem("PROJEK", selected = false, modifier = Modifier.weight(1f)) { ClipboardIcon(ServicesGrayIcon, Modifier.size(20.dp)) }
        BottomNavItem("CHAT", selected = false, modifier = Modifier.weight(1f)) { ChatIcon(ServicesGrayIcon, Modifier.size(20.dp)) }
        BottomNavItem("PROFIL", selected = false, modifier = Modifier.weight(1f)) { UserIcon(ServicesGrayIcon, Modifier.size(18.dp)) }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(45.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Transparent)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) ServicesGreen else ServicesGrayIcon,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun PlusCircleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, style = Stroke(1.7.dp.toPx()))
    drawLine(color, Offset(size.width / 2f, size.height * 0.30f), Offset(size.width / 2f, size.height * 0.70f), 1.7.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.30f, size.height / 2f), Offset(size.width * 0.70f, size.height / 2f), 1.7.dp.toPx(), StrokeCap.Round)
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

@Composable
private fun EditIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val sw = 1.6.dp.toPx()
    val capRound = StrokeCap.Round

    // Batang Pensil
    drawLine(
        color = color,
        start = Offset(size.width * 0.22f, size.height * 0.78f),
        end = Offset(size.width * 0.76f, size.height * 0.24f),
        strokeWidth = sw,
        cap = capRound
    )

    // Bagian Atas/Penutup Pensil
    drawLine(
        color = color,
        start = Offset(size.width * 0.64f, size.height * 0.16f),
        end = Offset(size.width * 0.84f, size.height * 0.36f),
        strokeWidth = sw,
        cap = capRound
    )

    // Bagian Ujung Bawah
    drawLine(
        color = color,
        start = Offset(size.width * 0.18f, size.height * 0.82f),
        end = Offset(size.width * 0.36f, size.height * 0.78f),
        strokeWidth = sw,
        cap = capRound
    )
}

@Composable
private fun TrashIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    // Ambil nilai dari stroke untuk memudahkan penggunaan ulang
    val sw = 1.5.dp.toPx()
    val swSmall = 1.dp.toPx()
    val capRound = StrokeCap.Round

    // Baris 1: Tutup tempat sampah (Atas)
    drawLine(
        color = color,
        start = Offset(size.width * 0.25f, size.height * 0.30f),
        end = Offset(size.width * 0.75f, size.height * 0.30f),
        strokeWidth = sw,
        cap = capRound
    )

    // Baris 2: Pegangan tutup (Paling atas)
    drawLine(
        color = color,
        start = Offset(size.width * 0.43f, size.height * 0.18f),
        end = Offset(size.width * 0.57f, size.height * 0.18f),
        strokeWidth = sw,
        cap = capRound
    )

    // Body tempat sampah (Menggunakan style karena drawRoundRect mendukungnya)
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.30f, size.height * 0.36f),
        size = Size(size.width * 0.40f, size.height * 0.46f),
        cornerRadius = CornerRadius(1.dp.toPx()),
        style = Stroke(sw, cap = capRound, join = StrokeJoin.Round)
    )

    // Garis vertikal 1 di dalam
    drawLine(
        color = color,
        start = Offset(size.width * 0.43f, size.height * 0.46f),
        end = Offset(size.width * 0.43f, size.height * 0.72f),
        strokeWidth = swSmall,
        cap = capRound
    )

    // Garis vertikal 2 di dalam
    drawLine(
        color = color,
        start = Offset(size.width * 0.57f, size.height * 0.46f),
        end = Offset(size.width * 0.57f, size.height * 0.72f),
        strokeWidth = swSmall,
        cap = capRound
    )
}

@Composable private fun BellIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val sw = 1.6.dp.toPx()
    val capRound = StrokeCap.Round
    val stroke = Stroke(sw, cap = capRound, join = StrokeJoin.Round)

    // Lengkungan Lonceng (drawArc mendukung style)
    drawArc(
        color = color,
        startAngle = 200f,
        sweepAngle = 140f,
        useCenter = false,
        topLeft = Offset(size.width * 0.22f, size.height * 0.20f),
        size = Size(size.width * 0.56f, size.height * 0.58f),
        style = stroke
    )

    // Garis bawah Lonceng (drawLine TIDAK mendukung style)
    drawLine(
        color = color,
        start = Offset(size.width * 0.24f, size.height * 0.68f),
        end = Offset(size.width * 0.76f, size.height * 0.68f),
        strokeWidth = sw,
        cap = capRound
    )

    // Bandul/Clapper Lonceng (Ini akan terisi penuh/filled karena tidak pakai style)
    drawCircle(
        color = color,
        radius = 1.4.dp.toPx(),
        center = Offset(size.width / 2f, size.height * 0.82f)
    )
}

@Composable private fun SearchIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val sw = 1.7.dp.toPx()
    val capRound = StrokeCap.Round
    val stroke = Stroke(sw, cap = capRound)

    // Lingkaran Kaca Pembesar
    drawCircle(
        color = color,
        radius = size.minDimension * 0.30f,
        center = Offset(size.width * 0.44f, size.height * 0.42f),
        style = stroke
    )

    // Gagang Kaca Pembesar
    drawLine(
        color = color,
        start = Offset(size.width * 0.64f, size.height * 0.64f),
        end = Offset(size.width * 0.82f, size.height * 0.82f),
        strokeWidth = sw,
        cap = capRound
    )
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
    drawPath(p, color)
}

@Composable private fun GridIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(2) { x ->
        repeat(2) { y ->
            drawRect(
                color,
                Offset((3 + x * 7).dp.toPx(), (3 + y * 7).dp.toPx()),
                Size(4.dp.toPx(), 4.dp.toPx()),
                style = Stroke(1.4.dp.toPx())
            )
        }
    }
}

@Composable private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawRoundRect(color, Offset(4.dp.toPx(), 4.dp.toPx()), Size(12.dp.toPx(), 14.dp.toPx()),
        CornerRadius(1.5.dp.toPx()), style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(7.dp.toPx(), 8.dp.toPx()), Offset(13.dp.toPx(), 8.dp.toPx()), 1.2.dp.toPx())
    drawLine(color, Offset(7.dp.toPx(), 12.dp.toPx()), Offset(13.dp.toPx(), 12.dp.toPx()), 1.2.dp.toPx())
}

@Composable private fun ChatIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()),
        CornerRadius(2.dp.toPx()), style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(7.dp.toPx(), 8.dp.toPx()), Offset(13.dp.toPx(), 8.dp.toPx()), 1.2.dp.toPx())
    drawLine(color, Offset(7.dp.toPx(), 11.dp.toPx()), Offset(12.dp.toPx(), 11.dp.toPx()), 1.2.dp.toPx())
}

@Composable private fun UserIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, radius = 3.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f), style = Stroke(1.5.dp.toPx()))
    drawArc(color, startAngle = 205f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.24f, size.height * 0.52f), size = Size(size.width * 0.52f, size.height * 0.36f), style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round))
}

private fun defaultSkemaServices(): List<SkemaServiceItem> {
    return listOf(
        SkemaServiceItem(
            title = "Desain Logo Minimalis untuk\nUMKM Kreatif",
            description = "Branding profesional dengan sentuhan modern\nuntuk bisnis berkembang.",
            rating = "4.9",
            price = "Rp 15.000",
            active = true,
            imageRes = R.drawable.konten1
        ),
        SkemaServiceItem(
            title = "Vidio editing",
            description = "Ubah vidio mentah menjadi vidio bisnis yang\nberharga bagi UMKM Anda.",
            rating = "5.0",
            price = "Rp 15.000",
            active = true,
            imageRes = R.drawable.konten1
        ),
        SkemaServiceItem(
            title = "Manajemen Instagram UMKM\nMingguan",
            description = "Paket lengkap konten dan interaksi untuk\nmeningkatkan engagement.",
            rating = "4.8",
            price = "Rp 15.000",
            active = false,
            imageRes = R.drawable.konten1
        )
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1603)
@Composable
private fun SkemaUServicesScreenPreview() {
    SkemaUServicesScreen()
}
