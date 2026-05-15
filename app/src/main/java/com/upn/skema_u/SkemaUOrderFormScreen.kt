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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val OrderFormBackground = Color(0xFFF6FBEE)
private val OrderFormSurface = Color.White
private val OrderFormGreen = Color(0xFF106E09)
private val OrderFormText = Color(0xFF181D15)
private val OrderFormBody = Color(0xFF404A3B)
private val OrderFormMuted = Color(0xFF94A3B8)
private val OrderFormSubtle = Color(0xFF707A6A)
private val OrderFormBorder = Color(0xFFBFCAB7)
private val OrderFormUploadBg = Color(0xFFF0F6E8)

@Composable
fun SkemaUOrderFormScreen(
    modifier: Modifier = Modifier,
    @DrawableRes profileImageRes: Int = R.drawable.profil_client,
    @DrawableRes containerImageRes: Int = R.drawable.container,
    @DrawableRes searchIconRes: Int = R.drawable.icon_cari,
    @DrawableRes uploadIconRes: Int = R.drawable.icon_upload
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrderFormBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 390.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(OrderFormBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ServiceSummaryCard(
                    profileImageRes = profileImageRes,
                    containerImageRes = containerImageRes
                )
                OrderFormContent(uploadIconRes = uploadIconRes)
            }

            OrderFormTopBar(
                profileImageRes = profileImageRes,
                searchIconRes = searchIconRes
            )
            OrderFormBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun OrderFormTopBar(
    @DrawableRes profileImageRes: Int,
    @DrawableRes searchIconRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(OrderFormSurface)
            .border(1.dp, OrderFormBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(profileImageRes),
                contentDescription = "Foto profil",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEAF0E2))
                    .border(1.dp, OrderFormBorder, CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Skema-U",
                color = OrderFormGreen,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        Image(
            painter = painterResource(searchIconRes),
            contentDescription = "Cari",
            modifier = Modifier.size(18.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
private fun ServiceSummaryCard(
    @DrawableRes profileImageRes: Int,
    @DrawableRes containerImageRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(14.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x1F106E09), spotColor = Color(0x1F106E09))
            .clip(RoundedCornerShape(12.dp))
            .background(OrderFormSurface)
            .border(1.dp, OrderFormBorder, RoundedCornerShape(12.dp))
            .padding(17.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(containerImageRes),
            contentDescription = "Thumbnail layanan",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, OrderFormBorder, RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "IDENTITAS VISUAL",
                    color = OrderFormGreen,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                Text(
                    text = "Paket Redesain Web",
                    color = OrderFormText,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(profileImageRes),
                    contentDescription = "Foto talenta",
                    modifier = Modifier
                        .width(18.dp)
                        .height(20.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEAF0E2)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = buildAnnotatedString {
                        append("Novia • ")
                        withStyle(SpanStyle(color = OrderFormGreen, fontWeight = FontWeight.SemiBold)) {
                            append("Profesional\nTerverifikasi")
                        }
                    },
                    color = OrderFormBody,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun OrderFormContent(@DrawableRes uploadIconRes: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Ringkasan Proyek",
                color = OrderFormText,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Beri tahu Novia tentang kebutuhan dan tujuan\nproyek anda untuk memulai kontrak.",
                color = OrderFormBody,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            LabeledArea(
                label = "DESKRIPSI PROYEK",
                modifier = Modifier.height(141.dp)
            ) {
                Text(
                    text = "Jelaskan kebutuhan, nilai brand, dan\ndeliverable spesifik yang Anda\nbutuhkan...",
                    color = OrderFormMuted,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }
            LabeledArea(
                label = "TENGGAT WAKTU",
                modifier = Modifier.height(58.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "mm/dd/yyyy",
                        color = OrderFormText,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer(Modifier.weight(1f))
                    CalendarIcon(OrderFormBody, Modifier.size(20.dp))
                }
            }
            LabeledArea(
                label = "ESTIMASI ANGGARAN (IDR)",
                modifier = Modifier.height(58.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Rp",
                        color = OrderFormBody.copy(alpha = 0.70f),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(18.dp))
                    Text(
                        text = "0",
                        color = Color(0xFF6B7280),
                        fontSize = 16.sp,
                        lineHeight = 20.sp
                    )
                }
            }
            FileAttachment(uploadIconRes = uploadIconRes)
            TrustRibbon()
            SubmitButton()
        }
    }
}

@Composable
private fun LabeledArea(
    label: String,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FieldLabel(label)
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(OrderFormSurface)
                .border(1.dp, OrderFormBorder, RoundedCornerShape(8.dp))
                .padding(17.dp),
            contentAlignment = Alignment.TopStart
        ) {
            content()
        }
    }
}

@Composable
private fun FieldLabel(label: String) {
    Text(
        text = label,
        color = OrderFormBody,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.6.sp
    )
}

@Composable
private fun FileAttachment(@DrawableRes uploadIconRes: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FieldLabel("LAMPIRAN (OPSIONAL)")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(147.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(OrderFormUploadBg)
                .border(2.dp, OrderFormBorder, RoundedCornerShape(12.dp))
                .padding(34.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(uploadIconRes),
                contentDescription = "Upload",
                modifier = Modifier
                    .width(33.dp)
                    .height(32.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.height(13.dp))
            Text(
                text = buildAnnotatedString {
                    append("Tarik file ke sini atau ")
                    withStyle(SpanStyle(color = OrderFormGreen, fontWeight = FontWeight.SemiBold)) {
                        append("Pilih File")
                    }
                },
                color = OrderFormBody,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "PDF, PNG, JPG (MAKS 10MB)",
                color = OrderFormSubtle,
                fontSize = 10.sp,
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun TrustRibbon() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(OrderFormGreen.copy(alpha = 0.05f))
            .border(1.dp, OrderFormBorder)
            .padding(horizontal = 20.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShieldIcon(OrderFormGreen, Modifier.width(10.dp).height(12.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text = "PEMBAYARAN AMAN • KUALITAS TERJAMIN •\nTALENTA MAHASISWA",
            color = OrderFormGreen,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.1.sp
        )
    }
}

@Composable
private fun SubmitButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(14.dp, RoundedCornerShape(8.dp), ambientColor = Color(0x1F106E09), spotColor = Color(0x1F106E09))
            .clip(RoundedCornerShape(8.dp))
            .background(OrderFormGreen),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Kirim Penawaran Proyek",
            color = OrderFormSurface,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(8.dp))
        SendIcon(OrderFormSurface, Modifier.width(19.dp).height(16.dp))
    }
}

@Composable
private fun OrderFormBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(OrderFormSurface.copy(alpha = 0.95f))
            .border(1.dp, OrderFormBorder)
            .shadow(8.dp, ambientColor = Color(0x14000000), spotColor = Color(0x14000000))
            .padding(start = 29.dp, end = 29.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OrderFormNavItem("BERANDA", selected = false) { HomeIcon(OrderFormBody, Modifier.size(19.dp)) }
        OrderFormNavItem("KATALOG", selected = true) { GridIcon(OrderFormGreen, Modifier.size(20.dp)) }
        OrderFormNavItem("PESAN", selected = false) { MessageIcon(OrderFormBody, Modifier.size(20.dp)) }
        OrderFormNavItem("STATUS", selected = false) { ClipboardIcon(OrderFormBody, Modifier.size(20.dp)) }
        OrderFormNavItem("PROFIL", selected = false) { UserIcon(OrderFormBody, Modifier.size(18.dp)) }
    }
}

@Composable
private fun OrderFormNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(21.dp), contentAlignment = Alignment.Center) { icon() }
        Spacer(Modifier.height(3.dp))
        Text(
            text = label,
            color = if (selected) OrderFormGreen else OrderFormBody,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun CalendarIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 13.dp.toPx()), CornerRadius(2.dp.toPx()), style = stroke)
    drawLine(color, Offset(3.dp.toPx(), 8.dp.toPx()), Offset(17.dp.toPx(), 8.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 2.5.dp.toPx()), Offset(7.dp.toPx(), 5.5.dp.toPx()), style = stroke)
    drawLine(color, Offset(13.dp.toPx(), 2.5.dp.toPx()), Offset(13.dp.toPx(), 5.5.dp.toPx()), style = stroke)
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
    drawPath(path, color, style = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
    drawLine(color, Offset(size.width * 0.36f, size.height * 0.50f), Offset(size.width * 0.47f, size.height * 0.62f), Stroke(1.4.dp.toPx(), cap = StrokeCap.Round))
    drawLine(color, Offset(size.width * 0.47f, size.height * 0.62f), Offset(size.width * 0.66f, size.height * 0.38f), Stroke(1.4.dp.toPx(), cap = StrokeCap.Round))
}

