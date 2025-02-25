package com.willfp.libreforge.conditions.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.ProvidedHolder
import com.willfp.libreforge.ViolationContext
import com.willfp.libreforge.arguments
import com.willfp.libreforge.conditions.Condition
import com.willfp.libreforge.conditions.ConditionList
import com.willfp.libreforge.conditions.Conditions
import org.bukkit.entity.Player

object ConditionAtLeastOf : Condition<ConditionList>("at_least_of") {
    override val arguments = arguments {
        require("conditions", "You must specify the conditions that can be met!")
        require("amount", "You must specify the minimum amount of conditions to meet!")
    }

    override fun isMet(player: Player, config: Config, holder: ProvidedHolder, compileData: ConditionList): Boolean {
        return compileData.count { it.isMet(player, holder) } >= config.getIntFromExpression("amount", player)
    }

    override fun makeCompileData(config: Config, context: ViolationContext): ConditionList {
        return Conditions.compile(
            config.getSubsections("conditions"),
            context.with("at_least_of conditions")
        )
    }
}
