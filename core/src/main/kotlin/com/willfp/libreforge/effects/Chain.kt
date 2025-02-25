package com.willfp.libreforge.effects

import com.willfp.libreforge.DelegatedList
import com.willfp.libreforge.effects.executors.ChainExecutor
import com.willfp.libreforge.triggers.DispatchedTrigger

/**
 * A list of effect blocks.
 */
class Chain(
    effects: List<ChainElement<*>>,
    private val executor: ChainExecutor
) : DelegatedList<ChainElement<*>>(
    effects.sortedBy {
        it.effect.runOrder.weight
    }
) {
    fun trigger(
        trigger: DispatchedTrigger,
        executor: ChainExecutor = this.executor
    ): Boolean {
        return executor.execute(this, trigger)
    }
}
