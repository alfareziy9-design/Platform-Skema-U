package com.upn.skema_u

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

private val DashboardGreen = Color(0xFF0B7A1B)
private val DashboardGreenDark = Color(0xFF087012)
private val DashboardSoftGreen = Color(0xFFF4FBEE)
private val DashboardChipGreen = Color(0xFFEAF8E8)
private val DashboardBorder = Color(0xFFE1EBDD)
private val DashboardText = Color(0xFF1B211A)
private val DashboardMuted = Color(0xFF697467)
private val DashboardInactive = Color(0xFF9AA2B0)

data class DashboardStat(
    val value: String,
    val label: String,
    val type: StatType,
)

enum class StatType {
    Completed,
    Rating,
}

data class QuickAccessItem(
    val title: String,
    val type: QuickAccessType,
)

enum class QuickAccessType {
    AddService,
    Portfolio,
    Statistic,
}

data class ActiveProject(
    val badge: String,
    val title: String,
    val client: String,
    val deadline: String,
    val progress: Float,
    val urgent: Boolean = false,
)

data class DashboardNotification(
    val title: String,
    val time: String,
    val type: NotificationType,
)

enum class NotificationType {
    Order,
    Revision,
    Payment,
}

enum class ProviderBottomMenu(val label: String) {
    Home("BERANDA"),
    Services("JASA SAYA"),
    Projects("PROJEK"),
    Chat("CHAT"),
    Profile("PROFIL"),
}

private val dummyStats = listOf(
    DashboardStat("24", "PROYEK SELESAI", StatType.Completed),
    DashboardStat("4.9/5.0", "RATING RATA-RATA", StatType.Rating),
)

private val dummyQuickAccess = listOf(
    QuickAccessItem("TAMBAH JASA", QuickAccessType.AddService),
    QuickAccessItem("PORTFOLIO", QuickAccessType.Portfolio),
    QuickAccessItem("STATISTIK", QuickAccessType.Statistic),
)

private val dummyProjects = listOf(
    ActiveProject(
        badge = "LOGO DESIGN",
        title = "Rebranding UMKM \"Kopi Kenangan\"",
        client = "Pak Aqilla Rasya",
        deadline = "2 Hari Lagi",
        progress = 0.75f,
    ),
    ActiveProject(
        badge = "VIDEO EDITING",
        title = "Video Promo \"Keripik Tempe\"",
        client = "Pak Budi Santoso",
        deadline = "Besok",
        progress = 0.30f,
        urgent = true,
    ),
)

private val dummyNotifications = listOf(
    DashboardNotification(
        title = "Pesanan Baru: 1x Landing Page\nUMKM dari 'Bakery Mama'.",
        time = "2 jam yang lalu",
        type = NotificationType.Order,
    ),
    DashboardNotification(
        title = "Revisi Masuk: Revisi warna logo\nuntuk 'Kopi Senja'.",
        time = "5 jam yang lalu",
        type = NotificationType.Revision,
    ),
    DashboardNotification(
        title = "Pembayaran: Dana proyek 'Poster\nEvent' telah dicairkan.",
        time = "Kemarin",
        type = NotificationType.Payment,
    ),
)

