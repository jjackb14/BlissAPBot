package com.jjackb14.blissapbot.commands.execution;

import com.jjackb14.blissapbot.database.Database;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Marks a player as seen in the AP System.
 */
public class MarkSeen {

    /**
     * Marks a player as seen in the AP System based on the given username and gamertag.
     * This will also update the value of seenToday.
     * @param event Event provided by the command.
     */
    public static void markSeen(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        String ign = Objects.requireNonNull(event.getOption("ign")).getAsString();
        String gamertag = Objects.requireNonNull(event.getOption("gamertag")).getAsString();

        String response = "";

        try {
            if (db.markSeen(ign, gamertag)) {
                response = ign + " has been marked seen for today!";

                EmbedBuilder eb = new EmbedBuilder();
                eb.setDescription(response);

                event.replyEmbeds(eb.build()).setEphemeral(true).queue();
            }
            else {
                response = ign + " could not be marked seen for today! (They have probably already been seen!)";

                EmbedBuilder eb = new EmbedBuilder();
                eb.setDescription(response);

                event.replyEmbeds(eb.build()).setEphemeral(true).queue();
            }
        } catch (RuntimeException e) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription("Failed to mark player as seen. They may not be registered.");

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }



    }

}
