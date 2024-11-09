package com.jjackb14.blissapbot.bot;

import com.jjackb14.blissapbot.commands.CommandManager;
import com.jjackb14.blissapbot.database.Database;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.security.auth.login.LoginException;
import java.sql.*;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A Discord bot for managing the Activity Points System on ARK Bliss.
 */
public class BlissAPBot {

    /** A final instance of the ShardManager. */
    private final ShardManager shardManager;

    /** A final instance of DotEnv. */
    private final Dotenv config;

    /**
     * Loads environment variables and builds the ShardManager.
     * @throws LoginException when bot token is invalid.
     * @throws SQLException if there are errors with the Database.
     */
    public BlissAPBot() throws LoginException, SQLException {

        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();

        //Register listeners
        shardManager.addEventListener(new CommandManager());

        Database db = Database.getInstance();

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

            Database db = Database.getInstance();

            ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/London"));
            ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
            if (now.compareTo(nextRun) > 0) {
                nextRun = nextRun.plusDays(1);
            }

            Duration duration = Duration.between(now, nextRun);
            long initialDelay = duration.getSeconds();
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            Runnable task = db::resetSeenToday;

            scheduler.scheduleAtFixedRate(task,
                    initialDelay,
                    TimeUnit.DAYS.toSeconds(1),
                    TimeUnit.SECONDS
            );

            System.out.println(duration.toHours() + " hours until seenToday is reset.");
        }
        catch (LoginException | SQLException e) {
            System.out.println("ERROR: Provided token is not valid");
        }

    }
}