@Composable
fun PenyediaDashboardScreen(
    modifier: Modifier = Modifier,
    providerName: String = "Novia",
    balance: String = "Rp 300.000",
    stats: List<DashboardStat> = dummyStats,
    quickAccessItems: List<QuickAccessItem> = dummyQuickAccess,
    activeProjects: List<ActiveProject> = dummyProjects,
    notifications: List<DashboardNotification> = dummyNotifications,
    selectedMenu: ProviderBottomMenu = ProviderBottomMenu.Home,
    onAvatarClick: () -> Unit = {},
    onQuickAccessClick: (QuickAccessItem) -> Unit = {},
    onProjectClick: (ActiveProject) -> Unit = {},
    onSeeAllProjectsClick: () -> Unit = {},
    onNotificationClick: (DashboardNotification) -> Unit = {},
    onBottomMenuClick: (ProviderBottomMenu) -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = DashboardSoftGreen,
        topBar = {
            DashboardHeader(onAvatarClick = onAvatarClick)
        },
        bottomBar = {
            ProviderBottomBar(
                selectedMenu = selectedMenu,
                onMenuClick = onBottomMenuClick,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(start = 28.dp, end = 28.dp, top = 28.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            item {
                GreetingSection(providerName = providerName)
            }

            item {
                BalanceCard(balance = balance)
            }

            item {
                StatsRow(stats = stats)
            }

            item {
                SectionTitle(title = "Akses Cepat")
            }

            item {
                QuickAccessRow(
                    items = quickAccessItems,
                    onItemClick = onQuickAccessClick,
                )
            }

            item {
                SectionHeader(
                    title = "Proyek Aktif",
                    actionText = "LIHAT SEMUA",
                    onActionClick = onSeeAllProjectsClick,
                )
            }

            itemsIndexed(activeProjects) { _, project ->
                ActiveProjectCard(
                    project = project,
                    onClick = { onProjectClick(project) },
                )
            }

            item {
                SectionTitle(title = "Notifikasi Terbaru")
            }

            item {
                NotificationCard(
                    notifications = notifications,
                    onNotificationClick = onNotificationClick,
                )
            }
        }
    }
}

@Composable
private fun DashboardHeader(onAvatarClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(horizontal = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Avatar(modifier = Modifier.clickable(onClick = onAvatarClick))

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = "Skema-U",
                color = DashboardGreen,
                fontSize = 31.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun Avatar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(54.dp)
            .background(Color(0xFFE7192A), CircleShape)
            .padding(3.dp)
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(46.dp)) {
            drawCircle(Color(0xFFF4D6D8), radius = size.minDimension * 0.48f)
            drawCircle(Color(0xFFFFEEF1), radius = size.minDimension * 0.30f, center = Offset(size.width * 0.5f, size.height * 0.36f))
            drawCircle(Color(0xFF101010), radius = size.minDimension * 0.022f, center = Offset(size.width * 0.43f, size.height * 0.34f))
            drawCircle(Color(0xFF101010), radius = size.minDimension * 0.022f, center = Offset(size.width * 0.57f, size.height * 0.34f))
            drawArc(
                color = Color(0xFFC45162),
                startAngle = 18f,
                sweepAngle = 144f,
                useCenter = false,
                topLeft = Offset(size.width * 0.42f, size.height * 0.37f),
                size = Size(size.width * 0.16f, size.height * 0.13f),
                style = Stroke(width = 1.3.dp.toPx(), cap = StrokeCap.Round),
            )
            drawPath(
                path = Path().apply {
                    moveTo(size.width * 0.50f, size.height * 0.06f)
                    cubicTo(size.width * 0.24f, size.height * 0.18f, size.width * 0.24f, size.height * 0.62f, size.width * 0.50f, size.height * 0.76f)
                    cubicTo(size.width * 0.76f, size.height * 0.62f, size.width * 0.76f, size.height * 0.18f, size.width * 0.50f, size.height * 0.06f)
                },
                color = Color.White,
                style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
            )
            drawRoundRect(
                color = Color(0xFF2AB66E),
                topLeft = Offset(size.width * 0.43f, size.height * 0.77f),
                size = Size(size.width * 0.14f, size.height * 0.16f),
                cornerRadius = CornerRadius(3.dp.toPx()),
            )
        }
    }
}

@Composable
private fun GreetingSection(providerName: String) {
    Column {
        Text(
            text = "Halo, $providerName! 👋",
            color = DashboardText,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            fontWeight = FontWeight.Bold,
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Status: ",
                color = DashboardMuted,
                fontSize = 20.sp,
                lineHeight = 26.sp,
            )
            Text(
                text = "Online",
                color = DashboardGreen,
                fontSize = 20.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = " • Siap menerima proyek.",
                color = DashboardMuted,
                fontSize = 20.sp,
                lineHeight = 26.sp,
            )
        }
    }
}

