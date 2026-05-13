package com.nusantara.culture.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nusantara.culture.R
import com.nusantara.culture.data.CultureCategory
import com.nusantara.culture.data.CultureItem
import com.nusantara.culture.data.CultureRepository
import com.nusantara.culture.viewmodel.CultureViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: CultureViewModel,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToAbout: () -> Unit
) {
    var showMenuDropdown by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
    var showFilterCard by remember { mutableStateOf(true) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    
    // Load all items or filtered items
    val displayItems = if (viewModel.hasSearched && viewModel.searchResults.isNotEmpty()) {
        viewModel.searchResults
    } else {
        CultureRepository.allItems
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Explore,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    // Toggle filter button
                    IconButton(onClick = { showFilterCard = !showFilterCard }) {
                        Icon(
                            imageVector = if (showFilterCard) Icons.Default.FilterAltOff else Icons.Default.FilterAlt,
                            contentDescription = "Toggle Filter",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { viewModel.toggleLanguage() }) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = stringResource(R.string.toggle_language),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Box {
                        IconButton(onClick = { showMenuDropdown = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = stringResource(R.string.menu)
                            )
                        }
                        DropdownMenu(
                            expanded = showMenuDropdown,
                            onDismissRequest = { showMenuDropdown = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.reset_data)) },
                                leadingIcon = {
                                    Icon(Icons.Default.Refresh, contentDescription = null)
                                },
                                onClick = {
                                    viewModel.reset()
                                    showMenuDropdown = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.about)) },
                                leadingIcon = {
                                    Icon(Icons.Default.Info, contentDescription = null)
                                },
                                onClick = {
                                    showMenuDropdown = false
                                    onNavigateToAbout()
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        snackbarHost = { 
            SnackbarHost(snackbarHostState) { data ->
                CustomSnackbar(snackbarData = data)
            }
        }
    ) { paddingValues ->

        // Pinterest-style Staggered Grid with all items
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp
        ) {
            // Hero Banner (full width)
            item(span = androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan.FullLine) {
                HeroBanner(isIndonesian = viewModel.isIndonesian)
            }

            // Filter Card (full width) - Collapsible
            if (showFilterCard) {
                item(span = androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan.FullLine) {
                    FilterCard(
                        viewModel = viewModel,
                        showDropdown = showDropdown,
                        onDropdownToggle = { showDropdown = it },
                        snackbarHostState = snackbarHostState,
                        scope = scope
                    )
                }
            }

            // Culture items in staggered grid
            items(
                items = displayItems,
                key = { it.id }
            ) { item ->
                PinterestStyleCard(
                    item = item,
                    isIndonesian = viewModel.isIndonesian,
                    onDetailClick = { onNavigateToDetail(item.id) }
                )
            }
        }
    }
}

// ── Filter Card (with Snackbar validation) ──────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterCard(
    viewModel: CultureViewModel,
    showDropdown: Boolean,
    onDropdownToggle: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: kotlinx.coroutines.CoroutineScope
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (viewModel.isIndonesian) "Filter Budaya" else "Filter Culture",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            // Name TextField (no inline error)
            OutlinedTextField(
                value = viewModel.userName,
                onValueChange = { viewModel.onUserNameChange(it) },
                label = {
                    Text(if (viewModel.isIndonesian) "Nama Kamu" else "Your Name")
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            // Category Dropdown (no inline error)
            Text(
                text = if (viewModel.isIndonesian) "Kategori Budaya" else "Culture Category",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            ExposedDropdownMenuBox(
                expanded = showDropdown,
                onExpandedChange = { onDropdownToggle(it) }
            ) {
                OutlinedTextField(
                    value = viewModel.selectedCategory?.let {
                        viewModel.getCategoryDisplayName(it)
                    } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(if (viewModel.isIndonesian) "Pilih Kategori" else "Select Category")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDropdown)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                    shape = RoundedCornerShape(12.dp)
                )

                ExposedDropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { onDropdownToggle(false) }
                ) {
                    CultureCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = categoryIcon(category),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(viewModel.getCategoryDisplayName(category))
                                }
                            },
                            onClick = {
                                viewModel.onCategorySelected(category)
                                onDropdownToggle(false)
                            }
                        )
                    }
                }
            }

            // Filter Button with Snackbar validation
            Button(
                onClick = {
                    val valid = viewModel.search()
                    if (!valid) {
                        // Show error in Snackbar
                        val errorMessage = when {
                            viewModel.userNameError != null && viewModel.categoryError != null -> {
                                if (viewModel.isIndonesian)
                                    "Nama dan kategori harus diisi!"
                                else
                                    "Name and category are required!"
                            }
                            viewModel.userNameError != null -> viewModel.userNameError!!
                            viewModel.categoryError != null -> viewModel.categoryError!!
                            else -> ""
                        }
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = errorMessage,
                                duration = SnackbarDuration.Short,
                                withDismissAction = true
                            )
                        }
                    } else {
                        // Show success message
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = if (viewModel.isIndonesian)
                                    "Filter diterapkan!"
                                else
                                    "Filter applied!",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (viewModel.isIndonesian) "Terapkan Filter" else "Apply Filter",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

// ── Hero Banner ──────────────────────────────────────────────────────────────

@Composable
private fun HeroBanner(isIndonesian: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1565C0),  // Deep Blue
                        Color(0xFF00838F),  // Teal
                        Color(0xFF00695C)   // Dark Teal
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (isIndonesian) "🇮🇩 Jelajahi Budaya" else "🇮🇩 Explore Culture",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nusantara",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isIndonesian)
                    "Temukan keindahan budaya Indonesia"
                else
                    "Discover the beauty of Indonesian culture",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.75f)
            )
        }
    }
}

