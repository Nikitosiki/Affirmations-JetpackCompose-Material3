package com.example.myapplicationmaterial3

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.myapplicationmaterial3.ui.theme.MyApplicationMaterial3Theme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationMaterial3Theme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }

}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Project by")
        Text("Nikita Savenko")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color("#50af8a".toColorInt()),
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            onClick = onContinueClicked
        ) {
            Text("Перейти к Аффирмациям")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
) {
    val ImageList = listOf(
        R.drawable.a_friends,
        R.drawable.a_love,
        R.drawable.a_philosofia,
        R.drawable.a_samoochenka
    )

    val TextList = listOf(
        "Я заслужил то, чтобы меня уважали и любили",
        "Куда бы я ни пошел, меня сопровождает любовь",
        "Люди склонны объяснять поведение других людей их личностными особенностями, а собственное поведение — внешними обстоятельствами",
        "В зависимости от благоприятного или неблагоприятного исхода ситуации действия человека получают позитивную или негативную оценку"
    )

    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(ImageList.size) { index ->
            Greeting(name = TextList[index], ImageList[index])
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Greeting(name: String, imageResId: Int) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color("#50af8a".toColorInt())
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name, imageResId)
    }
}

@Composable
private fun CardContent(name: String, imageResId: Int) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable(onClick = { expanded = !expanded })
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = name, style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            if (expanded) {
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(imageResId),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)).fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore, "Content"
//            )
//        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    MyApplicationMaterial3Theme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyApplicationMaterial3Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview
@Composable
fun MyAppPreview() {
    MyApplicationMaterial3Theme {
        MyApp(Modifier.fillMaxSize())
    }
}