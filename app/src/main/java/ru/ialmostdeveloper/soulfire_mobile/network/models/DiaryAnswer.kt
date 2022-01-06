package ru.ialmostdeveloper.soulfire_mobile.network.models

data class DiaryAnswer(
    val type: Int,
    val question: String,
    val stringContent: String?,
    val itemsContent:  Array<String>?,
    val percentContent: Int?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiaryAnswer

        if (type != other.type) return false
        if (question != other.question) return false
        if (stringContent != other.stringContent) return false
        if (itemsContent != null) {
            if (other.itemsContent == null) return false
            if (!itemsContent.contentEquals(other.itemsContent)) return false
        } else if (other.itemsContent != null) return false
        if (percentContent != other.percentContent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type
        result = 31 * result + question.hashCode()
        result = 31 * result + (stringContent?.hashCode() ?: 0)
        result = 31 * result + (itemsContent?.contentHashCode() ?: 0)
        result = 31 * result + (percentContent ?: 0)
        return result
    }
}
