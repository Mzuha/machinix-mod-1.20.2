package com.mzuha.entity

import com.mzuha.recipe.CrusherRecipe
import com.mzuha.screen.CrusherScreenHandler
import java.util.Optional
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.RecipeEntry
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

const val CRUSHER_INPUT_SLOT = 0
const val CRUSHER_OUTPUT_SLOT = 1

class CrusherEntity(pos: BlockPos, state: BlockState) : BlockEntity(ModBlockEntities.CRUSHER_ENTITY, pos, state),
    ExtendedScreenHandlerFactory, ImplementedInventory {

    companion object {
        var progress: Int = 0
        var maxProgress: Int = 100
    }

    private val propertyDelegate: PropertyDelegate

    init {
        this.propertyDelegate = object : PropertyDelegate {
            override fun get(index: Int) = when (index) {
                0 -> progress
                1 -> maxProgress
                else -> 0
            }

            override fun set(index: Int, value: Int) = when (index) {
                0 -> progress = value
                1 -> maxProgress = value
                else -> throw IllegalStateException("Something went wrong!")
            }

            override fun size() = 2

        }
    }

    private val inventory: DefaultedList<ItemStack> = DefaultedList.ofSize(2, ItemStack.EMPTY)

    override fun getItems(): DefaultedList<ItemStack> = inventory

    override fun getDisplayName(): Text = Text.translatable("entity.machinix.crusher_entity")

    override fun writeScreenOpeningData(player: ServerPlayerEntity, buf: PacketByteBuf) {
        buf.writeBlockPos(this.pos)
    }

    override fun writeNbt(nbt: NbtCompound) {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, inventory)
        nbt.putInt("crusher.progress", progress)
    }

    override fun markDirty() {
        super<BlockEntity>.markDirty()
    }

    override fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, inventory)
        progress = nbt.getInt("crusher.progress")
    }

    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler {
        return CrusherScreenHandler(syncId, playerInventory, this, propertyDelegate)
    }

    fun tick(world: World, pos: BlockPos, state: BlockState) {
        if (world.isClient) return
        if (isOutputSlotEmptyOrNotFull()) {
            if (hasRecipe()) {
                increaseProgress()
                if (hasRecipeFinished()) {
                    craftItem()
                    markDirty()
                    resetProgress()
                }
            } else {
                resetProgress()
            }
        } else {
            resetProgress()
        }
    }

    private fun resetProgress() {
        progress = 0
    }

    private fun craftItem() {
        val recipe = getCurrentRecipe().get()
        this.removeStack(CRUSHER_INPUT_SLOT, 1)
        this.setStack(
            CRUSHER_OUTPUT_SLOT,
            ItemStack(
                recipe.value.getResult(null).item,
                getStack(CRUSHER_OUTPUT_SLOT).count + recipe.value.getResult(null).count
            )
        )
    }

    private fun hasRecipeFinished(): Boolean {
        return progress == maxProgress
    }

    private fun increaseProgress() {
        progress++
    }

    private fun hasRecipe(): Boolean = getCurrentRecipe().isPresent

    private fun getCurrentRecipe(): Optional<RecipeEntry<CrusherRecipe>> {
        val inv = SimpleInventory(this.size())

        for (i in 0 until this.size()) {
            inv.setStack(i, this.getStack(i))
        }

        return world!!.recipeManager.getFirstMatch(CrusherRecipe.Type.INSTANCE, inv, world)
    }

    private fun isOutputSlotEmptyOrNotFull(): Boolean {
        val stack = this.getStack(CRUSHER_OUTPUT_SLOT)
        return stack.isEmpty
            || (stack.count < stack.maxCount && stack.count + 2 <= stack.maxCount)
    }
}