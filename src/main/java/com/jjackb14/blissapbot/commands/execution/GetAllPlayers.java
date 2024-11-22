package com.jjackb14.blissapbot.commands.execution;

import com.jjackb14.blissapbot.database.Database;
import com.jjackb14.blissapbot.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Retrieves all the players registered to the AP System.
 */
public class GetAllPlayers {

    /**
     * Gets all the players registered to the AP System.
     * @param event Event provided by the command.
     */
    public static void getAllPlayers(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        ArrayList<Player> playerList = db.getAllPlayers();

        StringBuilder stringBuilder = new StringBuilder();

        int count = 0;
        for (Player player : playerList) {
            count++;
            stringBuilder.append("**IGN: **").append(player.getUserName()).append(" - ");
            stringBuilder.append("**Gamertag: **").append(player.getGamerTag()).append(" - ");
            stringBuilder.append("**Map: **").append(player.getMap()).append(" - ");
            stringBuilder.append("**Tribe: **").append(player.getTribeName());
            if (count < playerList.size()) {
                stringBuilder.append("\n");
            }
        }
        System.out.println("Fetched " + count + " players");

        if (count == 0) {
            stringBuilder.append("There are no players registered!");
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Retrieved all registered players!");
        eb.setDescription(stringBuilder.toString());

        event.replyEmbeds(eb.build()).queue();
    }

}
