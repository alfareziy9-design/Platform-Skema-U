package com.upn.skema_u

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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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

private val OfferBackground = Color(0xFFF6FBEE)
private val OfferSurface = Color.White
private val OfferSurfaceMuted = Color(0xFFEAF0E2)
private val OfferDivider = Color(0x1A707A6A)
private val OfferBorder = Color(0x33707A6A)
private val OfferBorderSoft = Color(0x1A707A6A)
private val OfferTextPrimary = Color(0xFF181D15)
private val OfferTextSecondary = Color(0xFF404A3B)
private val OfferGreen = Color(0xFF106E09)
private val OfferGreenBright = Color(0xFF318825)
private val OfferGreenDeep = Color(0xFF45673C)
private val OfferGreenPanel = Color(0x4DC3EBB5)
private val OfferActionMuted = Color(0xFFE4EADD)
private val OfferPink = Color(0xFFA3326C)

@Composable
fun SkemaUOfferScreen(
    modifier: Modifier = Modifier,
    @DrawableRes contentImageRes: Int = R.drawable.konten2
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OfferBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 414.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(OfferBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 196.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                OfferHero(imageRes = contentImageRes)
                ProjectInvitationCard(imageRes = contentImageRes)
                ClientMessageSection()
                PortfolioInterestSection(imageRes = contentImageRes)
            }

            OfferTopAppBar()
            OfferActionBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun OfferHero(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp)
            .shadow(2.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x14000000), spotColor = Color(0x14000000))
            .clip(RoundedCornerShape(12.dp))
            .background(OfferSurface)
            .border(1.dp, OfferBorderSoft, RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Project invitation visual",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 12.dp)
                .shadow(4.dp, CircleShape, ambientColor = Color(0x22000000), spotColor = Color(0x22000000))
                .clip(CircleShape)
                .background(OfferGreenBright)
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = "NEW INVITATION",
                color = OfferSurface,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProjectInvitationCard(@DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(OfferSurface)
            .border(1.dp, OfferBorder, RoundedCornerShape(12.dp))
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, OfferBorder, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = "Client logo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Toko Berkah Mandiri",
                        color = OfferTextPrimary,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    VerifiedIcon(OfferGreen, Modifier.size(17.dp))
                }
                Text(
                    text = "Sektor: Retail UMKM",
                    color = OfferTextSecondary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Divider()

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            SmallCaps("PROJECT TITLE", color = OfferGreenDeep)
            Text(
                text = "Redesain Website UMKM",
                color = OfferGreen,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Kami membutuhkan tenaga kreatif\nuntuk memperbarui tampilan website\ne-commerce kami agar lebih modern,\nuser-friendly, dan mencerminkan\nidentitas brand yang berkelanjutan.",
            color = OfferTextSecondary,
            fontSize = 16.sp,
            lineHeight = 26.sp
        )

        DetailsBentoGrid()
    }
}

@Composable
private fun DetailsBentoGrid() {
    Column(
        modifier = Modifier.padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            DetailTile(
                modifier = Modifier.weight(1f).height(134.dp),
                icon = { MoneyIcon(OfferGreen, Modifier.width(22.dp).height(16.dp)) },
                label = "BUDGET",
                value = "Rp 100.000"
            )
            DetailTile(
                modifier = Modifier.weight(1f).height(134.dp),
                icon = { CalendarIcon(OfferGreen, Modifier.width(18.dp).height(20.dp)) },
                label = "DEADLINE",
                value = "14 Hari"
            )
        }
        DetailTile(
            modifier = Modifier.fillMaxWidth().height(106.dp),
            icon = { ExclamationIcon(OfferPink, Modifier.width(5.dp).height(18.dp)) },
            label = "PRIORITY",
            value = "High",
            leadingDot = OfferPink
        )
    }
}

