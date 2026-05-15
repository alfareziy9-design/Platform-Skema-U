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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val MessagesBackground = Color(0xFFF6FBEE)
private val MessagesSurface = Color.White
private val MessagesSurfaceMuted = Color(0xFFF0F6E8)
private val MessagesChipMuted = Color(0xFFE4EADD)
private val MessagesGreen = Color(0xFF106E09)
private val MessagesGreenSoft = Color(0xFFC3EBB5)
private val MessagesGreenText = Color(0xFF496B40)
private val MessagesTextPrimary = Color(0xFF181D15)
private val MessagesTextSecondary = Color(0xFF404A3B)
private val MessagesMuted = Color(0xFF707A6A)
private val MessagesPlaceholder = Color(0xFF6B7280)
private val MessagesBorder = Color(0x4DBFCAB7)
private val MessagesBorderStrong = Color(0x80BFCAB7)

data class ClientMessageItem(
    val name: String,
    val tag: String,
    val preview: String,
    val time: String,
    val unreadCount: Int = 0,
    val online: Boolean = false,
    @DrawableRes val avatarRes: Int
)

@Composable
fun SkemaUClientMessagesScreen(
    modifier: Modifier = Modifier,
    @DrawableRes userProfileImageRes: Int = R.drawable.profil_client,
    @DrawableRes lampIconRes: Int = R.drawable.icon_lampu,
    messages: List<ClientMessageItem> = defaultClientMessages()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MessagesBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MessagesBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                SearchFilterSection()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    messages.forEach { ChatListItem(it) }
                    SuggestionBox(lampIconRes = lampIconRes)
                }
            }

            ClientMessagesTopAppBar(userProfileImageRes = userProfileImageRes)
            ClientMessagesBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ClientMessagesTopAppBar(@DrawableRes userProfileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MessagesSurface)
            .border(1.dp, MessagesBorder)
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
                    .background(Color(0xFFEAF0E2))
                    .border(1.dp, Color(0xFFBFCAB7), CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Skema-U",
                color = MessagesGreen,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(34.dp), contentAlignment = Alignment.Center) {
                SearchIcon(MessagesTextSecondary, Modifier.size(18.dp))
            }
            Box(Modifier.width(20.dp).height(32.dp), contentAlignment = Alignment.Center) {
                MoreIcon(MessagesTextSecondary, Modifier.width(4.dp).height(16.dp))
            }
        }
    }
}

@Composable
private fun SearchFilterSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MessagesSurface)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MessagesSurfaceMuted)
                .border(1.dp, MessagesBorder, RoundedCornerShape(12.dp))
                .padding(start = 16.dp, end = 17.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchIcon(MessagesTextSecondary.copy(alpha = 0.50f), Modifier.size(18.dp))
            Text(
                text = "Cari pesan atau nama mahasiswa...",
                color = MessagesPlaceholder,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MessageFilterChip("Semua", selected = true)
            MessageFilterChip("Belum Dibaca")
            MessageFilterChip("Logo Redesign")
            MessageFilterChip("Web Dev")
        }
    }
}

