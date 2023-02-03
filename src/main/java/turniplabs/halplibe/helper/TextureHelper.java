package turniplabs.halplibe.helper;

import net.minecraft.src.Block;
import turniplabs.halplibe.util.TextureHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextureHelper {
    public static List<TextureHandler> textureHandlers = new ArrayList<>();
    public static HashMap<String, int[]> registeredBlockTextures = new HashMap<>();
    public static HashMap<String, int[]> registeredItemTextures = new HashMap<>();

    public static HashMap<String, int[]> registeredParticleTextures = new HashMap<>();


    // Put your textures in assets/modid/block/ or assets/modid/item/ or assets/modid/particle/

    public static int[] registerBlockTexture(String modId, String blockTexture) {
        int[] possibleCoords = registeredBlockTextures.get(modId + ":" + blockTexture);
        if (possibleCoords != null) {
            return possibleCoords;
        }

        int[] newCoords = BlockCoords.nextCoords();
        registeredBlockTextures.put(modId + ":" + blockTexture, newCoords);
        addTextureToTerrain(modId, blockTexture, newCoords[0], newCoords[1]);
        return newCoords;
    }

    public static int[] registerItemTexture(String modId, String itemTexture) {
        int[] possibleCoords = registeredItemTextures.get(modId + ":" + itemTexture);
        if (possibleCoords != null) {
            return possibleCoords;
        }

        int[] newCoords = ItemCoords.nextCoords();
        registeredItemTextures.put(modId + ":" + itemTexture, newCoords);
        addTextureToItems(modId, itemTexture, newCoords[0], newCoords[1]);
        return newCoords;
    }

    public static int[] registerParticleTexture(String modId, String ParticleTexture) {
        int[] possibleCoords = registeredParticleTextures.get(modId + ":" + ParticleTexture);
        if (possibleCoords != null) {
            return possibleCoords;
        }

        int[] newCoords = ParticleCords.nextCoords();
        registeredParticleTextures.put(modId + ":" + ParticleTexture, newCoords);
        addTextureToParticles(modId, ParticleTexture, newCoords[0], newCoords[1]);
        return newCoords;
    }

    public static void addTextureToTerrain(String modId, String blockTexture, int x, int y) {
        textureHandlers.add(new TextureHandler("/terrain.png", "/assets/" + modId + "/block/" + blockTexture, Block.texCoordToIndex(x, y), 16, 1));
    }

    public static void addTextureToParticles(String modId, String particleTexture, int x, int y) {
        textureHandlers.add(new TextureHandler("/particles.png", "/assets/" + modId + "/particle/" + particleTexture, Block.texCoordToIndex(x, y), 8, 1));
    }

    public static void addTextureToItems(String modId, String itemTexture, int x, int y) {
        textureHandlers.add(new TextureHandler("/gui/items.png", "/assets/" + modId + "/item/" + itemTexture, Block.texCoordToIndex(x, y), 16, 1));
    }

}