@Composable
private fun BalanceCard(balance: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, DashboardBorder, RoundedCornerShape(17.dp)),
        shape = RoundedCornerShape(17.dp),
        color = Color(0xFFF7FCF2),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "SALDO SAAT INI",
                    color = DashboardMuted,
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.6.sp,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = balance,
                    color = DashboardGreen,
                    fontSize = 42.sp,
                    lineHeight = 48.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Canvas(modifier = Modifier.size(72.dp)) {
                val iconColor = Color(0xFFDDE7D7)
                drawRoundRect(
                    color = iconColor,
                    topLeft = Offset(size.width * 0.10f, size.height * 0.18f),
                    size = Size(size.width * 0.74f, size.height * 0.64f),
                    cornerRadius = CornerRadius(7.dp.toPx()),
                    style = Stroke(width = 6.dp.toPx()),
                )
                drawRoundRect(
                    color = iconColor,
                    topLeft = Offset(size.width * 0.44f, size.height * 0.34f),
                    size = Size(size.width * 0.46f, size.height * 0.34f),
                    cornerRadius = CornerRadius(5.dp.toPx()),
                    style = Stroke(width = 5.dp.toPx()),
                )
                drawCircle(iconColor, radius = 6.dp.toPx(), center = Offset(size.width * 0.68f, size.height * 0.51f))
            }
        }
    }
}

