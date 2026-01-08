package sumo.lobby;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import sumo.Sumo;
import sumo.utils.FormattingCodesParser;

public class LobbyScoreboard {

	private Sumo plugin;

	public LobbyScoreboard(Sumo plugin) {
		this.plugin = plugin;
	}

	public void createLobbyScoreboard(Player player) {
		if (!plugin.getConfig().getBoolean("special.UseScoreboard") || !plugin.getConfig().getBoolean("scoreboard.enablelobbyscoreboard")) {
			return;
		}
		plugin.getScoreboardManager().storePrejoinScoreboard(player);

		Scoreboard scoreboard = plugin.getScoreboardManager().resetScoreboard(player);
		Objective o = scoreboard.getObjective(DisplaySlot.SIDEBAR);

		int size = plugin.getConfig().getStringList("scoreboard.lobby").size();

		for (String s : plugin.getConfig().getStringList("scoreboard.lobby")) {
			s = plugin.getScoreboardManager().getPlaceholderString(s, player);
			s = FormattingCodesParser.parseFormattingCodes(s);

			o.getScore(plugin.getScoreboardManager().getTeamEntry(scoreboard, size, s)).setScore(size);
			size--;
		}
		player.setScoreboard(scoreboard);
		plugin.getScoreboardManager().addLobbyScoreboard(player.getName());
	}
}
