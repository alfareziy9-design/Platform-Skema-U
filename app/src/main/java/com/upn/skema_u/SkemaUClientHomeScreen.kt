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

private val ClientHomeBackground = Color(0xFFF6FBEE)
private val ClientHomeSurface = Color.White
private val ClientHomeGreen = Color(0xFF106E09)
private val ClientHomeGreenBright = Color(0xFF318825)
private val ClientHomeTextPrimary = Color(0xFF181D15)
private val ClientHomeTextSecondary = Color(0xFF404A3B)
private val ClientHomeMuted = Color(0xFF707A6A)
private val ClientHomeInputText = Color(0xFF6B7280)
private val ClientHomeBorder = Color(0xFFE2E8F0)
private val ClientHomeSoftBorder = Color(0xFFF1F5F9)
private val ClientHomeNavBorder = Color(0xFFBFCAB7)
private val ClientHomeStar = Color(0xFFFFA000)

@Composable
fun SkemaUClientHomeScreen(
    modifier: Modifier = Modifier,
    @DrawableRes userProfileImageRes: Int = R.drawable.profil_client,
    @DrawableRes studentOneImageRes: Int = R.drawable.profil_student_1,
    @DrawableRes studentTwoImageRes: Int = R.drawable.profil_student_2,
    @DrawableRes serviceOneImageRes: Int = R.drawable.container_1,
    @DrawableRes serviceTwoImageRes: Int = R.drawable.container_2
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ClientHomeBackground),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .widthIn(max = 430.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(ClientHomeBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GreetingSection()
                SearchBar()
                CreativeServicesSection(
                    serviceOneImageRes = serviceOneImageRes,
                    serviceTwoImageRes = serviceTwoImageRes
                )
                TopRatedStudentsSection(
                    studentOneImageRes = studentOneImageRes,
                    studentTwoImageRes = studentTwoImageRes
                )
                TrustRibbon()
            }

            ClientHomeTopAppBar(userProfileImageRes = userProfileImageRes)
            ClientHomeBottomNavBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun ClientHomeTopAppBar(@DrawableRes userProfileImageRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(ClientHomeSurface)
            .border(1.dp, ClientHomeBorder)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(userProfileImageRes),
                contentDescription = "Foto profil pengguna",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, ClientHomeBorder, CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Skema-U",
                color = ClientHomeGreen,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            SearchIcon(Color(0xFF64748B), Modifier.size(18.dp))
        }
    }
}

@Composable
private fun GreetingSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "SELAMAT DATANG KEMBALI",
            color = ClientHomeTextSecondary,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hello, AQIL!",
            color = ClientHomeTextPrimary,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Temukan talenta mahasiswa terbaik untuk proyek anda!",
            color = ClientHomeTextSecondary,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .shadow(1.dp, RoundedCornerShape(12.dp), ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
            .clip(RoundedCornerShape(12.dp))
            .background(ClientHomeSurface)
            .border(1.dp, ClientHomeBorder, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 17.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchIcon(ClientHomeMuted, Modifier.size(18.dp))
            Text(
                text = "Cari logo, situs web, penulisan...",
                color = ClientHomeInputText,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun CreativeServicesSection(
    @DrawableRes serviceOneImageRes: Int,
    @DrawableRes serviceTwoImageRes: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "Creative Services",
                color = ClientHomeTextPrimary,
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "VIEW ALL",
                color = ClientHomeGreen,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ServiceCarouselCard(
                title = "DESAIN GRAFIS",
                imageRes = serviceOneImageRes
            )
            ServiceCarouselCard(
                title = "WEB DEVELOPMENT",
                imageRes = serviceTwoImageRes
            )
        }
    }
}

@Composable
private fun ServiceCarouselCard(title: String, @DrawableRes imageRes: Int) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, ClientHomeSoftBorder, RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.60f)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        Text(
            text = title,
            color = ClientHomeSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        )
    }
}

@Composable
private fun TopRatedStudentsSection(
    @DrawableRes studentOneImageRes: Int,
    @DrawableRes studentTwoImageRes: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Top Rated Students",
            color = ClientHomeTextPrimary,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold
        )
        StudentCard(
            imageRes = studentOneImageRes,
            name = "Novia F",
            expertise = "Seni Visual • UI/UX Design",
            rating = "4.9",
            reviews = "42 reviews",
            price = "Rp. 15.000",
            buttonText = "Rekrut"
        )
        StudentCard(
            imageRes = studentTwoImageRes,
            name = "Fahrezi",
            expertise = "Ilmu Komputer • Full-stack",
            rating = "5.0",
            reviews = "18 reviews",
            price = "RP. 30.000",
            buttonText = "REKRUT"
        )
    }
}

