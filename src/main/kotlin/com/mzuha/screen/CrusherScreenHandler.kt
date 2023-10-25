package com.mzuha.screen

import com.mzuha.entity.CrusherEntity
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot

class CrusherScreenHandler : ScreenHandler {
    private val inventory: Inventory
    private val propertyDelegate: PropertyDelegate
    private val crusherEntity: CrusherEntity

    constructor(syncId: Int, playerInventory: PlayerInventory, buf: PacketByteBuf) : this(
        syncId,
        playerInventory,
        playerInventory.player.world.getBlockEntity(buf.readBlockPos())!!,
        ArrayPropertyDelegate(2)
    )

    constructor(
        syncId: Int,
        playerInventory: PlayerInventory,
        blockEntity: BlockEntity,
        arrayPropertyDelegate: PropertyDelegate
    ) : super(ModScreenHandlers.CRUSHER_SCREEN_HANDLER, syncId) {
        checkSize(blockEntity as Inventory, 2)
        this.inventory = blockEntity
        inventory.onOpen(playerInventory.player)
        this.propertyDelegate = arrayPropertyDelegate
        this.crusherEntity = blockEntity as CrusherEntity

        addSlot(Slot(inventory, 0, 80, 11))
        addSlot(Slot(inventory, 1, 80, 59))

        addPlayerInventory(playerInventory)
        addPlayerHotbar(playerInventory)

        addProperties(arrayPropertyDelegate)
    }

    override fun quickMove(player: PlayerEntity, invSlot: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot = slots[invSlot]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (invSlot < inventory.size()) {
                if (!insertItem(originalStack, inventory.size(), slots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!insertItem(originalStack, 0, inventory.size(), false)) {
                return ItemStack.EMPTY
            }
            if (originalStack.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
        }

        return newStack
    }

    override fun canUse(player: PlayerEntity) = this.inventory.canPlayerUse(player)

    private fun addPlayerInventory(playerInventory: PlayerInventory) {
        for (i in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(playerInventory: PlayerInventory) {
        for (i in 0..8) {
            addSlot(Slot(playerInventory, i, 8 + i * 18, 142))
        }
    }

    fun isCrafting(): Boolean {
        return propertyDelegate[0] > 0
    }

    fun getScaledProgress(): Int {
        val progress = propertyDelegate[0]
        val maxProgress = propertyDelegate[1] // Max Progress
        val progressArrowSize = 26 // This is the width in pixels of your arrow
        return if (maxProgress != 0 && progress != 0) progress * progressArrowSize / maxProgress else 0
    }
}