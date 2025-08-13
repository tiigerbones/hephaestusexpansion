package com.enchantedwisp.hephaestusexpansion.modifiers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class PiercingThrustModifier extends Modifier {

    private static final float BASE_CHANCE = 0.25f; // 25% base
    private static final float CHANCE_PER_LEVEL = 0.05f; // +5% per level above 1
    private static final float DAMAGE_MULTIPLIER = 0.75f; // 75% damage to secondary targets
    private static final int MAX_TARGETS = 2;
    private static final double RANGE = 5.0; // 5 blocks straight line

    private static final Component PIERCING_CHANCE_TOOLTIP =
            Component.translatable("modifier.piercing_thrust.chance");
    private static final Component PIERCING_DAMAGE_TOOLTIP =
            Component.translatable("modifier.piercing_thrust.damage");

    @Override
    public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        LivingEntity mainTarget = context.getLivingTarget();

        if (attacker == null || mainTarget == null || attacker.level().isClientSide) {
            return 0;
        }

        // Scaled pierce chance
        float pierceChance = BASE_CHANCE + ((level - 1) * CHANCE_PER_LEVEL);

        if (RANDOM.nextFloat() > pierceChance) {
            return 0;
        }

        // Direction from attacker to main target
        Vec3 start = attacker.position().add(0, attacker.getEyeHeight() * 0.5, 0);
        Vec3 dir = mainTarget.position().subtract(start).normalize();

        // Find potential secondary targets
        List<LivingEntity> potentialTargets = attacker.level().getEntitiesOfClass(
                        LivingEntity.class,
                        new AABB(start, start.add(dir.scale(RANGE))).inflate(1.0),
                        e -> e != attacker && e != mainTarget && e.isAlive()
                ).stream()
                .sorted(Comparator.comparingDouble(e -> e.position().distanceTo(start)))
                .collect(Collectors.toList());

        int hitCount = 0;

        for (LivingEntity extraTarget : potentialTargets) {
            if (hitCount >= MAX_TARGETS) break;

            // Check alignment with main target direction
            Vec3 toTarget = extraTarget.position().subtract(start).normalize();
            double dot = dir.dot(toTarget);
            if (dot < 0.95) continue;

            // Deal reduced damage
            extraTarget.hurt(attacker.damageSources().mobAttack(attacker), damageDealt * DAMAGE_MULTIPLIER);

            // Particle trail
            if (attacker.level() instanceof ServerLevel serverLevel) {
                spawnPierceParticles(serverLevel, mainTarget.position().add(0, mainTarget.getBbHeight() * 0.5, 0),
                        extraTarget.position().add(0, extraTarget.getBbHeight() * 0.5, 0));
            }

            hitCount++;
        }

        return 0;
    }

    private void spawnPierceParticles(ServerLevel level, Vec3 from, Vec3 to) {
        Vec3 delta = to.subtract(from);
        int steps = (int) (delta.length() * 4); // density of particles
        Vec3 step = delta.normalize().scale(0.25);

        Vec3 current = from;
        for (int i = 0; i <= steps; i++) {
            level.sendParticles(ParticleTypes.CRIT, current.x, current.y, current.z,
                    1, 0, 0, 0, 0);
            current = current.add(step);
        }
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player,
                               List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        float pierceChance = BASE_CHANCE + ((level - 1) * CHANCE_PER_LEVEL);
        addPercentTooltip(PIERCING_CHANCE_TOOLTIP, pierceChance, tooltip);
        addPercentTooltip(PIERCING_DAMAGE_TOOLTIP, DAMAGE_MULTIPLIER, tooltip);
    }
}
