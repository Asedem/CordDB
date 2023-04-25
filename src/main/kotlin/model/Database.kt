package model

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageHistory
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel

class Database(
    private val logChannel: TextChannel
) {

    private val collections: MutableList<Collection> = listOf<Collection>().toMutableList();

    init {
        regenerateCollections()
    }

    fun regenerateCollections() {
        this.collections.clear()
        val history: MessageHistory = this.logChannel.history ?: return;
        val messages: MutableList<Message> = history.retrievedHistory ?: return;
        messages.forEach { collections.add(Collection(it.contentRaw.split(" | ")[0])) }
    }
}