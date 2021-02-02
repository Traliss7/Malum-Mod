package com.sammy.malum.core.modcontent;

import com.sammy.malum.core.init.MalumItems;
import com.sammy.malum.core.systems.recipes.MalumItemIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;

public class MalumSpiritKilnFuels
{
    public static final ArrayList<MalumSpiritKilnFuel> data = new ArrayList<>();
    
    public static void init()
    {
        new MalumSpiritKilnFuel(new MalumItemIngredient(MalumItems.ARCANE_CHARCOAL.get(), 1), 16, 1);
    }
    public static MalumSpiritKilnFuel getData(ItemStack stack)
    {
        for (MalumSpiritKilnFuel data : data)
        {
            if (data.inputIngredient.matches(stack))
            {
                return data;
            }
        }
        return null;
    }
    public static class MalumSpiritKilnFuel
    {
        public final MalumItemIngredient inputIngredient;
        public final int fuelDuration;
        public final float fuelSpeed;
    
        public MalumSpiritKilnFuel(MalumItemIngredient inputIngredient, int fuelDuration, float fuelSpeed) {
            this.inputIngredient = inputIngredient;
            this.fuelDuration = fuelDuration;
            this.fuelSpeed = fuelSpeed;
            data.add(this);
        }
    }
}
