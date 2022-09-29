package com.games.barley_break

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.games.barley_break.ui.BarelyBreakScreen
import com.games.barley_break.ui.theme.BarleybreakTheme
import com.games.barley_break.ui.viewmodel.BarelyBreakViewModel

class MainActivity : ComponentActivity() {

    val barelyBreakViewModel: BarelyBreakViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barelyBreakViewModel.initGame()
        setContent {
            BarleybreakTheme {
                BarelyBreakScreen(barelyBreakViewModel = barelyBreakViewModel)
             }
        }
    }
}