@Composable
private fun StatsRow(stats: List<DashboardStat>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        stats.forEach { stat ->
            StatCard(
                stat = stat,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun StatCard(stat: DashboardStat, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(150.dp)
            .shadow(3.dp, RoundedCornerShape(14.dp))
            .border(1.dp, DashboardBorder, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Canvas(modifier = Modifier.size(30.dp)) {
                if (stat.type == StatType.Completed) {
                    drawCircle(DashboardMuted, radius = size.minDimension * 0.42f, style = Stroke(width = 2.5.dp.toPx()))
                    drawLine(DashboardGreen, Offset(size.width * 0.28f, size.height * 0.52f), Offset(size.width * 0.43f, size.height * 0.68f), 2.7.dp.toPx(), cap = StrokeCap.Round)
                    drawLine(DashboardGreen, Offset(size.width * 0.43f, size.height * 0.68f), Offset(size.width * 0.74f, size.height * 0.30f), 2.7.dp.toPx(), cap = StrokeCap.Round)
                } else {
                    drawStar(Color(0xFFB02B73))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stat.value,
                color = DashboardText,
                fontSize = 26.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stat.label,
                color = DashboardMuted,
                fontSize = 13.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.6.sp,
            )
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        color = DashboardText,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 12.dp),
    )
}

@Composable
private fun SectionHeader(title: String, actionText: String, onActionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            color = DashboardText,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = actionText,
            color = DashboardGreen,
            fontSize = 14.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable(onClick = onActionClick),
        )
    }
}

@Composable
private fun QuickAccessRow(items: List<QuickAccessItem>, onItemClick: (QuickAccessItem) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        items.forEach { item ->
            QuickAccessCard(
                item = item,
                onClick = { onItemClick(item) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun QuickAccessCard(item: QuickAccessItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .height(122.dp)
            .shadow(3.dp, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(
                        color = when (item.type) {
                            QuickAccessType.AddService -> Color(0xFF278B23)
                            QuickAccessType.Portfolio -> Color(0xFF456F3C)
                            QuickAccessType.Statistic -> Color(0xFFE8F0E2)
                        },
                        shape = CircleShape,
                    )
                    .border(
                        width = if (item.type == QuickAccessType.Statistic) 1.4.dp else 0.dp,
                        color = if (item.type == QuickAccessType.Statistic) Color(0xFFC4D3BE) else Color.Transparent,
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Canvas(modifier = Modifier.size(30.dp)) {
                    when (item.type) {
                        QuickAccessType.AddService -> drawPlus(Color.White)
                        QuickAccessType.Portfolio -> drawPortfolioIcon(Color.White)
                        QuickAccessType.Statistic -> drawChartIcon(DashboardMuted)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                color = DashboardText,
                fontSize = 12.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun ActiveProjectCard(project: ActiveProject, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(14.dp))
            .border(1.dp, DashboardBorder, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 22.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .background(DashboardChipGreen, RoundedCornerShape(18.dp))
                        .border(1.dp, Color(0xFFC8DDBF), RoundedCornerShape(18.dp))
                        .padding(horizontal = 13.dp, vertical = 5.dp),
                ) {
                    Text(
                        text = project.badge,
                        color = if (project.urgent) Color(0xFFBEEFB7) else DashboardGreen,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp,
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Canvas(modifier = Modifier.size(18.dp)) {
                        if (project.urgent) drawWarning(Color(0xFFE21818)) else drawClock(DashboardMuted)
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = project.deadline,
                        color = if (project.urgent) Color(0xFFE21818) else DashboardMuted,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = project.title,
                color = DashboardText,
                fontSize = 22.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Klien: ${project.client}",
                color = DashboardMuted,
                fontSize = 21.sp,
                lineHeight = 26.sp,
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProgressBar(progress = project.progress)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "PROGRES: ${(project.progress * 100).toInt()}%",
                    color = DashboardMuted,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "••• Detail",
                    color = DashboardGreen,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onClick),
                )
            }
        }
    }
}

@Composable
private fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFDDE8D7)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .height(8.dp)
                .background(DashboardGreenDark, RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
private fun NotificationCard(
    notifications: List<DashboardNotification>,
    onNotificationClick: (DashboardNotification) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(14.dp))
            .border(1.dp, DashboardBorder, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
    ) {
        Column {
            notifications.forEachIndexed { index, notification ->
                NotificationRow(
                    notification = notification,
                    onClick = { onNotificationClick(notification) },
                )
                if (index != notifications.lastIndex) {
                    Divider(color = Color(0xFFE7ECE3), thickness = 1.dp)
                }
            }
        }
    }
}

@Composable
private fun NotificationRow(notification: DashboardNotification, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = when (notification.type) {
                        NotificationType.Order -> Color(0xFFE9F8E8)
                        NotificationType.Revision -> Color(0xFFF9EAF2)
                        NotificationType.Payment -> Color(0xFFEFFAE9)
                    },
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(modifier = Modifier.size(28.dp)) {
                when (notification.type) {
                    NotificationType.Order -> drawCart(DashboardGreen)
                    NotificationType.Revision -> drawRevision(Color(0xFFB02B73))
                    NotificationType.Payment -> drawMoney(Color(0xFF456F3C))
                }
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = notification.title,
                color = DashboardText,
                fontSize = 21.sp,
                lineHeight = 27.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = notification.time,
                color = DashboardMuted,
                fontSize = 16.sp,
                lineHeight = 20.sp,
            )
        }
    }
}

@Composable
private fun ProviderBottomBar(
    selectedMenu: ProviderBottomMenu,
    onMenuClick: (ProviderBottomMenu) -> Unit,
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 6.dp,
        modifier = Modifier.height(84.dp),
    ) {
        ProviderBottomMenu.values().forEach { menu ->
            val selected = menu == selectedMenu
            NavigationBarItem(
                selected = selected,
                onClick = { onMenuClick(menu) },
                icon = {
                    Canvas(modifier = Modifier.size(28.dp)) {
                        drawBottomIcon(menu, if (selected) DashboardGreen else DashboardInactive)
                    }
                },
                label = {
                    Text(
                        text = menu.label,
                        fontSize = 11.sp,
                        lineHeight = 13.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DashboardGreen,
                    selectedTextColor = DashboardGreen,
                    indicatorColor = Color(0xFFF0FBF2),
                    unselectedIconColor = DashboardInactive,
                    unselectedTextColor = DashboardInactive,
                ),
            )
        }
    }
}

private fun DrawScope.drawStar(color: Color) {
    val outer = size.minDimension * 0.48f
    val inner = outer * 0.45f
    val center = Offset(size.width / 2f, size.height / 2f)
    val path = Path()
    for (i in 0 until 10) {
        val radius = if (i % 2 == 0) outer else inner
        val angle = Math.toRadians((i * 36.0) - 90.0)
        val x = center.x + cos(angle).toFloat() * radius
        val y = center.y + sin(angle).toFloat() * radius
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color)
}

private fun DrawScope.drawPlus(color: Color) {
    val stroke = 3.dp.toPx()
    drawLine(color, Offset(size.width * 0.50f, size.height * 0.18f), Offset(size.width * 0.50f, size.height * 0.82f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.50f), Offset(size.width * 0.82f, size.height * 0.50f), stroke, cap = StrokeCap.Round)
}

private fun DrawScope.drawPortfolioIcon(color: Color) {
    val stroke = 2.dp.toPx()
    drawRoundRect(color, Offset(size.width * 0.08f, size.height * 0.22f), Size(size.width * 0.54f, size.height * 0.56f), CornerRadius(2.dp.toPx()), style = Stroke(stroke))
    drawCircle(color, radius = size.width * 0.07f, center = Offset(size.width * 0.24f, size.height * 0.42f))
    val mountain = Path().apply {
        moveTo(size.width * 0.16f, size.height * 0.68f)
        lineTo(size.width * 0.32f, size.height * 0.52f)
        lineTo(size.width * 0.47f, size.height * 0.68f)
    }
    drawPath(mountain, color, style = Stroke(stroke, cap = StrokeCap.Round))
    drawRoundRect(color, Offset(size.width * 0.72f, size.height * 0.18f), Size(size.width * 0.18f, size.height * 0.18f), CornerRadius(1.dp.toPx()), style = Stroke(stroke))
    drawRoundRect(color, Offset(size.width * 0.72f, size.height * 0.58f), Size(size.width * 0.18f, size.height * 0.18f), CornerRadius(1.dp.toPx()), style = Stroke(stroke))
}

private fun DrawScope.drawChartIcon(color: Color) {
    val stroke = 2.4.dp.toPx()
    drawCircle(color, radius = 2.dp.toPx(), center = Offset(size.width * 0.18f, size.height * 0.64f))
    drawCircle(color, radius = 2.dp.toPx(), center = Offset(size.width * 0.42f, size.height * 0.42f))
    drawCircle(color, radius = 2.dp.toPx(), center = Offset(size.width * 0.62f, size.height * 0.58f))
    drawCircle(color, radius = 2.dp.toPx(), center = Offset(size.width * 0.84f, size.height * 0.26f))
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.64f), Offset(size.width * 0.42f, size.height * 0.42f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.42f, size.height * 0.42f), Offset(size.width * 0.62f, size.height * 0.58f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.62f, size.height * 0.58f), Offset(size.width * 0.84f, size.height * 0.26f), stroke, cap = StrokeCap.Round)
}

private fun DrawScope.drawClock(color: Color) {
    val stroke = 1.8.dp.toPx()
    drawCircle(color, radius = size.minDimension * 0.42f, style = Stroke(stroke))
    drawLine(color, Offset(size.width * 0.50f, size.height * 0.26f), Offset(size.width * 0.50f, size.height * 0.52f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.50f, size.height * 0.52f), Offset(size.width * 0.66f, size.height * 0.60f), stroke, cap = StrokeCap.Round)
}

private fun DrawScope.drawWarning(color: Color) {
    val stroke = 1.8.dp.toPx()
    val path = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.08f)
        lineTo(size.width * 0.90f, size.height * 0.86f)
        lineTo(size.width * 0.10f, size.height * 0.86f)
        close()
    }
    drawPath(path, color, style = Stroke(stroke))
    drawLine(color, Offset(size.width * 0.50f, size.height * 0.34f), Offset(size.width * 0.50f, size.height * 0.58f), stroke, cap = StrokeCap.Round)
    drawCircle(color, radius = 1.5.dp.toPx(), center = Offset(size.width * 0.50f, size.height * 0.70f))
}

