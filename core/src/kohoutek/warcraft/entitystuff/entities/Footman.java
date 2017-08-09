package kohoutek.warcraft.entitystuff.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import kohoutek.warcraft.Player;
import kohoutek.warcraft.entitystuff.components.Animation8xComponent;
import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.AttackComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.HealthComponent;
import kohoutek.warcraft.entitystuff.components.OwnerComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.ScaleComponent;
import kohoutek.warcraft.entitystuff.components.SelectableComponent;
import kohoutek.warcraft.entitystuff.components.SolidComponent;
import kohoutek.warcraft.entitystuff.components.SpeedComponent;
import kohoutek.warcraft.entitystuff.components.StateAnimationsComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent.EntityState;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;
import static kohoutek.warcraft.entitystuff.components.Animation8xComponent.*;

import java.util.Arrays;

public class Footman extends Entity {
	public static final int COST_GOLD 	= 0;
	public static final int COST_LUMBER = 0;
	public static final int COST_FOOD 	= 0;
	
	/** to be reused by all instances of this class for memory efficiency **/
	private static final TextureRegion[][] REGIONS = new TextureRegion[16][3];
	
	public Footman(int x, int y, final AssetManager am, final Player owner){
		super();

		final PositionComponent pos = new PositionComponent(x, y);
		final Vector2 initialTargetPoint = new Vector2(x + 48, y + 48 + 130);
		
		final Array<Animation<TextureRegion>> movement = new Array<Animation<TextureRegion>>(8);
		final Array<Animation<TextureRegion>> attack = new Array<Animation<TextureRegion>>(8);
		final Array<Animation<TextureRegion>> death = new Array<Animation<TextureRegion>>(8);
				
		for(TextureRegion[] array : REGIONS) {
			for(TextureRegion region : array) {
				if(region == null) {
					initRegions(am);
					break;
				}
			}
		}
					
		
	     //walk animations
		Animation<TextureRegion> anim = new Animation<TextureRegion>(0.25f,REGIONS[0]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(E, anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[1]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(NE, anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[2]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(N,  anim);
		
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[3]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(NW,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[4]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(W,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[5]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(SW,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[6]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(S,  anim);
		
		anim = new Animation<TextureRegion>(0.25f,REGIONS[7]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(SE,  anim);	
		
		//attack anims
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[8]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(E, anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[9]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(NE, anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[10]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(N,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[11]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(NW,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[12]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(W,  anim);

		anim = new Animation<TextureRegion>(0.25f, REGIONS[13]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(SW,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[14]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(S,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, REGIONS[15]);
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		attack.insert(SE,  anim);	
			
		
		add(pos);
		add(new BoundsComponent(24,24,48,48));
		add(new RenderableComponent());
		add(new HealthComponent(60, 60));
		add(new SpeedComponent(32));
		add(new TargetPointComponent(initialTargetPoint));
		add(new ScaleComponent(2 ,2));
		add(new SelectableComponent());
		add(new StateComponent(EntityState.MOVING));
		add(new StateAnimationsComponent(movement, attack, null));
		add(new OwnerComponent(owner));	
		add(new Animation8xComponent(movement));
	}
	
	/**
	 * Initialize regions we need for animations
	 * @param am
	 */
	private static void initRegions(final AssetManager am) {
		final Texture sheet = am.get("../core/assets/FOOTMAN.png");
		final TextureRegion[][] tiles = TextureRegion.split(sheet, 48, 48);
		
		REGIONS[0][0] = tiles[0][2];
		REGIONS[0][1] = tiles[1][2];
		REGIONS[0][2] = tiles[2][2];
			
		REGIONS[1][0] = tiles[0][1];
		REGIONS[1][1] = tiles[1][1];
		REGIONS[1][2] = tiles[2][1];
		
		REGIONS[2][0] = tiles[0][0];
		REGIONS[2][1] = tiles[1][0];
		REGIONS[2][2] = tiles[2][0];
		
		TextureRegion flip1 = new TextureRegion(tiles[0][1]);
		flip1.flip(true, false);
		TextureRegion flip2 = new TextureRegion(tiles[1][1]);
		flip2.flip(true, false);
		TextureRegion flip3 = new TextureRegion(tiles[2][1]);
		flip3.flip(true, false);
		
		REGIONS[3][0] = flip1;
		REGIONS[3][1] = flip2;
		REGIONS[3][2] = flip3;
		
		flip1 = new TextureRegion(tiles[0][2]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][2]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][2]);
		flip3.flip(true, false);
		
		REGIONS[4][0] = flip1;
		REGIONS[4][1] = flip2;
		REGIONS[4][2] = flip3;
		
		flip1 = new TextureRegion(tiles[0][3]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][3]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][3]);
		flip3.flip(true, false);
		
		REGIONS[5][0] = flip1;
		REGIONS[5][1] = flip2;
		REGIONS[5][2] = flip3;
		
		REGIONS[6][0] = tiles[0][4];
		REGIONS[6][1] = tiles[1][4];
		REGIONS[6][2] = tiles[2][4];
		
		REGIONS[7][0] = tiles[0][3];
		REGIONS[7][1] = tiles[1][3];
		REGIONS[7][2] = tiles[2][3];
		
		REGIONS[8][0] = tiles[0][8];
		REGIONS[8][1] = tiles[1][8];
		REGIONS[8][2] = tiles[2][7];
		
		REGIONS[9][0] = tiles[4][6];
		REGIONS[9][1] = tiles[3][6];
		REGIONS[9][2] = tiles[2][6];
		
		REGIONS[10][0] = tiles[0][5];
		REGIONS[10][1] = tiles[1][5];
		REGIONS[10][2] = tiles[2][5];
		
		flip1 = new TextureRegion(tiles[0][8]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][8]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][8]);
		flip3.flip(true, false);
		
		REGIONS[11][0] = flip1;
		REGIONS[11][1] = flip2;
		REGIONS[11][2] = flip3;	
		
		flip1 = new TextureRegion(tiles[3][6]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[4][7]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][6]);
		flip3.flip(true, false);
		
		REGIONS[12][0] = flip1;
		REGIONS[12][1] = flip2;
		REGIONS[12][2] = flip3;
		
		flip1 = new TextureRegion(tiles[0][6]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][6]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][6]);
		flip3.flip(true, false);
		
		REGIONS[13][0] = flip1;
		REGIONS[13][1] = flip2;
		REGIONS[13][2] = flip3;

		REGIONS[14][0] = tiles[0][9];
		REGIONS[14][1] = tiles[1][9];
		REGIONS[14][2] = tiles[2][9];
		
		REGIONS[15][0] = tiles[0][8];
		REGIONS[15][1] = tiles[1][8];
		REGIONS[15][2] = tiles[2][8];
			
	}
}
