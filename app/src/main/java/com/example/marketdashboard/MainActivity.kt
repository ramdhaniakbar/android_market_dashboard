package com.example.marketdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.marketdashboard.ui.theme.ChartReuse
import com.example.marketdashboard.ui.theme.MarketDashboardTheme
import com.example.marketdashboard.ui.theme.Wheat

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         MarketDashboardTheme {
            // A surface container using the 'background' color from the theme
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
               Content()
            }
         }
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
   val processList = listOf(
      "Verification process with team",
      "Launch process with colleagues"
   )
   val flows = listOf("Document Verification", "Newbie Onboarding")
   val snackbarHostState = remember {
      SnackbarHostState()
   }
   val scope = rememberCoroutineScope()

   Scaffold(
      topBar = {
         Row(modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column {
               Text(text = "Welcome back", fontSize = 16.sp, color = Color.Gray)
               Text(text = "Ramdhani Akbar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            
            Box {
               Image(
                  painter = painterResource(id = R.drawable.profile),
                  contentDescription = "a profile picture",
                  contentScale = ContentScale.Fit,
                  modifier = Modifier
                     .size(55.dp)
                     .clip(RoundedCornerShape(50.dp))
               )

               Box(
                  modifier = Modifier
                     .clip(CircleShape)
                     .border(BorderStroke(3.dp, color = Color.Black))
                     .background(Color.Black)
                     .align(alignment = Alignment.BottomStart)
               ) {
                  Text(
                     text = "2",
                     color = Color.White,
                     fontWeight = FontWeight.Bold,
                     fontSize = 12.sp,
                     modifier = Modifier.padding(3.dp)
                  )
               }
            }
         }
      },
      bottomBar = {
         NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Wheat,
            contentColor = Wheat
         ) {
            NavigationBarItem(
               selected = true,
               colors = NavigationBarItemDefaults.colors(
                  indicatorColor = Wheat
               ),
               onClick = { /*TODO*/ },
               icon = {
                  Icon(
                     imageVector = Icons.Filled.Home,
                     contentDescription = "Home icon"
                  )
            })

            NavigationBarItem(
               selected = true,
               colors = NavigationBarItemDefaults.colors(
                  indicatorColor = Wheat
               ),
               onClick = { /*TODO*/ },
               icon = {
                  Icon(
                     imageVector = Icons.Outlined.AccountBox,
                     contentDescription = "Account Box icon"
                  )
            })

            NavigationBarItem(
               selected = true,
               colors = NavigationBarItemDefaults.colors(
                  indicatorColor = Wheat
               ),
               onClick = { /*TODO*/ },
               icon = {
                  Icon(
                     imageVector = Icons.Outlined.Settings,
                     contentDescription = "Home icon"
                  )
            })
         }
      },
      containerColor = Wheat
   ) {
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(start = 16.dp)
      ) {
         // Search bar
         SearchBar()

         // Spacer
         Spacer(modifier = Modifier.height(30.dp))

         // Heading Text
         Text(
            text = "Task-based \nexplanation process",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier.padding(
               start = 16.dp,
               bottom = 20.dp
            ),
            style = TextStyle(lineHeight = 1.2.em)
         )

         // Recycler View
         LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
         ) {
            item {
               LeadingRowItem()
            }

            items(processList) {process ->
               ProcessItem(process)
            }
         }

         // Bottom Section or Flow Section
         Spacer(modifier = Modifier.height(30.dp))

         Row(
            modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
         ) {
            // Heading Section
            Row(
               verticalAlignment = Alignment.Bottom,
               horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Text(
                  text = "Flows List",
                  fontWeight = FontWeight.Bold,
                  fontSize = 30.sp,
                  color = Color.Black,
               )

               IconButton(onClick = { /*TODO*/ }) {
                  Icon(
                     imageVector = Icons.Default.ArrowDropDown,
                     tint = Color.Black,
                     contentDescription = "Options menu"
                  )
               }
            }

            // See all text
            Text(
               text = "See all",
               color = Color.Gray,
               fontSize = 16.sp,
            )
         }

         Spacer(modifier = Modifier.height(20.dp))

         LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
         ) {
            itemsIndexed(flows) {index, flow ->
               FlowItem(flow)

               if (index < flows.lastIndex) {
                  Divider(
                     color = Color.LightGray,
                     thickness = 1.dp,
                     modifier = Modifier
                              .padding(horizontal = 16.dp)
                              .padding(top = 16.dp)
                  )
               }
            }
         }
      }
   }
}