private fun DrawScope.drawCart(color: Color) {
    val stroke = 2.4.dp.toPx()
    drawLine(color, Offset(size.width * 0.10f, size.height * 0.20f), Offset(size.width * 0.24f, size.height * 0.20f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.24f, size.height * 0.20f), Offset(size.width * 0.34f, size.height * 0.62f), stroke, cap = StrokeCap.Round)
    drawRoundRect(color, Offset(size.width * 0.30f, size.height * 0.30f), Size(size.width * 0.56f, size.height * 0.28f), CornerRadius(2.dp.toPx()), style = Stroke(stroke))
    drawCircle(color, radius = 2.8.dp.toPx(), center = Offset(size.width * 0.40f, size.height * 0.78f))
    drawCircle(color, radius = 2.8.dp.toPx(), center = Offset(size.width * 0.76f, size.height * 0.78f))
}

private fun DrawScope.drawRevision(color: Color) {
    val stroke = 2.3.dp.toPx()
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.25f), Offset(size.width * 0.62f, size.height * 0.25f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.44f), Offset(size.width * 0.48f, size.height * 0.44f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.18f, size.height * 0.63f), Offset(size.width * 0.38f, size.height * 0.63f), stroke, cap = StrokeCap.Round)
    drawLine(color, Offset(size.width * 0.58f, size.height * 0.72f), Offset(size.width * 0.82f, size.height * 0.48f), stroke, cap = StrokeCap.Round)
    drawCircle(color, radius = 2.5.dp.toPx(), center = Offset(size.width * 0.55f, size.height * 0.75f))
}

