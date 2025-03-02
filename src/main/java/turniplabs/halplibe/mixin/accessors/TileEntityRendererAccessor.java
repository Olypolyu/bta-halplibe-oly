package turniplabs.halplibe.mixin.accessors;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityRenderer;
import net.minecraft.src.TileEntitySpecialRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = TileEntityRenderer.class, remap = false)
public interface TileEntityRendererAccessor {

    @Accessor("specialRendererMap")
    Map<Class<? extends TileEntity>, TileEntitySpecialRenderer> getSpecialRendererMap();

}
