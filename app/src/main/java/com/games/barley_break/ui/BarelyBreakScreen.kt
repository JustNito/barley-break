package com.games.barley_break.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.games.barley_break.ui.theme.Red900
import com.games.barley_break.ui.theme.Yellow700
import com.games.barley_break.ui.utils.GameStatus
import com.games.barley_break.ui.viewmodel.BarelyBreakViewModel

@Composable
fun BarelyBreakScreen(barelyBreakViewModel: BarelyBreakViewModel) {
    BarelyBreak(
        field = barelyBreakViewModel.field,
        onClick = barelyBreakViewModel::onCellClick
    )
    if(barelyBreakViewModel.gameStatus == GameStatus.GameOver) {
        GameOver(restartGame = barelyBreakViewModel::restartGame)
    }
}

@Composable
fun GameOver(
    restartGame: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black.copy(alpha = 0.8f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Congratulations!",
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = restartGame
            ) {
                Text("Play again")
            }
        }
    }

}

@Composable
fun BarelyBreak(
    field: List<String>,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Cells(
            field = field,
            onClick = onClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Cells(
    field: List<String>,
    onClick: (Int) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Red900,
            elevation = 10.dp,
            border = BorderStroke(6.dp, MaterialTheme.colors.primary)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(10.dp)
                    .size(212.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                columns = GridCells.Fixed(4)
            ) {
                itemsIndexed(field, key = { _, text -> text }) { index, text ->
                    Cell(
                        modifier = Modifier.animateItemPlacement(
                        ),
                        index = index,
                        text = text,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    index: Int,
    text: String,
    onClick: (Int) -> Unit
) {
    val isEmpty = text == " "
    Surface(
        modifier = modifier
            .size(50.dp)
            .clickable(enabled = !isEmpty) { onClick(index) },
        border = BorderStroke(6.dp, if(isEmpty) Color.Unspecified else Yellow700),
        color = if(isEmpty) Color.Unspecified else MaterialTheme.colors.secondary
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = text
            )
        }
    }
}

@Preview
@Composable
fun PreviewCell() {
    Cell(index = 0, text = "1", onClick = {})
}