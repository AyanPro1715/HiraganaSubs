package com.ayan.hiragana

import com.lagradost.cloudstream3.app
import com.lagradost.cloudstream3.subtitles.AbstractSubProvider
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities.SubtitleEntity
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities.SubtitleSearch
import java.io.File

class HiraganaSubs : AbstractSubProvider {
    override val idPrefix = "hiragana"

    override suspend fun search(query: SubtitleSearch): List<SubtitleEntity>? {
        // TODO: Japanese .srt ka link yahan dhundo
        return emptyList()
    }

    override suspend fun load(data: SubtitleEntity): String? {
        val srt = app.get(data.data).text
        val file = File.createTempFile("hira", ".srt")
        file.writeText(Converter.convertSrt(srt))
        // TODO: confirm karo ki path chahiye ya URL
        return file.absolutePath
    }
}