@Composable
private fun StudentCard(
    @DrawableRes imageRes: Int,
    name: String,
    expertise: String,
    rating: String,
    reviews: String,
    price: String,
    buttonText: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(ClientHomeSurface)
            .border(1.dp, ClientHomeBorder, RoundedCornerShape(12.dp))
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, ClientHomeSoftBorder, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(96.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = name,
                            color = ClientHomeTextPrimary,
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        VerifiedBadge()
                    }
                    Text(
                        text = expertise,
                        color = ClientHomeGreen,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "UPNVJATIM",
                        color = ClientHomeTextSecondary,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                    StarIcon(ClientHomeStar, Modifier.size(12.dp))
                    Text(rating, color = ClientHomeTextPrimary, fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.SemiBold)
                    Text("($reviews)", color = ClientHomeTextSecondary, fontSize = 16.sp, lineHeight = 24.sp)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ClientHomeSoftBorder)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "MULAI DARI",
                    color = ClientHomeTextSecondary,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = price,
                    color = ClientHomeTextPrimary,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ClientHomeGreen)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonText,
                    color = ClientHomeSurface,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun VerifiedBadge() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(ClientHomeGreen)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CheckCircleIcon(ClientHomeSurface, Modifier.size(11.dp))
        Text(
            text = "TERVERIFIKASI",
            color = ClientHomeSurface,
            fontSize = 11.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun TrustRibbon() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(ClientHomeGreenBright)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "UMKM PERCAYA",
            color = ClientHomeSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(8.dp))
        ShieldIcon(ClientHomeSurface, Modifier.size(14.dp))
        Spacer(Modifier.width(8.dp))
        Text(
            text = "TALENTA TERVERIFIKASI",
            color = ClientHomeSurface,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ClientHomeBottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(ClientHomeSurface.copy(alpha = 0.95f))
            .border(1.dp, ClientHomeNavBorder)
            .shadow(8.dp, ambientColor = Color(0x12000000), spotColor = Color(0x12000000))
            .padding(start = 20.dp, end = 20.dp, top = 1.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClientHomeNavItem("BERANDA", selected = true) { HomeIcon(ClientHomeGreen, Modifier.size(18.dp)) }
        ClientHomeNavItem("KATALOG", selected = false) { GridIcon(ClientHomeMuted, Modifier.size(18.dp)) }
        ClientHomeNavItem("PESAN", selected = false) { MessageIcon(ClientHomeMuted, Modifier.size(20.dp)) }
        ClientHomeNavItem("STATUS", selected = false) { ClipboardIcon(ClientHomeMuted, Modifier.size(20.dp)) }
        ClientHomeNavItem("PROFIL", selected = false) { UserIcon(ClientHomeMuted, Modifier.size(18.dp)) }
    }
}

@Composable
private fun ClientHomeNavItem(label: String, selected: Boolean, icon: @Composable () -> Unit) {
    Column(
        modifier = Modifier.height(45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(20.dp), contentAlignment = Alignment.Center) { icon() }
        Text(
            text = label,
            color = if (selected) ClientHomeGreen else ClientHomeMuted,
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

@Composable private fun SearchIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val stroke = Stroke(1.8.dp.toPx(), cap = StrokeCap.Round)
    drawCircle(color, radius = size.minDimension * 0.30f, center = Offset(size.width * 0.44f, size.height * 0.42f), style = stroke)
    drawLine(color, Offset(size.width * 0.64f, size.height * 0.64f), Offset(size.width * 0.82f, size.height * 0.82f), style = stroke)
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
        val x = cx + kotlin.math.cos(angle).toFloat() * r
        val y = cy + kotlin.math.sin(angle).toFloat() * r
        if (i == 0) p.moveTo(x, y) else p.lineTo(x, y)
    }
    p.close()
    drawPath(p, color)
}

@Composable private fun CheckCircleIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    drawCircle(color)
    drawLine(ClientHomeGreen, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.67f), 1.2.dp.toPx(), StrokeCap.Round)
    drawLine(ClientHomeGreen, Offset(size.width * 0.43f, size.height * 0.67f), Offset(size.width * 0.74f, size.height * 0.33f), 1.2.dp.toPx(), StrokeCap.Round)
}

@Composable private fun ShieldIcon(color: Color, modifier: Modifier) = VectorIcon(modifier) {
    val p = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.06f)
        lineTo(size.width * 0.82f, size.height * 0.20f)
        lineTo(size.width * 0.76f, size.height * 0.62f)
        cubicTo(size.width * 0.70f, size.height * 0.80f, size.width * 0.58f, size.height * 0.90f, size.width * 0.50f, size.height * 0.96f)
        cubicTo(size.width * 0.42f, size.height * 0.90f, size.width * 0.30f, size.height * 0.80f, size.width * 0.24f, size.height * 0.62f)
        lineTo(size.width * 0.18f, size.height * 0.20f)
        close()
    }
    drawPath(p, color, style = Stroke(1.5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round))
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

@Preview(showBackground = true, widthDp = 430, heightDp = 1276)
@Composable
private fun SkemaUClientHomeScreenPreview() {
    SkemaUClientHomeScreen()
}