@Composable()
fun FlowItem(flow: String) {
   Row(
      modifier = Modifier
         .fillMaxWidth()
         .padding(horizontal = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      // Left Column
      Column(
         verticalArrangement = Arrangement.SpaceBetween,
         horizontalAlignment = Alignment.Start
      ) {
         Text(
            text = flow,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
         )

         Text(
            text = "3 min ago",
            color = Color.Gray,
            fontSize = 15.sp
         )
      }

      // Right Column
      IconButton(
         onClick = { /*TODO*/ },
         modifier = Modifier
            .clip(CircleShape)
            .background(color = ChartReuse)
      ) {
         Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add button",
            tint = Color.Black,
            modifier = Modifier.padding(7.dp)
         )
      }
   }
}

@Composable()
fun ProcessItem(process: String) {
   Box(
      modifier = Modifier
         .width(200.dp)
         .clip(RoundedCornerShape(20.dp))
         .background(color = Color.White)
   ) {
      Column(
         modifier = Modifier.padding(20.dp)
      ) {
         Box(
            modifier = Modifier
               .clip(RoundedCornerShape(10.dp))
               .background(color = Wheat),
            contentAlignment = Alignment.Center
         ) {
            Text(
               text = "Review",
               fontWeight = FontWeight.Bold,
               color = Color.Black,
               modifier = Modifier.padding(8.dp)
            )
         }

         Spacer(modifier = Modifier.height(20.dp))

         Text(
            text = process,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Black
         )
      }
   }
}

@Composable()
fun LeadingRowItem() {
   Box(
      modifier = Modifier
         .width(190.dp)
         .padding(start = 16.dp)
         .drawBehind {
            drawRoundRect(
               color = Color.Gray,
               cornerRadius = CornerRadius(20.dp.toPx()),
               style = Stroke(
                  width = 2f,
                  pathEffect = PathEffect.dashPathEffect(
                     floatArrayOf(10f, 10f)
                  )
               )
            )
         },
      contentAlignment = Alignment.Center
   ) {
      Column(
         modifier = Modifier.padding(20.dp),
      ) {
         Button(
               onClick = { /*TODO*/ },
               colors = ButtonDefaults.textButtonColors(containerColor = ChartReuse),
               shape = RoundedCornerShape(12.dp)
         ) {
            Text(
               text = "Add task",
               fontWeight = FontWeight.Bold,
               fontSize = 18.sp,
               color = Color.Black
            )
         }
         
         Spacer(modifier = Modifier.height(20.dp))

         Text(
            text = "Creative for branding",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color.Black
         )
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun SearchBar() {
   val textState = remember {
      mutableStateOf(TextFieldValue())
   }
   TextField(
      modifier = Modifier
         .fillMaxWidth()
         .padding(20.dp)
         .clip(RoundedCornerShape(20.dp)),
      value = textState.value,
      onValueChange = {textState.value = it},
      leadingIcon = {
         Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon")
      },
      colors = TextFieldDefaults.textFieldColors(
         textColor = Color.Gray,
         containerColor = Color.White,
         focusedIndicatorColor = Color.Transparent,
         disabledIndicatorColor = Color.Transparent,
         unfocusedIndicatorColor = Color.Transparent
      ),
      placeholder = {
         Text(
            text = "Try to find...",
            color = Color.Gray,
            fontSize = 16.sp
         )
      }
   )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
   MarketDashboardTheme {
      Content()
   }
}