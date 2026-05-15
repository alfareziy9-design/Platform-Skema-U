package com.upn.skema_u

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ClientRegisterBackground = Color(0xFFF6FBEE)
private val ClientRegisterFooter = Color(0xFFF0F6E8)
private val ClientRegisterSurface = Color.White
private val ClientRegisterText = Color(0xFF181D15)
private val ClientRegisterBody = Color(0xFF404A3B)
private val ClientRegisterMuted = Color(0xFF707A6A)
private val ClientRegisterPlaceholder = Color(0xFF6B7280)
private val ClientRegisterBorder = Color(0xFFBFCAB7)
private val ClientRegisterSoftBorder = Color(0x1A707A6A)
private val ClientRegisterGreen = Color(0xFF106E09)
private val ClientRegisterButton = Color(0xFF318825)
private val ClientRegisterSelected = Color(0xFFC3EBB5)

@Composable
fun SkemaUClientRegisterScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ClientRegisterBackground)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 32.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 350.dp)
                    .fillMaxWidth()
                    .shadow(2.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x0D000000), spotColor = Color(0x0D000000))
                    .clip(RoundedCornerShape(12.dp))
                    .background(ClientRegisterSurface)
                    .border(1.dp, ClientRegisterSoftBorder, RoundedCornerShape(12.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ClientRegisterHeader()
                Spacer(Modifier.height(32.dp))
                ClientRoleSelector()
                Spacer(Modifier.height(24.dp))
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ClientRegisterInput("NAMA LENGKAP", "Masukkan nama lengkap Anda") {
                        PersonInputIcon(ClientRegisterMuted, Modifier.size(14.dp))
                    }
                    ClientRegisterInput("EMAIL", "contoh@perusahaan.com") {
                        MailInputIcon(ClientRegisterMuted, Modifier.width(15.dp).height(12.dp))
                    }
                    ClientRegisterInput("KATA SANDI", "********") {
                        LockInputIcon(ClientRegisterMuted, Modifier.width(12.dp).height(16.dp))
                    }
                    ClientRegisterInput("KONFIRMASI", "********") {
                        ConfirmInputIcon(ClientRegisterMuted, Modifier.size(15.dp))
                    }
                }
                Spacer(Modifier.height(20.dp))
                ClientTerms()
                Spacer(Modifier.height(24.dp))
                ClientPrimaryButton()
                Spacer(Modifier.height(32.dp))
                ClientLoginPrompt()
            }
        }
        ClientFooter()
    }
}

@Composable
private fun ClientRegisterHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Skema-U",
            color = ClientRegisterGreen,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Daftar Akun Klien",
            color = ClientRegisterText,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Lengkapi data di bawah untuk mulai\nmerekrut mahasiswa.",
            color = ClientRegisterBody,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ClientRoleSelector() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ClientRegisterLabel("PERAN SAYA")
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ClientRoleCard(
                label = "Penyedia",
                selected = false,
                modifier = Modifier.weight(1f)
            ) {
                ProviderIcon(ClientRegisterGreen, Modifier.width(22.dp).height(18.dp))
            }
            ClientRoleCard(
                label = "Klien",
                selected = true,
                modifier = Modifier.weight(1f)
            ) {
                BriefcaseIcon(ClientRegisterGreen, Modifier.width(20.dp).height(19.dp))
            }
        }
    }
}

@Composable
private fun ClientRoleCard(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(76.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) ClientRegisterSelected else ClientRegisterSurface)
            .border(if (selected) 2.dp else 1.dp, if (selected) ClientRegisterGreen else ClientRegisterBorder, RoundedCornerShape(8.dp))
            .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = ClientRegisterText,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ClientRegisterInput(
    label: String,
    placeholder: String,
    icon: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        ClientRegisterLabel(label)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(ClientRegisterSurface)
                .border(1.dp, Color(0x4D707A6A), RoundedCornerShape(8.dp))
                .padding(start = 15.dp, end = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.width(18.dp), contentAlignment = Alignment.CenterStart) { icon() }
            Text(
                text = placeholder,
                color = ClientRegisterPlaceholder,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun ClientRegisterLabel(text: String) {
    Text(
        text = text,
        color = ClientRegisterBody,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.6.sp
    )
}

@Composable
private fun ClientTerms() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(ClientRegisterSurface)
                .border(1.dp, Color(0x4D707A6A), RoundedCornerShape(4.dp))
        )
        Text(
            text = buildAnnotatedString {
                append("Saya menyetujui ")
                withStyle(SpanStyle(color = ClientRegisterGreen, fontWeight = FontWeight.SemiBold)) {
                    append("Syarat & Ketentuan")
                }
                append("\nserta ")
                withStyle(SpanStyle(color = ClientRegisterGreen, fontWeight = FontWeight.SemiBold)) {
                    append("Kebijakan Privasi")
                }
                append(" yang berlaku.")
            },
            color = ClientRegisterBody,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun ClientPrimaryButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(1.dp, RoundedCornerShape(8.dp), ambientColor = Color(0x0D000000), spotColor = Color(0x0D000000))
            .clip(RoundedCornerShape(8.dp))
            .background(ClientRegisterButton),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Buat Akun Klien",
            color = ClientRegisterSurface,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(8.dp))
        ArrowRightIcon(ClientRegisterSurface, Modifier.size(16.dp))
    }
}

