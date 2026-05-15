package com.upn.skema_u

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val RegisterPrimaryGreen = Color(0xFF0B7A1B)
private val RegisterSoftGreen = Color(0xFFEAF8E8)
private val RegisterSelectedGreen = Color(0xFFC8F2BE)
private val RegisterBorderGreen = Color(0xFFBBCAB5)
private val RegisterTextDark = Color(0xFF1A2119)
private val RegisterTextMuted = Color(0xFF4B554A)
private val RegisterPlaceholder = Color(0xFF737D8E)

enum class RegisterRole {
    Provider,
    Client,
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (
        role: RegisterRole,
        fullName: String,
        universityOrTaxId: String,
        email: String,
        password: String,
        acceptedAgreement: Boolean,
    ) -> Unit = { _, _, _, _, _, _ -> },
    onLoginClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
) {
    var selectedRole by rememberSaveable { mutableStateOf(RegisterRole.Provider) }
    var fullName by rememberSaveable { mutableStateOf("") }
    var universityOrTaxId by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var acceptedAgreement by rememberSaveable { mutableStateOf(false) }

    RegisterScreen(
        selectedRole = selectedRole,
        fullName = fullName,
        universityOrTaxId = universityOrTaxId,
        email = email,
        password = password,
        acceptedAgreement = acceptedAgreement,
        onRoleChange = { selectedRole = it },
        onFullNameChange = { fullName = it },
        onUniversityOrTaxIdChange = { universityOrTaxId = it },
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onAgreementChange = { acceptedAgreement = it },
        onRegisterClick = {
            onRegisterClick(
                selectedRole,
                fullName,
                universityOrTaxId,
                email,
                password,
                acceptedAgreement,
            )
        },
        onLoginClick = onLoginClick,
        onTermsClick = onTermsClick,
        onPrivacyClick = onPrivacyClick,
        modifier = modifier,
    )
}

@Composable
fun RegisterScreen(
    selectedRole: RegisterRole,
    fullName: String,
    universityOrTaxId: String,
    email: String,
    password: String,
    acceptedAgreement: Boolean,
    onRoleChange: (RegisterRole) -> Unit,
    onFullNameChange: (String) -> Unit,
    onUniversityOrTaxIdChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAgreementChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RegisterSoftGreen.copy(alpha = 0.55f))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 26.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        RegisterCard(
            selectedRole = selectedRole,
            fullName = fullName,
            universityOrTaxId = universityOrTaxId,
            email = email,
            password = password,
            acceptedAgreement = acceptedAgreement,
            onRoleChange = onRoleChange,
            onFullNameChange = onFullNameChange,
            onUniversityOrTaxIdChange = onUniversityOrTaxIdChange,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onAgreementChange = onAgreementChange,
            onRegisterClick = onRegisterClick,
            onLoginClick = onLoginClick,
            onTermsClick = onTermsClick,
            onPrivacyClick = onPrivacyClick,
        )
    }
}

