package turniplabs.halplibe.mixin.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.command.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityClientPlayerMP.class, remap = false)
public abstract class EntityClientPlayerMPMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void halplibe_sendChatMessage(String s, CallbackInfo ci) {
        if(s.startsWith("/")) {
            String[] rawStrings = s.substring(1).split(" ");
            String[] args = new String[rawStrings.length - 1];
            System.arraycopy(rawStrings, 1, args, 0, rawStrings.length - 1);

            Minecraft mc = Minecraft.getMinecraft();
            ClientCommandHandler handler = mc.commandHandler;
            ClientPlayerCommandSender sender = new ClientPlayerCommandSender(mc, (EntityPlayerSP) (Object) this);

            for (Command command : Commands.commands) {
                if (command instanceof ClientCommand && command.isName(rawStrings[0])) {
                    try {
                        boolean e1 = command.execute(handler, sender, args);
                        if(!e1) {
                            command.sendCommandSyntax(handler, sender);
                        }
                    } catch (CommandError commandError8) {
                        sender.sendMessage("\u00a7" + ChatColor.red + commandError8.getMessage());
                    }

                    ci.cancel();
                    return;
                }
            }
        }
    }
}
