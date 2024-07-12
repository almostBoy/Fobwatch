package net.bency.fobwatch;

import net.bency.fobwatch.tardis_classes.TARDIS;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class FobwatchClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(TARDIS.TARDIS, RenderLayer.getCutout());
		RaycastingClass.registerSonicUseCallback();
	}
}