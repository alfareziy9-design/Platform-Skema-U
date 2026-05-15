package com.upn.skema_u

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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

private val ProfileBackground = Color(0xFFF6FBEE)
private val ProfileSurface = Color.White
private val ProfileSurfaceMuted = Color(0xFFEAF0E2)
private val ProfileStatsBg = Color(0xFFEAF0E2)
private val ProfileGreen = Color(0xFF106E09)
private val ProfileGreenDeep = Color(0xFF45673C)
private val ProfileGreenText = Color(0xFF496B40)
private val ProfileTextPrimary = Color(0xFF181D15)
private val ProfileTextSecondary = Color(0xFF404A3B)
private val ProfileBorder = Color(0x4DBFCAB7)
private val ProfileBorderStrong = Color(0xFFBFCAB7)
private val ProfileGrayIcon = Color(0xFF9CA3AF)
private val ProfileProjectInactive = Color(0xFF94A3B8)
private val ProfileStar = Color(0xFFFFA000)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkemaUProfileScreen(
    modifier: Modifier = Modifier,
    @DrawableRes profileImageRes: Int = R.drawable.farah,
    @DrawableRes portfolioImageOneRes: Int = R.drawable.logo,
    @DrawableRes portfolioImageTwoRes: Int = R.drawable.portofolio_2
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ProfileBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 395.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ProfileBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 26.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ProfileHero(profileImageRes = profileImageRes)
                StatsGrid()
                SkillsSection()
                PortfolioSection(
                    portfolioImageOneRes = portfolioImageOneRes,
                    portfolioImageTwoRes = portfolioImageTwoRes
                )
                ToolsSection()
                ReviewsSection()
            }

            ProfileTopAppBar()
            ProfileBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ProfileHero(@DrawableRes profileImageRes: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Box {
            Image(
                painter = painterResource(profileImageRes),
                contentDescription = "Foto profil Novia Farah H",
                modifier = Modifier
                    .size(128.dp)
                    .shadow(
                        elevation = 14.dp,
                        shape = RoundedCornerShape(12.dp),
                        ambientColor = ProfileGreen.copy(alpha = 0.12f),
                        spotColor = ProfileGreen.copy(alpha = 0.12f)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, ProfileSurface, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 0.dp, bottom = 0.dp)
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(ProfileGreen)
                    .border(2.dp, ProfileSurface, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                CheckIcon(ProfileSurface, Modifier.size(13.dp))
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "Novia Farah H",
                color = ProfileTextPrimary,
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                GraduationIcon(ProfileGreenDeep, Modifier.width(17.dp).height(14.dp))
                Text(
                    text = "Sistem Informasi, UPNVJATIM",
                    color = ProfileTextSecondary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfileBadge(
                    text = "MITRA UNIVERSITAS",
                    background = Color.Transparent,
                    foreground = ProfileGreen,
                    border = ProfileGreen
                )
                ProfileBadge(
                    text = "KEAHLIAN TERVERIFIKASI",
                    background = ProfileGreen,
                    foreground = ProfileSurface,
                    border = ProfileGreen
                )
            }
        }
    }
}

@Composable
private fun ProfileBadge(text: String, background: Color, foreground: Color, border: Color) {
    Box(
        modifier = Modifier
            .height(34.dp)
            .clip(CircleShape)
            .background(background)
            .border(1.dp, border, CircleShape)
            .padding(horizontal = 17.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = foreground, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun StatsGrid() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ProfileStatsBg)
            .border(1.dp, ProfileBorder, RoundedCornerShape(12.dp))
            .padding(25.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(label = "PROYEK", value = "24", modifier = Modifier.weight(1f))
        StatItem(label = "RATING", value = "4.9", modifier = Modifier.weight(1f), bordered = true)
        StatItem(label = "KLIEN", value = "18", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun StatItem(label: String, value: String, modifier: Modifier, bordered: Boolean = false) {
    Column(
        modifier = modifier
            .then(
                if (bordered) {
                    Modifier
                        .border(1.dp, Color.Transparent)
                        .padding(horizontal = 1.dp)
                } else Modifier
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(label, color = ProfileTextSecondary, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
        Text(value, color = ProfileGreen, fontSize = 24.sp, lineHeight = 32.sp, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection() {
    SectionBlock(title = "KOMPETENSI UTAMA") {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "Identitas Brand",
                "Desain UI/UX",
                "Pemodelan 3D",
                "Motion Graphics",
                "Adobe Creative Suite",
                "Riset Pengguna"
            ).forEach {
                WhiteChip(text = it)
            }
        }
    }
}

@Composable
private fun PortfolioSection(
    @DrawableRes portfolioImageOneRes: Int,
    @DrawableRes portfolioImageTwoRes: Int
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            SectionTitle("SHOWCASE PORTOFOLIO")
            Text(
                text = "LIHAT SEMUA",
                color = ProfileGreen,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PortfolioTile(imageRes = portfolioImageOneRes, modifier = Modifier.weight(1f))
            PortfolioTile(imageRes = portfolioImageTwoRes, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun PortfolioTile(@DrawableRes imageRes: Int, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(125.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ProfileSurfaceMuted)
            .border(1.dp, ProfileBorderStrong, RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Showcase portofolio",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ToolsSection() {
    SectionBlock(title = "TOOLS & SOFTWARE") {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Adobe Photoshop", "Figma", "After Effects", "Blender", "VS Code").forEach {
                GreenChip(text = it)
            }
        }
    }
}

@Composable
private fun ReviewsSection() {
    SectionBlock(title = "TESTIMONI KLIEN") {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            ReviewCard(
                initials = "YS",
                name = "Yanto Supri",
                role = "Pemilik, GreenLeaf\nOrganics",
                rating = 5,
                quote = "\"Novia melampaui ekspektasi kami.\nSelera estetikanya yang modern dan\nprofesionalismenya membuat\nrebranding kami berjalan lancar. Sangat\ndirekomendasikan untuk UMKM\nmanapun yang ingin naik kelas.\""
            )
            ReviewCard(
                initials = "SW",
                name = "Susi Wati",
                role = "Founder, Velocity\nTech",
                rating = 4,
                quote = "\"Komunikasi yang sangat baik dan\nsangat tepat waktu. Dia mengambil\nbrief kompleks kami dan mengubahnya\nmenjadi UI yang bersih dan profesional.\nPasti akan bekerja sama lagi.\""
            )
        }
    }
}

@Composable
private fun ReviewCard(initials: String, name: String, role: String, rating: Int, quote: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ProfileSurface)
            .border(1.dp, ProfileBorderStrong, RoundedCornerShape(12.dp))
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ProfileActionMuted),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = initials,
                        color = ProfileGreen,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
                Column {
                    Text(name, color = ProfileTextPrimary, fontSize = 16.sp, lineHeight = 20.sp)
                    Text(role, color = ProfileTextSecondary, fontSize = 16.sp, lineHeight = 24.sp)
                }
            }
            Row {
                repeat(5) { index ->
                    StarIcon(
                        color = if (index < rating) ProfileStar else Color(0xFFD6D3D1),
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
        Text(
            text = quote,
            color = ProfileTextPrimary,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontStyle = FontStyle.Italic
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(8.dp).clip(CircleShape).background(ProfileGreen))
            Text(
                text = "TRANSAKSI TERVERIFIKASI",
                color = ProfileGreen,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}

private val ProfileActionMuted = Color(0xFFE4EADD)

@Composable
private fun SectionBlock(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SectionTitle(title)
        content()
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        color = ProfileTextSecondary,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun WhiteChip(text: String) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ProfileSurface)
            .border(1.dp, ProfileBorderStrong, RoundedCornerShape(8.dp))
            .padding(horizontal = 17.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = ProfileTextPrimary, fontSize = 16.sp, lineHeight = 24.sp)
    }
}

@Composable
private fun GreenChip(text: String) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ProfileGreenSoft)
            .border(1.dp, ProfileGreenDeep, RoundedCornerShape(8.dp))
            .padding(horizontal = 17.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = ProfileGreenText, fontSize = 16.sp, lineHeight = 24.sp)
    }
}

private val ProfileGreenSoft = Color(0x4DC3EBB5)

@Composable
private fun ProfileTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ProfileSurface)
            .border(1.dp, ProfileBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Skema-U",
            color = ProfileGreen,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold
        )
        Box(modifier = Modifier.size(40.dp), contentAlignment = Alignment.Center) {
            EditIcon(ProfileGreen, Modifier.size(18.dp))
        }
    }
}

@Composable
private fun ProfileBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(ProfileSurface)
            .border(1.dp, Color(0xFFF3F4F6))
            .padding(start = 12.dp, end = 12.dp, top = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileBottomNavItem("BERANDA", selected = false, modifier = Modifier.weight(1f)) { HomeIcon(ProfileGrayIcon, Modifier.size(18.dp)) }
        ProfileBottomNavItem("JASA SAYA", selected = false, modifier = Modifier.weight(1f)) { GridIcon(ProfileGrayIcon, Modifier.size(18.dp)) }
        ProfileBottomNavItem("PROJEK", selected = false, modifier = Modifier.weight(1f)) { ClipboardIcon(ProfileProjectInactive, Modifier.size(20.dp)) }
        ProfileBottomNavItem("CHAT", selected = false, modifier = Modifier.weight(1f)) { ChatIcon(ProfileGrayIcon, Modifier.size(20.dp)) }
        ProfileBottomNavItem("PROFIL", selected = true, modifier = Modifier.weight(1f)) { UserIcon(ProfileGreen, Modifier.size(18.dp)) }
    }
}

@Composable
private fun ProfileBottomNavItem(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(45.dp)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) ProfileGreen else ProfileGrayIcon,
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

@Composable private fun CheckIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawLine(color, Offset(size.width * 0.16f, size.height * 0.54f), Offset(size.width * 0.42f, size.height * 0.78f), 1.8.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.42f, size.height * 0.78f), Offset(size.width * 0.86f, size.height * 0.20f), 1.8.dp.toPx(), StrokeCap.Round)
}

