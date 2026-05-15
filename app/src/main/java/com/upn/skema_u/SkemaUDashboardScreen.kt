package com.upn.skema_u

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import kotlin.math.cos
import kotlin.math.sin

private val AppBackground = Color(0xFFF6FBEE)
private val Surface = Color.White
private val SurfaceMuted = Color(0xFFF0F6E8)
private val Border = Color(0x4DBFCAB7)
private val TextPrimary = Color(0xFF181D15)
private val TextSecondary = Color(0xFF404A3B)
private val TextMuted = Color(0xFF707A6A)
private val Green = Color(0xFF106E09)
private val GreenBright = Color(0xFF318825)
private val GreenDeep = Color(0xFF45673C)
private val SoftTrack = Color(0xFFE4EADD)
private val Pink = Color(0xFFC34B85)
private val Danger = Color(0xFFBA1A1A)

@Composable
fun SkemaUDashboardScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(AppBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 48.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                WelcomeSection()
                StatsGrid()
                QuickAccessSection()
                ActiveProjectsSection()
                RecentNotificationsSection()
            }

            TopAppBar()
            BottomNavigationBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Surface)
            .border(width = 1.dp, color = Color(0xFFE5E7EB)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color(0xFFE2E8F0), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.farah),
                contentDescription = "Foto Farah",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(7.dp))
        Text(
            text = "Skema-U",
            color = Green,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun WelcomeSection() {
    Column {
        Text(
            text = "Halo, Novia! 👋",
            color = TextPrimary,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = buildAnnotatedString {
                append("Status: ")
                withStyle(SpanStyle(color = Green, fontWeight = FontWeight.SemiBold)) {
                    append("Online")
                }
                append(" • Siap menerima proyek.")
            },
            color = TextSecondary,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun StatsGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        BalanceCard()
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SmallStatCard(
                modifier = Modifier.weight(1f),
                icon = { CheckCircleIcon(GreenDeep) },
                value = "24",
                label = "PROYEK SELESAI"
            )
            SmallStatCard(
                modifier = Modifier.weight(1f),
                icon = { StarIcon(Pink) },
                value = "4.9/5.0",
                label = "RATING RATA-RATA"
            )
        }
    }
}

@Composable
private fun BalanceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(94.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceMuted)
            .border(1.dp, Border, RoundedCornerShape(12.dp))
            .padding(17.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            LabelText("SALDO SAAT INI")
            Text(
                text = "Rp 300.000",
                color = Green,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        WalletIcon(
            color = Color(0xFFC9D6BE),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 4.dp, y = 8.dp)
                .size(64.dp)
        )
    }
}

@Composable
private fun SmallStatCard(
    modifier: Modifier,
    icon: @Composable () -> Unit,
    value: String,
    label: String
) {
    Column(
        modifier = modifier
            .height(106.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Surface)
            .border(1.dp, Border, RoundedCornerShape(12.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        icon()
        Column {
            Text(value, color = TextPrimary, fontSize = 20.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold)
            LabelText(label)
        }
    }
}

@Composable
private fun QuickAccessSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 8.dp)) {
        SectionTitle("Akses Cepat")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            QuickAction(
                modifier = Modifier.weight(1f),
                circleColor = GreenBright,
                label = "TAMBAH JASA",
                icon = { PlusIcon(Surface) }
            )
            QuickAction(
                modifier = Modifier.weight(1f),
                circleColor = GreenDeep,
                label = "PORTFOLIO",
                icon = { PortfolioIcon(Surface) }
            )
            QuickAction(
                modifier = Modifier.weight(1f),
                circleColor = Color(0xFFEAF0E2),
                borderColor = Color(0xFFBFCAB7),
                label = "STATISTIK",
                icon = { SparkIcon(GreenDeep) }
            )
        }
    }
}

