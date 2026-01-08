/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package sumo.commands.setup;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sumo.Sumo;
import sumo.commands.setup.arena.AddSpawn;
import sumo.commands.setup.arena.Configure;
import sumo.commands.setup.arena.CreateArena;
import sumo.commands.setup.arena.DeleteArena;
import sumo.commands.setup.arena.DeleteSpawnPoints;
import sumo.commands.setup.arena.DeleteSpectatorSpawn;
import sumo.commands.setup.arena.DeleteWaitingSpawn;
import sumo.commands.setup.arena.DisableArena;
import sumo.commands.setup.arena.DisableKits;
import sumo.commands.setup.arena.EnableArena;
import sumo.commands.setup.arena.EnableKits;
import sumo.commands.setup.arena.FinishArena;
import sumo.commands.setup.arena.SetArena;
import sumo.commands.setup.arena.SetBarColor;
import sumo.commands.setup.arena.SetCountdown;
import sumo.commands.setup.arena.SetCurrency;
import sumo.commands.setup.arena.SetDamage;
import sumo.commands.setup.arena.SetFee;
import sumo.commands.setup.arena.SetLoseLevel;
import sumo.commands.setup.arena.SetMaxPlayers;
import sumo.commands.setup.arena.SetMinPlayers;
import sumo.commands.setup.arena.SetMoneyRewards;
import sumo.commands.setup.arena.SetRegenerationDelay;
import sumo.commands.setup.arena.SetReward;
import sumo.commands.setup.arena.SetSpawn;
import sumo.commands.setup.arena.SetSpectatorSpawn;
import sumo.commands.setup.arena.SetTeleport;
import sumo.commands.setup.arena.SetTimeLimit;
import sumo.commands.setup.arena.SetVotePercent;
import sumo.commands.setup.arena.SetWaitingSpawn;
import sumo.commands.setup.arena.SetupHelp;
import sumo.commands.setup.kits.AddKit;
import sumo.commands.setup.kits.DeleteKit;
import sumo.commands.setup.kits.LinkKit;
import sumo.commands.setup.kits.UnlinkKit;
import sumo.commands.setup.lobby.DeleteLobby;
import sumo.commands.setup.lobby.SetLobby;
import sumo.commands.setup.other.AddToWhitelist;
import sumo.commands.setup.other.GiveDoubleJumps;
import sumo.commands.setup.other.ResetCachedRank;
import sumo.commands.setup.other.ResetStats;
import sumo.commands.setup.other.SetLanguage;
import sumo.commands.setup.reload.ReloadBars;
import sumo.commands.setup.reload.ReloadConfig;
import sumo.commands.setup.reload.ReloadMSG;
import sumo.commands.setup.reload.ReloadTitles;
import sumo.commands.setup.selection.Clear;
import sumo.commands.setup.selection.SetP1;
import sumo.commands.setup.selection.SetP2;
import sumo.messages.Messages;
import sumo.selectionget.PlayerSelection;

public class SetupCommandsHandler implements CommandExecutor {

	private PlayerSelection plselection = new PlayerSelection();

	private HashMap<String, CommandHandlerInterface> commandHandlers = new HashMap<>();

