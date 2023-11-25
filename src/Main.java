import entity.system.Game;
import entity.system.Player;
import entity.unit.Unit;
import util.Data;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Pixess RPG // In terminal edition");
        

        Data.send("Привет! Это игра " + game.getName());

        Data.send("Сколько игроков будет играть? (max 2)");
        int playersCount = Data.getInt();
        if (playersCount < 1 || playersCount > 2) {
            Data.sendError("Wrong player count");
            return;
        }

        for (int i = 0; i < playersCount; i++) {
            Data.send("Имя игрок " + (i + 1));
            String name = Data.getString();

            try {
                game.addPlayer(i, name);
            } catch (Exception e){
                Data.sendError("Player already exists");
                return;
            }
        }

        for (int i = 0; i < playersCount; i++) {
            Player player = game.getPlayers()[i];

            Data.send("Назначьте героев для игрока " + player.getName());
            String[] heroes = new String[3];

            for (int j = 0; j < 3; j++) {
                heroes[j] = Data.getString().toUpperCase();
            }

            try {
                game.setHeroesToPlayer(player, heroes);
            } catch (Exception e) {
                Data.sendError("Wrong heroes count");
                return;
            }

            Data.send("Назначены герои " + Arrays.toString(heroes) + " для игрока " + player.getName());
        }

        Data.send("Выберите режим игры");
        String play = Data.getString().toUpperCase();

        try {
            game.setPlayType(play);
        } catch (Exception e) {
            Data.sendError("Wrong play type");
            return;
        }

        if (playersCount == 1) {
            Data.send("Это конечно хорошо, что вы хотите играть в одного, но пока нельзя. Минус полбалла вам!");
            return;
        }

        Data.send("Игра начинается!");
        game.play();

        do {
            Data.send("Раунд " + game.getRound());

            for (int i = 0; i < game.getPlayers().length; i++) {
                Player currentPlayer = game.getPlayers()[i];
                Unit currentPlayerUnit = currentPlayer.getUnits()[(game.getRound() - 1) % 3];

                Player enemyPlayer = game.getPlayers()[(i + 1) % 2];

                Data.send(" ");
                Data.send("Ход игрока " + currentPlayer.getName());
                Data.send("Вы ходите за " + currentPlayerUnit.getName());

                for (int j = 0; j < 3; j++) {
                    String id = enemyPlayer.getUnits()[j].getHealth() == 0 ? "X" : String.valueOf(j + 1);

                    if (j == 0) {
                        Data.send("Выберите, кого атаковать: (" + id + ") -- " + enemyPlayer.getUnits()[0].getName() + " HP: " + enemyPlayer.getUnits()[0].getHealth());
                        continue;
                    }

                    Data.send("                          (" + id + ") -- " + enemyPlayer.getUnits()[j].getName() + " HP: " + enemyPlayer.getUnits()[j].getHealth());
                }

                int enemyId = Data.getInt();

                if (enemyId < 1 || enemyId > 3) {
                    Data.sendError("wrong unit id");
                    return;
                }

                if (enemyPlayer.getUnits()[enemyId - 1].getHealth() == 0) {
                    Data.send("Враг уже разбит! Вы пропускаете ход");
                    continue;
                }
                int damage = currentPlayerUnit.attack();
                int enemyUnitHealth = enemyPlayer.getUnits()[enemyId - 1].takeDamage(damage);

                if (enemyUnitHealth == -1) {
                    Data.send("Неудача! Атака отбита");
                } else if (currentPlayerUnit.getDamage() < damage) {
                    Data.send("Критический удар!");
                    Data.send("Здоровье врага: " + enemyUnitHealth);
                } else if (enemyUnitHealth == 0) {
                    Data.send("Враг повержен!");

                    if (!game.isAllPlayersAlive()) {
                        Data.send("Победил игрок: " + currentPlayer.getName());
                        return;
                    }
                } else {
                    Data.send("Здоровье врага: " + enemyUnitHealth);
                }

               try {
                   Thread.sleep(1000);
               } catch (Exception e) {

               }
            }

            game.play();
        } while (true);
    }
}
