package com.ayan.hiragana

import com.atilika.kuromoji.ipadic.Tokenizer

object Converter {
    private val tokenizer by lazy { Tokenizer() }

    fun toHiragana(text: String): String =
        tokenizer.tokenize(text).joinToString("") { t ->
            val r = t.reading
            if (r.isNullOrBlank() || r == "*") t.surface
            else r.map { if (it in 'ァ'..'ヶ') it - 0x60 else it }
                .joinToString("")
        }

    fun convertSrt(srt: String): String =
        srt.lines().joinToString("\n") { line ->
            val t = line.trim()
            if (t.isEmpty() || t.all { it.isDigit() } || "-->" in t) line
            else toHiragana(line)
        }
}
