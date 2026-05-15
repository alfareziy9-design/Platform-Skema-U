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

private val RegisterBackground = Color(0xFFF6FBEE)
private val RegisterSurface = Color.White
private val RegisterText = Color(0xFF181D15)
private val RegisterBody = Color(0xFF404A3B)
private val RegisterPlaceholder = Color(0xFF6B7280)
private val RegisterBorder = Color(0xFFBFCAB7)
private val RegisterGreen = Color(0xFF106E09)
private val RegisterSelected = Color(0xFFC3EBB5)

@Composable
fun SkemaURegisterScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RegisterBackground)
            .padding(horizontal = 16.dp, vertical = 25.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 358.dp)
                .fillMaxWidth()
                .height(905.dp)
                .shadow(2.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x0D000000), spotColor = Color(0x0D000000))
                .clip(RoundedCornerShape(12.dp))
                .background(RegisterSurface)
                .border(1.dp, Color(0x4DBFCAB7), RoundedCornerShape(12.dp))
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            RegisterHeader()
            Spacer(Modifier.height(24.dp))
            RegisterRoleSelection()
            Spacer(Modifier.height(24.dp))
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                RegisterInput("NAMA LENGKAP", "Masukkan nama lengkap Anda")
                RegisterInput("ID UNIVERSITAS / ID PAJAK", "NIM atau ID Perusahaan")
                RegisterInput("ALAMAT EMAIL", "nama@universitas.ac.id")
                RegisterInput("KATA SANDI", "********")
            }
            Spacer(Modifier.height(16.dp))
            RegisterTerms()
            Spacer(Modifier.height(16.dp))
            RegisterPrimaryButton()
            Spacer(Modifier.height(24.dp))
            RegisterDivider()
            Spacer(Modifier.height(16.dp))
            RegisterLoginButton()
        }
    }
}

@Composable
private fun RegisterHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Daftar Skema-U",
            color = RegisterText,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Buka peluang profesional dan temukan\ntalenta terbaik.",
            color = RegisterBody,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}

@Composable
private fun RegisterRoleSelection() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        RegisterLabel("SAYA INGIN BERGABUNG SEBAGAI")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            RegisterRoleCard(
                label = "Penyedia",
                selected = true,
                modifier = Modifier.weight(1f)
            ) {
                ProviderIcon(RegisterGreen, Modifier.width(22.dp).height(18.dp))
            }
            RegisterRoleCard(
                label = "Klien",
                selected = false,
                modifier = Modifier.weight(1f)
            ) {
                ClientBriefcaseIcon(RegisterGreen, Modifier.width(20.dp).height(19.dp))
            }
        }
    }
}

@Composable
private fun RegisterRoleCard(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .height(76.dp)
            .shadow(if (selected) 1.dp else 0.dp, RoundedCornerShape(8.dp), ambientColor = RegisterGreen, spotColor = RegisterGreen)
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) RegisterSelected else RegisterSurface)
            .border(if (selected) 2.dp else 1.dp, if (selected) RegisterGreen else RegisterBorder, RoundedCornerShape(8.dp))
            .padding(17.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            color = RegisterText,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RegisterInput(label: String, placeholder: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        RegisterLabel(label)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(RegisterSurface)
                .border(1.dp, RegisterBorder, RoundedCornerShape(8.dp))
                .padding(horizontal = 17.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = placeholder,
                color = RegisterPlaceholder,
                fontSize = 16.sp,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun RegisterLabel(text: String) {
    Text(
        text = text,
        color = RegisterBody,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.6.sp
    )
}

@Composable
private fun RegisterTerms() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(RegisterSurface)
                .border(1.dp, RegisterBorder, RoundedCornerShape(4.dp))
        )
        Text(
            text = buildAnnotatedString {
                append("Dengan membuat akun, saya\nmenyetujui ")
                withStyle(SpanStyle(color = RegisterGreen)) { append("Ketentuan Layanan") }
                append(" dan\n")
                withStyle(SpanStyle(color = RegisterGreen)) { append("Kebijakan Privasi") }
                append(" Skema-U.")
            },
            color = RegisterBody,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}

@Composable
private fun RegisterPrimaryButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(10.dp, RoundedCornerShape(8.dp), ambientColor = Color(0x26000000), spotColor = Color(0x26000000))
            .clip(RoundedCornerShape(8.dp))
            .background(RegisterGreen),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Buat Akun",
            color = RegisterSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun RegisterDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(RegisterBorder)
        )
        Text(
            text = "SUDAH PUNYA AKUN?",
            color = RegisterBody,
            fontSize = 10.sp,
            lineHeight = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(RegisterBorder)
        )
    }
}

@Composable
private fun RegisterLoginButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(RegisterSurface)
            .border(1.dp, RegisterBorder, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Masuk",
            color = RegisterText,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.SemiBold,
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
    val sw = 1.7.dp.toPx()
    val capRound = StrokeCap.Round
    val stroke = Stroke(sw, cap = capRound, join = StrokeJoin.Round)

    // Bagian Atas (Berbentuk Diamond/Topi)
    val cap = Path().apply {
        moveTo(size.width * 0.08f, size.height * 0.36f)
        lineTo(size.width * 0.50f, size.height * 0.10f)
        lineTo(size.width * 0.92f, size.height * 0.36f)
        lineTo(size.width * 0.50f, size.height * 0.62f)
        close()
    }
    // drawPath sudah benar menggunakan style
    drawPath(cap, color, style = stroke)

    // Bagian Bawah (Body)
    val body = Path().apply {
        moveTo(size.width * 0.26f, size.height * 0.48f)
        lineTo(size.width * 0.26f, size.height * 0.76f)
        quadraticBezierTo(
            size.width * 0.50f, size.height * 0.94f,
            size.width * 0.74f, size.height * 0.76f
        )
        lineTo(size.width * 0.74f, size.height * 0.48f)
    }
    // drawPath sudah benar menggunakan style
    drawPath(body, color, style = stroke)

    // INI YANG DIPERBAIKI: drawLine menggunakan strokeWidth dan cap secara langsung
    drawLine(
        color = color,
        start = Offset(size.width * 0.92f, size.height * 0.36f),
        end = Offset(size.width * 0.92f, size.height * 0.68f),
        strokeWidth = sw,
        cap = capRound
    )
}

@Composable
private fun ClientBriefcaseIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val sw = 1.7.dp.toPx()
    val capRound = StrokeCap.Round
    val stroke = Stroke(sw, cap = capRound, join = StrokeJoin.Round)

    // Body Tas (Rounded Rect)
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.12f, size.height * 0.32f),
        size = Size(size.width * 0.76f, size.height * 0.54f),
        cornerRadius = CornerRadius(1.8.dp.toPx()),
        style = stroke
    )

    // Pegangan Tas (Atas)
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.36f, size.height * 0.12f),
        size = Size(size.width * 0.28f, size.height * 0.20f),
        cornerRadius = CornerRadius(1.dp.toPx()),
        style = stroke
    )

    // GARIS TENGAH (DIREVISI: Menghapus style = stroke)
    drawLine(
        color = color,
        start = Offset(size.width * 0.12f, size.height * 0.52f),
        end = Offset(size.width * 0.88f, size.height * 0.52f),
        strokeWidth = sw,
        cap = capRound
    )

    // Pengunci Tas (Kecil di tengah)
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.44f, size.height * 0.45f),
        size = Size(size.width * 0.12f, size.height * 0.14f),
        cornerRadius = CornerRadius(0.8.dp.toPx()),
        style = stroke
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 955)
@Composable
private fun SkemaURegisterScreenPreview() {
    SkemaURegisterScreen()
}
