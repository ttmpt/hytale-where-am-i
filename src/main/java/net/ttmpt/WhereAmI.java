package net.ttmpt;

import com.hypixel.hytale.common.plugin.PluginIdentifier;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.plugin.PluginBase;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import net.ttmpt.api.HudProvider;
import net.ttmpt.api.MultiHudProvider;
import net.ttmpt.api.VanillaHudProvider;
import net.ttmpt.impl.PlayerTickSystem;

import javax.annotation.Nonnull;

public class WhereAmI extends JavaPlugin {

    public static HudProvider provider = new VanillaHudProvider();

    public WhereAmI(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        this.getEntityStoreRegistry().registerSystem(new PlayerTickSystem());
    }

    @Override
    protected void start() {
        PluginBase plugin = PluginManager.get().getPlugin(PluginIdentifier.fromString("Buuz135:MultipleHUD"));
        if (plugin != null) {
            WhereAmI.provider = new MultiHudProvider();
        }
    }
}
