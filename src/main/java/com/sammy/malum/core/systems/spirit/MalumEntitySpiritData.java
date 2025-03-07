package com.sammy.malum.core.systems.spirit;

import com.sammy.malum.core.helper.SpiritHelper;
import com.sammy.malum.core.setup.content.SpiritTypeRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MalumEntitySpiritData
{
    public static final String NBT = "soul_data";
    public static final MalumEntitySpiritData EMPTY = new MalumEntitySpiritData(SpiritTypeRegistry.SACRED_SPIRIT, new ArrayList<>());
    public final MalumSpiritType primaryType;
    public final int totalCount;
    public final ArrayList<DataEntry> dataEntries;

    public MalumEntitySpiritData(MalumSpiritType primaryType, ArrayList<DataEntry> dataEntries)
    {
        this.primaryType = primaryType;
        this.totalCount = dataEntries.stream().mapToInt(d -> d.count).sum();
        this.dataEntries = dataEntries;
    }

    public ArrayList<Component> createTooltip()
    {
        return dataEntries.stream().map(DataEntry::getComponent).collect(Collectors.toCollection(ArrayList::new));
    }
    public void saveTo(CompoundTag tag)
    {
        tag.put(NBT, save());
    }
    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putString("primaryType", primaryType.identifier);
        tag.putInt("dataAmount", dataEntries.size());
        for (int i = 0; i < dataEntries.size(); i++) {
            CompoundTag dataTag = dataEntries.get(i).save(new CompoundTag());
            tag.put("dataEntry"+i, dataTag);
        }
        return tag;
    }

    public static MalumEntitySpiritData load(CompoundTag tag) {
        CompoundTag nbt = tag.getCompound(NBT);


        String type = nbt.getString("primaryType");
        int dataAmount = nbt.getInt("dataAmount");
        if (dataAmount == 0)
        {
            return EMPTY;
        }
        ArrayList<DataEntry> data = new ArrayList<>();
        for (int i = 0; i < dataAmount; i++) {
            data.add(DataEntry.load(nbt.getCompound("dataEntry"+i)));
        }
        return new MalumEntitySpiritData(SpiritHelper.getSpiritType(type), data);
    }
    public static class DataEntry
    {
        public final MalumSpiritType type;
        public final int count;
        public DataEntry(MalumSpiritType type, int count)
        {
            this.type = type;
            this.count = count;
        }
        public Component getComponent()
        {
            return type.getComponent(count);
        }
        public CompoundTag save(CompoundTag tag)
        {
            tag.putString("type", type.identifier);
            tag.putInt("count", count);
            return tag;
        }
        public static DataEntry load(CompoundTag tag)
        {
            MalumSpiritType type = SpiritHelper.getSpiritType(tag.getString("type"));
            int count = tag.getInt("count");
            return new DataEntry(type, count);
        }
    }
}
