# BWMod (Forge 1.8.9 Mod)

Grabs the bedwars stats from everyone in the lobby and sends in chat.
(set the "Grab Stats" keybind in the control settings)

`/SetAPIKey <key> - (do "/api new" on hypixel.net to get your API Key)`

`/GetAPIKey - to see what your API Key is set to`

Example:

`[20✫] Tzmo | 12.12 FKDR | 3 WS | 4.15 WLR | 812 Finals | 245 Wins | 59 Losses`

- if player level is 100+ then it is highlighted in gold
- if player FKDR is 3+ then it is highlighted in gold
- `[HIDDEN] WS` = the player has hidden their winstreak with hypixel "/settings"
- caches recently checked player stats to help prevent getting rate limited as often (clears after 1m30s of not checking so you can get fresh player stats after a game of bedwars)

(Hypixel's API rate limit is set to 120 requests per minute so dont spam it too much, you can use it in the main lobbies but wouldnt recommend in lobby 1)

![image](https://i.imgur.com/r3aLg6r.png)

First mod i have ever made myself with some help from [@xPenguinx](https://github.com/xpenguinx) so wont be the best.