// ── Input Card ───────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputCard(
    viewModel: CultureViewModel,
    showDropdown: Boolean,
    onDropdownToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = if (viewModel.isIndonesian) "Cari Budaya" else "Search Culture",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            // Name TextField
            OutlinedTextField(
                value = viewModel.userName,
                onValueChange = { viewModel.onUserNameChange(it) },
                label = {
                    Text(if (viewModel.isIndonesian) "Nama Kamu" else "Your Name")
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                isError = viewModel.userNameError != null,
                supportingText = {
                    viewModel.userNameError?.let {
                        Text(it, color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            // Category label
            Text(
                text = if (viewModel.isIndonesian) "Kategori Budaya" else "Culture Category",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Category Dropdown
            ExposedDropdownMenuBox(
                expanded = showDropdown,
                onExpandedChange = { onDropdownToggle(it) }
            ) {
                OutlinedTextField(
                    value = viewModel.selectedCategory?.let {
                        viewModel.getCategoryDisplayName(it)
                    } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(if (viewModel.isIndonesian) "Pilih Kategori" else "Select Category")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Category, contentDescription = null)
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDropdown)
                    },
                    isError = viewModel.categoryError != null,
                    supportingText = {
                        viewModel.categoryError?.let {
                            Text(it, color = MaterialTheme.colorScheme.error)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                    shape = RoundedCornerShape(12.dp)
                )

                ExposedDropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { onDropdownToggle(false) }
                ) {
                    CultureCategory.entries.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = categoryIcon(category),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(viewModel.getCategoryDisplayName(category))
                                }
                            },
                            onClick = {
                                viewModel.onCategorySelected(category)
                                onDropdownToggle(false)
                            }
                        )
                    }
                }
            }

            // Search Button
            Button(
                onClick = { viewModel.search() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (viewModel.isIndonesian) "Jelajahi Sekarang" else "Explore Now",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

// ── Results Header ───────────────────────────────────────────────────────────

@Composable
private fun ResultsHeader(greeting: String, count: Int, isIndonesian: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = greeting,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = if (isIndonesian)
                "Ditemukan $count budaya untuk kamu 🎉"
            else
                "Found $count cultures for you 🎉",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ── Pinterest-Style Card (Optimized) ────────────────────────────────────────

@Composable
fun PinterestStyleCard(
    item: CultureItem,
    isIndonesian: Boolean,
    onDetailClick: () -> Unit
) {
    // Consistent height based on item ID to avoid recomposition lag
    val imageHeight = remember(item.id) {
        when (item.id % 3) {
            0 -> 200.dp
            1 -> 240.dp
            else -> 280.dp
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .graphicsLayer {
                // Enable hardware acceleration
                compositingStrategy = androidx.compose.ui.graphics.CompositingStrategy.Offscreen
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        onClick = onDetailClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) {
            // Image with Coil for better performance
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageRes)
                    .crossfade(true)
                    .build(),
                contentDescription = if (isIndonesian) item.nameId else item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            
            // Simple dark scrim at bottom
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.75f)
                            )
                        )
                    )
            )
            
            // Category badge at top
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = categoryIcon(item.category),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = if (isIndonesian) item.category.displayNameId else item.category.displayName,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 10.sp
                    )
                }
            }
            
            // Title at bottom
            Text(
                text = if (isIndonesian) item.nameId else item.name,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            )
        }
    }
}

// ── Old Culture Card (kept for reference) ───────────────────────────────────

@Composable
fun CultureCard(
    item: CultureItem,
    isIndonesian: Boolean,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {
            // Image
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = if (isIndonesian) item.nameId else item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Category chip
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = categoryIcon(item.category),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = if (isIndonesian) item.category.displayNameId else item.category.displayName,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                // Title
                Text(
                    text = if (isIndonesian) item.nameId else item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                // Origin
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = if (isIndonesian) item.originId else item.origin,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                // Short description
                Text(
                    text = if (isIndonesian) item.shortDescriptionId else item.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Detail button
                FilledTonalButton(
                    onClick = onDetailClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = if (isIndonesian) "Lihat Detail" else "View Detail")
                }
            }
        }
    }
}

// ── Helper ───────────────────────────────────────────────────────────────────

fun categoryIcon(category: CultureCategory): ImageVector = when (category) {
    CultureCategory.TARIAN -> Icons.Default.MusicNote
    CultureCategory.MAKANAN -> Icons.Default.Restaurant
    CultureCategory.RUMAH_ADAT -> Icons.Default.Home
    CultureCategory.PAKAIAN_ADAT -> Icons.Default.Checkroom
}

// ── Custom Snackbar with Icon ────────────────────────────────────────────────

@Composable
fun CustomSnackbar(snackbarData: androidx.compose.material3.SnackbarData) {
    val isError = snackbarData.visuals.actionLabel != null
    val icon = if (isError) Icons.Default.ErrorOutline else Icons.Default.CheckCircleOutline
    val containerColor = if (isError) 
        MaterialTheme.colorScheme.errorContainer 
    else 
        MaterialTheme.colorScheme.primaryContainer
    val contentColor = if (isError)
        MaterialTheme.colorScheme.onErrorContainer
    else
        MaterialTheme.colorScheme.onPrimaryContainer
    
    Snackbar(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        containerColor = containerColor,
        contentColor = contentColor,
        dismissAction = if (snackbarData.visuals.withDismissAction) {
            {
                IconButton(onClick = { snackbarData.dismiss() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        tint = contentColor
                    )
                }
            }
        } else null
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = snackbarData.visuals.message,
                style = MaterialTheme.typography.bodyMedium,
                color = contentColor
            )
        }
    }
}
