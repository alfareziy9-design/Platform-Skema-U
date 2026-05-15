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

private val ProjectBackground = Color(0xFFF6FBEE)
private val ProjectSurface = Color.White
private val ProjectSurfaceMuted = Color(0xFFF0F6E8)
private val ProjectTrack = Color(0xFFE4EADD)
private val ProjectBorder = Color(0x33707A6A)
private val ProjectBorderGreen = Color(0x4D106E09)
private val ProjectTextPrimary = Color(0xFF181D15)
private val ProjectTextSecondary = Color(0xFF404A3B)
private val ProjectTextMuted = Color(0xFF707A6A)
private val ProjectGreen = Color(0xFF106E09)
private val ProjectGreenSoft = Color(0xFFC3EBB5)
private val ProjectGreenDeep = Color(0xFF45673C)
private val ProjectGrayIcon = Color(0xFF9CA3AF)

@Composable
fun SkemaUProjectScreen(
    modifier: Modifier = Modifier,
    @DrawableRes contentImageRes: Int = R.drawable.konten2
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ProjectBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ProjectBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 96.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ProjectOfferBanner()
                CurrentProjectSummary(imageRes = contentImageRes)
                ProjectProgressCard()
                MilestonesSection()
                UpcomingDeliverablesSection()
                PortfolioBanner(imageRes = contentImageRes)
            }

            ProjectTopAppBar()
            ProjectBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ProjectOfferBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = ProjectGreen.copy(alpha = 0.12f),
                spotColor = ProjectGreen.copy(alpha = 0.12f)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(ProjectGreen)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MegaphoneIcon(ProjectSurface, Modifier.width(20.dp).height(16.dp))
            Text(
                text = "Ada Tawaran Proyek\nBaru!",
                color = ProjectSurface,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .width(95.dp)
                .height(36.dp)
                .clip(CircleShape)
                .background(ProjectSurface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "LIHAT\nDETAIL",
                color = ProjectGreen,
                fontSize = 11.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun CurrentProjectSummary(@DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier.padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ID Proyek: SK-2045",
                color = ProjectTextSecondary.copy(alpha = 0.70f),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(ProjectGreenSoft)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SEDANG BERJALAN",
                    color = Color(0xFF496B40),
                    fontSize = 11.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(116.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(ProjectSurface)
                .border(1.dp, ProjectBorder, RoundedCornerShape(12.dp))
                .padding(17.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ProjectSurfaceMuted),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "Logo Skema-U",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Redesain WEB\nUMKM",
                    color = ProjectTextPrimary,
                    fontSize = 20.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Blossom Café & Bakery",
                    color = ProjectTextSecondary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
        }
    }
}

@Composable
private fun ProjectProgressCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ProjectSurface)
            .border(1.dp, ProjectBorder, RoundedCornerShape(16.dp))
            .shadow(1.dp, RoundedCornerShape(16.dp))
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "PROGRES PENGERJAAN",
                    color = ProjectTextSecondary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("65", color = ProjectGreen, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.width(4.dp))
                    Text("%", color = ProjectGreen.copy(alpha = 0.60f), fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "TARGET SELESAI",
                    color = ProjectTextSecondary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
                Text(
                    text = "24 Okt 2023",
                    color = ProjectTextPrimary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(CircleShape)
                .background(ProjectTrack)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(12.dp)
                    .clip(CircleShape)
                    .background(ProjectGreen)
            )
        }
    }
}

@Composable
private fun MilestonesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Milestones",
            color = ProjectTextPrimary,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Box {
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, top = 32.dp, bottom = 32.dp)
                    .width(2.dp)
                    .height(206.dp)
                    .background(Color(0x4DBFCAB7))
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                MilestoneRow(
                    title = "Kebutuhan Awal",
                    status = "Selesai",
                    completed = true
                )
                MilestoneRow(
                    title = "Konsep Desain",
                    status = "Selesai",
                    completed = true
                )
                MilestoneRow(
                    title = "Umpan Balik & Revisi",
                    status = "Dalam Tinjauan",
                    completed = false,
                    expanded = true
                )
            }
        }
    }
}

