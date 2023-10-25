package com.mzuha.entity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.Direction

/**
 * A simple [SidedInventory] implementation with only default methods + an item list getter.
 *
 * <h2>Reading and writing to tags</h2>
 * Use [Inventories.writeNbt] and [Inventories.readNbt]
 * on [getItems] the item list.
 *
 * License: <a href="https://creativecommons.org/publicdomain/zero/1.0/">CC0</a>
 * @author Juuz
 */
@FunctionalInterface
interface ImplementedInventory : SidedInventory {
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     *
     * @return the item list
     */
    fun getItems(): DefaultedList<ItemStack>

    /**
     * Creates an inventory from the item list.
     *
     * @param items the item list
     * @return a new inventory
     */
    companion object {
        fun of(items: DefaultedList<ItemStack>): ImplementedInventory {
            return object : ImplementedInventory {
                override fun getItems(): DefaultedList<ItemStack> = items
            }
        }

        fun ofSize(size: Int): ImplementedInventory {
            return of(DefaultedList.ofSize(size, ItemStack.EMPTY))
        }
    }

    // SidedInventory

    /**
     * Gets the available slots to automation on the side.
     *
     * <p>The default implementation returns an array of all slots.
     *
     * @param side the side
     * @return the available slots
     */
    override fun getAvailableSlots(side: Direction): IntArray {
        val result = IntArray(getItems().size)
        for (i in result.indices) {
            result[i] = i
        }

        return result
    }

    /**
     * Returns true if the stack can be inserted in the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be inserted
     */
    override fun canInsert(slot: Int, stack: ItemStack, side: Direction?): Boolean {
        return true
    }

    /**
     * Returns true if the stack can be extracted from the slot at the side.
     *
     * <p>The default implementation returns true.
     *
     * @param slot the slot
     * @param stack the stack
     * @param side the side
     * @return true if the stack can be extracted
     */
    override fun canExtract(slot: Int, stack: ItemStack, side: Direction): Boolean {
        return true
    }

    // Inventory

    /**
     * Returns the inventory size.
     *
     * <p>The default implementation returns the size of [getItems].
     *
     * @return the inventory size
     */
    override fun size(): Int {
        return getItems().size
    }

    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    override fun isEmpty(): Boolean {
        for (i in 0 until size()) {
            val stack = getStack(i)
            if (!stack.isEmpty) {
                return false
            }
        }

        return true
    }

    /**
     * Gets the item in the slot.
     *
     * @param slot the slot
     * @return the item in the slot
     */
    override fun getStack(slot: Int): ItemStack {
        return getItems()[slot]
    }

    /**
     * Takes a stack of the size from the slot.
     *
     * <p>(default implementation) If there are fewer items in the slot than what are requested,
     * takes all items in that slot.
     *
     * @param slot the slot
     * @param count the item count
     * @return a stack
     */
    override fun removeStack(slot: Int, count: Int): ItemStack {
        val result = Inventories.splitStack(getItems(), slot, count)
        if (!result.isEmpty) {
            markDirty()
        }

        return result
    }

    /**
     * Removes the current stack in the [slot] and returns it.
     *
     * <p>The default implementation uses [Inventories.removeStack]
     *
     * @param slot the slot
     * @return the removed stack
     */
    override fun removeStack(slot: Int): ItemStack {
        return Inventories.removeStack(getItems(), slot)
    }

    /**
     * Replaces the current stack in the [slot] with the provided stack.
     *
     * <p>If the stack is too big for this inventory ([Inventory.getMaxCountPerStack]),
     * it gets resized to this inventory's maximum amount.
     *
     * @param slot the slot
     * @param stack the stack
     */
    override fun setStack(slot: Int, stack: ItemStack) {
        getItems()[slot] = stack
        if (stack.count > maxCountPerStack) {
            stack.count = maxCountPerStack
        }
        markDirty()
    }

    /**
     * Clears [getItems] the item list.
     */
    override fun clear() {
        getItems().clear()
    }

    override fun markDirty() {
        // Override if you want behavior.
    }

    override fun canPlayerUse(player: PlayerEntity): Boolean {
        return true
    }
}
