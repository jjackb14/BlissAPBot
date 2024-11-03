package com.jjackb14.blissapbot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    /**
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
//        if (command.equals("welcome")) {
//            String userTag = event.getUser().getAsTag();
//            event.reply("Welcome to the server, **" + userTag + "**!").setEphemeral(true).queue();
//        }
//        else if (command.equals("roles")) {
//            event.deferReply().setEphemeral(true).queue();
//            String response = "";
//            for (Role role : Objects.requireNonNull(event.getGuild()).getRoles()) {
//                response += role.getAsMention() + "\n";
//            }
//            event.getHook().sendMessage(response).queue();
//        }
//        else if (command.equals("say")) {
//            OptionMapping messageOption = event.getOption("message");
//            assert messageOption != null;
//            String message = messageOption.getAsString();
//
//            MessageChannel channel;
//            OptionMapping channelOption = event.getOption("channel");
//            if (channelOption != null) {
//                channel = channelOption.getAsChannel().asStandardGuildMessageChannel();
//            }
//            else {
//                channel = event.getChannel();
//            }
//
//            channel.sendMessage(message).queue();
//            event.reply("Your message was sent!").setEphemeral(true).queue();
//        }
    }

    /**
     * @param event
     */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
//        commandData.add(Commands.slash("welcome", "Send a welcome message"));
//        commandData.add(Commands.slash("roles", "Display all roles on the server"));
//
//
//        OptionData option1 = new OptionData(OptionType.STRING, "message", "The message you want the bot to say", true);
//        OptionData option2 = new OptionData(OptionType.CHANNEL, "channel", "The channel you want to send this message in");
//        commandData.add(Commands.slash("say", "make the bot say something").addOptions(option1, option2));
//
//        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
