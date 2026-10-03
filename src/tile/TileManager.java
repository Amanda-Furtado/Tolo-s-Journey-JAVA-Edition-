package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    public GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[3]; //numero de tiles diferentes.
        mapTileNum = new int[gp.maxScreenCollum][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/moonMap.txt");
    }

    public void getTileImage(){
        try {
            //vazio
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/empty_tile_spr.png"));
            //bloqueador
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/empty_tile_spr.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/ocean_top_spr.png"));
            tile[2].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadMap(String mapPath){
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int collum = 0;
            int row = 0;

            while(collum < gp.maxScreenCollum && row < gp.maxScreenRow){
                String line = br.readLine();

                while(collum < gp.maxScreenCollum){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[collum]);

                    mapTileNum[collum][row] = num;
                    collum++;

                }
                if(collum == gp.maxScreenCollum){
                    collum = 0;
                    row++;
                } 
            }
            br.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public void draw(Graphics2D g2){
        int collum = 1;
        int row = 1;
        int x = 0;
        int y = 0;
       

        while(collum < gp.maxScreenCollum - 2 && row <= gp.maxScreenRow - 2){
            int tileNum = mapTileNum[collum][row];

            if(tileNum == 0){
                collum++;
                x += gp.tileSize;
            } else if (tileNum == 1){
                collum++;
                x += gp.tileSize;
            } else {
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
                collum++;
                x += gp.tileSize;
            }
            

            if(collum == gp.maxScreenCollum - 2){
                collum = 1;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }


}
