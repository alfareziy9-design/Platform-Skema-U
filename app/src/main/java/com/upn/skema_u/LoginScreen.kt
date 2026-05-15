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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LoginPrimaryGreen = Color(0xFF0B7A1B)
private val LoginDarkGreen = Color(0xFF0A6817)
private val LoginSoftGreen = Color(0xFFEAF8E8)
private val LoginBorderGreen = Color(0xFFBBCAB5)
private val LoginTextDark = Color(0xFF121A12)
private val LoginTextMuted = Color(0xFF485246)
private val LoginPlaceholder = Color(0xFF9AA8BC)

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (emailOrUsername: String, password: String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onRememberMeChange: (Boolean) -> Unit = {},
) {
    var emailOrUsername by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var rememberMe by rememberSaveable { mutableStateOf(false) }

    LoginScreen(
        emailOrUsername = emailOrUsername,
        password = password,
        rememberMe = rememberMe,
        onEmailOrUsernameChange = { emailOrUsername = it },
        onPasswordChange = { password = it },
        onRememberMeChange = {
            rememberMe = it
            onRememberMeChange(it)
        },
        onLoginClick = { onLoginClick(emailOrUsername, password) },
        onRegisterClick = onRegisterClick,
        onForgotPasswordClick = onForgotPasswordClick,
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    emailOrUsername: String,
    password: String,
    rememberMe: Boolean,
    onEmailOrUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRememberMeChange: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.White,
                        LoginSoftGreen.copy(alpha = 0.78f),
                        LoginSoftGreen.copy(alpha = 0.24f),
                    ),
                ),
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
        ) {
            LoginHeader()

            LoginCard(
                emailOrUsername = emailOrUsername,
                password = password,
                rememberMe = rememberMe,
                onEmailOrUsernameChange = onEmailOrUsernameChange,
                onPasswordChange = onPasswordChange,
                onRememberMeChange = onRememberMeChange,
                onLoginClick = onLoginClick,
                onRegisterClick = onRegisterClick,
                onForgotPasswordClick = onForgotPasswordClick,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(26.dp))

            SecurityFooter()

            Spacer(modifier = Modifier.height(22.dp))

            AudienceText()

            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
private fun LoginHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(126.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = "Skema-U",
            color = LoginPrimaryGreen,
            fontSize = 29.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun LoginCard(
    emailOrUsername: String,
    password: String,
    rememberMe: Boolean,
    onEmailOrUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRememberMeChange: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = 18.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = LoginPrimaryGreen.copy(alpha = 0.12f),
                ambientColor = Color.Black.copy(alpha = 0.04f),
            )
            .border(
                width = 1.dp,
                color = LoginBorderGreen,
                shape = RoundedCornerShape(12.dp),
            ),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            EducationIconBox()

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Selamat Datang",
                color = LoginTextDark,
                fontSize = 31.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Akses bursa talenta akademik Anda",
                color = LoginTextMuted,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(54.dp))

            LoginTextField(
                label = "EMAIL ATAU USERNAME",
                value = emailOrUsername,
                onValueChange = onEmailOrUsernameChange,
                placeholder = "Masukkan kredensial Anda",
                keyboardType = KeyboardType.Email,
            )

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FieldLabel(text = "KATA SANDI")

                Text(
                    text = "Lupa Kata Sandi?",
                    color = LoginDarkGreen,
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = onForgotPasswordClick),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            PasswordField(
                value = password,
                onValueChange = onPasswordChange,
            )

            Spacer(modifier = Modifier.height(28.dp))

            RememberMeRow(
                checked = rememberMe,
                onCheckedChange = onRememberMeChange,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LoginPrimaryGreen,
                    contentColor = Color.White,
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
            ) {
                Text(
                    text = "Masuk",
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                )
            }

            Spacer(modifier = Modifier.height(34.dp))

            Divider(color = Color(0xFFD9E0D6), thickness = 1.dp)

            Spacer(modifier = Modifier.height(34.dp))

            Text(
                text = "Baru di Skema-U?",
                color = LoginTextMuted,
                fontSize = 17.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedButton(
                onClick = onRegisterClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = LoginDarkGreen,
                ),
                border = androidx.compose.foundation.BorderStroke(1.4.dp, LoginDarkGreen),
            ) {
                Text(
                    text = "Daftar Akun",
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                )
            }
        }
    }
}

@Composable
private fun EducationIconBox() {
    Box(
        modifier = Modifier
            .size(width = 64.dp, height = 50.dp)
            .background(LoginPrimaryGreen, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(32.dp)) {
            drawGraduateCap(
                center = Offset(size.width * 0.48f, size.height * 0.42f),
                width = size.width * 0.88f,
                color = Color.White,
                strokeWidth = 2.5.dp.toPx(),
            )
        }
    }
}

