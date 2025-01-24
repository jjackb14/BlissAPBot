package com.jjackb14.blissapbot.commands.manager;

import com.jjackb14.blissapbot.commands.execution.*;
import com.jjackb14.blissapbot.playerlist.PlayerList;
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

/**
 * Manages all the commands associated with the BlissAPBot.
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
            case("remove") -> {
                Remove.remove(event);
            }
            case("getallplayers") -> {
                GetAllPlayers.getAllPlayers(event);
            }
            case("getplayersbymap") -> {
                GetPlayersByMap.getPlayersByMap(event);
            }
            case("markseen") -> {
                MarkSeen.markSeen(event);
            }
            case("getplayerinfo") -> {
                GetPlayerInfo.getPlayerInfo(event);
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
        setUpCommands(event);
    }

    /**
     * Private method to add the commands to the bot to protect access to the discord api
     * @param event The guild event.
     */
    private void setUpCommands(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        //Command /test
        commandData.add(Commands.slash("test", "a debug command")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED));

        // Command: /register [IGN], [GT], [Tribe], [Map]
        commandData.add(Commands.slash("register", "Register to the AP System.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
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

        // Command: /remove [GT]
        commandData.add(Commands.slash("remove", "Remove a player from the AP Sytem.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addOptions(
                        new OptionData(OptionType.STRING, "ign", "The ign of the player to remove", true),
                        new OptionData(OptionType.STRING, "gamertag", "The gamertag of the player to remove", true)
                ));

        // Command: /get all players
        commandData.add(Commands.slash("getallplayers", "Gets all the players in the AP System.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED));

        // Command: /getplayersbymap [Map]
        commandData.add(Commands.slash("getplayersbymap", "Gets all the players registered to a specific map.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addOptions(
                        new OptionData(OptionType.STRING, "map", "The map to get all players from.", true)
                                .addChoice("The Island", "Island")
                                .addChoice("The Center", "Center")
                                .addChoice("Scorched Earth", "Scorched")
                                .addChoice("Aberration", "Aberration")
                ));

        // Command: /markseen [IGN] [GT]
        commandData.add(Commands.slash("markseen", "Marks a player as seen for today.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addOptions(
                        new OptionData(OptionType.STRING, "ign", "Player's character name to be marked as seen.", true),
                        new OptionData(OptionType.STRING, "gamertag", "Player's gamertag to be marked as seen", true)
                ));

        // Command: /getplayerinfo [IGN][GT]
        commandData.add(Commands.slash("getplayerinfo", "Gets the value of all fields for a specific player.")
                .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
                .addOptions(
                        new OptionData(OptionType.STRING, "ign", "Player's character name to be found.", true),
                        new OptionData(OptionType.STRING, "gamertag", "Player's gamertag to be found.", true)
                ));

        // Adds the Commands to the bot
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
