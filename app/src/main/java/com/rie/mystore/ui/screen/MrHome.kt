package com.rie.mystore.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rie.mystore.R
import com.rie.mystore.model.Injection
import com.rie.mystore.model.OrderVivo
import com.rie.mystore.ui.common.UiState
import com.rie.mystore.ui.components.MrList



@Composable
fun HomeContent(
    orderVivo: List<OrderVivo>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
     ){
        items(orderVivo){ data->
            MrList(
                img = data.order.img,
                title = data.order.title,
                price = data.order.price,
            modifier =Modifier.clickable {
                navigateToDetail(data.order.id)
            }
                )
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MrHome(
    modifier: Modifier = Modifier,
    viewModel: HomeVmodel = viewModel(
        factory = VMFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = stringResource(R.string.Search))
                    }
                }
            )
        },
        content = {
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllVivos()
                    }
                    is UiState.Success -> {
                        HomeContent(
                            orderVivo = uiState.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                    is UiState.Error -> {}
                }
            }
        }
    )
}