@Composable
private fun RegisterCard(
    selectedRole: RegisterRole,
    fullName: String,
    universityOrTaxId: String,
    email: String,
    password: String,
    acceptedAgreement: Boolean,
    onRoleChange: (RegisterRole) -> Unit,
    onFullNameChange: (String) -> Unit,
    onUniversityOrTaxIdChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onAgreementChange: (Boolean) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(10.dp),
                spotColor = Color.Black.copy(alpha = 0.08f),
                ambientColor = RegisterPrimaryGreen.copy(alpha = 0.05f),
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE0E8DB),
                shape = RoundedCornerShape(10.dp),
            ),
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp),
        ) {
            RegisterHeader()

            Spacer(modifier = Modifier.height(26.dp))

            RegisterSectionLabel(text = "SAYA INGIN BERGABUNG SEBAGAI")

            Spacer(modifier = Modifier.height(8.dp))

            RoleSelector(
                selectedRole = selectedRole,
                onRoleChange = onRoleChange,
            )

            Spacer(modifier = Modifier.height(30.dp))

            RegisterTextField(
                label = "NAMA LENGKAP",
                value = fullName,
                onValueChange = onFullNameChange,
                placeholder = "Masukkan nama lengkap Anda",
                keyboardType = KeyboardType.Text,
            )

            Spacer(modifier = Modifier.height(18.dp))

            RegisterTextField(
                label = "ID UNIVERSITAS / ID PAJAK",
                value = universityOrTaxId,
                onValueChange = onUniversityOrTaxIdChange,
                placeholder = "NIM atau ID Perusahaan",
                keyboardType = KeyboardType.Text,
            )

            Spacer(modifier = Modifier.height(18.dp))

            RegisterTextField(
                label = "ALAMAT EMAIL",
                value = email,
                onValueChange = onEmailChange,
                placeholder = "nama@universitas.ac.id",
                keyboardType = KeyboardType.Email,
            )

            Spacer(modifier = Modifier.height(18.dp))

            RegisterTextField(
                label = "KATA SANDI",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "........",
                keyboardType = KeyboardType.Password,
                isPassword = true,
            )

            Spacer(modifier = Modifier.height(24.dp))

            AgreementRow(
                checked = acceptedAgreement,
                onCheckedChange = onAgreementChange,
                onTermsClick = onTermsClick,
                onPrivacyClick = onPrivacyClick,
            )

            Spacer(modifier = Modifier.height(38.dp))

            Button(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RegisterPrimaryGreen,
                    contentColor = Color.White,
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
            ) {
                Text(
                    text = "Buat Akun",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            ExistingAccountDivider()

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedButton(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(7.dp),
                border = BorderStroke(1.dp, RegisterBorderGreen),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = RegisterTextDark,
                ),
            ) {
                Text(
                    text = "Masuk",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun RegisterHeader() {
    Column {
        Text(
            text = "Daftar Skema-U",
            color = RegisterTextDark,
            fontSize = 25.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Buka peluang profesional dan temukan\ntalenta terbaik.",
            color = RegisterTextMuted,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        )
    }
}

@Composable
private fun RoleSelector(
    selectedRole: RegisterRole,
    onRoleChange: (RegisterRole) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        RoleCard(
            title = "Penyedia",
            selected = selectedRole == RegisterRole.Provider,
            icon = RegisterRole.Provider,
            onClick = { onRoleChange(RegisterRole.Provider) },
            modifier = Modifier.weight(1f),
        )

        RoleCard(
            title = "Klien",
            selected = selectedRole == RegisterRole.Client,
            icon = RegisterRole.Client,
            onClick = { onRoleChange(RegisterRole.Client) },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun RoleCard(
    title: String,
    selected: Boolean,
    icon: RegisterRole,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = if (selected) RegisterPrimaryGreen else RegisterBorderGreen
    val backgroundColor = if (selected) RegisterSelectedGreen else Color.White

    Column(
        modifier = modifier
            .height(76.dp)
            .background(backgroundColor, RoundedCornerShape(7.dp))
            .border(1.4.dp, borderColor, RoundedCornerShape(7.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Canvas(modifier = Modifier.size(22.dp)) {
            if (icon == RegisterRole.Provider) {
                drawGraduateIcon(RegisterPrimaryGreen)
            } else {
                drawBriefcaseIcon(RegisterPrimaryGreen)
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = title,
            color = RegisterTextDark,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        )
    }
}

@Composable
private fun RegisterTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
    isPassword: Boolean = false,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        RegisterSectionLabel(text = label)

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = RegisterPlaceholder,
                    fontSize = 14.sp,
                    letterSpacing = if (isPassword) 2.sp else 0.sp,
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(7.dp),
            colors = registerTextFieldColors(),
        )
    }
}

@Composable
private fun registerTextFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    focusedIndicatorColor = RegisterBorderGreen,
    unfocusedIndicatorColor = RegisterBorderGreen,
    cursorColor = RegisterPrimaryGreen,
)

@Composable
private fun RegisterSectionLabel(text: String) {
    Text(
        text = text,
        color = RegisterTextMuted,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.7.sp,
    )
}

@Composable
private fun AgreementRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(22.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = RegisterPrimaryGreen,
                uncheckedColor = RegisterBorderGreen,
                checkmarkColor = Color.White,
            ),
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Dengan membuat akun, saya",
                color = RegisterTextMuted,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            )

            Row {
                Text(
                    text = "menyetujui ",
                    color = RegisterTextMuted,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                )
                Text(
                    text = "Ketentuan Layanan",
                    color = RegisterPrimaryGreen,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.clickable(onClick = onTermsClick),
                )
                Text(
                    text = " dan",
                    color = RegisterTextMuted,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                )
            }

            Row {
                Text(
                    text = "Kebijakan Privasi",
                    color = RegisterPrimaryGreen,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    modifier = Modifier.clickable(onClick = onPrivacyClick),
                )
                Text(
                    text = " Skema-U.",
                    color = RegisterTextMuted,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                )
            }
        }
    }
}

@Composable
private fun ExistingAccountDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = Color(0xFFD5DDD1),
            thickness = 1.dp,
        )

        Text(
            text = "SUDAH PUNYA AKUN?",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = RegisterTextMuted,
            fontSize = 8.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Bold,
        )

        Divider(
            modifier = Modifier.weight(1f),
            color = Color(0xFFD5DDD1),
            thickness = 1.dp,
        )
    }
}

private fun DrawScope.drawGraduateIcon(color: Color) {
    val stroke = 1.8.dp.toPx()
    val cap = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.12f)
        lineTo(size.width * 0.90f, size.height * 0.34f)
        lineTo(size.width * 0.50f, size.height * 0.56f)
        lineTo(size.width * 0.10f, size.height * 0.34f)
        close()
    }

    drawPath(path = cap, color = color, style = Stroke(width = stroke, cap = StrokeCap.Round))
    drawLine(
        color = color,
        start = Offset(size.width * 0.24f, size.height * 0.46f),
        end = Offset(size.width * 0.50f, size.height * 0.72f),
        strokeWidth = stroke,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = Offset(size.width * 0.50f, size.height * 0.72f),
        end = Offset(size.width * 0.76f, size.height * 0.46f),
        strokeWidth = stroke,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = Offset(size.width * 0.84f, size.height * 0.38f),
        end = Offset(size.width * 0.84f, size.height * 0.64f),
        strokeWidth = stroke,
        cap = StrokeCap.Round,
    )
}

private fun DrawScope.drawBriefcaseIcon(color: Color) {
    val stroke = 1.8.dp.toPx()
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.14f, size.height * 0.34f),
        size = Size(size.width * 0.72f, size.height * 0.48f),
        style = Stroke(width = stroke),
    )
    drawRoundRect(
        color = color,
        topLeft = Offset(size.width * 0.36f, size.height * 0.18f),
        size = Size(size.width * 0.28f, size.height * 0.20f),
        style = Stroke(width = stroke),
    )
    drawLine(
        color = color,
        start = Offset(size.width * 0.14f, size.height * 0.52f),
        end = Offset(size.width * 0.86f, size.height * 0.52f),
        strokeWidth = stroke,
    )
    drawCircle(
        color = color,
        radius = stroke * 0.60f,
        center = Offset(size.width * 0.50f, size.height * 0.52f),
    )
}

@Preview(
    name = "Register Screen",
    showBackground = true,
    backgroundColor = 0xFFEAF8E8,
    widthDp = 390,
    heightDp = 884,
)
@Composable
private fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}
