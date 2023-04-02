package net.lionarius.skinrestorer.mixin;

import carpet.commands.PlayerCommand;
import com.mojang.authlib.GameProfile;
import net.lionarius.skinrestorer.SkinRestorer;
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(PlayerCommand.class)
public class PlayerCommandMixin {
    @Redirect(method = "cantSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/UserCache;findByName(Ljava/lang/String;)Ljava/util/Optional;"))
    private static Optional<GameProfile> skinrestorer_skipProfileLoading(UserCache instance, String name) {
        SkinRestorer.LOGGER.info("Skip profile loading for bot {}", name);
        return Optional.empty();
    }
}
