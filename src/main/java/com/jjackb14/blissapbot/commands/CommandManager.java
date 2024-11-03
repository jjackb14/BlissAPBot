package com.jjackb14.blissapbot.commands;

import com.jjackb14.blissapbot.player.Player;
import com.jjackb14.blissapbot.playerlist.PlayerList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class CommandManager extends ListenerAdapter {

    /**
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        PlayerList playerList = new PlayerList();
        String command = event.getName();

        switch (command) {
            case("register") -> {
                event.deferReply().setEphemeral(true).queue();

                String ign = Objects.requireNonNull(event.getOption("ign")).getAsString();
                String gamertag = Objects.requireNonNull(event.getOption("gamertag")).getAsString();
                String tribe = Objects.requireNonNull(event.getOption("tribe")).getAsString();
                String map = Objects.requireNonNull(event.getOption("map")).getAsString();

                Player newPlayer = new Player(ign, gamertag, map, tribe);

                String response = "You have been successfully registered to the AP System!";

                //TODO will need a try catch when existing player has been implemented
                playerList.addPlayer(newPlayer);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setDescription(response);

                event.getHook().sendMessageEmbeds(eb.build()).queue();
            }
            default -> {
                return;
            }
        }
    }

    /**
     * @param event
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        // Command: /register [IGN], [GT], [Tribe], [Map]
        commandData.add(Commands.slash("register", "Register to the AP System.")
                .addOptions(
                        new OptionData(OptionType.STRING, "ign", "Your character name on Bliss.", true),
                        new OptionData(OptionType.STRING, "gamertag", "Your gamertag.", true),
                        new OptionData(OptionType.STRING, "map", "The main map you play on. Please only provide 1 map.", true),
                        new OptionData(OptionType.STRING, "tribe", "The name of your tribe.", true)
                ));

        // Adds the Commands to the bot
       event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
