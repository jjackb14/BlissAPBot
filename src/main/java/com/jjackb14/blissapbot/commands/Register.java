package com.jjackb14.blissapbot.commands;

import com.jjackb14.blissapbot.database.Database;
import com.jjackb14.blissapbot.exceptions.InvalidGamertagException;
import com.jjackb14.blissapbot.exceptions.InvalidNameException;
import com.jjackb14.blissapbot.exceptions.InvalidTribeNameException;
import com.jjackb14.blissapbot.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * The backend functionality of the /register command.
 */
public class Register {

    /**
     * Registers a user to the AP system. That user is then stored in the Database.
     * @param event Event provided by the command.
     */
    public static void register(@NotNull SlashCommandInteractionEvent event) {
        Database db = Database.getInstance();

        String ign = Objects.requireNonNull(event.getOption("ign")).getAsString();
        String gamertag = Objects.requireNonNull(event.getOption("gamertag")).getAsString();
        String tribe = Objects.requireNonNull(event.getOption("tribe")).getAsString();
        String map = Objects.requireNonNull(event.getOption("map")).getAsString();

        String response = "";

        try {
            Player newPlayer = new Player(ign, gamertag, map, tribe);
            db.insertData(newPlayer);
            response = "You have been successfully registered to the AP System!";
        } catch (IllegalArgumentException e) {
            response = "Registration failed. Make sure that you have entered a valid map and that your IGN, tribe name, or Gamertag is not " +
                    "more than 24 characters";
            System.out.println("Invalid register attempted");
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        } catch (RuntimeException e) {
            response = "Failed to add player to the Database.";
            System.out.println("Player could not be added to the DB, probably already exists.");
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        } catch (InvalidNameException e) {
            response = e.getMessage();
            System.out.println("Invalid register attempted");
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        } catch (InvalidGamertagException e) {
            response = e.getMessage();
            System.out.println("Invalid register attempted");
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        } catch (InvalidTribeNameException e) {
            response = e.getMessage();
            System.out.println("Invalid register attempted");
            EmbedBuilder eb = new EmbedBuilder();
            eb.setDescription(response);

            event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setDescription(response);

        event.replyEmbeds(eb.build()).queue();
    }

}
