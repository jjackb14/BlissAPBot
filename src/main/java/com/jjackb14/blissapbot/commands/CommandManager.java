package com.jjackb14.blissapbot.commands;

import com.jjackb14.blissapbot.exceptions.ExistingPlayerException;
import com.jjackb14.blissapbot.player.Player;
import com.jjackb14.blissapbot.playerlist.PlayerList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Manages all the commands associated with the BlissAPBot.
 *
 * @author Jack Benoit
 */
public class CommandManager extends ListenerAdapter {

    /**
     * Executes the various functionalities based on the slash command entered.
     * @param event The slash command event.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        PlayerList playerList = new PlayerList();
        String command = event.getName();

        switch (command) {
            case("register") -> {
                Register.register(event);
            }
            case("test") -> {
                event.reply("Test message").queue();
            }
            default -> {
                return;
            }
        }
    }

    /**
     * Adds the Commands to the bot when the guild is ready.
     * @param event The guild event.
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //Command /test
        commandData.add(Commands.slash("test", "a debug command"));

        // Command: /register [IGN], [GT], [Tribe], [Map]
        commandData.add(Commands.slash("register", "Register to the AP System.")
                .setDefaultPermissions(DefaultMemberPermissions.ENABLED)
                .addOptions(
                        new OptionData(OptionType.STRING, "ign", "Your character name on Bliss.", true),
                        new OptionData(OptionType.STRING, "gamertag", "Your gamertag.", true),
                        new OptionData(OptionType.STRING, "map", "The main map you play on.", true)
                                .addChoice("The Island", "Island")
                                .addChoice("The Center", "Center")
                                .addChoice("Scorched Earth", "Scorched")
                                .addChoice("Aberration", "Aberration"),
                        new OptionData(OptionType.STRING, "tribe", "The name of your tribe.", true)
                ));

        // Adds the Commands to the bot
       event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
