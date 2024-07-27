package com.example.composenewsapp.news.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composenewsapp.R
import com.example.composenewsapp.news.screen.BookMarkArticleListScreen
import com.example.composenewsapp.news.screen.HomeScreenpage
import com.example.composenewsapp.news.screen.NewsDetailScreen
import com.example.composenewsapp.news.uiComponent.BottomNavigationBarItem
import com.example.composenewsapp.news.uiComponent.NewsBottomNavigationBar
import com.example.composenewsapp.news.viewmodel.DetailScreenViewModel
import com.example.composenewsapp.news.viewmodel.HomeViewModel
import com.example.rainbow.newsAppModule.news.remote.response.Article
import com.example.rainbow.newsAppModule.news.viewmodel.BookmarksScreenViewModel


@Composable
fun NewsNavigator(appModuleMenuClicked:()->Unit) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationBarItem("home", R.drawable.ic_home),
//            BottomNavigationBarItem("Search", R.drawable.ic_search),
            BottomNavigationBarItem("BookMark", R.drawable.ic_bookmarks)
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    val selectedItem = rememberSaveable {
        mutableStateOf(0)
    }

        
    selectedItem.value = when (backStackState?.destination?.route) {
        NewAppRoute.HomeScreen.route -> 0
//        Route.SearchScreen.route -> 1
        NewAppRoute.BookMarkScreen.route -> 1
        else -> 0
    }

    val isBottomNavVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route in listOf(NewAppRoute.HomeScreen.route, NewAppRoute.BookMarkScreen.route)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomNavVisible){
                NewsBottomNavigationBar(Modifier, bottomNavigationItems, selected = selectedItem.value, onItemClicked = {
                    when (it) {
                        0 -> {
                            navigateToTab(navController, NewAppRoute.HomeScreen.route)
                        }

//                        1 -> {
//                            navigateToTab(navController, Route.SearchScreen.route)
//                        }

                        1 -> {
                            navigateToTab(navController, NewAppRoute.BookMarkScreen.route)
                        }
                    }
                })
            }
        }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = NewAppRoute.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route= NewAppRoute.HomeScreen.route){
                val viewModel: HomeViewModel = hiltViewModel()
                val searchState = remember {
                    mutableStateOf("")
                }
                val article =
                    if (searchState.value.isNotEmpty()) viewModel.getSearchNews(searchState.value)
                        .collectAsLazyPagingItems() else viewModel.news.collectAsLazyPagingItems()
                HomeScreenpage(article,
                    onSearchClicked = {
                        searchState.value = it
                    },
                    navigateToDetail = {
                        navigateToDetailPage(navController,NewAppRoute.DetailScreen.route,it)
                    }, appModuleMenuClicked)
            }

            composable(route= NewAppRoute.DetailScreen.route){
                val context = LocalContext.current
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")?.let {
                    val detailViewModel:DetailScreenViewModel = hiltViewModel()
                    NewsDetailScreen(
                        modifier= Modifier,
                        context = context,
                        article = it,
                        navigateUp = {
                            navController.navigateUp()
                        },
                        onBookMarkClick = {
                            detailViewModel.addRemoveArticleToBookMark(it)
                        }
                    )
                }
            }

            composable(route= NewAppRoute.BookMarkScreen.route){

                val bookmarkViewmodel: BookmarksScreenViewModel = hiltViewModel()


                LaunchedEffect(true) {
                    bookmarkViewmodel.getAllBookMarkedArticle()
                }

                BookMarkArticleListScreen(modifier = Modifier,
                    article = bookmarkViewmodel.allBookmarkArticle.value,
                    onArticleClick = {
                        navigateToDetailPage(navController,NewAppRoute.DetailScreen.route,it)
                    },
                    onDeleteArticle = {
                    //delete logic
                        bookmarkViewmodel.deleteArticle(it)
                })
            }

        }
    }

}


fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

fun navigateToDetailPage(navController: NavController, route: String,article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route= route)
}

@Preview
@Composable
private fun NewsNavigatorPrev() {
    
}