@Composable
private fun MilestoneRow(
    title: String,
    status: String,
    completed: Boolean,
    expanded: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(if (completed) RoundedCornerShape(12.dp) else CircleShape)
                .background(if (completed) ProjectGreen else ProjectGreenSoft),
            contentAlignment = Alignment.Center
        ) {
            if (completed) CheckIcon(ProjectSurface, Modifier.width(16.dp).height(12.dp))
            else CycleIcon(ProjectGreenDeep, Modifier.size(18.dp))
        }
        if (expanded) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ProjectSurface)
                    .border(2.dp, ProjectBorderGreen, RoundedCornerShape(12.dp))
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    Text(title, color = ProjectTextPrimary, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
                    Text(status, color = ProjectGreenDeep, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ProjectMiniButton(text = "LIHAT UPDATE", filled = true, modifier = Modifier.weight(1f))
                    ProjectMiniButton(text = "CHAT", filled = false, modifier = Modifier.weight(1f))
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(ProjectSurface)
                    .border(1.dp, ProjectBorder, RoundedCornerShape(12.dp))
                    .padding(17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(title, color = ProjectTextPrimary, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
                    Text(status, color = ProjectGreen, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
                }
                GearIcon(ProjectTextMuted.copy(alpha = 0.55f), Modifier.size(22.dp))
            }
        }
    }
}

@Composable
private fun ProjectMiniButton(text: String, filled: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(33.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (filled) ProjectGreen else ProjectSurface)
            .then(if (filled) Modifier else Modifier.border(1.dp, ProjectGreen, RoundedCornerShape(8.dp))),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (filled) ProjectSurface else ProjectGreen,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun UpcomingDeliverablesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Deliverables Mendatang",
            color = ProjectTextPrimary,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            DeliverableCard(
                modifier = Modifier.weight(1f),
                title = "Aset Vektor Final",
                icon = { VectorAssetIcon(ProjectGreen, Modifier.size(20.dp)) }
            )
            DeliverableCard(
                modifier = Modifier.weight(1f),
                title = "Panduan Gaya\nBrand",
                icon = { BookIcon(ProjectGreen, Modifier.size(22.dp)) }
            )
        }
    }
}

@Composable
private fun DeliverableCard(
    modifier: Modifier,
    title: String,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(138.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ProjectSurfaceMuted)
            .border(1.dp, Color(0x1A707A6A), RoundedCornerShape(12.dp))
            .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .shadow(1.dp, RoundedCornerShape(8.dp))
                .background(ProjectSurface),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = title,
            color = ProjectTextPrimary,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PortfolioBanner(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Portfolio banner",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        0f to ProjectGreen.copy(alpha = 0.80f),
                        1f to ProjectGreen.copy(alpha = 0f)
                    )
                )
        )
        Text(
            text = "Tingkatkan portofolio\nAnda dengan proyek ini.",
            color = ProjectSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 24.dp)
                .width(200.dp)
        )
    }
}

@Composable
private fun ProjectTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ProjectSurface)
            .border(1.dp, Color(0x99E7E5E4))
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
                Text("N", color = ProjectSurface, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(
                text = "Skema-U",
                color = ProjectGreen,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            BellIcon(ProjectGreen, Modifier.width(16.dp).height(20.dp))
        }
    }
}

@Composable
private fun ProjectBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(ProjectSurface)
            .border(1.dp, Color(0xFFF3F4F6))
            .padding(start = 12.dp, end = 12.dp, top = 1.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProjectBottomNavItem("BERANDA", selected = false, modifier = Modifier.weight(1f)) { HomeIcon(ProjectGrayIcon, Modifier.size(18.dp)) }
        ProjectBottomNavItem("JASA SAYA", selected = false, modifier = Modifier.weight(1f)) { GridIcon(ProjectGrayIcon, Modifier.size(18.dp)) }
        ProjectBottomNavItem("PROJEK", selected = true, modifier = Modifier.weight(1f)) { ClipboardIcon(ProjectGreen, Modifier.size(20.dp)) }
        ProjectBottomNavItem("CHAT", selected = false, modifier = Modifier.weight(1f)) { ChatIcon(ProjectGrayIcon, Modifier.size(20.dp)) }
        ProjectBottomNavItem("PROFIL", selected = false, modifier = Modifier.weight(1f)) { UserIcon(ProjectGrayIcon, Modifier.size(18.dp)) }
    }
}

