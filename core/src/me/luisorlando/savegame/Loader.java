package me.luisorlando.savegame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Loader {

    private File file;
    private int unlockeds = 1;

    public Loader() {
        File folder = new File("saves");
        if (!folder.exists()) {
            folder.mkdir();
        }
        file = new File("saves/level-unlocked.yml");
        /*if (!existConfig()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void save(int level) {
        if (level > unlockeds) {
            try {
                FileOutputStream f = new FileOutputStream(file);
                ObjectOutputStream o = new ObjectOutputStream(f);

                // Write objects to file
                o.writeObject(level);

                o.close();
                f.close();
                System.out.printf("Juego guardado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int load() {
        if (existConfig()) {
            try {
                FileInputStream fi = new FileInputStream(file);
                ObjectInputStream oi = new ObjectInputStream(fi);

                // Read objects
                int level = (Integer) oi.readObject();

                oi.close();
                fi.close();
                System.out.println("Juego cargado.");
                unlockeds = level;
                return level;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public boolean existConfig() {
        return file.exists();
    }
}
