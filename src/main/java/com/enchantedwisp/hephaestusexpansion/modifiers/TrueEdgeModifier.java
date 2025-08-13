package com.enchantedwisp.hephaestusexpansion.modifiers;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

@SuppressWarnings("deprecation")
public class TrueEdgeModifier extends Modifier {

    private static final Component TRUE_EDGE_TOOLTIP = Component.translatable("modifier.true_edge.crit_chance");
    private static final Component TRUE_PRECISION_TOOLTIP = Component.translatable("modifier.true_edge.true_precision");

    @Override
    public float getEntityDamage(@NotNull IToolStackView tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        LivingEntity attacker = context.getAttacker();

        // Crit chance: 5% base + 5% per level above 1
        float critChance = 0.05f + (Math.max(0, level - 1) * 0.05f);

        if (RANDOM.nextFloat() < critChance) {
            damage *= 1.5f; // Vanilla crit multiplier
            if (attacker instanceof Player player) {
                player.level().levelEvent(2005, player.blockPosition(), 0); // crit particles
            }
        }

        return damage;
    }

    @Override
    public int afterEntityHit(@NotNull IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
        LivingEntity target = context.getLivingTarget();
        LivingEntity attacker = context.getAttacker();

        if (target == null || !target.isAlive() || level < 2) {
            return 0; // True Precision starts at level 2
        }

        // True Precision chance: 10% base at level 2 + 5% per extra level
        float truePrecisionChance = 0.10f + ((level - 2) * 0.05f);

        if (RANDOM.nextFloat() < truePrecisionChance) {
            float trueDamage = damageDealt * 0.5f; // Ignores armor
            target.hurt(target.damageSources().magic(), trueDamage);
        }
        return 0;
    }

    @Override
    public void addInformation(IToolStackView tool, int level, @Nullable Player player,
                               List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        float critChance = 0.05f + (Math.max(0, level - 1) * 0.05f);
        addPercentTooltip(TRUE_EDGE_TOOLTIP, critChance, tooltip);

        if (level >= 2) {
            float truePrecisionChance = 0.10f + ((level - 2) * 0.05f);
            addPercentTooltip(TRUE_PRECISION_TOOLTIP, truePrecisionChance, tooltip);
        }
    }
}
