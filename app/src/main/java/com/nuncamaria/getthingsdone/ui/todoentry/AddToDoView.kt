package com.nuncamaria.getthingsdone.ui.todoentry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.di.AppViewModelProvider
import com.nuncamaria.getthingsdone.ui.theme.Spacing
import com.nuncamaria.getthingsdone.ui.utils.ToDoModelState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddToDoView(
    viewModel: AddToDoViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Add a task") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = Spacing.md)
                .padding(horizontal = Spacing.md),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            ToDoEntryBody(
                toDoModelState = viewModel.toDoItemState,
                onItemValueChange = viewModel::updateInputState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveToDo()
                        navigateBack()
                    }
                }
            )

        }
    }
}


@Composable
fun ToDoEntryBody(
    toDoModelState: ToDoModelState,
    onItemValueChange: (ToDoModel) -> Unit,
    onSaveClick: () -> Unit,
) {
    ItemInputForm(
        item = toDoModelState.toDoModel,
        onValueChange = onItemValueChange,
    )

    Button(
        onClick = onSaveClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = toDoModelState.isEntryValid
    ) {
        Text(text = "Add task")
    }
}

@Composable
fun ItemInputForm(
    item: ToDoModel,
    onValueChange: (ToDoModel) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.lg)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = item.title,
            onValueChange = {
                onValueChange(item.copy(title = it))
            },
            label = { Text(text = "Title") },
            placeholder = {
                Text(text = "Title")
            },
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = item.description,
            onValueChange = {
                onValueChange(item.copy(description = it))
            },
            label = { Text(text = "Description") },
            placeholder = {
                Text(text = "Description")
            },
        )
    }
}
