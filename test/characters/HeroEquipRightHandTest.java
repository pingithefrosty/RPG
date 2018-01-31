/*
package characters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import items.Inventory;
import items.equipments.weapons.OneHandedWeapon;
import items.equipments.weapons.Weapon;

@RunWith(JUnit4.class)
public class HeroEquipRightHandTest {

	Hero h;
	
	@Before
	public void setup(){
		h = new Hero(10,1,0);
		h.setInv(new Inventory(3));
		//h.pickUp(new OneHandedWeapon("shortword", 8));
	}
	
	@Test
	public void testHeroUse_shouldEquip(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.use(w);
		assertSame(h.getRightHand(),w);
	}

	//Should move to Weapon test
	@Test
	public void testWeaponUse_shouldEquip(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		w.use(h);
		assertThat(h.getRightHand(),is(w));
	}
	
	//Should move to Weapon test
	@Test
	public void testWeaponEquip_shouldEquip(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		w.equip(h);
		assertSame(h.getRightHand(),w);
	}
	
	@Test
	public void testHeroEquipRighHand_shouldEquip(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.equipRightHand(w);
		assertSame(h.getRightHand(),w);
	}
	
	@Test
	public void testHeroUnEquipRighHand_shouldBeNone(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.equipRightHand(w);
		h.unEquipRightHand();
		assertSame(h.getRightHand(),Weapon.none);
	}
	
	@Test
	public void testHeroEquipRighHand_shouldRemoveFromInv(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.pickUp(w);
		h.equipRightHand(w);
		assertThat(h.getInv().getItemList(), hasSize(0));
	}
	
	@Test
	public void testHeroUnEquipRighHand_shouldPutToInv(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.equipRightHand(w);
		h.unEquipRightHand();
		assertThat(h.getInv().getItemList(), hasSize(1));
		assertThat(h.getInv().getItemList(), hasItem(w));
	}
	
	@Test
	public void testHeroUnEquipRighHand_OnFullInv_shouldDoNothing(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.equipRightHand(w);
		h.pickUp(new OneHandedWeapon("shortword", 10));
		h.pickUp(new OneHandedWeapon("shortword", 10));
		h.pickUp(new OneHandedWeapon("shortword", 10));
		h.unEquipRightHand();
		assertSame(h.getRightHand(),w);
		assertThat(h.getInv().getItemList(), hasSize(3));
		assertThat(h.getInv().getItemList(), not(hasItem(w)));
	}
	
	@Test
	public void testHeroEquipRighHand_OnFullInv_shouldSwap(){
		Weapon w = new OneHandedWeapon("shortword", 8);
		h.equipRightHand(w);
		Weapon w2 = new OneHandedWeapon("shortword", 6);
		h.pickUp(w2);
		h.pickUp(new OneHandedWeapon("shortword", 10));
		h.pickUp(new OneHandedWeapon("shortword", 10));
		System.out.println(h.getInv());
		h.equipRightHand(w2);
		System.out.println(h.getInv());
		assertSame(h.getRightHand(),w2);
		assertThat(h.getInv().getItemList(), hasSize(3));
		assertThat(h.getInv().getItemList(), hasItem(w));
		assertThat(h.getInv().getItemList(), not(hasItem(w2)));
	}

}
*/
