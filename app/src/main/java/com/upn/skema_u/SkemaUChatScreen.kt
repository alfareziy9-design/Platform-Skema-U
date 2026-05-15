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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ChatSurface = Color.White
private val ChatProjectBanner = Color(0xFFF0F6E8)
private val ChatReceived = Color(0xFFEAF0E2)
private val ChatSent = Color(0xFF318825)
private val ChatGreen = Color(0xFF106E09)
private val ChatOnline = Color(0xFF22C55E)
private val ChatTextPrimary = Color(0xFF181D15)
private val ChatTextSecondary = Color(0xFF404A3B)
private val ChatTextMuted = Color(0xFF64748B)
private val ChatTime = Color(0xFF94A3B8)
private val ChatBorder = Color(0xFFE2E8F0)
private val ChatSoftBorder = Color(0x33BFCAB7)
private val ChatButtonBorder = Color(0xFFBFCAB7)

@Composable
fun SkemaUChatScreen(
    modifier: Modifier = Modifier,
    @DrawableRes profileImageRes: Int = R.drawable.profil_klien,
    @DrawableRes pdfIconRes: Int = R.drawable.icon_pdf
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2)),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 394.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ChatSurface)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ChatTopAppBar(profileImageRes = profileImageRes)
                ProjectContextBanner()
                ChatHistory(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    pdfIconRes = pdfIconRes
                )
                ChatInputArea()
            }
        }
    }
}

