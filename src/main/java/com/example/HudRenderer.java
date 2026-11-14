package com.uzcheat;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class HudRenderer {
    public static void init() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            net.minecraft.client.MinecraftClient client = net.minecraft.client.MinecraftClient.getInstance();
            if (client.player == null || client.world == null) return;

            TextRenderer textRenderer = client.textRenderer;

            // WaterMark
            drawContext.drawText(textRenderer, "§bUZCHEAT §7| §f1.21.4 §7| FPS: " + client.getCurrentFps(), 10, 10, 0xFFFFFF, true);

            // RShift Menu
            if (UzCheatClient.menuOpen) {
                drawContext.fill(50, 50, 250, 200, 0x80000000);
                drawContext.drawText(textRenderer, "§6=== UZCHEAT MENU ===", 70, 60, 0xFFFFFF, true);
                drawContext.drawText(textRenderer, "§a[1] NoRot - ACTIVE", 70, 80, 0xFFFFFF, true);
                drawContext.drawText(textRenderer, "§7[Right Shift] to close", 70, 170, 0xFFFFFF, true);
            }

            // Target HUD
            HitResult hit = client.crosshairTarget;
            if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) hit].getEntity();
                if (entity instanceof LivingEntity target && !(target instanceof ClientPlayerEntity)) {
                    String targetName = target.getName().getString();
                    int health = (int) target.getHealth();
                    
                    int screenWidth = client.getWindow().getScaledWidth();
                    int screenHeight = client.getWindow().getScaledHeight();
                    int x = screenWidth / 2 + 20;
                    int y = screenHeight / 2 - 10;

                    drawContext.fill(x - 5, y - 5, x + 110, y + 30, 0x90000000);
                    drawContext.drawText(textRenderer, "Target: " + targetName, x, y, 0xFF5555, true);
                    drawContext.drawText(textRenderer, "Health: " + health + " HP", x, y + 12, 0xFFFFFF, true);
                }
            }
        });
    }
}
