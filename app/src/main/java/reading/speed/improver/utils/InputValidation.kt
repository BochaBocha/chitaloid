package reading.speed.improver.utils

enum class ValidationResult{
    SUCCESS,
    TO_SHORT,
    ALREADY_TAKEN
}

fun isUserNameValid(name : String): ValidationResult {

    if (name.isNullOrEmpty() || name.isNullOrBlank()) {
        return ValidationResult.TO_SHORT
    }
    if (isNameTooShort(name)) {
        return ValidationResult.TO_SHORT
    }

    if (UsersDataHandler.isUserExists(name)) {
        return ValidationResult.ALREADY_TAKEN
    }
    return ValidationResult.SUCCESS
}

fun isNameTooShort(str: String): Boolean {
    return str.length < 3
}