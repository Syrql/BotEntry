package fr.syrql.botentry.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

public class Bot implements Serializable {

    @JsonProperty
    private String botName;

    @JsonProperty
    private String botUUID;

    public Bot() {
        super();
    }

    public Bot(String botName, UUID botUUID) {
        this.botName = botName;
        this.botUUID = botUUID.toString();
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getBotUUID() {
        return botUUID;
    }

    public void setBotUUID(UUID botUUID) {
        this.botUUID = botUUID.toString();
    }

    public EntityPlayer toEntityPlayer(CraftPlayer player) {


        return new EntityPlayer(MinecraftServer.getServer(),
                ((CraftWorld) player.getWorld()).getHandle(),
                new GameProfile(UUID.fromString(this.getBotUUID()), this.getBotName()),
                new PlayerInteractManager(player.getHandle().getWorld()));
    }

    public void showInTab(Player player){
        EntityPlayer entityPlayer = this.toEntityPlayer((CraftPlayer) player);

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void removeInTab(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, this.toEntityPlayer(craftPlayer));
        craftPlayer.getHandle().playerConnection.sendPacket(packet);

    }
}
