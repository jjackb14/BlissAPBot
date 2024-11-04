package com.jjackb14.blissapbot.bot;

import com.jjackb14.blissapbot.commands.CommandManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.sql.*;

/**
 * A Discord bot for managing the Activity Points System on ARK Bliss.
 *
 * @author Jack Benoit
 */
public class BlissAPBot {

    /** A final instance of the ShardManager. */
    private final ShardManager shardManager;

    /** A final instance of DotEnv. */
    private final Dotenv config;

    /**
     * Loads environment variables and builds the ShardManager.
     * @throws LoginException when bot token is invalid.
     * @throws SQLException
     */
    public BlissAPBot() throws LoginException, SQLException {

        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();

        //Register listeners
        shardManager.addEventListener(new CommandManager());

        System.out.println("Bot started");
    }

    /**
     * Retrieves the ShardManager.
     * @return the ShardManager instance of the bot.
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * Retrieves the bot environment variables.
     * @return the DotEnv instance for the bot.
     */
    public Dotenv getConfig() {
        return config;
    }

    /**
     * Main method of the bot.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            BlissAPBot bot = new BlissAPBot();
        }
        catch (LoginException | SQLException e) {
            System.out.println("ERROR: Provided token is not valid");
        }

    }
}
