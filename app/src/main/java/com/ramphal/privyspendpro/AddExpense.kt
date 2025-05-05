package com.ramphal.privyspendpro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ramphal.privyspendpro.ui.theme.PrivySpendProTheme
import com.ramphal.privyspendpro.ui.theme.myFont

@Composable
fun AddExpense(modifier: Modifier = Modifier){
    var titleValue by remember { mutableStateOf("") }
    var amountValue by remember { mutableStateOf("") }
    var dateValue by remember { mutableStateOf("") }
    var noteValue by remember { mutableStateOf("") }
    var selected by remember { mutableIntStateOf(0) }
    Surface(modifier = modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (topBar, totalAmount, card, inAndOut, list) = createRefs()
            AppTopBar(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .constrainAs(topBar){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            CardItem(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .constrainAs(totalAmount) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            AddItemCard(
                modifier = Modifier
                    .constrainAs(card) {
                        top.linkTo(totalAmount.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                firstValue = titleValue,
                onFirstValueChange = { titleValue = it },
                amountValue = amountValue,
                onAmountValueChange = { amountValue = it },
                selected = selected,
                onSelectionChanged = { newIndex ->
                    selected = newIndex
                },
                dateValue = dateValue,
                onDateValueChange = { dateValue = it },
                noteValue = noteValue,
                onNoteValueChange = { noteValue = it }
            )
        }
    }
}

@Composable
fun AddItemCard(
    modifier: Modifier,
    firstValue: String,
    onFirstValueChange: (String) -> Unit,
    amountValue: String,
    onAmountValueChange: (String) -> Unit,
    dateValue: String,
    onDateValueChange: (String) -> Unit,
    noteValue: String,
    onNoteValueChange: (String) -> Unit,
    selected: Int,
    onSelectionChanged: (Int) -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SingleChoiceSegmentedButton(
                selectedIndex = selected,
                onSelectionChanged = onSelectionChanged,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            CustomTextBox(
                firstLabel = "Title",
                firstValue = firstValue,
                onFirstValueChange = onFirstValueChange,
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            CustomTextBox(
                firstLabel = "Amount",
                firstValue = amountValue,
                onFirstValueChange = onAmountValueChange,
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            CustomTextBox(
                firstLabel = "Date",
                firstValue = dateValue,
                onFirstValueChange = onDateValueChange,
                modifier = Modifier.padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            CustomTextBox(
                firstLabel = "Note",
                firstValue = noteValue,
                onFirstValueChange = onNoteValueChange,
                singleLine = false,
                modifier = Modifier.padding(bottom = 16.dp),
                textFieldModifier = Modifier.heightIn(min = 108.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Create Track")
            }
        }
    }

}

@Composable
fun AppTopBar(modifier: Modifier){
    Box(
        modifier = modifier.fillMaxWidth()
            .wrapContentHeight()
    ){
        Text(
            text = "New Track",
            fontFamily = myFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        OutlinedIconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back"
            )
        }
        OutlinedIconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = "more"
            )
        }
    }
}


@Composable
fun SingleChoiceSegmentedButton(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onSelectionChanged: (Int) -> Unit
) {
    // The two options, with their drawable IDs
    val options = listOf(
        "Debit"  to R.drawable.send_24px,
        "Credit" to R.drawable.receive_24px
    )

    MultiChoiceSegmentedButtonRow(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(16.dp))    // overall container still has rounded outer corners
            .background(MaterialTheme.colorScheme.surface)
    ) {
        options.forEachIndexed { index, (label, iconRes) ->
            val checked = (index == selectedIndex)

            // build a shape that is square on the “midnight” side
            val segmentShape = when (index) {
                0 -> RoundedCornerShape(
                    topStart     = 16.dp,
                    bottomStart  = 16.dp,
                    topEnd       = 0.dp,
                    bottomEnd    = 0.dp
                )
                options.lastIndex -> RoundedCornerShape(
                    topStart     = 0.dp,
                    bottomStart  = 0.dp,
                    topEnd       = 16.dp,
                    bottomEnd    = 16.dp
                )
                else -> RoundedCornerShape(0.dp) // for more than two items, fully square in the middle
            }

            SegmentedButton(
                shape           = segmentShape,
                checked         = checked,
                onCheckedChange = { onSelectionChanged(index) },
                // ① Set custom container and content colors
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor    = if (label == "Debit") colorResource(R.color.red) else colorResource(R.color.green),
                    activeContentColor      = MaterialTheme.colorScheme.onSurface,
                    activeBorderColor       = if (label == "Debit") Color.Red else Color.Green,
                    inactiveContainerColor  = MaterialTheme.colorScheme.surfaceVariant,
                    inactiveContentColor    = MaterialTheme.colorScheme.onSurfaceVariant,
                    inactiveBorderColor     = MaterialTheme.colorScheme.outline
                ),
                icon = {
                    if (checked){
                        Icon(
                            imageVector = ImageVector.vectorResource(id = iconRes),
                            contentDescription = "$label selected",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                label = {
                    Text(
                        text     = label,
                        style    = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                },
                modifier = Modifier.height(54.dp),
            )
        }
    }
}



@Composable
fun CustomTextBox(
    firstLabel: String,
    firstValue: String,
    onFirstValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true,
    textFieldModifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .wrapContentHeight()
        .clip(shape)
        .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        TextField(
            value = firstValue,
            onValueChange = onFirstValueChange,
            singleLine = singleLine,
            textStyle = TextStyle(fontFamily = myFont, fontSize = 16.sp),
            modifier = textFieldModifier.fillMaxWidth().height(60.dp),
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = { Text(text = firstLabel) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddExpensePreview() {
    PrivySpendProTheme {
        AddExpense()
    }
}