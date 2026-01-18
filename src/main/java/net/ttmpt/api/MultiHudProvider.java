package net.ttmpt.api;

import com.buuz135.mhud.MultipleHUD;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.EntityUtils;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.ttmpt.impl.WhereAmIHud;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.HashMap;
import java.util.Map;

public class MultiHudProvider implements HudProvider {

    private final Map<PlayerRef, WhereAmIHud> huds = new HashMap<>();

    @Override
    public void showHud(float dt, int index, @NonNullDecl ArchetypeChunk<EntityStore> archetypeChunk, @NonNullDecl Store<EntityStore> store, @NonNullDecl CommandBuffer<EntityStore> commandBuffer) {
        final Holder<EntityStore> holder = EntityUtils.toHolder(index, archetypeChunk);
        final Player player = holder.getComponent(Player.getComponentType());
        final PlayerRef playerRef = holder.getComponent(PlayerRef.getComponentType());
        if (player == null || playerRef == null) {
            return;
        }
        if (!huds.containsKey(playerRef)) {
            WhereAmIHud value = new WhereAmIHud(playerRef);
            huds.put(playerRef, value);
            value.updateHud(dt, index, archetypeChunk, store, commandBuffer, playerRef.getReference());
            MultipleHUD.getInstance().setCustomHud(player, playerRef, "ttmpt:WhereAmI", value);
        } else {
            WhereAmIHud customUIHud = huds.get(playerRef);
            customUIHud.updateHud(dt, index, archetypeChunk, store, commandBuffer, playerRef.getReference());
        }
    }
}