@Composable
private fun ProjectBottomNavItem(
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
            color = if (selected) ProjectGreen else ProjectGrayIcon,
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

@Composable private fun MegaphoneIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.08f, size.height * 0.48f), Offset(size.width * 0.38f, size.height * 0.48f), style = stroke)
    drawLine(color, Offset(size.width * 0.38f, size.height * 0.25f), Offset(size.width * 0.82f, size.height * 0.08f), style = stroke)
    drawLine(color, Offset(size.width * 0.38f, size.height * 0.70f), Offset(size.width * 0.82f, size.height * 0.88f), style = stroke)
    drawLine(color, Offset(size.width * 0.38f, size.height * 0.25f), Offset(size.width * 0.38f, size.height * 0.70f), style = stroke)
    drawLine(color, Offset(size.width * 0.22f, size.height * 0.52f), Offset(size.width * 0.30f, size.height * 0.88f), style = stroke)
}

@Composable private fun CheckIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawLine(color, Offset(size.width * 0.12f, size.height * 0.54f), Offset(size.width * 0.42f, size.height * 0.82f), 2.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.42f, size.height * 0.82f), Offset(size.width * 0.90f, size.height * 0.18f), 2.dp.toPx(), StrokeCap.Round)
}

@Composable private fun CycleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawArc(color, startAngle = 35f, sweepAngle = 250f, useCenter = false, topLeft = Offset(size.width * 0.16f, size.height * 0.16f), size = Size(size.width * 0.68f, size.height * 0.68f), style = stroke)
    drawLine(color, Offset(size.width * 0.64f, size.height * 0.13f), Offset(size.width * 0.83f, size.height * 0.18f), style = stroke)
    drawLine(color, Offset(size.width * 0.78f, size.height * 0.34f), Offset(size.width * 0.83f, size.height * 0.18f), style = stroke)
    drawArc(color, startAngle = 215f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.18f, size.height * 0.18f), size = Size(size.width * 0.64f, size.height * 0.64f), style = stroke)
}

@Composable private fun GearIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.3.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawCircle(color, radius = size.minDimension * 0.23f, center = Offset(size.width / 2f, size.height / 2f), style = stroke)
    repeat(8) { i ->
        val angle = Math.toRadians((i * 45).toDouble())
        val sx = size.width / 2f + cos(angle).toFloat() * size.minDimension * 0.32f
        val sy = size.height / 2f + sin(angle).toFloat() * size.minDimension * 0.32f
        val ex = size.width / 2f + cos(angle).toFloat() * size.minDimension * 0.42f
        val ey = size.height / 2f + sin(angle).toFloat() * size.minDimension * 0.42f
        drawLine(color, Offset(sx, sy), Offset(ex, ey), style = stroke)
    }
}

@Composable private fun VectorAssetIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.25f, size.height * 0.72f), Offset(size.width * 0.66f, size.height * 0.30f), style = stroke)
    drawCircle(color, 2.dp.toPx(), Offset(size.width * 0.28f, size.height * 0.72f), style = stroke)
    drawCircle(color, 2.dp.toPx(), Offset(size.width * 0.66f, size.height * 0.30f), style = stroke)
    drawLine(color, Offset(size.width * 0.42f, size.height * 0.76f), Offset(size.width * 0.76f, size.height * 0.42f), style = stroke)
}

@Composable private fun BookIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawPath(Path().apply {
        moveTo(size.width * 0.18f, size.height * 0.22f)
        lineTo(size.width * 0.46f, size.height * 0.32f)
        lineTo(size.width * 0.46f, size.height * 0.78f)
        lineTo(size.width * 0.18f, size.height * 0.68f)
        close()
    }, color, style = stroke)
    drawPath(Path().apply {
        moveTo(size.width * 0.54f, size.height * 0.32f)
        lineTo(size.width * 0.82f, size.height * 0.22f)
        lineTo(size.width * 0.82f, size.height * 0.68f)
        lineTo(size.width * 0.54f, size.height * 0.78f)
        close()
    }, color, style = stroke)
}

@Composable private fun BellIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawArc(color, startAngle = 200f, sweepAngle = 140f, useCenter = false, topLeft = Offset(size.width * 0.16f, size.height * 0.18f), size = Size(size.width * 0.68f, size.height * 0.58f), style = stroke)
    drawLine(color, Offset(size.width * 0.16f, size.height * 0.68f), Offset(size.width * 0.84f, size.height * 0.68f), style = stroke)
    drawCircle(color, 1.4.dp.toPx(), Offset(size.width / 2f, size.height * 0.82f))
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

@Preview(showBackground = true, widthDp = 390, heightDp = 1380)
@Composable
private fun SkemaUProjectScreenPreview() {
    SkemaUProjectScreen()
}