@Composable
private fun ChatTopAppBar(@DrawableRes profileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ChatSurface)
            .border(1.dp, ChatBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            BackIcon(Color(0xFF64748B), Modifier.size(18.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(profileImageRes),
                    contentDescription = "Profil klien",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(ChatReceived),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(
                        text = "Aqilla Rasya",
                        color = ChatTextPrimary,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(ChatOnline)
                        )
                        Text(
                            text = "Aktif",
                            color = ChatTextMuted,
                            fontSize = 11.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            PhoneIcon(Color(0xFF64748B), Modifier.size(19.dp))
            MoreIcon(Color(0xFF64748B), Modifier.width(4.dp).height(16.dp))
        }
    }
}

@Composable
private fun ProjectContextBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .background(ChatProjectBanner)
            .border(1.dp, ChatSoftBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            ClipboardIcon(ChatGreen, Modifier.width(15.dp).height(17.dp))
            Text(
                text = "Redesain Logo - Kopi Kenangan",
                color = ChatTextPrimary,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(ChatGreen)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AKTIF",
                color = ChatSurface,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ChatHistory(modifier: Modifier, @DrawableRes pdfIconRes: Int) {
    Column(
        modifier = modifier
            .background(ChatSurface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        DateSeparator()
        ReceivedMessage(
            text = "Halo Novia! Saya sudah cek\nmoodboard yang dikirim kemarin. Saya\nsuka arah tipografinya. Bisa coba\npilihan palet warna yang lebih earthy?",
            time = "09:15"
        )
        SentMessage(
            text = "Tentu, Pak Aqill. Saya siapkan tiga opsi\nwarna bumi (terracotta, sage, dan deep\nclay) sore ini ya. Bagaimana?",
            time = "09:18"
        )
        ReceivedFileMessage(pdfIconRes = pdfIconRes)
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun DateSeparator() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color(0xFFF8FAFC))
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text(
                text = "Hari Ini, 24 Okt",
                color = ChatTime,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ReceivedMessage(text: String, time: String) {
    Column(horizontalAlignment = Alignment.Start) {
        Box(
            modifier = Modifier
                .width(298.dp)
                .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .background(ChatReceived)
                .border(1.dp, ChatSoftBorder, RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .padding(start = 17.dp, end = 23.dp, top = 17.dp, bottom = 17.dp)
        ) {
            Text(text = text, color = ChatTextPrimary, fontSize = 14.sp, lineHeight = 20.sp)
        }
        Text(
            text = time,
            color = ChatTime,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
    }
}

@Composable
private fun SentMessage(text: String, time: String) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Box(
            modifier = Modifier
                .width(298.dp)
                .shadow(1.dp, RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .background(ChatSent)
                .padding(start = 16.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
        ) {
            Text(text = text, color = ChatSurface, fontSize = 14.sp, lineHeight = 20.sp)
        }
        Row(
            modifier = Modifier.padding(end = 4.dp, top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = time, color = ChatTime, fontSize = 11.sp, lineHeight = 12.sp, fontWeight = FontWeight.SemiBold)
            CheckCircleIcon(ChatGreen, Modifier.size(12.dp))
        }
    }
}

@Composable
private fun ReceivedFileMessage(@DrawableRes pdfIconRes: Int) {
    Column(horizontalAlignment = Alignment.Start) {
        Column(
            modifier = Modifier
                .width(298.dp)
                .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .background(ChatReceived)
                .border(1.dp, ChatSoftBorder, RoundedCornerShape(topStart = 0.dp, topEnd = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp))
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bagus! Ini panduan brand dari anak\nperusahaan untuk referensi Anda.",
                color = ChatTextPrimary,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(ChatSurface)
                    .border(1.dp, Color(0x66BFCAB7), RoundedCornerShape(8.dp))
                    .padding(9.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFFEF2F2)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(pdfIconRes),
                        contentDescription = "PDF",
                        modifier = Modifier.size(20.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Panduan_Brand_V2.pdf",
                        color = ChatTextPrimary,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "2.4 MB",
                        color = ChatTime,
                        fontSize = 11.sp,
                        lineHeight = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                DownloadIcon(ChatGreen, Modifier.size(16.dp))
            }
        }
        Text(
            text = "09:22",
            color = ChatTime,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
    }
}

@Composable
private fun ChatInputArea() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(ChatSurface)
            .border(1.dp, ChatBorder)
            .padding(start = 20.dp, end = 20.dp, top = 21.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            QuickActionChip("GALERI") { GalleryIcon(ChatTextSecondary, Modifier.size(14.dp)) }
            QuickActionChip("DOKUMEN") { DocumentIcon(ChatTextSecondary, Modifier.width(12.dp).height(15.dp)) }
            QuickActionChip("TAGIHAN") { BillIcon(ChatTextSecondary, Modifier.width(17.dp).height(12.dp)) }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.Bottom) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(ChatProjectBanner)
                    .border(1.dp, ChatSoftBorder, RoundedCornerShape(16.dp))
                    .padding(start = 17.dp, end = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ketik pesan...",
                    color = Color(0xFF6B7280),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.weight(1f)
                )
                SmileIcon(ChatTime, Modifier.size(20.dp))
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .shadow(6.dp, CircleShape, ambientColor = Color(0x1A000000), spotColor = Color(0x1A000000))
                    .clip(CircleShape)
                    .background(ChatGreen),
                contentAlignment = Alignment.Center
            ) {
                SendIcon(ChatSurface, Modifier.width(19.dp).height(16.dp))
            }
        }
    }
}

@Composable
private fun QuickActionChip(text: String, icon: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .height(28.dp)
            .clip(CircleShape)
            .border(1.dp, ChatButtonBorder, CircleShape)
            .padding(horizontal = 17.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Text(
            text = text,
            color = ChatTextSecondary,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun BackIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.72f, size.height * 0.12f), Offset(size.width * 0.24f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.24f, size.height * 0.50f), Offset(size.width * 0.72f, size.height * 0.88f), style = stroke)
    drawLine(color, Offset(size.width * 0.26f, size.height * 0.50f), Offset(size.width * 0.88f, size.height * 0.50f), style = stroke)
}

@Composable private fun PhoneIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val p = Path().apply {
        moveTo(size.width * 0.25f, size.height * 0.10f)
        cubicTo(size.width * 0.12f, size.height * 0.20f, size.width * 0.16f, size.height * 0.58f, size.width * 0.46f, size.height * 0.82f)
        cubicTo(size.width * 0.70f, size.height * 1.00f, size.width * 0.88f, size.height * 0.82f, size.width * 0.82f, size.height * 0.68f)
    }
    drawPath(p, color, style = stroke)
}

@Composable private fun MoreIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(3) { i -> drawCircle(color, 1.5.dp.toPx(), Offset(size.width / 2f, (3 + i * 5).dp.toPx())) }
}

@Composable private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.18f), Size(size.width * 0.64f, size.height * 0.70f), androidx.compose.ui.geometry.CornerRadius(1.5.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.34f), Offset(size.width * 0.66f, size.height * 0.34f), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.50f), Offset(size.width * 0.66f, size.height * 0.50f), style = stroke)
}

