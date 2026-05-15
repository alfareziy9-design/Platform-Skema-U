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

private val ClientProfileBackground = Color(0xFFF6FBEE)
private val ClientProfileSurface = Color.White
private val ClientProfileMenuIconBg = Color(0xFFEAF0E2)
private val ClientProfileGreen = Color(0xFF318825)
private val ClientProfileDarkGreen = Color(0xFF106E09)
private val ClientProfileText = Color(0xFF181D15)
private val ClientProfileBorder = Color(0x4DBFCAB7)
private val ClientProfileChevron = Color(0xFFB5BDB0)
private val ClientProfileMuted = Color(0xFF94A3B8)
private val ClientProfileDanger = Color(0xFFC81D1D)

@Composable
fun SkemaUClientProfileScreen(
    modifier: Modifier = Modifier,
    @DrawableRes profileImageRes: Int = R.drawable.profil_client,
    @DrawableRes settingsIconRes: Int = R.drawable.icon_pengaturan
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ClientProfileBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ClientProfileBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                ClientProfileHero(profileImageRes = profileImageRes)
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ClientProfileMenuItem("Edit Profil") {
                        EditProfileIcon(ClientProfileDarkGreen, Modifier.size(20.dp))
                    }
                    ClientProfileMenuItem("Pengaturan Akun") {
                        AccountSettingsIcon(ClientProfileDarkGreen, Modifier.size(22.dp))
                    }
                    ClientProfileMenuItem("Riwayat Proyek") {
                        ProjectHistoryIcon(ClientProfileDarkGreen, Modifier.size(22.dp))
                    }
                    ClientProfileMenuItem("Pusat Bantuan") {
                        HelpIcon(ClientProfileDarkGreen, Modifier.size(20.dp))
                    }
                    ClientProfileMenuItem("Syarat & Ketentuan") {
                        TermsIcon(ClientProfileDarkGreen, Modifier.size(20.dp))
                    }
                }
                Spacer(Modifier.height(24.dp))
                ClientProfileLogoutButton()
                Spacer(Modifier.height(28.dp))
            }

            ClientProfileTopBar(settingsIconRes = settingsIconRes)
            ClientProfileBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ClientProfileTopBar(@DrawableRes settingsIconRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ClientProfileSurface)
            .border(1.dp, ClientProfileBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Skema-U",
            color = ClientProfileDarkGreen,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Image(
            painter = painterResource(settingsIconRes),
            contentDescription = "Pengaturan",
            modifier = Modifier.size(36.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ClientProfileHero(@DrawableRes profileImageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(80.dp), contentAlignment = Alignment.TopCenter) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(ClientProfileSurface)
                    .border(2.dp, ClientProfileGreen, CircleShape)
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(profileImageRes),
                    contentDescription = "Foto profil",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-2).dp, y = (-2).dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(ClientProfileGreen)
                    .border(2.dp, ClientProfileSurface, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                VerifiedBadgeIcon(ClientProfileSurface, Modifier.size(13.dp))
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = "Aqilla Rasya",
            color = ClientProfileText,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .height(25.dp)
                .clip(RoundedCornerShape(999.dp))
                .background(ClientProfileDarkGreen)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "VERIFIED CLIENT",
                color = ClientProfileSurface,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
private fun ClientProfileMenuItem(label: String, icon: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(ClientProfileSurface)
            .border(1.dp, ClientProfileBorder, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ClientProfileMenuIconBg),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Text(
                text = label,
                color = ClientProfileText,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        ChevronRightIcon(ClientProfileChevron, Modifier.width(8.dp).height(14.dp))
    }
}

@Composable
private fun ClientProfileLogoutButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(10.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x26000000), spotColor = Color(0x26000000))
            .clip(RoundedCornerShape(12.dp))
            .background(ClientProfileDanger),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogoutIcon(ClientProfileSurface, Modifier.size(20.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text = "Keluar",
            color = ClientProfileSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}

@Composable
private fun ClientProfileBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(ClientProfileSurface.copy(alpha = 0.95f))
            .border(1.dp, ClientProfileBorder)
            .shadow(8.dp, ambientColor = Color(0x14000000), spotColor = Color(0x14000000))
            .padding(start = 20.dp, end = 20.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClientProfileNavItem("BERANDA", selected = false) { HomeIcon(ClientProfileMuted, Modifier.size(20.dp)) }
        ClientProfileNavItem("KATALOG", selected = false) { GridIcon(ClientProfileMuted, Modifier.size(20.dp)) }
        ClientProfileNavItem("PESAN", selected = false) { MessageIcon(ClientProfileMuted, Modifier.size(20.dp)) }
        ClientProfileNavItem("STATUS", selected = false) { ClipboardIcon(ClientProfileMuted, Modifier.size(20.dp)) }
        ClientProfileNavItem("PROFIL", selected = true) { UserIcon(ClientProfileDarkGreen, Modifier.size(20.dp)) }
    }
}

@Composable
private fun ClientProfileNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(46.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(21.dp), contentAlignment = Alignment.Center) { icon() }
        Spacer(Modifier.height(3.dp))
        Text(
            text = label,
            color = if (selected) ClientProfileDarkGreen else ClientProfileMuted,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp
        )
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun VerifiedBadgeIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawCircle(color, 3.dp.toPx(), Offset(size.width * 0.38f, size.height * 0.36f), style = stroke)
    drawArc(color, 200f, 140f, false, Offset(size.width * 0.16f, size.height * 0.52f), Size(size.width * 0.45f, size.height * 0.32f), style = stroke)
    drawLine(color, Offset(size.width * 0.58f, size.height * 0.52f), Offset(size.width * 0.70f, size.height * 0.64f), style = stroke)
    drawLine(color, Offset(size.width * 0.70f, size.height * 0.64f), Offset(size.width * 0.88f, size.height * 0.42f), style = stroke)
}

@Composable private fun EditProfileIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawCircle(color, 3.dp.toPx(), Offset(size.width * 0.50f, size.height * 0.32f), style = stroke)
    drawArc(color, 200f, 140f, false, Offset(size.width * 0.25f, size.height * 0.50f), Size(size.width * 0.50f, size.height * 0.32f), style = stroke)
    drawLine(color, Offset(size.width * 0.72f, size.height * 0.22f), Offset(size.width * 0.88f, size.height * 0.22f), style = stroke)
}

