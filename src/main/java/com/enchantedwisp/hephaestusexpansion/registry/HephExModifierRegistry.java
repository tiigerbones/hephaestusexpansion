package com.enchantedwisp.hephaestusexpansion.registry;

import com.enchantedwisp.hephaestusexpansion.modifiers.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

import static com.enchantedwisp.hephaestusexpansion.HephaestusExpansion.MOD_ID;

public class HephExModifierRegistry {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MOD_ID);

    public static final StaticModifier<Modifier> TRUE_EDGE = MODIFIERS.register("true_edge", TrueEdgeModifier::new);
    public static final StaticModifier<Modifier> PIERCING_THRUST = MODIFIERS.register("piercing_thrust", PiercingThrustModifier::new);
    public static void register() {
        MODIFIERS.register();
    }
}