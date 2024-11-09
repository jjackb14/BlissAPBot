package com.jjackb14.blissapbot.commands;

import com.jjackb14.blissapbot.database.Database;
import com.jjackb14.blissapbot.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Gets the values of all fields for a specific player.
 */
public class GetPlayerInfo {

    /**
     * Gets the values of all fields for a specific player based on the given username and gamertag.
     * @param event Event provided by the command.
     */
    public static void getPlayerInfo(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        ArrayList<Player> playerList = db.getAllPlayers();

        String ign = Objects.requireNonNull(event.getOption("ign")).getAsString();
        String gamertag = Objects.requireNonNull(event.getOption("gamertag")).getAsString();

        Player found = null;

        for (Player player : playerList) {
            if (player.getUserName().equals(ign) && player.getGamerTag().equals(gamertag)) {
                found = player;
            }
        }

        if (found == null) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription("Failed to find player! They may not be registered.");
            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("**IGN: **").append(found.getUserName()).append(" - ");
            stringBuilder.append("**Gamertag: **").append(found.getGamerTag()).append(" - ");
            stringBuilder.append("**Map: **").append(found.getMap()).append(" - ");
            stringBuilder.append("**Tribe: **").append(found.getTribeName()).append(" - ");
            stringBuilder.append("**Seen: **").append(found.getSeen()).append(" days - ");
            stringBuilder.append("**Seen Today: **").append(found.getSeenToday());

            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(stringBuilder.toString());
            event.replyEmbeds(eb.build()).queue();
        }

    }

}