@Composable private fun AccountSettingsIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawCircle(color, 3.dp.toPx(), Offset(size.width * 0.40f, size.height * 0.30f), style = stroke)
    drawArc(color, 200f, 140f, false, Offset(size.width * 0.17f, size.height * 0.48f), Size(size.width * 0.46f, size.height * 0.34f), style = stroke)
    drawCircle(color, 2.6.dp.toPx(), Offset(size.width * 0.76f, size.height * 0.63f), style = stroke)
    repeat(6) { index ->
        val angle = Math.toRadians((index * 60).toDouble())
        val start = Offset(
            x = size.width * 0.76f + kotlin.math.cos(angle).toFloat() * 4.0.dp.toPx(),
            y = size.height * 0.63f + kotlin.math.sin(angle).toFloat() * 4.0.dp.toPx()
        )
        val end = Offset(
            x = size.width * 0.76f + kotlin.math.cos(angle).toFloat() * 5.7.dp.toPx(),
            y = size.height * 0.63f + kotlin.math.sin(angle).toFloat() * 5.7.dp.toPx()
        )
        drawLine(color, start, end, style = stroke)
    }
}

@Composable private fun ProjectHistoryIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(4.dp.toPx(), 5.dp.toPx()), Size(14.dp.toPx(), 15.dp.toPx()), CornerRadius(1.6.dp.toPx()), style = stroke)
    drawRoundRect(color, Offset(7.dp.toPx(), 2.5.dp.toPx()), Size(8.dp.toPx(), 4.dp.toPx()), CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(8.dp.toPx(), 10.dp.toPx()), Offset(15.dp.toPx(), 10.dp.toPx()), style = stroke)
    drawLine(color, Offset(8.dp.toPx(), 14.dp.toPx()), Offset(13.dp.toPx(), 14.dp.toPx()), style = stroke)
}

