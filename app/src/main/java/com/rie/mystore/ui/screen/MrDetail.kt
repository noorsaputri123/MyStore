package com.rie.mystore.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rie.mystore.R
import com.rie.mystore.model.Injection
import com.rie.mystore.ui.common.UiState
import com.rie.mystore.ui.theme.MyStoreTheme

@Composable
fun MrDetail(
    vivoId: Long,
    viewModel: DetailVmodel = viewModel(factory = VMFactory(Injection.provideRepository())),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    LaunchedEffect(key1 = vivoId) {
        viewModel.getVivoById(vivoId)
    }
    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success).data
            MrDetailContent(
                img = data.order.img,
                title = data.order.title,
                price = data.order.price,
                desc = data.order.desc,
                onBackClick = navigateBack,
            )
        }
        else -> {}
    }
}

@Composable
fun MrDetailContent(
    @DrawableRes img: Int,
    title: String,
    desc: String,
    price: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = price.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(
                            color = Color.Blue
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = desc, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