@Composable
private fun QuickAction(
    modifier: Modifier,
    circleColor: Color,
    label: String,
    icon: @Composable () -> Unit,
    borderColor: Color? = null
) {
    Column(
        modifier = modifier
            .height(86.5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Surface)
            .border(1.dp, Color(0x33BFCAB7), RoundedCornerShape(12.dp))
            .padding(top = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(circleColor)
                .then(if (borderColor != null) Modifier.border(1.dp, borderColor, CircleShape) else Modifier),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Spacer(Modifier.height(7.75.dp))
        Text(
            text = label,
            color = TextPrimary,
            fontSize = 10.sp,
            lineHeight = 12.5.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ActiveProjectsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
            SectionTitle("Proyek Aktif", modifier = Modifier.weight(1f))
            Text(
                text = "LIHAT SEMUA",
                color = Green,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ProjectCard(
                category = "LOGO DESIGN",
                categoryColor = GreenBright,
                due = "2 Hari Lagi",
                dueColor = TextSecondary,
                title = "Rebranding UMKM \"Kopi Kenangan\"",
                client = "Klien: Pak Aqilla Rasya",
                progress = 0.75f,
                progressColor = Green
            )
            ProjectCard(
                category = "VIDEO EDITING",
                categoryColor = Color(0xFFC3EBB5),
                due = "Besok",
                dueColor = Danger,
                title = "Video Promo \"Keripik Tempe\"",
                client = "Klien: Pak Budi Santoso",
                progress = 0.30f,
                progressColor = GreenDeep
            )
        }
    }
}

@Composable
private fun ProjectCard(
    category: String,
    categoryColor: Color,
    due: String,
    dueColor: Color,
    title: String,
    client: String,
    progress: Float,
    progressColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(163.5.dp)
            .shadow(1.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x14000000), spotColor = Color(0x14000000))
            .clip(RoundedCornerShape(12.dp))
            .background(Surface)
            .border(1.dp, Border, RoundedCornerShape(12.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .height(22.dp)
                    .clip(CircleShape)
                    .background(SurfaceMuted)
                    .border(1.dp, Green.copy(alpha = 0.2f), CircleShape)
                    .padding(horizontal = 9.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(category, color = categoryColor, fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                if (dueColor == Danger) WarningIcon(Danger, Modifier.size(13.dp)) else ClockIcon(TextSecondary, Modifier.size(12.dp))
                Text(due, color = dueColor, fontSize = 12.sp, lineHeight = 18.sp)
            }
        }
        Text(title, color = TextPrimary, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
        Text(client, color = TextSecondary, fontSize = 16.sp, lineHeight = 24.sp)
        Spacer(Modifier.height(4.dp))
        ProgressBar(progress = progress, color = progressColor)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "PROGRES: ${(progress * 100).toInt()}%",
                color = TextMuted,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                DotsIcon(Green, Modifier.width(11.dp).height(3.dp))
                Text("Detail", color = Green, fontSize = 12.sp, lineHeight = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun ProgressBar(progress: Float, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(CircleShape)
            .background(SoftTrack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(6.dp)
                .clip(CircleShape)
                .background(color)
        )
    }
}

@Composable
private fun RecentNotificationsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(top = 8.dp)) {
        SectionTitle("Notifikasi Terbaru")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Surface)
                .border(1.dp, Border, RoundedCornerShape(12.dp))
        ) {
            NotificationRow(
                iconBackground = GreenBright.copy(alpha = 0.10f),
                icon = { CartIcon(GreenBright) },
                title = "Pesanan Baru:",
                body = " 1x Landing Page\nUMKM dari 'Bakery Mama'.",
                time = "2 jam yang lalu"
            )
            NotificationDivider()
            NotificationRow(
                iconBackground = Pink.copy(alpha = 0.10f),
                icon = { RevisionIcon(Pink) },
                title = "Revisi Masuk:",
                body = " Revisi warna logo\nuntuk 'Kopi Senja'.",
                time = "5 jam yang lalu"
            )
            NotificationDivider()
            NotificationRow(
                iconBackground = Color(0x33C3EBB5),
                icon = { MoneyIcon(GreenDeep) },
                title = "Pembayaran:",
                body = " Dana proyek 'Poster\nEvent' telah dicairkan.",
                time = "Kemarin"
            )
        }
    }
}

@Composable
private fun NotificationRow(
    iconBackground: Color,
    icon: @Composable () -> Unit,
    title: String,
    body: String,
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append(title) }
                    append(body)
                },
                color = TextPrimary,
                fontSize = 16.sp,
                lineHeight = 22.sp
            )
            Text(time, color = TextMuted, fontSize = 12.sp, lineHeight = 18.sp)
        }
    }
}

@Composable
private fun NotificationDivider() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0x33BFCAB7))
    )
}

@Composable
private fun BottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(4.dp)
            .background(Surface)
            .border(1.dp, Color(0xFFF3F4F6))
            .padding(start = 12.dp, end = 12.dp, top = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem("BERANDA", selected = true, modifier = Modifier.weight(1f)) { HomeIcon(Color(0xFF15803D)) }
        BottomNavItem("JASA SAYA", modifier = Modifier.weight(1f)) { GridIcon(Color(0xFF9CA3AF)) }
        BottomNavItem("PROJEK", modifier = Modifier.weight(1f)) { ClipboardIcon(Color(0xFF9CA3AF)) }
        BottomNavItem("CHAT", modifier = Modifier.weight(1f)) { ChatIcon(Color(0xFF9CA3AF)) }
        BottomNavItem("PROFIL", modifier = Modifier.weight(1f)) { UserIcon(Color(0xFF9CA3AF)) }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(45.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color(0x801DCF44) else Color.Transparent)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) Color(0xFF15803D) else Color(0xFF9CA3AF),
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SectionTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = TextPrimary,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}