@Composable
private fun DetailTile(
    modifier: Modifier,
    icon: @Composable () -> Unit,
    label: String,
    value: String,
    leadingDot: Color? = null
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(OfferSurfaceMuted)
            .border(1.dp, OfferBorderSoft, RoundedCornerShape(8.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.Top
    ) {
        icon()
        Spacer(Modifier.height(4.dp))
        SmallCaps(label)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
            if (leadingDot != null) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(leadingDot)
                )
            }
            Text(
                text = value,
                color = OfferTextPrimary,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ClientMessageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(OfferGreenPanel)
            .border(1.dp, OfferGreenDeep.copy(alpha = 0.20f), RoundedCornerShape(12.dp))
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            ChatBubbleIcon(OfferGreenDeep, Modifier.size(20.dp))
            SmallCaps("PESAN DARI KLIEN", color = OfferGreenDeep)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(OfferSurface.copy(alpha = 0.60f))
                .border(1.dp, Color(0x0D707A6A), RoundedCornerShape(8.dp))
                .padding(17.dp)
        ) {
            Text(
                text = "\"Halo, saya tertarik dengan\nportofolio Anda. Bisakah bantu\nsaya desain ulang website toko\nsaya?\"",
                color = OfferTextPrimary,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
private fun PortfolioInterestSection(@DrawableRes imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(OfferSurface)
            .border(1.dp, OfferBorderSoft, RoundedCornerShape(12.dp))
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SmallCaps("KLIEN TERTARIK DENGAN")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(158.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PortfolioPreview(imageRes = imageRes)
            PortfolioPreview(imageRes = imageRes)
        }
    }
}

@Composable
private fun PortfolioPreview(@DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(OfferSurface)
            .border(1.dp, OfferBorder, RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Portfolio preview",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun OfferTopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(OfferSurface)
            .border(1.dp, Color(0x4DE5E5E5))
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(32.dp), contentAlignment = Alignment.Center) {
                BackIcon(OfferGreen, Modifier.size(20.dp))
            }
            Text(
                text = "Penawaran Proyek Baru",
                color = OfferTextPrimary,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        MoreIcon(OfferTextPrimary, Modifier.width(4.dp).height(16.dp))
    }
}

@Composable
private fun OfferActionBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(OfferSurface.copy(alpha = 0.95f))
            .border(1.dp, OfferBorderSoft)
            .shadow(8.dp, ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
            .padding(start = 20.dp, end = 20.dp, top = 21.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ActionButton(
            text = "Terima Proyek",
            background = OfferGreen,
            foreground = OfferSurface,
            icon = { CheckCircleIcon(OfferSurface, Modifier.size(20.dp)) }
        )
        ActionButton(
            text = "Negosiasi/Tolak",
            background = OfferActionMuted,
            foreground = OfferGreenDeep,
            icon = { MessageSquareIcon(OfferGreenDeep, Modifier.size(20.dp)) }
        )
    }
}

@Composable
private fun ActionButton(
    text: String,
    background: Color,
    foreground: Color,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .then(
                if (background == OfferActionMuted) Modifier.border(1.dp, OfferBorderSoft, RoundedCornerShape(12.dp))
                else Modifier
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = foreground,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.width(8.dp))
        icon()
    }
}

@Composable
private fun Divider() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(OfferDivider)
    )
}

@Composable
private fun SmallCaps(text: String, color: Color = OfferTextSecondary) {
    Text(
        text = text,
        color = color,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun VectorIcon(modifier: Modifier, draw: DrawScope.() -> Unit) {
    Canvas(modifier = modifier) { draw() }
}

@Composable private fun BackIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawLine(color, Offset(size.width * 0.70f, size.height * 0.18f), Offset(size.width * 0.30f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.30f, size.height * 0.50f), Offset(size.width * 0.70f, size.height * 0.82f), style = stroke)
    drawLine(color, Offset(size.width * 0.32f, size.height * 0.50f), Offset(size.width * 0.86f, size.height * 0.50f), style = stroke)
}

@Composable private fun MoreIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    repeat(3) { i -> drawCircle(color, 1.5.dp.toPx(), Offset(size.width / 2f, (3 + i * 5).dp.toPx())) }
}

@Composable private fun VerifiedIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color)
    drawLine(OfferSurface, Offset(size.width * 0.27f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.68f), 1.5.dp.toPx(), StrokeCap.Round)
    drawLine(OfferSurface, Offset(size.width * 0.43f, size.height * 0.68f), Offset(size.width * 0.74f, size.height * 0.34f), 1.5.dp.toPx(), StrokeCap.Round)
}

@Composable private fun MoneyIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.08f, size.height * 0.18f), Size(size.width * 0.84f, size.height * 0.64f),
        CornerRadius(2.dp.toPx()), style = stroke)
    drawCircle(color, 2.dp.toPx(), Offset(size.width * 0.50f, size.height * 0.50f), style = stroke)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.30f), Offset(size.width * 0.28f, size.height * 0.30f), style = stroke)
    drawLine(color, Offset(size.width * 0.72f, size.height * 0.70f), Offset(size.width * 0.82f, size.height * 0.70f), style = stroke)
}

@Composable private fun CalendarIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.20f), Size(size.width * 0.72f, size.height * 0.66f),
        CornerRadius(2.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.14f, size.height * 0.40f), Offset(size.width * 0.86f, size.height * 0.40f), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.12f), Offset(size.width * 0.34f, size.height * 0.28f), style = stroke)
    drawLine(color, Offset(size.width * 0.66f, size.height * 0.12f), Offset(size.width * 0.66f, size.height * 0.28f), style = stroke)
}

@Composable private fun ExclamationIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawLine(color, Offset(size.width / 2f, 0f), Offset(size.width / 2f, size.height * 0.65f), 2.dp.toPx(), StrokeCap.Round)
    drawCircle(color, 1.8.dp.toPx(), Offset(size.width / 2f, size.height * 0.90f))
}

@Composable private fun ChatBubbleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.6.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.12f, size.height * 0.18f), Size(size.width * 0.72f, size.height * 0.52f),
        CornerRadius(2.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.70f), Offset(size.width * 0.20f, size.height * 0.88f), style = stroke)
}

@Composable private fun CheckCircleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color, style = Stroke(1.6.dp.toPx()))
    drawLine(color, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.6.dp.toPx(), StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.6.dp.toPx(), StrokeCap.Round)
}

@Composable private fun MessageSquareIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.7.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    drawRoundRect(color, Offset(size.width * 0.16f, size.height * 0.20f), Size(size.width * 0.68f, size.height * 0.52f),
        CornerRadius(2.dp.toPx()), style = stroke)
    drawLine(color, Offset(size.width * 0.34f, size.height * 0.72f), Offset(size.width * 0.26f, size.height * 0.88f), style = stroke)
}

@Preview(showBackground = true, widthDp = 414, heightDp = 1653)
@Composable
private fun SkemaUOfferScreenPreview() {
    SkemaUOfferScreen()
}
