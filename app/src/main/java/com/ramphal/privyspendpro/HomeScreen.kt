package com.ramphal.privyspendpro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ramphal.privyspendpro.ui.theme.PrivySpendProTheme
import com.ramphal.privyspendpro.ui.theme.ReceivedColor
import com.ramphal.privyspendpro.ui.theme.SentColor
import com.ramphal.privyspendpro.ui.theme.myFont

@Composable
fun HomeScreen(modifier: Modifier = Modifier ){
    Surface(modifier = modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, card, inAndOut, list) = createRefs()
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                
                OutlinedCard(
                    modifier = Modifier.size(60.dp)
                        .align(Alignment.CenterStart)
                        .padding(1.dp),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_pic),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier.align(Alignment.CenterStart)
                        .padding(start = 68.dp)
                ) {
                    Text(
                        text = "Hello, Mr./Ms.",
                        fontSize = 16.sp,
                        fontFamily = myFont
                    )
                    Text(
                        text = "Welcome Back!",
                        fontSize = 16.sp,
                        fontFamily = myFont,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedIconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .size(48.dp)
                ) {
                    Icon(painter = painterResource(R.drawable.category_24px),
                        contentDescription = "Settings",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            CardItem(
                modifier = Modifier
                    .constrainAs(card) {
                        top.linkTo(nameRow.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(state = rememberScrollState())
                    .constrainAs(inAndOut) {
                        top.linkTo(card.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                RowCardItem(
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                    title = "Total Income",
                    disc = "Total income this month only",
                    amount = "235400",
                    icon = R.drawable.receive_24px,
                )
                RowCardItem(
                    modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                    title = "Total Expense",
                    disc = "Total expense this month only",
                    amount = "120052",
                    icon = R.drawable.send_24px,
                )
            }
            TransitionList(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(inAndOut.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }
}


@Composable
fun TransitionList(modifier: Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Recent Transactions",
                fontSize = 16.sp,
                fontFamily = myFont,
                modifier = Modifier.padding(top = 16.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "See all",
                fontSize = 16.sp,
                fontFamily = myFont,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        TransitionItem(
            modifier = Modifier,
            title = "PG Rent",
            amount = "5500",
            icon = R.drawable.send_24px,
            date = "12/06/2023",
            color = SentColor
        )
        TransitionItem(
            modifier = Modifier,
            title = "Punit",
            amount = "2000",
            icon = R.drawable.receive_24px,
            date = "01/06/2023",
            color = ReceivedColor
        )
        TransitionItem(
            modifier = Modifier,
            title = "Ram",
            amount = "1500",
            icon = R.drawable.receive_24px,
            date = "19/05/2023",
            color = ReceivedColor
        )

    }
}

@Composable
fun TransitionItem(
    modifier: Modifier,
    title: String,
    amount: String,
    icon: Int,
    date: String,
    color: Color
){
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp, bottom = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedIconButton(
                onClick = {},
                modifier = Modifier.size(40.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = color           // Your custom outline color
                )
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = color
                )
            }

            // Give this Column weight so it fills remaining space
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontFamily = myFont
                    )
                    Text(
                        text = "$$amount",
                        fontSize = 18.sp,
                        fontFamily = myFont,
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = date,
                    fontSize = 12.sp,
                    fontFamily = myFont,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

}


@Composable
fun CardItem(modifier: Modifier){
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
                .padding(2.dp)
        ) {
            Column(
                modifier = modifier.padding(14.dp)

            ){
                Text(
                    text = "Total Balance",
                    fontSize = 18.sp,
                    fontFamily = myFont,
                )
                Text(
                    text = "$120000000",
                    fontSize = 42.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            OutlinedIconButton(
                onClick = {},
                modifier = Modifier.size(48.dp)
                    .align(Alignment.BottomEnd)
            ){
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "Wallet"
                )
            }
        }

    }
}

@Composable
fun RowCardItem(
    modifier: Modifier,
    title: String,
    disc: String,
    amount: String,
    icon: Int
){
    OutlinedCard(
        modifier = modifier.width(290.dp)
            .wrapContentHeight()
            .padding(top = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
        ) {
            Row{
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    OutlinedIconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.wallet_24px),
                            contentDescription = "Wallet"
                        )
                    }
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontFamily = myFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = disc,
                        fontSize = 12.sp,
                        fontFamily = myFont,
                        modifier = Modifier.padding(top = 26.dp)
                    )
                    Text(
                        text = "$$amount",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                OutlinedIconButton(onClick = {}) {
                    Icon(imageVector = ImageVector.vectorResource(icon), contentDescription = "Send")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PrivySpendProTheme {
        HomeScreen()
    }
}