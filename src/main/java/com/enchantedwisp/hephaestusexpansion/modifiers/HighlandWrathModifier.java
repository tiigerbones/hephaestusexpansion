package com.enchantedwisp.hephaestusexpansion.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.*;

@SuppressWarnings("deprecation")
public class HighlandWrathModifier extends Modifier {

    /* -------------------- Config -------------------- */
    private static final float DAMAGE_PER_HIT_L1_4 = 0.01f; // 1% per hit
    private static final float DAMAGE_PER_HIT_L5 = 0.02f; // 2% per hit
    private static final float MAX_DAMAGE_L1_4 = 0.08f; // 8% max
    private static final float MAX_DAMAGE_L5 = 0.15f; // 15% max
    private static final float DECAY_RATE = 0.01f; // 1% per tick after decay delay
    private static final long DECAY_DELAY = 200L;  // 10 seconds before decay starts
    private static final double SLOW_MULTIPLIER = 0.95;  // 5% slow per hit

    /* -------------------- Hit state -------------------- */
    private static class HitState {
        int consecutiveHits = 0;
        float bonusDamage = 0f;
        long lastHitTick = 0;
    }

    private final Map<UUID, HitState> hitStates = new HashMap<>();

    private HitState getState(LivingEntity entity) {
        return hitStates.computeIfAbsent(entity.getUUID(), k -> new HitState());
    }

    /* -------------------- Modifier Logic -------------------- */
    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context,
                                 float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (attacker == null || target == null) return damage;

        long now = attacker.level().getGameTime();
        HitState state = getState(attacker);

        boolean hitSuccessful = damage > 0f;
        if (hitSuccessful) {
            // Determine max damage per level
            float maxDamage = level < 5 ? MAX_DAMAGE_L1_4 : MAX_DAMAGE_L5;
            float perHit = level < 5 ? DAMAGE_PER_HIT_L1_4 : DAMAGE_PER_HIT_L5;

            // Increase consecutive hits and bonus damage
            state.consecutiveHits++;
            state.bonusDamage = Math.min(state.bonusDamage + perHit, maxDamage);
            state.lastHitTick = now;

            // If target is blocking and 3 consecutive hits, “break guard”
            if (target.isUsingItem() && state.consecutiveHits >= 3) {
                target.stopUsingItem();
                if (target.level() instanceof ServerLevel server) {
                    server.playSound(null, target.getX(), target.getY(), target.getZ(),
                            SoundEvents.SHIELD_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
                }
            }

            // Apply slow to target
            target.setDeltaMovement(target.getDeltaMovement().multiply(SLOW_MULTIPLIER, 1.0, SLOW_MULTIPLIER));

            // Hunger penalty for player attacker
            if (attacker instanceof Player player) {
                float baseExhaustion = 0.03f; // base per hit
                float scaledExhaustion = Math.min(baseExhaustion * state.consecutiveHits, 0.16f); // cap at 0.05f
                player.getFoodData().addExhaustion(scaledExhaustion);
            }
        } else {
            // Missed attack resets consecutive hits and bonus damage
            state.consecutiveHits = 0;
            state.bonusDamage = 0f;
        }

        // Decay bonus damage over time
        if (now - state.lastHitTick > DECAY_DELAY && state.bonusDamage > 0f) {
            state.bonusDamage = Math.max(state.bonusDamage - DECAY_RATE, 0f);
        }

        // Return damage including bonus
        return damage + damage * state.bonusDamage;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player,
                               List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {

        float maxBonus = level < 5 ? MAX_DAMAGE_L1_4 : MAX_DAMAGE_L5;
        Style modifierColor = getDisplayName().getStyle();

        tooltip.add(
                Component.translatable("modifier.hephaestusexpansion.highland_wrath.bonus_damage")
                        .append(": ")
                        .append(Component.literal(String.format("%.1f%%", maxBonus * 100)).setStyle(modifierColor))
        );
    }
}