@Composable
private fun LabelText(text: String) {
    Text(
        text = text,
        color = TextMuted,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun VectorIcon(modifier: Modifier = Modifier.size(22.dp), draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun CheckCircleIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    drawCircle(color, style = Stroke(width = 1.8.dp.toPx()))
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.8.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.8.dp.toPx(), StrokeCap.Round)
}

@Composable private fun StarIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    val p = Path()
    val cx = size.width / 2f
    val cy = size.height / 2f
    val outer = size.minDimension * 0.44f
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

@Composable private fun WalletIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, topLeft = Offset(size.width * 0.18f, size.height * 0.22f), size = Size(size.width * 0.56f, size.height * 0.48f), cornerRadius = CornerRadius(5.dp.toPx()), style = stroke)
    drawRoundRect(color, topLeft = Offset(size.width * 0.46f, size.height * 0.38f), size = Size(size.width * 0.38f, size.height * 0.24f), cornerRadius = CornerRadius(4.dp.toPx()), style = stroke)
    drawCircle(color, radius = 2.5.dp.toPx(), center = Offset(size.width * 0.65f, size.height * 0.50f))
}

@Composable private fun PlusIcon(color: Color) = VectorIcon(Modifier.size(18.dp)) {
    drawLine(color, Offset(size.width / 2f, 0f), Offset(size.width / 2f, size.height), 2.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(0f, size.height / 2f), Offset(size.width, size.height / 2f), 2.dp.toPx(), StrokeCap.Round)
}

@Composable private fun PortfolioIcon(color: Color) = VectorIcon(Modifier.size(22.dp)) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, topLeft = Offset(1.dp.toPx(), 4.dp.toPx()), size = Size(size.width - 2.dp.toPx(), size.height - 7.dp.toPx()), cornerRadius = CornerRadius(2.dp.toPx()), style = stroke)
    drawCircle(color, radius = 2.dp.toPx(), center = Offset(size.width * 0.35f, size.height * 0.52f), style = stroke)
    drawRect(color, topLeft = Offset(size.width * 0.58f, size.height * 0.35f), size = Size(5.dp.toPx(), 4.dp.toPx()), style = stroke)
    drawRect(color, topLeft = Offset(size.width * 0.58f, size.height * 0.58f), size = Size(5.dp.toPx(), 4.dp.toPx()), style = stroke)
}

@Composable private fun SparkIcon(color: Color) = VectorIcon(Modifier.size(22.dp)) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.3f, size.height * 0.75f), Offset(size.width * 0.7f, size.height * 0.25f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.32f), Offset(size.width * 0.28f, size.height * 0.46f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.21f, size.height * 0.39f), Offset(size.width * 0.35f, size.height * 0.39f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.76f, size.height * 0.56f), Offset(size.width * 0.76f, size.height * 0.72f), strokeWidth = stroke.width, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.68f, size.height * 0.64f), Offset(size.width * 0.84f, size.height * 0.64f), strokeWidth = stroke.width, cap = StrokeCap.Round)
}

@Composable private fun ClockIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, style = Stroke(1.4.dp.toPx()))
    drawLine(color, Offset(size.width / 2f, size.height / 2f), Offset(size.width / 2f, size.height * 0.25f), 1.2.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width / 2f, size.height / 2f), Offset(size.width * 0.68f, size.height * 0.58f), 1.2.dp.toPx(), StrokeCap.Round)
}

@Composable private fun WarningIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path().apply {
        moveTo(size.width / 2f, size.height * 0.12f)
        lineTo(size.width * 0.92f, size.height * 0.86f)
        lineTo(size.width * 0.08f, size.height * 0.86f)
        close()
    }
    drawPath(p, color, style = Stroke(1.2.dp.toPx(), join = StrokeJoin.Round))
    drawLine(color, Offset(size.width / 2f, size.height * 0.38f), Offset(size.width / 2f, size.height * 0.58f), 1.1.dp.toPx(), StrokeCap.Round)
    drawCircle(color, 0.9.dp.toPx(), Offset(size.width / 2f, size.height * 0.70f))
}

