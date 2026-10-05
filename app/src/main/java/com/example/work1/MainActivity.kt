package com.example.work1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                onBack = {
                    finish()
                }
            )
        }
    }
}

@Composable
fun MainScreen(
    onBack: () -> Unit
) {

    // Thông tin hiện tại
    var name by remember {
        mutableStateOf("Lâm Thành Nam")
    }

    var studentId by remember {
        mutableStateOf("SV-072205005140")
    }

    // Hiển thị hộp thoại Edit
    var showEditDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {


        // THANH TRÊN


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Nút quay lại
            IconButton(
                onClick = {
                    onBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Quay lại",
                    tint = Color(0xFF555555)
                )
            }

            // Nút Edit
            IconButton(
                onClick = {
                    showEditDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Chỉnh sửa",
                    tint = Color(0xFF555555),
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        // Khoảng cách
        Spacer(
            modifier = Modifier.height(55.dp)
        )


        // THÔNG TIN SINH VIÊN


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Vòng tròn nền avatar
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFDCEEFF)),
                contentAlignment = Alignment.Center
            ) {

                Image(
                    painter = painterResource(
                        id = R.drawable.avatar
                    ),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(115.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Tên
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF171717)
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            // MSSV
            Text(
                text = studentId,
                fontSize = 12.sp,
                color = Color(0xFF777777)
            )
        }
    }


    // HỘP THOẠI EDIT


    if (showEditDialog) {

        EditStudentDialog(
            currentName = name,
            currentStudentId = studentId,

            onDismiss = {
                showEditDialog = false
            },

            onSave = { newName, newStudentId ->

                name = newName
                studentId = newStudentId

                showEditDialog = false
            }
        )
    }
}



// HỘP THOẠI CHỈNH SỬA


@Composable
fun EditStudentDialog(
    currentName: String,
    currentStudentId: String,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {

    var newName by remember {
        mutableStateOf(currentName)
    }

    var newStudentId by remember {
        mutableStateOf(currentStudentId)
    }

    AlertDialog(

        onDismissRequest = {
            onDismiss()
        },

        title = {
            Text(
                text = "Chỉnh sửa thông tin"
            )
        },

        text = {

            Column {

                // Ô nhập tên
                OutlinedTextField(
                    value = newName,
                    onValueChange = {
                        newName = it
                    },
                    label = {
                        Text("Họ và tên")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // Ô nhập MSSV
                OutlinedTextField(
                    value = newStudentId,
                    onValueChange = {
                        newStudentId = it
                    },
                    label = {
                        Text("Mã sinh viên")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {

                    onSave(
                        newName,
                        newStudentId
                    )
                }
            ) {
                Text("Lưu")
            }
        },

        dismissButton = {

            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Hủy")
            }
        }
    )
}