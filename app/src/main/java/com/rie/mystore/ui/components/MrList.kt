package com.rie.mystore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rie.mystore.R
import com.rie.mystore.ui.theme.MyStoreTheme
import com.rie.mystore.ui.theme.Shapes

@Composable
@Preview(showBackground = true)
fun MrListPreview(){
    MyStoreTheme() {
        MrList(R.drawable.y_25e,"Vivo y 22", 2500000)
    }
}

@Composable
fun MrList(
    img : Int,
    title : String,
    price : Int,
    modifier: Modifier = Modifier,
    ) {
    Column(modifier = modifier) {
        Image(painter = painterResource(img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
            )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )

        )
//        Text(text = stringResource(id = ))


    }

}
