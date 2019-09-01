package uet.oop.bomberman.level;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Kondoria;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	public static char[][] _map;
	private static final String pathLevel = "res\\levels\\";

	public char[][] getMap(){
		return _map;
	}

	public FileLevelLoader(Board board, int level) throws LoadLevelException, IOException {
		super(board, level);
	}

	@Override
	public void loadLevel(int level) throws IOException{
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = pathLevel + "Level"+ String.valueOf(level) + ".txt";
		Stream<String> stream = Files.lines(Paths.get(path));
		String[] map = stream.toArray(String[]::new);
		String[] tmp = map[0].split(" ");
		_level = Integer.valueOf(tmp[0]);
		_height = Integer.valueOf(tmp[1]);
		_width = Integer.valueOf(tmp[2]);
		_map = new char[_height+1][_width+1];
		for(int i = 1;i<=_height;i++)
			for(int j = 0;j<_width;j++)
				_map[i-1][j] = map[i].charAt(j);
	}

	@Override
	public void createEntities() {
//		 TODO: tạo các Entity của màn chơi
//		 TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game
//
//		 TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
//		 TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
//		// thêm Wall
//		for (int x = 0; x < getWidth(); x++) {
//			for (int y = 0; y < getHeight(); y++) {
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//			}
//		}
//
//		// thêm Bomber
//		int xBomber = 3, yBomber = 3;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//		// thêm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thêm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//
//		// thêm Item kèm Brick che phủ ở trên
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
		for (int x = 0; x < getWidth(); x++)
			for (int y = 0; y < getHeight(); y++)
				if(_map[y][x] == '#'){
					int pos = x + y * _width;
					Sprite sprite = Sprite.wall;
					_board.addEntity(pos, new Wall(x, y, sprite));
				} else if(_map[y][x] == 'p'){
					int xBomber = x, yBomber = y;
					_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
					Screen.setOffset(0, 0);
					_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
				} else if(_map[y][x] == '*'){
					int xB = x, yB = y;
					_board.addEntity(xB + yB * _width,
							new LayeredEntity(xB, yB,
								new Grass(xB, yB, Sprite.grass),
								new Brick(xB, yB, Sprite.brick)
							)
					);
				} else if(_map[y][x] == 'x'){
					int xP = x, yP = y;
					_board.addEntity(xP + yP * _width,
							new LayeredEntity(xP, yP,
									new Portal(xP, yP, Sprite.portal, _board),
									new Brick(xP,yP,Sprite.brick)
							)
					);
				} else if(_map[y][x] == '1'){
					int xE = x, yE = y;
					_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
					_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
				} else if(_map[y][x] == '2') {
					int xE = x, yE = y;
					_board.addCharacter(new Oneal(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
					_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
				} else if(_map[y][x] == '3'){
					int xE = x, yE = y;
					_board.addCharacter( new Kondoria(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
					_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
				} else if(_map[y][x] == 'e'){
					int xE = x, yE = y;
					Random rd = new Random();
					int tmp = rd.nextInt(3);
					while(tmp == 0){
						tmp = rd.nextInt(3);
					}
					if(tmp == 1){
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
						_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
					} else{
						_board.addCharacter(new Oneal(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
						_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
					}
				} else if(_map[y][x] == 'b'){
					int xI = x, yI = y;
					_board.addEntity(xI + yI * _width,
							new LayeredEntity(xI, yI,
								new Grass(xI ,yI, Sprite.grass),
								new BombItem(xI, yI, Sprite.powerup_bombs),
								new Brick(xI, yI, Sprite.brick)
							)
					);
				} else if(_map[y][x] == 'f'){
					int xI = x, yI = y;
					_board.addEntity(xI + yI * _width,
							new LayeredEntity(xI, yI,
									new Grass(xI ,yI, Sprite.grass),
									new FlameItem(xI, yI, Sprite.powerup_flames),
									new Brick(xI, yI, Sprite.brick)
							)
					);
				} else if(_map[y][x] == 's'){
					int xI = x, yI = y;
					_board.addEntity(xI + yI * _width,
							new LayeredEntity(xI, yI,
									new Grass(xI ,yI, Sprite.grass),
									new SpeedItem(xI, yI, Sprite.powerup_speed),
									new Brick(xI, yI, Sprite.brick)
							)
					);
				} else {
					int pos = x + y * _width;
					Sprite sprite = Sprite.grass;
					_board.addEntity(pos, new Grass(x, y, sprite));
				}
	}

	public static void main(String[] args) throws LoadLevelException, IOException {
		Board board = null;
		FileLevelLoader f = new FileLevelLoader(board,1);
		System.out.println(f._height);
		System.out.println(f._width);
//		for(int i = 0;i<f._height;i++) {
//			for (int j = 0; j < f._width; j++)
//				System.out.print(_map[i][j]);
//			System.out.println();
//		}
		System.out.println(_map[0][0]);
	}

}
