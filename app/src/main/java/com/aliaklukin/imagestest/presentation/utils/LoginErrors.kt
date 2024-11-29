package com.aliaklukin.imagestest.presentation.utils

enum class LoginErrors(val error: String) {
    EMAIL_INVALID("Wrong email format"),
    EMAIL_EMPTY("Email is empty"),
    PASSWORD_INVALID("Password must be 6-12 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (@, \$, !, %, *, ?, &, or #)."),
    PASSWORD_EMPTY("Password is empty"),
    AGE_EMPTY("Age is empty"),
    AGE_INVALID("Age should be between 18 and 99 years old")
}