@Composable private fun SendIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    val path = Path().apply {
        moveTo(size.width * 0.08f, size.height * 0.08f)
        lineTo(size.width * 0.92f, size.height * 0.50f)
        lineTo(size.width * 0.08f, size.height * 0.92f)
        lineTo(size.width * 0.22f, size.height * 0.50f)
        close()
    }
    drawPath(path, color, style = stroke)
    drawLine(color, Offset(size.width * 0.22f, size.height * 0.50f), Offset(size.width * 0.60f, size.height * 0.50f), style = stroke)
}

@Composable private fun HomeIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val path = Path().apply {
        moveTo(size.width * 0.14f, size.height * 0.50f)
        lineTo(size.width * 0.50f, size.height * 0.20f)
        lineTo(size.width * 0.86f, size.height * 0.50f)
        lineTo(size.width * 0.78f, size.height * 0.86f)
        lineTo(size.width * 0.22f, size.height * 0.86f)
        close()
    }
    drawPath(path, color, style = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

@Composable private fun GridIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(2) { x ->
        repeat(2) { y ->
            drawRect(color, Offset((3 + x * 8).dp.toPx(), (3 + y * 8).dp.toPx()), Size(5.dp.toPx(), 5.dp.toPx()))
        }
    }
}

@Composable private fun MessageIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawRoundRect(color, Offset(3.dp.toPx(), 4.dp.toPx()), Size(14.dp.toPx(), 11.dp.toPx()), CornerRadius(2.dp.toPx()), style = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
}

@Composable private fun ClipboardIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(4.dp.toPx(), 4.dp.toPx()), Size(12.dp.toPx(), 14.dp.toPx()), CornerRadius(1.5.dp.toPx()), style = stroke)
    drawRoundRect(color, Offset(7.dp.toPx(), 2.5.dp.toPx()), Size(6.dp.toPx(), 4.dp.toPx()), CornerRadius(1.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 9.dp.toPx()), Offset(13.dp.toPx(), 9.dp.toPx()), style = stroke)
    drawLine(color, Offset(7.dp.toPx(), 13.dp.toPx()), Offset(12.dp.toPx(), 13.dp.toPx()), style = stroke)
}

@Composable private fun UserIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round)
    drawCircle(color, radius = 3.dp.toPx(), center = Offset(size.width / 2f, size.height * 0.34f), style = stroke)
    drawArc(color, 205f, 130f, false, Offset(size.width * 0.24f, size.height * 0.52f), Size(size.width * 0.52f, size.height * 0.34f), style = stroke)
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1269)
@Composable
private fun SkemaUOrderFormScreenPreview() {
    SkemaUOrderFormScreen()
}
