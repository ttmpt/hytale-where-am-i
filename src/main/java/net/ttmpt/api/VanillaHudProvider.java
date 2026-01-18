package net.ttmpt.api;

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

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class VanillaHudProvider implements HudProvider {

    private final Map<PlayerRef, WhereAmIHud> huds = new HashMap<>();

    @Override
    public void showHud(int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store) {
        final Holder<EntityStore> holder = EntityUtils.toHolder(index, archetypeChunk);
        final Player player = holder.getComponent(Player.getComponentType());
        final PlayerRef playerRef = holder.getComponent(PlayerRef.getComponentType());

        if (player == null || playerRef == null) {
            return;
        }

        if (!huds.containsKey(playerRef)) {
            WhereAmIHud value = new WhereAmIHud(playerRef);
            huds.put(playerRef, value);
            value.updateHud(store, playerRef.getReference());
            player.getHudManager().setCustomHud(playerRef, value);
        } else {
            WhereAmIHud customUIHud = huds.get(playerRef);
            customUIHud.updateHud(store, playerRef.getReference());
            customUIHud.show();
        }
    }
}
