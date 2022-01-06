package ru.ialmostdeveloper.soulfire_mobile.network.models

data class DiaryQuestion(
    // Поле с типом вопроса
    // 1 - вопрос и ответ
    // 2 - вопрос, ответ и степень веры в ответ
    // 3 - вопрос и несколько иконок - вариантов выбора
    val type: Int,
    val content: String,
    val canSkip: Boolean,
    val optionsToSelect: Array<String>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiaryQuestion

        if (type != other.type) return false
        if (content != other.content) return false
        if (canSkip != other.canSkip) return false
        if (!optionsToSelect.contentEquals(other.optionsToSelect)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type
        result = 31 * result + content.hashCode()
        result = 31 * result + canSkip.hashCode()
        result = 31 * result + optionsToSelect.contentHashCode()
        return result
    }
}
