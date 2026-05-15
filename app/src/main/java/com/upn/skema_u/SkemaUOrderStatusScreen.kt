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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val OrderStatusBackground = Color(0xFFF6FBEE)
private val OrderStatusSurface = Color.White
private val OrderStatusPanel = Color(0xFFF0F6E8)
private val OrderStatusTrack = Color(0xFFEAF0E2)
private val OrderStatusGreen = Color(0xFF318825)
private val OrderStatusDarkGreen = Color(0xFF106E09)
private val OrderStatusOlive = Color(0xFF45673C)
private val OrderStatusSuccessBg = Color(0xFFC3EBB5)
private val OrderStatusSuccessText = Color(0xFF496B40)
private val OrderStatusDangerBg = Color(0xFFFFDAD6)
private val OrderStatusDangerText = Color(0xFF93000A)
private val OrderStatusTextPrimary = Color(0xFF181D15)
private val OrderStatusTextSecondary = Color(0xFF404A3B)
private val OrderStatusMuted = Color(0xFF707A6A)
private val OrderStatusBorder = Color(0x33707A6A)
private val OrderStatusNavMuted = Color(0xFF707A6A)

@Composable
fun SkemaUOrderStatusScreen(
    modifier: Modifier = Modifier,
    @DrawableRes userProfileImageRes: Int = R.drawable.profil_client,
    @DrawableRes bellIconRes: Int = R.drawable.icon_lonceng,
    @DrawableRes projectOneImageRes: Int = R.drawable.profil_student_1,
    @DrawableRes projectTwoImageRes: Int = R.drawable.profil_student_2,
    @DrawableRes projectThreeImageRes: Int = R.drawable.profil_student_4
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrderStatusBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 430.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(OrderStatusBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Manajemen Proyek",
                    color = OrderStatusTextPrimary,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                SegmentedTabs()
                WaitingPaymentCard(imageRes = projectOneImageRes)
                InProgressCard(imageRes = projectTwoImageRes)
                FinishedReviewCard(imageRes = projectThreeImageRes)
            }

            OrderStatusTopAppBar(
                userProfileImageRes = userProfileImageRes,
                bellIconRes = bellIconRes
            )
            OrderStatusBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun OrderStatusTopAppBar(
    @DrawableRes userProfileImageRes: Int,
    @DrawableRes bellIconRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(OrderStatusSurface)
            .border(1.dp, Color(0x4DBFCAB7))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(userProfileImageRes),
                contentDescription = "Foto profil",
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .border(1.dp, OrderStatusBorder, CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Skema-U",
                color = OrderStatusGreen,
                fontSize = 18.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Image(
            painter = painterResource(bellIconRes),
            contentDescription = "Notifikasi",
            modifier = Modifier
                .width(31.dp)
                .height(34.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun SegmentedTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(OrderStatusPanel)
            .border(1.dp, Color(0x1A707A6A), RoundedCornerShape(12.dp))
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .shadow(1.dp, RoundedCornerShape(8.dp), ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
                .clip(RoundedCornerShape(8.dp))
                .background(OrderStatusSurface)
                .border(1.dp, Color(0x0D707A6A), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Proyek Berjalan", color = OrderStatusGreen, fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text("Riwayat", color = Color(0xFF71717A), fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun WaitingPaymentCard(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(222.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(OrderStatusSurface)
            .border(1.dp, OrderStatusBorder, RoundedCornerShape(12.dp))
    ) {
        StatusBadge(
            text = "MENUNGGU PEMBAYARAN",
            background = OrderStatusDangerBg,
            foreground = OrderStatusDangerText,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 7.dp, end = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 22.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProjectThumb(imageRes = imageRes, size = 64.dp)
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Redesain WEB UMKM",
                    color = OrderStatusTextPrimary,
                    fontSize = 20.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text("Novia Farah", color = OrderStatusTextSecondary, fontSize = 14.sp, lineHeight = 20.sp)
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 21.dp)
                .border(1.dp, Color.Transparent)
                .padding(top = 17.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SmallCaps("BIAYA PROYEK")
                Text(
                    text = "Rp 100.000",
                    color = OrderStatusTextPrimary,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            PrimaryActionButton(text = "Bayar Sekarang", color = OrderStatusGreen, width = 142.dp)
        }
    }
}

@Composable
private fun InProgressCard(@DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(OrderStatusSurface)
            .border(1.dp, OrderStatusBorder, RoundedCornerShape(12.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                ProjectThumb(imageRes = imageRes, width = 48.dp, height = 64.dp)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Desain Logo UMKM",
                        color = OrderStatusTextPrimary,
                        fontSize = 20.sp,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text("Zerlina Anggun", color = OrderStatusTextSecondary, fontSize = 14.sp, lineHeight = 20.sp)
                }
            }
            StatusBadge(
                text = "SEDANG\nDIKERJAKAN",
                background = OrderStatusSuccessBg,
                foreground = OrderStatusSuccessText
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                SmallCaps("PROGRESS")
                Text("65%", color = OrderStatusGreen, fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
            }
            ProgressBar(0.65f)
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CalendarIcon(OrderStatusTextSecondary, Modifier.size(12.dp))
                Text(
                    text = buildAnnotatedString {
                        append("Target Selesai: ")
                        withStyle(SpanStyle(color = OrderStatusTextPrimary, fontWeight = FontWeight.SemiBold)) {
                            append("24 Okt 2023")
                        }
                    },
                    color = OrderStatusTextSecondary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun FinishedReviewCard(@DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(OrderStatusSurface)
            .border(1.dp, OrderStatusBorder, RoundedCornerShape(12.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                ProjectThumb(imageRes = imageRes, width = 61.dp, height = 64.dp)
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Content\nPlanner",
                        color = OrderStatusTextPrimary,
                        fontSize = 20.sp,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text("Rafli Saputra", color = OrderStatusTextSecondary, fontSize = 14.sp, lineHeight = 20.sp)
                }
            }
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(OrderStatusDarkGreen.copy(alpha = 0.10f))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckCircleIcon(OrderStatusDarkGreen, Modifier.size(10.dp))
                Text(
                    text = "SELESAI",
                    color = OrderStatusDarkGreen,
                    fontSize = 11.sp,
                    lineHeight = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(OrderStatusPanel)
                .border(1.dp, Color(0x0D707A6A), RoundedCornerShape(8.dp))
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "\"Draf konten minggu ke-1 & ke-2 sudah\nsaya unggah. Silakan tinjau kembali\nsebelum saya lanjutkan.\"",
                color = OrderStatusTextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontStyle = FontStyle.Italic
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OrderStatusSurface)
                        .border(1.dp, OrderStatusBorder, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tinjau Hasil", color = OrderStatusTextPrimary, fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
                }
                PrimaryActionButton(text = "Approve", color = OrderStatusOlive, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun ProjectThumb(@DrawableRes imageRes: Int, size: androidx.compose.ui.unit.Dp) {
    ProjectThumb(imageRes = imageRes, width = size, height = size)
}

@Composable
private fun ProjectThumb(@DrawableRes imageRes: Int, width: androidx.compose.ui.unit.Dp, height: androidx.compose.ui.unit.Dp) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFDFE4D7)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun StatusBadge(text: String, background: Color, foreground: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(background)
            .padding(horizontal = 12.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = foreground,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PrimaryActionButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    width: androidx.compose.ui.unit.Dp? = null
) {
    Box(
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier)
            .height(40.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp), ambientColor = color.copy(alpha = 0.16f), spotColor = color.copy(alpha = 0.16f))
            .clip(RoundedCornerShape(8.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = OrderStatusSurface, fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(CircleShape)
            .background(OrderStatusTrack)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(8.dp)
                .clip(CircleShape)
                .background(OrderStatusGreen)
        )
    }
}

@Composable
private fun SmallCaps(text: String) {
    Text(
        text = text,
        color = OrderStatusTextSecondary,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun OrderStatusBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(OrderStatusSurface.copy(alpha = 0.95f))
            .border(1.dp, Color(0xFFBFCAB7))
            .shadow(8.dp, ambientColor = OrderStatusDarkGreen.copy(alpha = 0.08f), spotColor = OrderStatusDarkGreen.copy(alpha = 0.08f))
            .padding(start = 20.dp, end = 20.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderStatusNavItem("BERANDA", selected = false) { HomeIcon(OrderStatusNavMuted, Modifier.size(18.dp)) }
        OrderStatusNavItem("KATALOG", selected = false) { GridIcon(OrderStatusNavMuted, Modifier.size(18.dp)) }
        OrderStatusNavItem("PESAN", selected = false) { MessageIcon(OrderStatusNavMuted, Modifier.size(20.dp)) }
        OrderStatusNavItem("STATUS", selected = true) { ClipboardIcon(OrderStatusDarkGreen, Modifier.size(20.dp)) }
        OrderStatusNavItem("PROFIL", selected = false) { UserIcon(OrderStatusNavMuted, Modifier.size(18.dp)) }
    }
}

@Composable
private fun OrderStatusNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) OrderStatusDarkGreen else OrderStatusNavMuted,
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

@Composable private fun CalendarIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.20f), Size(size.width * 0.72f, size.height * 0.66f), androidx.compose.ui.geometry.CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.14f, size.height * 0.40f), Offset(size.width * 0.86f, size.height * 0.40f), style = stroke)
}

@Composable private fun CheckCircleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color)
    drawLine(OrderStatusSurface, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.dp.toPx(), StrokeCap.Round)
    drawLine(OrderStatusSurface, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.dp.toPx(), StrokeCap.Round)
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
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()), androidx.compose.ui.geometry.CornerRadius(2.dp.toPx()), style = Stroke(1.5.dp.toPx()))
}

@Composable private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(4.dp.toPx(), 4.dp.toPx()), Size(12.dp.toPx(), 14.dp.toPx()), androidx.compose.ui.geometry.CornerRadius(1.5.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 8.dp.toPx()), Offset(13.dp.toPx(), 8.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 12.dp.toPx()), Offset(13.dp.toPx(), 12.dp.toPx()), style = stroke)
}

@Composable private fun UserIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, radius = 3.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f), style = Stroke(1.5.dp.toPx()))
    drawArc(color, startAngle = 205f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.24f, size.height * 0.52f), size = Size(size.width * 0.52f, size.height * 0.36f), style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round))
}

@Preview(showBackground = true, widthDp = 430, heightDp = 1119)
@Composable
private fun SkemaUOrderStatusScreenPreview() {
    SkemaUOrderStatusScreen()
}
