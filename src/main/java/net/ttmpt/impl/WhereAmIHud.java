package net.ttmpt.impl;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.Anchor;
import com.hypixel.hytale.server.core.ui.Value;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.entity.entities.Player;

import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.server.core.entity.EntityUtils;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.Store;

import javax.annotation.Nonnull;

public class WhereAmIHud extends CustomUIHud {
    private UICommandBuilder ui;

    public WhereAmIHud(@Nonnull PlayerRef playerRef) {
        super(playerRef);
    }

    public void updateHud(float dt, int index, ArchetypeChunk<EntityStore> archetypeChunk, Store<EntityStore> store, Object commandBuffer, Ref<EntityStore> playerRef) {
        if (ui == null) {
            return;
        }
        ui.set("#Info #Header.TextSpans", Message.raw("Example text updated"));
        try {

        final Holder<EntityStore> holder = EntityUtils.toHolder(index, archetypeChunk);
        final Player player = holder.getComponent(Player.getComponentType());

        TransformComponent playerTransform = store.getComponent(playerRef, TransformComponent.getComponentType());
        if (playerTransform == null) {
            return;
        }

        Vector3d playerPos = playerTransform.getPosition();

        ui.set("#Info #Header.TextSpans", Message.raw("Example text " + playerPos.getX()));

        } catch (Exception e) {
            ui.set("#Info #Header.TextSpans", Message.raw(e.toString()));
        }
    }

    @Override
    protected void build(@Nonnull UICommandBuilder ui) {
        this.ui = ui;

        ui.append("WhereAmI.ui");

        ui.appendInline("#Info", """
                Label #Header {
                  Style: LabelStyle(FontSize: 24);
                }""");
        ui.set("#Info #Header.TextSpans", Message.raw("Example text"));

        Anchor anchor = new Anchor();
        anchor.setTop(Value.of(20));
        // anchor.setLeft(Value.of(20));
        anchor.setHeight(Value.of(48));
        ui.setObject("#WhereAmI.Anchor", anchor);
    }
}
