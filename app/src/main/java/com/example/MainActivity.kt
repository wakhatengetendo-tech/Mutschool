package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Launch
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.*
import com.example.ui.theme.AyGoldSecondary
import com.example.ui.theme.AyNavyDark
import com.example.ui.theme.AyNavyPrimary
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AYViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AYViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(viewModel: AYViewModel) {
    val context = LocalContext.current
    val selectedTab by viewModel.selectedTab.collectAsState()

    val navItems = listOf(
        NavItem("Hub", Icons.Default.Home, "nav_hub"),
        NavItem("Classes", Icons.Default.MenuBook, "nav_curriculum"),
        NavItem("Honors", Icons.Default.MilitaryTech, "nav_honors"),
        NavItem("Pledges", Icons.Default.Flag, "nav_pledges"),
        NavItem("Remnant Hub", Icons.Default.Language, "nav_remnant")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "AY Ministries",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "Standard Compiled Hub",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = AyGoldSecondary
                            )
                        )
                    }
                },
                actions = {
                    // Quick Direct External Link Chip
                    Surface(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.repository.remnantSchoolUrl))
                            context.startActivity(intent)
                        },
                        shape = RoundedCornerShape(20.dp),
                        color = AyGoldSecondary,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .testTag("top_bar_remnant_link_chip")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "remnantschool.com",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = AyNavyDark
                                )
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Launch,
                                contentDescription = "Open Web Link",
                                tint = AyNavyDark,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AyNavyDark,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = AyNavyDark,
                contentColor = Color.White,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("main_navigation_bar")
            ) {
                navItems.forEachIndexed { index, item ->
                    val isSelected = selectedTab == index
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { viewModel.onTabSelected(index) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AyNavyDark,
                            selectedTextColor = AyGoldSecondary,
                            indicatorColor = AyGoldSecondary,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f)
                        ),
                        modifier = Modifier.testTag(item.testTag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = selectedTab,
                label = "TabScreenTransition"
            ) { tab ->
                when (tab) {
                    0 -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateTab = { viewModel.onTabSelected(it) }
                    )
                    1 -> CurriculumScreen(viewModel = viewModel)
                    2 -> HonorsAndKnotsScreen(viewModel = viewModel)
                    3 -> PledgesAndSongsScreen(viewModel = viewModel)
                    4 -> RemnantHubScreen(viewModel = viewModel)
                    else -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateTab = { viewModel.onTabSelected(it) }
                    )
                }
            }
        }
    }
}

data class NavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val testTag: String
)
