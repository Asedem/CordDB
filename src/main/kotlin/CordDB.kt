import model.Database
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
    private var logChannel: TextChannel? = null

    init {
        jda = JDABuilder.createDefault(token)
            .setStatus(OnlineStatus.DO_NOT_DISTURB)
            .setActivity(Activity.listening("CordDB"))
            .build()
    }

    fun connect() {
        val guild: Guild = this.jda.getGuildById(this.serverId) ?: return
        this.logChannel = guild.getTextChannelById(this.channelId)
    }

    val disconnect = { this.logChannel = null }

    var connected: Boolean = this.logChannel != null

    fun getServerDatabase(): Database? {
        if (!this.connected) return null
        return Database(this.logChannel!!)
    }

    fun query(statement: String, vararg inserts: Any) {
        val reader: QueryReader = QueryReader(statement, inserts)
        val type: QueryType = reader.evaluateQueryType()
    }
}