package com.enchantedwisp.hephaestusexpansion.modifiers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class PiercingThrustModifier extends Modifier {

    /* -------------------- Normal mode config -------------------- */
    private static final float BASE_SECONDARY_DAMAGE = 0.35f;
    private static final float DAMAGE_PER_LEVEL      = 0.05f;
    private static final int   BASE_MAX_TARGETS      = 1;
    private static final int   EXTENDED_MAX_TARGETS  = 2;
    private static final double RANGE                = 5.0;
    private static final double ALIGN_DOT_MIN        = 0.95;

    /* -------------------- Crit config -------------------- */
    private static final float CRIT_MULTIPLIER = 1.65f; // above vanilla
    private static final float BASE_CRIT_CHANCE = 0.02f; // per stack
    private static final int   BASE_STACKS      = 3;     // normal mode max stacks
    private static final int   MAX_STACKS_L5    = 5;     // level 5+
    private static final int   STACK_HIT_COUNT  = 3;     // hits per stack increment
    private static final int   TIMEOUT_TICKS    = 100;   // 5 seconds


    private static class HitState {
        UUID currentTarget = null;
        int hitsOnTarget   = 0;
        int stacks         = 0;
        long lastHitTick   = 0;
    }

    private final Map<UUID, HitState> hitStates = new HashMap<>();

    /* -------------------- Utility Methods -------------------- */

    private int getMaxTargets(int level) {
        return level >= 5 ? EXTENDED_MAX_TARGETS : BASE_MAX_TARGETS;
    }

    private float getSecondaryDamageMultiplier(int level) {
        return BASE_SECONDARY_DAMAGE + Math.max(0, level - 1) * DAMAGE_PER_LEVEL;
    }

    private void cleanupOldEntries(long now) {
        hitStates.entrySet().removeIf(e -> now - e.getValue().lastHitTick > TIMEOUT_TICKS * 2);
    }

    private boolean isValidExtraTarget(LivingEntity e, LivingEntity attacker, LivingEntity mainTarget) {
        return e != attacker && e != mainTarget && e.isAlive();
    }

    /* -------------------- Modifier Logic -------------------- */

    @Override
    public float getEntityDamage(IToolStackView tool, int level, ToolAttackContext context,
                                 float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity target = context.getLivingTarget();
        if (attacker == null || target == null) return damage;

        long now = attacker.level().getGameTime();
        cleanupOldEntries(now);

        HitState state = hitStates.computeIfAbsent(attacker.getUUID(), k -> new HitState());

        boolean targetChanged = state.currentTarget == null || !state.currentTarget.equals(target.getUUID());
        boolean timedOut = (now - state.lastHitTick) > TIMEOUT_TICKS;

        if (targetChanged || timedOut) {
            state.currentTarget = target.getUUID();
            state.hitsOnTarget = 0;
            state.stacks = 0;
        }

        state.hitsOnTarget++;
        int maxStacks = level >= 5 ? MAX_STACKS_L5 : BASE_STACKS;
        if (state.hitsOnTarget % STACK_HIT_COUNT == 0 && state.stacks < maxStacks) {
            state.stacks++;
        }
        state.lastHitTick = now;

        float totalCritChance = (BASE_CRIT_CHANCE * level) * state.stacks;
        if (totalCritChance > 0f && RANDOM.nextFloat() < totalCritChance) {
            if (attacker.level() instanceof ServerLevel server) {
                spawnCritParticles(server, target);
            }
            return damage * CRIT_MULTIPLIER;
        }

        return damage;
    }

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {

        LivingEntity attacker = context.getAttacker();
        LivingEntity mainTarget = context.getLivingTarget();
        if (attacker == null || mainTarget == null || attacker.level().isClientSide) return 0;

        float secondaryDamage = getSecondaryDamageMultiplier(level);
        int maxTargets = getMaxTargets(level);

        Vec3 start = attacker.position().add(0, attacker.getEyeHeight() * 0.5, 0);
        Vec3 dir   = mainTarget.position().subtract(start).normalize();

        List<LivingEntity> targets = attacker.level()
                .getEntitiesOfClass(LivingEntity.class,
                        new AABB(start, start.add(dir.scale(RANGE))).inflate(1.0),
                        e -> isValidExtraTarget(e, attacker, mainTarget))
                .stream()
                .sorted(Comparator.comparingDouble(e -> e.position().distanceTo(start)))
                .collect(Collectors.toList());

        int hitCount = 0;
        for (LivingEntity extra : targets) {
            if (hitCount >= maxTargets) break;
            Vec3 toTarget = extra.position().subtract(start).normalize();
            if (dir.dot(toTarget) < ALIGN_DOT_MIN) continue;

            extra.hurt(attacker.damageSources().mobAttack(attacker), damageDealt * secondaryDamage);
            if (attacker.level() instanceof ServerLevel server) {
                spawnTrail(server, centerOf(mainTarget), centerOf(extra));
            }
            hitCount++;
        }
        return 0;
    }

    /* -------------------- Visual Effects -------------------- */

    private Vec3 centerOf(LivingEntity e) {
        return e.position().add(0, e.getBbHeight() * 0.5, 0);
    }

    private void spawnTrail(ServerLevel level, Vec3 from, Vec3 to) {
        Vec3 delta = to.subtract(from);
        double length = delta.length();
        if (length <= 0.01) return;
        Vec3 step = delta.normalize().scale(0.25);
        int steps = (int)Math.ceil(length / 0.25);
        Vec3 cur = from;
        for (int i = 0; i <= steps; i++) {
            level.sendParticles(ParticleTypes.CRIT, cur.x, cur.y, cur.z, 1, 0, 0, 0, 0);
            cur = cur.add(step);
        }
    }

    private void spawnCritParticles(ServerLevel level, LivingEntity target) {
        double x = target.getX();
        double y = target.getY() + target.getBbHeight() * 0.5;
        double z = target.getZ();
        for (int i = 0; i < 6; i++) {
            level.sendParticles(ParticleTypes.CRIT, x, y, z, 1, 0.1, 0.1, 0.1, 0.0);
        }
    }

    /* -------------------- Tooltip -------------------- */

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player,
                               List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {

        float secondaryDamage = getSecondaryDamageMultiplier(level);
        int maxTargets = getMaxTargets(level);
        float critPerStack = BASE_CRIT_CHANCE * level;
        int maxStacks = level >= 5 ? MAX_STACKS_L5 : BASE_STACKS;
        float maxCritChance = critPerStack * maxStacks;
        Style modifierColor = getDisplayName().getStyle();



        tooltip.add(Component.translatable("modifier.hephaestusexpansion.piercing_thrust.description"));
        tooltip.add(
                Component.translatable("modifier.hephaestusexpansion.piercing_thrust.targets.value")
                        .append(": ")
                        .append(Component.literal(String.valueOf(maxTargets)).setStyle(modifierColor))
                        .append(" | ")
                        .append(Component.translatable("modifier.hephaestusexpansion.piercing_thrust.damage"))
                        .append(": ")
                        .append(Component.literal(String.format("%.1f%%", secondaryDamage * 100)).setStyle(modifierColor))
        );
        tooltip.add(Component.translatable("modifier.hephaestusexpansion.piercing_thrust.description1"));
        tooltip.add(
                Component.translatable("modifier.hephaestusexpansion.piercing_thrust.crit_chance_stack")
                        .append(": ")
                        .append(Component.literal(String.format("%.1f%%", critPerStack * 100)).setStyle(modifierColor))
        );
        tooltip.add(
                Component.translatable("modifier.hephaestusexpansion.piercing_thrust.crit_chance")
                        .append(": ")
                        .append(Component.literal(String.format("%.1f%%", maxCritChance * 100)).setStyle(modifierColor))
                        .append(" | ")
                        .append(Component.translatable("modifier.hephaestusexpansion.piercing_thrust.description2"))
        );
    }
}
