/*
 * Luna
 * Copyright (C) 2023  DashNetwork
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package xyz.dashnetwork.luna.utils.chat.builder.action;

import net.md_5.bungee.api.chat.ClickEvent;

public enum ClickAction {

    OPEN_URL(ClickEvent.Action.OPEN_URL),
    OPEN_FILE(ClickEvent.Action.OPEN_FILE),
    RUN_COMMAND(ClickEvent.Action.RUN_COMMAND),
    SUGGEST_COMMAND(ClickEvent.Action.SUGGEST_COMMAND),
    CHANGE_PAGE(ClickEvent.Action.CHANGE_PAGE);

    private final ClickEvent.Action action;

    ClickAction(ClickEvent.Action action) { this.action = action; }

    public ClickEvent.Action getAction() { return action; }

}
