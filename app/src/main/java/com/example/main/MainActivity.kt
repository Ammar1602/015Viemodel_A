package com.example.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.main.Data.DataForm
import com.example.main.Data.DataSource.jenis
import com.example.main.Data.DataSource.sekarang
import com.example.main.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayout()
                }
            }
        }
    }
}

@Composable
fun TampilLayout(
    modifier: Modifier = Modifier
){
    Card (modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)){
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
        ) {
            TampilText()
            Spacer(modifier = Modifier.padding(10.dp))
            BoxHeader()

        }
    }
}

@Composable
fun BoxHeader(modifier: Modifier){
    Row{
        Image(
            painter = painterResource(id = R.drawable.black_arrow),
            contentDescription = "",
            modifier = Modifier.padding(5.dp))
        Text(text = "Register", fontSize = 20.sp)
    }
    Text(
        text = "Create Your Account",
        textAlign = TextAlign.Center,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
        )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilText(cobaViewModel: ViewModel = viewModel()){

    var textForm by remember { mutableStateOf("") }
    var phoneForm by remember { mutableStateOf("") }
    var emailForm by remember {mutableStateOf("")}
    var alamatForm by remember { mutableStateOf("") }

    val  context = LocalContext.current
    val dataForm: DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState

    OutlinedTextField(
        value = textForm,
        onValueChange = {textForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label =
        {
            Text(
                text = "Nama Lengkap")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = phoneForm,
        onValueChange = {phoneForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label =
        {
            Text(
                text = "Nomor Telepon")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    OutlinedTextField(
        value = emailForm,
        onValueChange = {emailForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label =
        {
            Text(
                text = "Email")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )

    SelectJK(options = jenis.map { id -> context.resources.getString(id) },
        onSelectedChanged = {
            cobaViewModel.setJenisJK(it)
        })
    SelectStatus(options =  jenis.map { id -> context.resources.getString(id) },
        onSelectedChanged = {
            cobaViewModel.setStatus(it)
        })
    OutlinedTextField(
        value = alamatForm,
        onValueChange = {alamatForm = it},
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        label =
        {
            Text(
                text = "Alamat")},
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
    Button(modifier = Modifier.fillMaxWidth(),
        onClick = {cobaViewModel.readdata(textForm,phoneForm,emailForm,alamatForm,dataForm.sex,dataForm.status)
        }
    ) {
        Text(text = stringResource(R.string.submit),
            fontSize = 16.sp)

    }
    Spacer(modifier = Modifier.height(150.dp))
    TextHasil(
        namanya = cobaViewModel.namaUSr,
        telponnya =cobaViewModel.noTlp ,
        emailnya = cobaViewModel.emailUSr,
        jenisnya = cobaViewModel.jenisKL,
        alamatnya = cobaViewModel.alamatUsr,
        statusnya = cobaViewModel.statusUsr,
    )

}



@Composable
fun TextHasil(namanya:String,telponnya:String,emailnya:String,jenisnya:String,statusnya: String,alamatnya:String){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(
            text = "Telepon : " + telponnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(
            text = "Email : " + emailnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(
            text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))
        Text(text = "Status : " + statusnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
        Text(
            text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp))


    }

}
@Composable
fun SelectJK(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable { mutableStateOf("")
    }
    Column (modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }

}

@Composable
fun SelectStatus(
    options: List<String>,
    onSelectedChanged: (String) -> Unit = {}) {

    var selectedValue by rememberSaveable { mutableStateOf("")
    }
    Column (modifier = Modifier.padding(16.dp)) {
        options.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectedChanged(item)
                    }
                )
                Text(item)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainTheme {
        TampilLayout()
    }
}