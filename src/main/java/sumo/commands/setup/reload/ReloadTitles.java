package sumo.commands.setup.reload;

import org.bukkit.entity.Player;

import sumo.Sumo;
import sumo.commands.setup.CommandHandlerInterface;
import sumo.utils.TitleMsg;

public class ReloadTitles  implements CommandHandlerInterface {

	private Sumo plugin;
	
	public ReloadTitles(Sumo plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean handleCommand(Player player, String[] args) {
		TitleMsg.loadTitles(plugin);
		player.sendMessage("Titles reloaded");
		return true;
	}

	@Override
	public int getMinArgsLength() {
		return 0;
	}

}
