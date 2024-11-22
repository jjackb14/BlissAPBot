package com.jjackb14.blissapbot.commands.execution;

import com.jjackb14.blissapbot.database.Database;
import com.jjackb14.blissapbot.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Gets all the players registered to the AP System by a provided map.
 */
public class GetPlayersByMap {

    /**
     * Gets all the players registered to the AP System based on the map given in the command.
     * @param event Event provided by the command.
     */
    public static void getPlayersByMap(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        String map = Objects.requireNonNull(event.getOption("map")).getAsString();

        ArrayList<Player> playerList = db.getAllPlayers();
        ArrayList<Player> filtered = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getMap().equals(map)) {
                filtered.add(player);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        int count = 0;
        for (Player player : filtered) {
            count++;
            stringBuilder.append("**IGN: **").append(player.getUserName()).append(" - ");
            stringBuilder.append("**Gamertag: **").append(player.getGamerTag()).append(" - ");
            stringBuilder.append("**Map: **").append(player.getMap()).append(" - ");
            stringBuilder.append("**Tribe: **").append(player.getTribeName());
            if (count < playerList.size()) {
                stringBuilder.append("\n");
            }
        }

        if (count == 0) {
            stringBuilder.append("There are no players registered on ").append(map).append("!");
        }

        System.out.println("Fetched " + count + " players out of " + playerList.size() + " total players.");

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Retrieved all registered players on " + map + "!");
        eb.setDescription(stringBuilder.toString());

        event.replyEmbeds(eb.build()).queue();
    }

}
