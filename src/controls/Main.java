package controls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import characters.Hero;
import controls.visuals.ArmorSlot;
import controls.visuals.EquipmentSlot;
import controls.visuals.InventorySlot;
import controls.visuals.ItemSlot;
import items.Item;
import items.consumables.HealthItem;
import items.equipments.armor.Armor;
import items.equipments.weapons.Dagger;
import items.equipments.weapons.OneHandedWeapon;
import items.equipments.weapons.Shield;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.Coord;
import map.Direction;
import map.GameMap;
import map.MapElement;
import map.MapObject;
import utils.MonsterDiedException;
import utils.VisualUtils;

public class Main extends Application{
	private static final GameLogger LOGGER = new GameLogger(Main.class);
	//game logic elements
	private Hero h;
	private GameMap m;
	
	//visual elements
	private VBox footer = new VBox(4);
	private SplitPane mainWindow = new SplitPane();
	
	//window sizes
	public static final int WHEIGHT = 600;
	public static final int WWIDTH = 800;
	public static final int FHEIGHT = 60;
	public static final int MHEIGHT = WHEIGHT - FHEIGHT;

	public static void main(String args[]){
		
		Application.launch(args);
				
	}

	@Override
	public void start(Stage primaryStage) {
		setupHero();
		//setupMap();
		m = new GameMap();

		SplitPane root = new SplitPane();
		root.setOrientation(Orientation.VERTICAL);
		root.setDividerPositions(MHEIGHT/(double) WHEIGHT);
        root.getItems().addAll(mainWindow, footer);
        
        Scene scene = new Scene(root, WWIDTH, WHEIGHT);
        
		primaryStage.setTitle("RPG game");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(x->GameLogger.closeLog());

		generateStatusBar();
		createMainWindow();
	}
	
	public void createMainWindow(){
		GridPane mapView = new GridPane();
		MapElement[][] visMap = m.getVisibleMap();
		
		for(int j=0; j<GameMap.VISIBLESIZE; j++){
			for (int i =0; i<GameMap.VISIBLESIZE; ++i){
				mapView.add(VisualUtils.getImageView(MapObject.EMPTY), i, j);
				mapView.add(VisualUtils.getImageView(visMap[i][j]), i, j);
				
			}
		}
		mapView.add(VisualUtils.getImageView(h), 4, 4);
		mainWindow.getItems().setAll(mapView);
		mainWindow.setOnMouseReleased(null);
		
		mainWindow.getScene().setOnKeyReleased(x->{	KeyCode key = x.getCode(); 
													LOGGER.notice("Main window buttonpress: " + x.getCode()); 
													if (key.equals(KeyCode.I)){ 
														switchToInv();
													} else if (key.equals(KeyCode.L)){ 
														switchToLogs();
													} else if (Arrays.asList(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT).contains(key)) {
														Direction d = Direction.valueOf(x.getCode().toString());
														h.move(m, d);
														createMainWindow();
													} else if (key.equals(KeyCode.SPACE)) {
														Coord hitPlace = h.getLocation().add(h.getFacing());
														try {
															h.hit(m.getTile(hitPlace));
														} catch (MonsterDiedException e) {
															m.forceSetTile(hitPlace, MapObject.EMPTY); //TODO: tombstone?
														}
														createMainWindow();
													}
													generateStatusBar();
													});
		
	}
	
	
	public void switchToInv(){
		VBox inv = generateInventoryView(new VBox());
		inv.setMaxWidth(WWIDTH/2);
		inv.setMinWidth(WWIDTH/2);
		VBox equip = generateEquipView(new VBox());
		mainWindow.getItems().setAll(inv,equip);


        mainWindow.setOnMouseReleased(x->{	LOGGER.notice("Click on inventory");
        								generateInventoryView(inv);
        								generateEquipView(equip);
        								generateStatusBar();
        	
        });
        
        mainWindow.getScene().setOnKeyReleased(x->{	LOGGER.notice("Buttonpress in inventory");
        											if (!x.getCode().equals(KeyCode.SHIFT)) createMainWindow();
        										});
	}
	
	private VBox generateInventoryView(VBox inv){
		List<InventorySlot> list = new ArrayList<InventorySlot>();
		for (Item i : h.getInv().getItemList()){
			list.add(new ItemSlot(h,i,h.getInv().getQuantity(i)));
		}
		inv.getChildren().setAll(list);
		return inv;
	}
	
	private VBox generateEquipView(VBox equip){
		equip.getChildren().setAll(new Text("Hp: " + h.getCurhp()),new Text("Def: " + h.getDef()), new Text("Dmg: " + h.getDmgRange()));
		equip.getChildren().addAll(new EquipmentSlot(h, h.getRightHand(), "MH"),new EquipmentSlot(h, h.getLeftHand(), "OH"));
		for (Armor.Type t : Armor.Type.values()){
			ArmorSlot a = new ArmorSlot(h, h.getArmors().get(t), t);
			equip.getChildren().add(a);
		}
		return equip;
	}
	
	public void switchToLogs(){
		VBox logs = new VBox();
		GameLogger.getLastGameLogs(30).stream().forEach(x->logs.getChildren().add(new Text(x)));
		mainWindow.getItems().setAll(logs);
        
        mainWindow.getScene().setOnKeyReleased(x->{	LOGGER.notice("Buttonpress in logs");
        											createMainWindow();
        										});
        //TODO search?
	}
	
	private void generateStatusBar(){
		footer.setMaxHeight(FHEIGHT);
		footer.setMinHeight(FHEIGHT);
		HBox status = new HBox(190);
		status.getChildren().setAll(new Text("Hp: " + h.getHpState()),new Text("Def: " + h.getDef()), new Text("Dmg: " + h.getDmgRange()), new Text("Loc: " + h.getLocation()));
		footer.getChildren().setAll(status);
		for (String s : GameLogger.getLastGameLogs(2)){
			footer.getChildren().add(new Text (s));
		}
	}
	
	private void setupHero(){
		h = new Hero(50,1,0);
		h.pickUp(HealthItem.POISON, 3);
		h.pickUp(HealthItem.SMALLPOTION, 3);
		h.equipArmor(new Armor("leg", Armor.Type.LEG,2));
		h.equipRightHand(new OneHandedWeapon("sword", 5));
		h.equipLeftHand(new Shield("shield", 1, 4));
		h.pickUp(new Dagger("dagger", 2, 1));
	}
	
	private void setupMap(){
		Map<Coord, MapElement> tom = new HashMap<Coord, MapElement>();
		tom.put(new Coord(0,0), MapObject.EMPTY);
		tom.put(new Coord(0,1), MapObject.EMPTY);
		tom.put(new Coord(1,1), MapObject.ROCK);
		tom.put(new Coord(-1,-1), MapObject.TREE);
		tom.put(new Coord(1,-1), MapObject.ROCK);
		tom.put(new Coord(-1,1), MapObject.WALL);
		tom.put(new Coord(0,-1), MapObject.EMPTY);
		tom.put(new Coord(1,0), MapObject.EMPTY);
		tom.put(new Coord(-1,0), MapObject.EMPTY);
		m = new GameMap(tom);
		
	}
	
	//TODO notes on map tiles
	//TODO quests
	//TODO merchants
	//TODO monsters move and attack on their own
	

}
