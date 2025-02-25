package game;
import game.level.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A class that lets you save and load the game */

public class GameSaverLoader {

    public static void save(GameLevel level, String fileName) throws IOException {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "," +  CharacterController.getKillCount() +  "," + CharacterController.getCoinCount() + "\n");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    public static GameLevel load(GameLevel level, String fileName) throws IOException{
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");
            String levelName = tokens[0];
            int killCount = Integer.parseInt(tokens[1]);
            int coinCount = Integer.parseInt(tokens[2]);


            if (levelName.equals("Level1"))
                level.getGame().setLevel(1);
            else if (levelName.equals("Level2"))
                level.getGame().setLevel(2);
            else if (levelName.equals("Bossfight:1"))
                level.getGame().setLevel(3);
            else if (levelName.equals("Bossfight:2"))
                level.getGame().setLevel(4);
            else if (levelName.equals("LevelEnd"))
                level.getGame().setLevel(5);

            CharacterController.setKillCount(killCount);
            CharacterController.setCoinCount(coinCount);
            return level;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }


    }
}