@Composable
private fun MessageFilterChip(text: String, selected: Boolean = false) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .clip(CircleShape)
            .background(if (selected) MessagesGreen else MessagesChipMuted)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) MessagesSurface else MessagesTextSecondary,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ChatListItem(item: ClientMessageItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (item.unreadCount > 0) 1.dp else 0.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color(0x12000000),
                spotColor = Color(0x12000000)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(MessagesSurface)
            .border(
                width = 1.dp,
                color = if (item.unreadCount > 0) MessagesBorderStrong else MessagesBorder,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(17.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Image(
                painter = painterResource(item.avatarRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEAF0E2))
                    .border(1.dp, MessagesBorder, CircleShape),
                contentScale = ContentScale.Crop
            )
            if (item.online) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(MessagesGreen)
                        .border(2.dp, MessagesSurface, CircleShape)
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = item.name,
                    color = MessagesTextPrimary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = if (item.unreadCount > 0) FontWeight.Bold else FontWeight.ExtraBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.time,
                    color = if (item.unreadCount > 0) MessagesGreen else MessagesTextSecondary.copy(alpha = 0.60f),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MessagesGreenSoft)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = item.tag.uppercase(),
                    color = MessagesGreenText,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = item.preview,
                color = MessagesTextSecondary,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = if (item.unreadCount > 0) FontWeight.Bold else FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (item.unreadCount > 0) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(MessagesGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.unreadCount.toString(),
                    color = MessagesSurface,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun SuggestionBox(@DrawableRes lampIconRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MessagesGreen.copy(alpha = 0.05f))
            .border(1.dp, MessagesGreen.copy(alpha = 0.30f), RoundedCornerShape(16.dp))
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(lampIconRes),
            contentDescription = "Icon lampu",
            modifier = Modifier
                .width(23.dp)
                .height(30.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Butuh Bantuan Lebih?",
            color = MessagesGreen,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Cari talenta mahasiswa baru untuk proyek\nkreatif Anda selanjutnya.",
            color = MessagesTextSecondary,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .height(40.dp)
                .shadow(6.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x1A000000), spotColor = Color(0x1A000000))
                .clip(RoundedCornerShape(12.dp))
                .background(MessagesGreen)
                .padding(horizontal = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cari Mahasiswa",
                color = MessagesSurface,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ClientMessagesBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(MessagesSurface.copy(alpha = 0.95f))
            .border(1.dp, Color(0xFFBFCAB7))
            .shadow(8.dp, ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
            .padding(start = 20.dp, end = 20.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        MessagesNavItem("BERANDA", selected = false) { HomeIcon(MessagesMuted, Modifier.size(18.dp)) }
        MessagesNavItem("KATALOG", selected = false) { GridIcon(MessagesTextSecondary, Modifier.size(18.dp)) }
        MessagesNavItem("PESAN", selected = true) { MessageIcon(MessagesGreen, Modifier.size(20.dp)) }
        MessagesNavItem("STATUS", selected = false) { ClipboardIcon(MessagesMuted, Modifier.size(20.dp)) }
        MessagesNavItem("PROFIL", selected = false) { UserIcon(MessagesMuted, Modifier.size(18.dp)) }
    }
}

@Composable
private fun MessagesNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) MessagesGreen else MessagesMuted,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

private fun defaultClientMessages(): List<ClientMessageItem> {
    return listOf(
        ClientMessageItem(
            name = "Novia Farah",
            tag = "Logo Redesign",
            preview = "Halo Pak, revisi logonya sudah selesai saya kerjakan. Bisa dicek?",
            time = "10:45",
            unreadCount = 2,
            online = true,
            avatarRes = R.drawable.profil_student_1
        ),
        ClientMessageItem(
            name = "Zerlina Anggun",
            tag = "Vidio Sinematik",
            preview = "Baik pak, akan saya update progressnya di dashboard besok pagi.",
            time = "Kemarin",
            avatarRes = R.drawable.profil_student_2
        ),
        ClientMessageItem(
            name = "Yusuf Fahrezi",
            tag = "Strategi Medsos",
            preview = "Draft pertama sudah saya kirimkan via email ya Pak.",
            time = "Senin",
            avatarRes = R.drawable.profil_student_3
        )
    )
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun SearchIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round)
    drawCircle(color, radius = size.minDimension * 0.30f, center = Offset(size.width * 0.44f, size.height * 0.42f), style = stroke)
    drawLine(color, Offset(size.width * 0.64f, size.height * 0.64f), Offset(size.width * 0.82f, size.height * 0.82f), style = stroke)
}

@Composable private fun MoreIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(3) { i -> drawCircle(color, 1.5.dp.toPx(), Offset(size.width / 2f, (3 + i * 5).dp.toPx())) }
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

@Preview(showBackground = true, widthDp = 390, heightDp = 939)
@Composable
private fun SkemaUClientMessagesScreenPreview() {
    SkemaUClientMessagesScreen()
}