@Composable
private fun ClientLoginPrompt() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ClientRegisterSoftBorder)
        )
        Spacer(Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sudah memiliki akun? ",
                color = ClientRegisterBody,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Masuk di sini",
                color = ClientRegisterGreen,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ClientFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ClientRegisterFooter)
            .border(1.dp, ClientRegisterSoftBorder)
            .padding(horizontal = 20.dp, vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2024 SKEMA-U SUSTAINABLE INNOVATION PLATFORM.\nSEMUA HAK DILINDUNGI.",
            color = ClientRegisterMuted,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.6.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable
private fun ProviderIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.7.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    val capPath = Path().apply {
        moveTo(size.width * 0.08f, size.height * 0.36f)
        lineTo(size.width * 0.50f, size.height * 0.10f)
        lineTo(size.width * 0.92f, size.height * 0.36f)
        lineTo(size.width * 0.50f, size.height * 0.62f)
        close()
    }
    drawPath(capPath, color, style = stroke)

    val body = Path().apply {
        moveTo(size.width * 0.26f, size.height * 0.48f)
        lineTo(size.width * 0.26f, size.height * 0.76f)
        quadraticBezierTo(size.width * 0.50f, size.height * 0.94f, size.width * 0.74f, size.height * 0.76f)
        lineTo(size.width * 0.74f, size.height * 0.48f)
    }
    drawPath(body, color, style = stroke)

    drawLine(
        color = color,
        start = Offset(size.width * 0.92f, size.height * 0.36f),
        end = Offset(size.width * 0.92f, size.height * 0.68f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
private fun BriefcaseIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.7.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawRoundRect(color, Offset(size.width * 0.12f, size.height * 0.32f), Size(size.width * 0.76f, size.height * 0.54f), CornerRadius(1.8.dp.toPx()), style = stroke)
    drawRoundRect(color, Offset(size.width * 0.36f, size.height * 0.12f), Size(size.width * 0.28f, size.height * 0.20f), CornerRadius(1.dp.toPx()), style = stroke)

    drawLine(
        color = color,
        start = Offset(size.width * 0.12f, size.height * 0.52f),
        end = Offset(size.width * 0.88f, size.height * 0.52f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round
    )

    drawRoundRect(color, Offset(size.width * 0.44f, size.height * 0.45f), Size(size.width * 0.12f, size.height * 0.14f), CornerRadius(0.8.dp.toPx()), style = stroke)
}


@Composable private fun PersonInputIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round)
    drawCircle(color, radius = size.minDimension * 0.22f, center = Offset(size.width / 2f, size.height * 0.34f), style = stroke)
    drawArc(color, 205f, 130f, false, Offset(size.width * 0.22f, size.height * 0.54f), Size(size.width * 0.56f, size.height * 0.34f), style = stroke)
}

@Composable
private fun MailInputIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.4.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawRoundRect(color, Offset(1.dp.toPx(), 2.dp.toPx()), Size(size.width - 2.dp.toPx(), size.height - 4.dp.toPx()), CornerRadius(1.2.dp.toPx()), style = stroke)

    drawLine(color, Offset(2.dp.toPx(), 3.dp.toPx()), Offset(size.width / 2f, size.height * 0.58f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width - 2.dp.toPx(), 3.dp.toPx()), Offset(size.width / 2f, size.height * 0.58f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
}

@Composable private fun LockInputIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.4.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.45f), Size(size.width * 0.64f, size.height * 0.45f), CornerRadius(1.2.dp.toPx()), style = stroke)
    drawArc(color, 180f, 180f, false, Offset(size.width * 0.26f, size.height * 0.10f), Size(size.width * 0.48f, size.height * 0.55f), style = stroke)
}

@Composable
private fun ConfirmInputIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.5.dp.toPx()
    val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)

    drawArc(color, 40f, 280f, false, Offset(size.width * 0.15f, size.height * 0.15f), Size(size.width * 0.70f, size.height * 0.70f), style = stroke)

    drawLine(color, Offset(size.width * 0.16f, size.height * 0.42f), Offset(size.width * 0.02f, size.height * 0.42f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.16f, size.height * 0.42f), Offset(size.width * 0.14f, size.height * 0.26f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.38f, size.height * 0.53f), Offset(size.width * 0.49f, size.height * 0.64f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.49f, size.height * 0.64f), Offset(size.width * 0.70f, size.height * 0.38f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
}

@Composable
private fun ArrowRightIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val strokeWidth = 1.8.dp.toPx()

    drawLine(color, Offset(size.width * 0.12f, size.height * 0.50f), Offset(size.width * 0.84f, size.height * 0.50f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.62f, size.height * 0.26f), Offset(size.width * 0.84f, size.height * 0.50f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.62f, size.height * 0.74f), Offset(size.width * 0.84f, size.height * 0.50f), strokeWidth = strokeWidth, cap = StrokeCap.Round)
}

@Preview(showBackground = true, widthDp = 390, heightDp = 1096)
@Composable
private fun SkemaUClientRegisterScreenPreview() {
    SkemaUClientRegisterScreen()
}
