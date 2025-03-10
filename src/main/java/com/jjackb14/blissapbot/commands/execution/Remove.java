package com.jjackb14.blissapbot.commands.execution;

import com.jjackb14.blissapbot.database.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Removes a player from the AP System.
 */
public class Remove {

    /**
     * Removes a player from the AP System based on the provided username and gamertag.
     * @param event Event provided by the command.
     */
    public static void remove(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        String user_name = Objects.requireNonNull(event.getOption("ign")).getAsString();
        String gamertag = Objects.requireNonNull(event.getOption("gamertag")).getAsString();

        String response = "";

        boolean flag = db.removeData(user_name, gamertag);

        if (!flag) {
            response = "Player could not be removed from the AP System!";
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }
        else {
            response = "Player has successfully been removed from the AP System!";
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).queue();
        }
    }

}