@Composable
private fun GraduationIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.4.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    val p = Path().apply {
        moveTo(size.width * 0.06f, size.height * 0.38f)
        lineTo(size.width * 0.50f, size.height * 0.12f)
        lineTo(size.width * 0.94f, size.height * 0.38f)
        lineTo(size.width * 0.50f, size.height * 0.64f)
        close()
    }

    drawPath(
        path = p,
        color = color,
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.26f, size.height * 0.52f),
        end = Offset(size.width * 0.26f, size.height * 0.82f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.26f, size.height * 0.82f),
        end = Offset(size.width * 0.72f, size.height * 0.82f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
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

@Composable
private fun EditIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.8.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    val pencil = Path().apply {
        moveTo(size.width * 0.20f, size.height * 0.80f)
        lineTo(size.width * 0.28f, size.height * 0.58f)
        lineTo(size.width * 0.68f, size.height * 0.18f)
        lineTo(size.width * 0.82f, size.height * 0.32f)
        lineTo(size.width * 0.42f, size.height * 0.72f)
        lineTo(size.width * 0.20f, size.height * 0.80f)
        close()
    }

    drawPath(
        path = pencil,
        color = color,
        style = stroke
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.62f, size.height * 0.24f),
        end = Offset(size.width * 0.76f, size.height * 0.38f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawLine(
        color = color,
        start = Offset(size.width * 0.28f, size.height * 0.58f),
        end = Offset(size.width * 0.42f, size.height * 0.72f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
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
            drawRect(color, Offset((3 + x * 7).dp.toPx(), (3 + y * 7).dp.toPx()), Size(4.dp.toPx(), 4.dp.toPx()), style = Stroke(1.4.dp.toPx()))
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

@Preview(showBackground = true, widthDp = 395, heightDp = 1950)
@Composable
private fun SkemaUProfileScreenPreview() {
    SkemaUProfileScreen()
}