@Composable private fun CheckCircleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color)
    drawLine(ChatSurface, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.2.dp.toPx(), StrokeCap.Round)
    drawLine(ChatSurface, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.2.dp.toPx(), StrokeCap.Round)
}

@Composable private fun DownloadIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.50f, size.height * 0.12f), Offset(size.width * 0.50f, size.height * 0.68f), style = stroke)
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.48f), Offset(size.width * 0.50f, size.height * 0.70f), style = stroke)
    drawLine(color, Offset(size.width * 0.72f, size.height * 0.48f), Offset(size.width * 0.50f, size.height * 0.70f), style = stroke)
    drawLine(color, Offset(size.width * 0.22f, size.height * 0.88f), Offset(size.width * 0.78f, size.height * 0.88f), style = stroke)
}

@Composable private fun GalleryIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRect(color, Offset(size.width * 0.10f, size.height * 0.18f), Size(size.width * 0.80f, size.height * 0.64f), style = stroke)
    drawCircle(color, 1.4.dp.toPx(), Offset(size.width * 0.32f, size.height * 0.38f), style = stroke)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.76f), Offset(size.width * 0.44f, size.height * 0.54f), style = stroke)
    drawLine(color, Offset(size.width * 0.44f, size.height * 0.54f), Offset(size.width * 0.68f, size.height * 0.76f), style = stroke)
}

@Composable private fun DocumentIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.08f), Size(size.width * 0.64f, size.height * 0.84f), androidx.compose.ui.geometry.CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.42f), Offset(size.width * 0.66f, size.height * 0.42f), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.58f), Offset(size.width * 0.66f, size.height * 0.58f), style = stroke)
}

@Composable private fun BillIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.08f, size.height * 0.18f), Size(size.width * 0.84f, size.height * 0.64f), androidx.compose.ui.geometry.CornerRadius(1.dp.toPx()), style = stroke)
    drawCircle(color, 1.8.dp.toPx(), Offset(size.width * 0.50f, size.height * 0.50f), style = stroke)
}

@Composable private fun SmileIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round)
    drawCircle(color, style = stroke)
    drawCircle(color, 1.dp.toPx(), Offset(size.width * 0.36f, size.height * 0.40f))
    drawCircle(color, 1.dp.toPx(), Offset(size.width * 0.64f, size.height * 0.40f))
    drawArc(color, startAngle = 25f, sweepAngle = 130f, useCenter = false, topLeft = Offset(size.width * 0.32f, size.height * 0.42f), size = Size(size.width * 0.36f, size.height * 0.30f), style = stroke)
}

@Composable private fun SendIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path().apply {
        moveTo(0f, size.height * 0.10f)
        lineTo(size.width, size.height * 0.50f)
        lineTo(0f, size.height * 0.90f)
        lineTo(size.width * 0.22f, size.height * 0.50f)
        close()
    }
    drawPath(p, color)
}

@Preview(showBackground = true, widthDp = 394, heightDp = 854)
@Composable
private fun SkemaUChatScreenPreview() {
    SkemaUChatScreen()
}