private fun DrawScope.drawMoney(color: Color) {
    val stroke = 2.dp.toPx()
    drawRoundRect(color, Offset(size.width * 0.12f, size.height * 0.24f), Size(size.width * 0.76f, size.height * 0.48f), CornerRadius(3.dp.toPx()), style = Stroke(stroke))
    drawCircle(color, radius = size.width * 0.13f, center = Offset(size.width * 0.50f, size.height * 0.48f), style = Stroke(stroke))
    drawCircle(color, radius = 1.7.dp.toPx(), center = Offset(size.width * 0.25f, size.height * 0.48f))
    drawCircle(color, radius = 1.7.dp.toPx(), center = Offset(size.width * 0.75f, size.height * 0.48f))
}

private fun DrawScope.drawBottomIcon(menu: ProviderBottomMenu, color: Color) {
    val stroke = 2.4.dp.toPx()
    when (menu) {
        ProviderBottomMenu.Home -> {
            val path = Path().apply {
                moveTo(size.width * 0.16f, size.height * 0.48f)
                lineTo(size.width * 0.50f, size.height * 0.18f)
                lineTo(size.width * 0.84f, size.height * 0.48f)
                lineTo(size.width * 0.78f, size.height * 0.84f)
                lineTo(size.width * 0.26f, size.height * 0.84f)
                close()
            }
            drawPath(path, color)
        }
        ProviderBottomMenu.Services -> {
            repeat(2) { row ->
                repeat(2) { col ->
                    drawRoundRect(color, Offset(size.width * (0.18f + col * 0.34f), size.height * (0.18f + row * 0.34f)), Size(size.width * 0.18f, size.height * 0.18f), CornerRadius(1.dp.toPx()), style = Stroke(stroke))
                }
            }
        }
        ProviderBottomMenu.Projects -> {
            drawRoundRect(color, Offset(size.width * 0.18f, size.height * 0.18f), Size(size.width * 0.64f, size.height * 0.66f), CornerRadius(2.dp.toPx()), style = Stroke(stroke))
            drawLine(color, Offset(size.width * 0.34f, size.height * 0.32f), Offset(size.width * 0.66f, size.height * 0.32f), stroke, cap = StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.32f, size.height * 0.50f), Offset(size.width * 0.68f, size.height * 0.50f), stroke, cap = StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.32f, size.height * 0.66f), Offset(size.width * 0.58f, size.height * 0.66f), stroke, cap = StrokeCap.Round)
        }
        ProviderBottomMenu.Chat -> {
            drawRoundRect(color, Offset(size.width * 0.14f, size.height * 0.20f), Size(size.width * 0.72f, size.height * 0.50f), CornerRadius(2.dp.toPx()), style = Stroke(stroke))
            drawLine(color, Offset(size.width * 0.30f, size.height * 0.38f), Offset(size.width * 0.70f, size.height * 0.38f), stroke, cap = StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.30f, size.height * 0.54f), Offset(size.width * 0.60f, size.height * 0.54f), stroke, cap = StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.34f, size.height * 0.70f), Offset(size.width * 0.24f, size.height * 0.84f), stroke, cap = StrokeCap.Round)
        }
        ProviderBottomMenu.Profile -> {
            drawCircle(color, radius = size.width * 0.14f, center = Offset(size.width * 0.50f, size.height * 0.30f), style = Stroke(stroke))
            drawArc(color, 200f, 140f, false, Offset(size.width * 0.22f, size.height * 0.48f), Size(size.width * 0.56f, size.height * 0.40f), style = Stroke(stroke, cap = StrokeCap.Round))
        }
    }
}

@Preview(
    name = "Penyedia Dashboard",
    showBackground = true,
    backgroundColor = 0xFFF4FBEE,
    widthDp = 390,
    heightDp = 884,
)
@Composable
private fun PenyediaDashboardScreenPreview() {
    MaterialTheme {
        PenyediaDashboardScreen()
    }
}
