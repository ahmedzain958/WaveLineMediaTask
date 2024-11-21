package com.wavelinemedia.task
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.wavelinemedia.cookies.presentation.details.DetailsScreen
import com.wavelinemedia.cookies.presentation.list.ListViewModel
import com.wavelinemedia.cookies.presentation.list.MainScreen
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder
import com.wavelinemedia.medicationreminderalarmbroadcast.presentation.MainViewModel
import com.wavelinemedia.medicationreminderalarmbroadcast.presentation.cancelAlarm
import com.wavelinemedia.medicationreminderalarmbroadcast.presentation.setUpAlarm
import com.wavelinemedia.medicationreminderalarmbroadcast.presentation.setUpPeriodicAlarm
import com.wavelinemedia.shopper_fk.presentation.HomeScreen
import com.wavelinemedia.shopper_fk.presentation.model.UiProductModel
import com.wavelinemedia.shopper_fk.presentation.navigation.CartScreen
import com.wavelinemedia.shopper_fk.presentation.navigation.CartSummaryScreen
import com.wavelinemedia.shopper_fk.presentation.navigation.HomeScreen
import com.wavelinemedia.shopper_fk.presentation.navigation.ProductDetailsRouteScreen
import com.wavelinemedia.shopper_fk.presentation.navigation.ProfileScreen
import com.wavelinemedia.shopper_fk.presentation.navigation.productNavType
import com.wavelinemedia.shopper_fk.presentation.ui.feature.cart.CartScreen
import com.wavelinemedia.shopper_fk.presentation.ui.feature.home.HomeViewModel
import com.wavelinemedia.shopper_fk.presentation.ui.feature.product_details.ProductDetailsScreen
import com.wavelinemedia.shopper_fk.presentation.ui.feature.summary.CartSummaryScreen
import com.wavelinemedia.task.ui.theme.Purple40
import dagger.hilt.android.AndroidEntryPoint
import com.wavelinemedia.medicationreminderalarmbroadcast.presentation.ui.theme.MedicationRemainderAppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.reflect.typeOf


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*WaveLineMediaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    AlarmManagerMainScreen(viewModel)
                }
            }*/
            MedicationRemainderAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    AlarmManagerMainScreen(viewModel)
                }
            }
        }
    }


    @Composable
    private fun AroundApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable(route = "list") {
                val listViewModel: ListViewModel = hiltViewModel()
                Column {
                    MainScreen(listViewModel.state.value, { id ->
                        navController.navigate("list/$id")
                    }, onFavouriteIconClick = { id: Int, oldValue: Boolean ->
                        listViewModel.toggleFavState(id, oldValue)
                    })
                }

            }

            composable(
                route = "list/{id}", arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }), deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "https://www.gymsaround.com/details/{id}"
                    }
                )
            ) { it: androidx.navigation.NavBackStackEntry ->
                DetailsScreen()
            }
        }
    }

    @Composable
    private fun ShopperCode() {
        val shouldShowBottomNav = remember {
            mutableStateOf(false)
        }
        val navController = rememberNavController()
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                LaunchedEffect(true) {
                    shouldShowBottomNav.value = true
                }
                AnimatedVisibility(visible = shouldShowBottomNav.value, enter = fadeIn()) {
                    BottomNavigationBar(navController)
                }
            }) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NavHost(navController = navController, startDestination = HomeScreen) {
                    composable<HomeScreen> {
                        val viewModel: HomeViewModel = koinViewModel()
                        HomeScreen(navController, viewModel.uiState.collectAsState().value)
                        shouldShowBottomNav.value = true
                    }
                    composable<CartScreen> {
                        shouldShowBottomNav.value = true
                        CartScreen(navController)
                    }
                    composable<ProfileScreen> {
                        shouldShowBottomNav.value = true
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "profile")
                        }
                    }
                    composable<CartSummaryScreen> {
                        shouldShowBottomNav.value = false
                        CartSummaryScreen(navController = navController)
                    }
                    composable<ProductDetailsRouteScreen>(typeMap = mapOf(typeOf<UiProductModel>() to productNavType)) {
                        shouldShowBottomNav.value = false
                        val productRoute: ProductDetailsRouteScreen =
                            it.toRoute<ProductDetailsRouteScreen>()
                        ProductDetailsScreen(navController, productRoute.product)
                    }
                }
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        NavigationBar {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            val items = listOf(
                BottomNavItems.Home,
                BottomNavItems.Cart,
                BottomNavItems.Profile
            )
            items.forEach { item ->
                val isSelected =
                    currentRoute?.substringBefore("?") == item.route::class.qualifiedName
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    label = {
                        Text(text = item.title)
                    },
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon), contentDescription = null,
                            colorFilter = ColorFilter.tint(if (isSelected) Purple40 else Color.Gray)
                        )
                    }, colors = NavigationBarItemDefaults.colors().copy(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = Color.Gray,
                        unselectedIconColor = Color.Gray
                    )
                )
            }
        }
    }

    sealed class BottomNavItems(val route: Any, val title: String, val icon: Int) {
        object Home : BottomNavItems(HomeScreen, "Home", icon = R.drawable.ic_home)
        object Cart : BottomNavItems(CartScreen, "Cart", icon = R.drawable.ic_cart)
        object Profile : BottomNavItems(ProfileScreen, "Profile", icon = R.drawable.ic_profile)

    }

    /////////////////////alarmmanger
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AlarmManagerMainScreen(viewModel: MainViewModel) {
        val uiState = viewModel.uiState.collectAsState().value
        val sheetState =
            androidx.compose.material.rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val isTimePickerVisible = remember {
            mutableStateOf(false)
        }
        val timePickerState = rememberTimePickerState()
        val format = remember {
            SimpleDateFormat("hh:mm a", Locale.getDefault())
        }
        val timeInMillis = remember { mutableStateOf(0L) }

        ModalBottomSheetLayout(
            sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
            sheetState = sheetState,
            sheetContent = {
                Form(
                    time = format.format(timeInMillis.value),
                    onTimeClick = {
                        isTimePickerVisible.value = true
                    }) { name, dosage, check ->
                    val reminder = Reminder(
                        name, dosage, timeInMillis.value, isTaken = false,
                        isRepeat = check
                    )
                    viewModel.insert(reminder)
                    if (check) {
                        setUpPeriodicAlarm(context, reminder)
                    } else {
                        setUpAlarm(context, reminder)
                    }
                    scope.launch {
                        sheetState.hide()
                    }
                }
            }) {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text(text = "Medication Reminder")
                }, actions = {
                    IconButton(onClick = {
                        scope.launch {
                            sheetState.show()
                        }
                    }, content = {})
                })
            }) {
                if (isTimePickerVisible.value) {
                    Dialog(onDismissRequest = {}) {
                        Column() {
                            TimePicker(state = timePickerState)
                            Row {
                                Button(onClick = {
                                    isTimePickerVisible.value = isTimePickerVisible.value.not()
                                }) {
                                    Text(text = "Cancel")
                                }

                                Button(onClick = {
                                    val calendar = Calendar.getInstance().apply {
                                        set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                                        set(Calendar.MINUTE, timePickerState.minute)
                                    }
                                    timeInMillis.value = calendar.timeInMillis
                                    isTimePickerVisible.value = false
                                }) {
                                    Text(text = "Confirm")
                                }
                            }
                        }
                    }
                }
                if (uiState.data.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Text(text = "Nothing found")
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        items(uiState.data) {
                            Card(modifier = Modifier.padding(8.dp)) {
                                Row(
                                    modifier = Modifier.padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(text = it.name)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(text = it.dosage)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(text = format.format(it.timeInMillis))
                                    }

                                    if (it.isRepeat) {
                                        IconButton(onClick = {
                                            cancelAlarm(context, it)
                                            viewModel.update(
                                                it.copy(
                                                    isTaken = true,
                                                    isRepeat = false
                                                )
                                            )
                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_schedule),
                                                contentDescription = null
                                            )
                                        }
                                    }

                                    IconButton(onClick = {
                                        cancelAlarm(context, it)
                                        viewModel.delete(it)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_delete),
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Composable
    fun Form(
        time: String,
        onTimeClick: () -> Unit,
        onSaveClick: (String, String, Boolean) -> Unit,
    ) {
        val name = remember {
            mutableStateOf("")
        }
        val dosage = remember {
            mutableStateOf("")
        }
        val isChecked = remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = name.value, onValueChange = {
                name.value = it
            }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = dosage.value, onValueChange = {
                dosage.value = it
            }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = time, onValueChange = {
            }, modifier = Modifier
                .clickable {
                    onTimeClick.invoke()
                }
                .fillMaxWidth(), enabled = false
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Schedule")
                Spacer(modifier = Modifier.width(20.dp))
                Switch(checked = isChecked.value, onCheckedChange = {
                    isChecked.value = it
                })
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                onSaveClick.invoke(name.value, dosage.value, isChecked.value)
            }) {
                Text(text = "Save")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }


}



