package com.nuncamaria.getthingsdone.ui.todolist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nuncamaria.getthingsdone.data.ToDoModel
import com.nuncamaria.getthingsdone.data.ToDoStatus
import com.nuncamaria.getthingsdone.data.getStatusTitle
import com.nuncamaria.getthingsdone.di.AppViewModelProvider
import com.nuncamaria.getthingsdone.domain.ToDosLinkedHashMap
import com.nuncamaria.getthingsdone.ui.theme.Spacing
import com.nuncamaria.getthingsdone.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ToDoListView(
    viewModel: ToDoListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToAddToDo: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val toDoListUiState by viewModel.toDoList.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "Let's get things done") },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddToDo) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add To Do")

            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = Spacing.md)
                .padding(horizontal = Spacing.md),
        ) {

            ToDos(
                toDoList = toDoListUiState.toDoList,
                onCheckedChange = { item, isChecked ->
                    viewModel.checkItem(item, isChecked)
                },
                onClickDelete = { item ->
                    viewModel.deleteItem(item)
                }
            )
        }
    }
}

@Composable
private fun ToDos(
    toDoList: ToDosLinkedHashMap,
    onCheckedChange: (ToDoModel, Boolean) -> Unit,
    onClickDelete: (ToDoModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.testTag(ToDoListId.TODO_LIST),
        verticalArrangement = Arrangement.spacedBy(Spacing.s)
    ) {

        if (toDoList.isEmpty()) {
            item { EmptyMessage() }
        } else {

            toDoList.forEach { (status, list) ->
                if (list.isNotEmpty()) {
                    item {
                        StatusTitle(status)
                    }

                    items(list) {
                        ToDoItem(item = it,
                            onCheckedChange = { isChecked ->
                                onCheckedChange(it, isChecked)
                            },
                            onClickDelete = { onClickDelete(it) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ToDoItem(
    item: ToDoModel,
    onCheckedChange: (Boolean) -> Unit,
    onClickDelete: () -> Unit
) {
    val isChecked = remember { mutableStateOf(item.isDone) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow.copy(alpha = 0.3F)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    onCheckedChange(it)
                }
            )

            Column(
                modifier = Modifier
                    .padding(Spacing.md)
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                Text(
                    text = item.title,
                    style = Typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = item.description,
                    style = Typography.bodyLarge
                )
            }

            IconButton(onClick = onClickDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove task")
            }
        }
    }
}

@Composable
private fun StatusTitle(status: ToDoStatus) {
    Text(
        modifier = Modifier.padding(top = Spacing.md),
        text = status.getStatusTitle(),
        style = Typography.titleMedium
    )
}

@Composable
private fun EmptyMessage() {
    Text(
        modifier = Modifier.testTag(ToDoListId.EMPTY_MESSAGE),
        text = "Seems like you don't have any To Do yet."
    )
}
