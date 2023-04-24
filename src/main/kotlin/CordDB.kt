import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel
import query.QueryReader
import query.QueryType

class CordDB(
    token: String,
    private val serverId: Long,
    private val channelId: Long
) {

    private val jda: JDA
    private var guild: Guild? = null;
    private var logChannel: TextChannel? = null;

    init {
        jda = JDABuilder.createDefault(token)
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.listening("CordDB"))
            .build()
    }

    fun connectToServer() {
        this.guild = this.jda.getGuildById(this.serverId)
        if (this.guild == null) return
        this.logChannel = this.guild!!.getTextChannelById(this.channelId)
    }

    fun query(statement: String, vararg inserts: Any) {
        val reader: QueryReader = QueryReader(statement, inserts)
        val type: QueryType = reader.evaluateQueryType()
    }
}