	public SetupCommandsHandler(Sumo plugin) {
		commandHandlers.put("setp1", new SetP1(plselection));
		commandHandlers.put("setp2", new SetP2(plselection));
		commandHandlers.put("clear", new Clear(plselection));
		commandHandlers.put("setlobby", new SetLobby(plugin));
		commandHandlers.put("deletelobby", new DeleteLobby(plugin));
		commandHandlers.put("reloadmsg", new ReloadMSG(plugin));
		commandHandlers.put("reloadbars", new ReloadBars(plugin));
		commandHandlers.put("reloadconfig", new ReloadConfig(plugin));
		commandHandlers.put("reloadtitles", new ReloadTitles(plugin));
		commandHandlers.put("create", new CreateArena(plugin));
		commandHandlers.put("delete", new DeleteArena(plugin));
		commandHandlers.put("setarena", new SetArena(plugin, plselection));
		commandHandlers.put("setregenerationdelay", new SetRegenerationDelay(plugin));
		commandHandlers.put("setloselevel", new SetLoseLevel(plugin));
		commandHandlers.put("setspawn", new SetSpawn(plugin));
		commandHandlers.put("addspawn", new AddSpawn(plugin));
		commandHandlers.put("deletespawnpoints", new DeleteSpawnPoints(plugin));
		commandHandlers.put("setspectate", new SetSpectatorSpawn(plugin));
		commandHandlers.put("deletespectate", new DeleteSpectatorSpawn(plugin));
		commandHandlers.put("setwaitingspawn", new SetWaitingSpawn(plugin));
		commandHandlers.put("deletewaitingspawn", new DeleteWaitingSpawn(plugin));
		commandHandlers.put("setmaxplayers", new SetMaxPlayers(plugin));
		commandHandlers.put("setminplayers", new SetMinPlayers(plugin));
		commandHandlers.put("setvotepercent", new SetVotePercent(plugin));
		commandHandlers.put("setcountdown", new SetCountdown(plugin));
		commandHandlers.put("setmoneyreward", new SetMoneyRewards(plugin));
		commandHandlers.put("settimelimit", new SetTimeLimit(plugin));
		commandHandlers.put("setteleport", new SetTeleport(plugin));
		commandHandlers.put("setdamage", new SetDamage(plugin));
		commandHandlers.put("finish", new FinishArena(plugin));
		commandHandlers.put("disable", new DisableArena(plugin));
		commandHandlers.put("enable", new EnableArena(plugin));
		commandHandlers.put("enablekits", new EnableKits(plugin));
		commandHandlers.put("disablekits", new DisableKits(plugin));
		commandHandlers.put("addkit", new AddKit(plugin));
		commandHandlers.put("deletekit", new DeleteKit(plugin));
		commandHandlers.put("linkkit", new LinkKit(plugin));
		commandHandlers.put("unlinkkit", new UnlinkKit(plugin));
		commandHandlers.put("setbarcolor", new SetBarColor(plugin));
		commandHandlers.put("setbarcolour", new SetBarColor(plugin));
		commandHandlers.put("setreward", new SetReward(plugin));
		commandHandlers.put("setfee", new SetFee(plugin));
		commandHandlers.put("setcurrency", new SetCurrency(plugin));
		commandHandlers.put("setlanguage", new SetLanguage(plugin));
		commandHandlers.put("addtowhitelist", new AddToWhitelist(plugin));
		commandHandlers.put("configure", new Configure(plugin));
		commandHandlers.put("help", new SetupHelp(plugin));
		commandHandlers.put("resetstats", new ResetStats(plugin));
		commandHandlers.put("resetcachedrank", new ResetCachedRank(plugin));
		commandHandlers.put("givedoublejumps", new GiveDoubleJumps(plugin));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Player is expected");
			return true;
		}
		Player player = (Player) sender;
		// check permissions
		if (!player.hasPermission("sumo.setup")) {
			Messages.sendMessage(player, Messages.nopermission);
			return true;
		}
		// get command
		if (args.length > 0 && commandHandlers.containsKey(args[0])) {
			CommandHandlerInterface commandh = commandHandlers.get(args[0]);
			//check args length
			if (args.length - 1 < commandh.getMinArgsLength()) {
				Messages.sendMessage(player, "&c ERROR: Please use &6/sumo cmds&c to view required arguments for all game commands");
				return false;
			}
			//execute command
			boolean result = commandh.handleCommand(player, Arrays.copyOfRange(args, 1, args.length));
			return result;
		} 
		Messages.sendMessage(player, "&c ERROR: Please use &6/sumo cmds&c to view all valid game commands");
		return false;
	}
}
