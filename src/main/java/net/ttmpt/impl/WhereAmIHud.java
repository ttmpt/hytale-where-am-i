package net.ttmpt.impl;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import net.ttmpt.api.MultiHudProvider;

import javax.annotation.Nonnull;

public class WhereAmIHud extends CustomUIHud {

    private Vector3d playerPos = new Vector3d(0, 0, 0);

    public WhereAmIHud(@Nonnull PlayerRef playerRef) {
        super(playerRef);
    }

    public void updateHud(Store<EntityStore> store, Ref<EntityStore> playerRef) {
        TransformComponent playerTransform = store.getComponent(playerRef, TransformComponent.getComponentType());
        if (playerTransform == null) {
            return;
        }

        playerPos = playerTransform.getPosition();
    }

    @Override
    protected void build(@Nonnull UICommandBuilder ui) {
        ui.append("WhereAmI.ui");
        ui.set("#WhereAmI #Header.TextSpans", Message.raw("%.2f | %.2f | %.2f".formatted(playerPos.getX(), playerPos.getY(), playerPos.getZ())));
    }
}