@Composable
private fun LoginTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        FieldLabel(text = label)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholder,
                    color = LoginPlaceholder,
                    fontSize = 18.sp,
                )
            },
            shape = RoundedCornerShape(4.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = loginTextFieldColors(),
        )
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        singleLine = true,
        placeholder = {
            Text(
                text = "........",
                color = LoginPlaceholder,
                fontSize = 20.sp,
                letterSpacing = 2.sp,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        shape = RoundedCornerShape(4.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = loginTextFieldColors(),
    )
}

@Composable
private fun loginTextFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    disabledContainerColor = Color.White,
    focusedIndicatorColor = LoginBorderGreen,
    unfocusedIndicatorColor = LoginBorderGreen,
    cursorColor = LoginPrimaryGreen,
)

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        color = LoginTextMuted,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp,
    )
}

@Composable
private fun RememberMeRow(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(26.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = LoginPrimaryGreen,
                uncheckedColor = LoginBorderGreen,
                checkmarkColor = Color.White,
            ),
        )

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = "Tetap masuk",
            color = LoginTextMuted,
            fontSize = 17.sp,
            lineHeight = 22.sp,
        )
    }
}

@Composable
private fun SecurityFooter() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(modifier = Modifier.size(18.dp)) {
            drawShield(color = LoginPrimaryGreen)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "KEAMANAN TERJAMIN STANDAR AKADEMIK",
            color = LoginTextMuted,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.6.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun AudienceText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "UMKM",
            color = Color(0xFF7D857A).copy(alpha = 0.70f),
            fontSize = 25.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.width(34.dp))

        Text(
            text = "MAHASISWA",
            color = Color(0xFF7D857A).copy(alpha = 0.70f),
            fontSize = 25.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawGraduateCap(
    center: Offset,
    width: Float,
    color: Color,
    strokeWidth: Float,
) {
    val capHeight = width * 0.30f
    val left = center.x - width / 2f
    val right = center.x + width / 2f
    val top = center.y - capHeight * 0.70f
    val bottom = center.y + capHeight * 0.70f

    val cap = Path().apply {
        moveTo(center.x, top)
        lineTo(right, center.y)
        lineTo(center.x, bottom)
        lineTo(left, center.y)
        close()
    }

    drawPath(path = cap, color = color)
    drawLine(
        color = color,
        start = Offset(left + width * 0.20f, center.y + capHeight * 0.18f),
        end = Offset(center.x, bottom + capHeight * 0.34f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = Offset(center.x, bottom + capHeight * 0.34f),
        end = Offset(right - width * 0.20f, center.y + capHeight * 0.18f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
    drawLine(
        color = color,
        start = Offset(right - width * 0.06f, center.y + capHeight * 0.08f),
        end = Offset(right - width * 0.06f, bottom + capHeight * 0.50f),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
    )
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawShield(color: Color) {
    val shield = Path().apply {
        moveTo(size.width * 0.50f, size.height * 0.04f)
        lineTo(size.width * 0.90f, size.height * 0.22f)
        lineTo(size.width * 0.86f, size.height * 0.58f)
        cubicTo(
            size.width * 0.82f,
            size.height * 0.78f,
            size.width * 0.66f,
            size.height * 0.90f,
            size.width * 0.50f,
            size.height * 0.98f,
        )
        cubicTo(
            size.width * 0.34f,
            size.height * 0.90f,
            size.width * 0.18f,
            size.height * 0.78f,
            size.width * 0.14f,
            size.height * 0.58f,
        )
        lineTo(size.width * 0.10f, size.height * 0.22f)
        close()
    }

    drawPath(path = shield, color = color)
    drawLine(
        color = Color.White,
        start = Offset(size.width * 0.32f, size.height * 0.52f),
        end = Offset(size.width * 0.45f, size.height * 0.66f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round,
    )
    drawLine(
        color = Color.White,
        start = Offset(size.width * 0.45f, size.height * 0.66f),
        end = Offset(size.width * 0.70f, size.height * 0.38f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round,
    )
}

@Preview(
    name = "Login Screen",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 390,
    heightDp = 884,
)
@Composable
private fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            emailOrUsername = "",
            password = "",
            rememberMe = false,
            onEmailOrUsernameChange = {},
            onPasswordChange = {},
            onRememberMeChange = {},
            onLoginClick = {},
            onRegisterClick = {},
            onForgotPasswordClick = {},
        )
    }
}