@Composable private fun HelpIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(3.dp.toPx(), 3.dp.toPx()), Size(14.dp.toPx(), 14.dp.toPx()), CornerRadius(1.dp.toPx()), style = stroke)
    drawArc(color, 210f, 250f, false, Offset(7.dp.toPx(), 6.dp.toPx()), Size(6.dp.toPx(), 6.dp.toPx()), style = stroke)
    drawCircle(color, 0.9.dp.toPx(), Offset(size.width * 0.50f, size.height * 0.75f))
}

@Composable private fun TermsIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(4.dp.toPx(), 3.dp.toPx()), Size(13.dp.toPx(), 15.dp.toPx()), CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 7.dp.toPx()), Offset(14.dp.toPx(), 7.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 11.dp.toPx()), Offset(14.dp.toPx(), 11.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 15.dp.toPx()), Offset(12.dp.toPx(), 15.dp.toPx()), style = stroke)
}

@Composable private fun ChevronRightIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.22f, size.height * 0.12f), Offset(size.width * 0.78f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.78f, size.height * 0.50f), Offset(size.width * 0.22f, size.height * 0.88f), style = stroke)
}

@Composable private fun LogoutIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.18f), Offset(size.width * 0.18f, size.height * 0.82f), style = stroke)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.18f), Offset(size.width * 0.50f, size.height * 0.18f), style = stroke)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.82f), Offset(size.width * 0.50f, size.height * 0.82f), style = stroke)
    drawLine(color, Offset(size.width * 0.45f, size.height * 0.50f), Offset(size.width * 0.84f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.70f, size.height * 0.34f), Offset(size.width * 0.84f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.70f, size.height * 0.66f), Offset(size.width * 0.84f, size.height * 0.50f), style = stroke)
}

@Composable private fun HomeIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path().apply {
        moveTo(size.width * 0.14f, size.height * 0.50f)
        lineTo(size.width * 0.50f, size.height * 0.20f)
        lineTo(size.width * 0.86f, size.height * 0.50f)
        lineTo(size.width * 0.78f, size.height * 0.86f)
        lineTo(size.width * 0.22f, size.height * 0.86f)
        close()
    }
    drawPath(p, color, style = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

@Composable private fun GridIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), join = StrokeJoin.Round)
    repeat(2) { x ->
        repeat(2) { y ->
            drawRoundRect(
                color = color,
                topLeft = Offset((3 + x * 8).dp.toPx(), (3 + y * 8).dp.toPx()),
                size = Size(5.dp.toPx(), 5.dp.toPx()),
                cornerRadius = CornerRadius(0.8.dp.toPx()),
                style = stroke
            )
        }
    }
}

@Composable private fun MessageIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()), CornerRadius(2.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 15.dp.toPx()), Offset(5.dp.toPx(), 18.dp.toPx()), style = stroke)
}

@Composable private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(4.dp.toPx(), 4.dp.toPx()), Size(12.dp.toPx(), 14.dp.toPx()), CornerRadius(1.5.dp.toPx()), style = stroke)
    drawRoundRect(color, Offset(7.dp.toPx(), 2.5.dp.toPx()), Size(6.dp.toPx(), 4.dp.toPx()), CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 9.dp.toPx()), Offset(13.dp.toPx(), 9.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 13.dp.toPx()), Offset(12.dp.toPx(), 13.dp.toPx()), style = stroke)
}

@Composable private fun UserIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, radius = 3.2.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f))
    drawArc(
        color = color,
        startAngle = 205f,
        sweepAngle = 130f,
        useCenter = false,
        topLeft = Offset(size.width * 0.23f, size.height * 0.52f),
        size = Size(size.width * 0.54f, size.height * 0.34f),
        style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 884)
@Composable
private fun SkemaUClientProfileScreenPreview() {
    SkemaUClientProfileScreen()
}
