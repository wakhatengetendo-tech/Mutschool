package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MinistryLevel
import com.example.data.model.RequirementItem
import com.example.ui.theme.AyEmeraldGreen
import com.example.ui.theme.AyGoldSecondary
import com.example.ui.theme.AyNavyDark
import com.example.ui.theme.AyNavyPrimary
import com.example.ui.viewmodel.AYViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurriculumScreen(
    viewModel: AYViewModel,
    modifier: Modifier = Modifier
) {
    val repository = viewModel.repository
    val userProgressMap by viewModel.userProgressMap.collectAsState()

    var selectedGroupFilter by remember { mutableStateOf("All") }
    val groupFilters = listOf("All", "Pathfinders", "Adventurers", "Master Guide")

    val filteredLevels = repository.ministryLevels.filter { level ->
        if (selectedGroupFilter == "All") true
        else level.ministryGroup.equals(selectedGroupFilter, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Filter Bar
        Surface(
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(vertical = 12.dp)) {
                Text(
                    text = "AY Class Requirements",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items(groupFilters) { filter ->
                        val isSelected = selectedGroupFilter == filter
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedGroupFilter = filter },
                            label = { Text(filter) },
                            leadingIcon = if (isSelected) {
                                {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = AyNavyPrimary,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_chip_$filter")
                        )
                    }
                }
            }
        }

        // List of Ministry Levels
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredLevels) { level ->
                MinistryLevelCard(
                    level = level,
                    userProgressMap = userProgressMap,
                    onToggleRequirement = { req ->
                        val current = userProgressMap[req.id]?.isCompleted ?: false
                        viewModel.toggleProgress(
                            id = req.id,
                            type = "CLASS_REQ",
                            title = req.title,
                            category = level.name,
                            currentStatus = current
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MinistryLevelCard(
    level: MinistryLevel,
    userProgressMap: Map<String, com.example.data.model.UserProgressEntity>,
    onToggleRequirement: (RequirementItem) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val totalReqs = level.requirements.size
    val completedReqs = level.requirements.count { userProgressMap[it.id]?.isCompleted == true }
    val progressPercent = if (totalReqs > 0) completedReqs.toFloat() / totalReqs else 0f

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("level_card_${level.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color(level.colorHex),
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = level.name.take(2).uppercase(),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = level.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = Color(level.colorHex).copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = level.ageGroup,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(level.colorHex)
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = level.description,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        maxLines = if (expanded) 5 else 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Progress Bar
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LinearProgressIndicator(
                            progress = { progressPercent },
                            color = Color(level.colorHex),
                            trackColor = Color(level.colorHex).copy(alpha = 0.2f),
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "$completedReqs/$totalReqs",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }

                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.testTag("expand_level_${level.id}")
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Expand"
                    )
                }
            }

            // Expandable List of Requirements
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Text(
                        text = "Class Requirements Checklist:",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    level.requirements.forEach { req ->
                        val isChecked = userProgressMap[req.id]?.isCompleted == true
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable { onToggleRequirement(req) }
                                .testTag("req_item_${req.id}")
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { onToggleRequirement(req) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(level.colorHex)
                                ),
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = req.title,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant
                                        else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Text(
                                    text = req.details,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
