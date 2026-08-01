package me.sylvanserenity.modifiers.modifier;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.sylvanserenity.modifiers.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Modifiers {
    public static final Map<ResourceLocation, Modifier> MODIFIERS = new LinkedHashMap<>();

    /* ACCESSORY MODIFIERS */
    public static final Modifier HARD = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "hard"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ARMOR, 1.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier GUARDING = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "guarding"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ARMOR, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier ARMORED = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "armored"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ARMOR, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier WARDING = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "warding"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ARMOR, 4.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));

    public static final Modifier PRECISE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "precise"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier LUCKY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "lucky"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 4.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));

    public static final Modifier BRISK = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "brisk"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.MOVEMENT_SPEED, 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier FLEETING = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "fleeting"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.MOVEMENT_SPEED, 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier HASTY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "hasty"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.MOVEMENT_SPEED, 0.03, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
        // TODO Ranged
    ));
    public static final Modifier QUICK = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "quick"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.MOVEMENT_SPEED, 0.04, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            )),
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));

    public static final Modifier JAGGED = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "jagged"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier SPIKED = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "spiked"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier ANGRY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "angry"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.03, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier MENACING = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "menacing"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.04, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));

    public static final Modifier WILD = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "wild"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.01, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier RASH = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "rash"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.02, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier INTREPID = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "intrepid"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.03, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));
    public static final Modifier VIOLENT = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "violent"),
        Map.of(
            Modifier.Category.ACCESSORY, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.04, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.ARMOR)
            ))
        )
    ));

    /* UNIVERSAL MODIFIERS */
    public static final Modifier KEEN = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "keen"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier ZEALOUS = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "zealous"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SUPERIOR = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "superior"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier DEMONIC = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "demonic"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier GODLY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "godly"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier LAZY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "lazy"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.08, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.08, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SLOW = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "slow"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SLUGGISH = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "sluggish"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier ANNOYING = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "annoying"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier DAMAGED = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "damaged"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SHODDY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "shoddy"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier BROKEN = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "broken"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier WEAK = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "weak"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier STRONG = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "strong"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier HURTFUL = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "hurtful"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier FORCEFUL = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "forceful"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier UNPLEASANT = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "unpleasant"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier RUTHLESS = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "ruthless"),
        Map.of(
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.18, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier DEADLY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "deadly"),
        Map.of(
            // TODO Ranged
            Modifier.Category.MELEE, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier AGILE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "agile"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier MURDEROUS = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "murderous"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.06, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.07, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.06, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 3.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier NIMBLE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "nimble"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier NASTY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "nasty"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SMALL = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "small"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier TINY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "tiny"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, -0.18, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, -0.18, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier TERRIBLE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "terrible"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, -0.13, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, -0.13, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier UNHAPPY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "unhappy"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SHAMEFUL = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "shameful"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(-2, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier BULKY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "bulky"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier LARGE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "large"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, 0.12, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier MASSIVE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "massive"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.18, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, 0.18, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SAVAGE = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "savage"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            )),
            Modifier.Category.TOOL, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.BLOCK_INTERACTION_RANGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));

    /* MELEE MODIFIERS */
    public static final Modifier POINTY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "pointy"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier SHARP = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "sharp"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier LIGHT = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "light"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(0, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier HEAVY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "heavy"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(0, List.of(
                new Modifier.Entry(Attributes.BLOCK_BREAK_SPEED, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, -0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier DULL = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "dull"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(-1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, -0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier DANGEROUS = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "dangerous"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(1, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 2.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));
    public static final Modifier LEGENDARY = register(new Modifier(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "legendary"),
        Map.of(
            Modifier.Category.MELEE, new Modifier.CategoryData(2, List.of(
                new Modifier.Entry(Attributes.ATTACK_DAMAGE, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_SPEED, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(ModAttributes.CRITICAL_CHANCE, 5.0, AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ENTITY_INTERACTION_RANGE, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND),
                new Modifier.Entry(Attributes.ATTACK_KNOCKBACK, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, EquipmentSlotGroup.MAINHAND)
            ))
        )
    ));

    private static Modifier register(Modifier mod) {
        MODIFIERS.put(mod.id(), mod);
        return mod;
    }
}
