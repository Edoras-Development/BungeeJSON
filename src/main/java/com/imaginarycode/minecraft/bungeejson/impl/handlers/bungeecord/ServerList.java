/**
 * This file is part of BungeeJSON.
 *
 * BungeeJSON is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BungeeJSON is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BungeeJSON.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.imaginarycode.minecraft.bungeejson.impl.handlers.bungeecord;

import com.imaginarycode.minecraft.bungeejson.api.ApiRequest;
import com.imaginarycode.minecraft.bungeejson.api.RequestHandler;
import lombok.Data;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerList implements RequestHandler {
    @Override
    public Object handle(ApiRequest request) {
        List<Server> servers = new ArrayList<>();
        for (ServerInfo si : ProxyServer.getInstance().getServers().values()) {
            Server server = new Server();
            server.setName(si.getName());
            List<Player> players = new ArrayList<>();
            for (ProxiedPlayer pp : si.getPlayers()) {
                Player player = new Player();
                player.setName(pp.getName());
                player.setUuid(pp.getUniqueId());
                players.add(player);
            }
            server.setPlayers(players);
            servers.add(server);
        }
        return servers;
    }

    @Override
    public boolean requiresAuthentication() {
        return false;
    }

    @Data
    private class Server {
        private String name;
        private List<Player> players;
    }

    @Data
    private class Player {
        private String name;
        private UUID uuid;
    }
}