@Composable private fun DotsIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(3) { i -> drawCircle(color, 1.2.dp.toPx(), Offset(2.dp.toPx() + i * 4.dp.toPx(), size.height / 2f)) }
}

@Composable private fun CartIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    drawLine(color, Offset(2.dp.toPx(), 3.dp.toPx()), Offset(5.dp.toPx(), 3.dp.toPx()), 1.8.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(5.dp.toPx(), 3.dp.toPx()), Offset(7.dp.toPx(), 12.dp.toPx()), 1.8.dp.toPx(), StrokeCap.Round)
    drawRoundRect(color, Offset(6.dp.toPx(), 6.dp.toPx()), Size(11.dp.toPx(), 7.dp.toPx()),
        CornerRadius(2.dp.toPx()), style = Stroke(1.8.dp.toPx()))
    drawCircle(color, 1.5.dp.toPx(), Offset(8.dp.toPx(), 16.dp.toPx()))
    drawCircle(color, 1.5.dp.toPx(), Offset(15.dp.toPx(), 16.dp.toPx()))
}

@Composable private fun RevisionIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    repeat(3) { i ->
        val y = 5.dp.toPx() + i * 4.dp.toPx()
        drawLine(color, Offset(4.dp.toPx(), y), Offset((12 + i * 2).dp.toPx(), y), 1.7.dp.toPx(), StrokeCap.Round)
    }
    drawLine(color, Offset(12.dp.toPx(), 15.dp.toPx()), Offset(17.dp.toPx(), 10.dp.toPx()), 1.7.dp.toPx(), StrokeCap.Round)
}

@Composable private fun MoneyIcon(color: Color) = VectorIcon(Modifier.size(22.dp)) {
    drawRoundRect(color, Offset(3.dp.toPx(), 6.dp.toPx()), Size(16.dp.toPx(), 10.dp.toPx()),
        CornerRadius(2.dp.toPx()), style = Stroke(1.6.dp.toPx()))
    drawCircle(color, 2.2.dp.toPx(), Offset(11.dp.toPx(), 11.dp.toPx()), style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(5.dp.toPx(), 8.dp.toPx()), Offset(7.dp.toPx(), 8.dp.toPx()), 1.3.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(15.dp.toPx(), 14.dp.toPx()), Offset(17.dp.toPx(), 14.dp.toPx()), 1.3.dp.toPx(), StrokeCap.Round)
}

@Composable private fun HomeIcon(color: Color) = VectorIcon(Modifier.size(18.dp)) {
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

@Composable private fun GridIcon(color: Color) = VectorIcon(Modifier.size(18.dp)) {
    repeat(2) { x -> repeat(2) { y ->
        drawRect(color, Offset((3 + x * 7).dp.toPx(), (3 + y * 7).dp.toPx()), Size(4.dp.toPx(), 4.dp.toPx()), style = Stroke(1.4.dp.toPx()))
    } }
}

@Composable private fun ClipboardIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    drawRoundRect(color, Offset(4.dp.toPx(), 4.dp.toPx()), Size(12.dp.toPx(), 14.dp.toPx()),
        CornerRadius(1.5.dp.toPx()), style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(7.dp.toPx(), 8.dp.toPx()), Offset(13.dp.toPx(), 8.dp.toPx()), 1.2.dp.toPx())
    drawLine(color, Offset(7.dp.toPx(), 12.dp.toPx()), Offset(13.dp.toPx(), 12.dp.toPx()), 1.2.dp.toPx())
}

@Composable private fun ChatIcon(color: Color) = VectorIcon(Modifier.size(20.dp)) {
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()),
        CornerRadius(2.dp.toPx()), style = Stroke(1.5.dp.toPx()))
    drawLine(color, Offset(7.dp.toPx(), 8.dp.toPx()), Offset(13.dp.toPx(), 8.dp.toPx()), 1.2.dp.toPx())
    drawLine(color, Offset(7.dp.toPx(), 11.dp.toPx()), Offset(12.dp.toPx(), 11.dp.toPx()), 1.2.dp.toPx())
}

@Composable private fun UserIcon(color: Color) = VectorIcon(Modifier.size(18.dp)) {
    drawCircle(color, radius = 3.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f), style = Stroke(1.5.dp.toPx()))
    drawArc(color, startAngle = 205f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.24f, size.height * 0.52f), size = Size(size.width * 0.52f, size.height * 0.36f), style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round))
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1450)
@Composable
private fun SkemaUDashboardScreenPreview() {
    SkemaUDashboardScreen()
}
